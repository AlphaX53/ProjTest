package test.java.com.proj.arnab.service;

import test.java.com.proj.arnab.service; // Corrected import path
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StockQuoteServiceTest {

    @Mock
    private RestTemplate restTemplate;

    private StockQuoteService stockQuoteService;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
        stockQuoteService = new StockQuoteService(restTemplate, objectMapper);
    }

    @Test
    void getQuoteBySymbol_shouldReturnStockQuote() {
        // Arrange
        String symbol = "AAPL";
        String mockResponse = "{\"status\":\"OK\",\"results\":[{\"c\":150.0,\"o\":145.0,\"t\":1633046400000}]}";
        when(restTemplate.getForObject(anyString(), eq(String.class))).thenReturn(mockResponse);

        // Act
        StockQuote result = stockQuoteService.getQuoteBySymbol(symbol);

        // Assert
        assertNotNull(result);
        assertEquals(symbol, result.getSymbol());
        assertEquals(150.0, result.getPrice());
        assertEquals(5.0, result.getChange());
        assertEquals(3.45, result.getPercentageChange(), 0.01);
        assertNotNull(result.getTimestamp());
        verify(restTemplate).getForObject(anyString(), eq(String.class));
    }
}
