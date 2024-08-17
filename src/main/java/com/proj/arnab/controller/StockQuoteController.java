package main.java.com.proj.arnab.controller;

import main.java.com.proj.arnab.model.StockQuote;
import main.java.com.proj.arnab.service.StockQuoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stocks")
public class StockQuoteController {

    private final StockQuoteService stockQuoteService;

    public StockQuoteController(StockQuoteService stockQuoteService) {
        this.stockQuoteService = stockQuoteService;
    }

    @GetMapping("/{symbol}")
    public ResponseEntity<StockQuote> getQuoteBySymbol(@PathVariable String symbol) {
        return ResponseEntity.ok(stockQuoteService.getQuoteBySymbol(symbol));
    }

    @GetMapping("/batch")
    public ResponseEntity<List<StockQuote>> getBatchQuotes(@RequestParam List<String> symbols) {
        return ResponseEntity.ok(stockQuoteService.getBatchQuotes(symbols));
    }
}