package main.java.com.proj.arnab.model;

import java.time.LocalDateTime;

public class StockQuote {
    private String symbol;
    private double price;
    private double change;
    private double percentageChange;
    private LocalDateTime timestamp;

    // Constructors
    public StockQuote() {}

    public StockQuote(String symbol, double price, double change, double percentageChange, LocalDateTime timestamp) {
        this.symbol = symbol;
        this.price = price;
        this.change = change;
        this.percentageChange = percentageChange;
        this.timestamp = timestamp;
    }

    // Getters and setters
    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getChange() {
        return change;
    }

    public void setChange(double change) {
        this.change = change;
    }

    public double getPercentageChange() {
        return percentageChange;
    }

    public void setPercentageChange(double percentageChange) {
        this.percentageChange = percentageChange;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}