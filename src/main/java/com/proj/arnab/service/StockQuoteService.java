package main.java.com.proj.arnab.service;

import main.java.com.proj.arnab.model.StockQuote;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockQuoteService {

    @Value("${polygon.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public StockQuoteService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @Cacheable(value = "stockQuotes", key = "#symbol")
    public StockQuote getQuoteBySymbol(String symbol) {
        String url = String.format("https://api.polygon.io/v2/aggs/ticker/%s/prev?apiKey=%s", symbol, apiKey);
        String response = restTemplate.getForObject(url, String.class);
        
        try {
            JsonNode rootNode = objectMapper.readTree(response);
            JsonNode resultsNode = rootNode.get("results").get(0);
            
            double closePrice = resultsNode.get("c").asDouble();
            double openPrice = resultsNode.get("o").asDouble();
            double change = closePrice - openPrice;
            double percentageChange = (change / openPrice) * 100;
            long timestamp = resultsNode.get("t").asLong();

            return new StockQuote(
                symbol,
                closePrice,
                change,
                percentageChange,
                LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault())
            );
        } catch (Exception e) {
            throw new RuntimeException("Error parsing stock quote data for symbol: " + symbol, e);
        }
    }

    public List<StockQuote> getBatchQuotes(List<String> symbols) {
        return symbols.stream()
                .map(this::getQuoteBySymbol) // Use method reference instead of lambda
                .collect(Collectors.toList());
    }
}