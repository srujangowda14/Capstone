package com.fidelity.portfolio;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StockHolding {
    private String symbol;
    private int quantity;

    public StockHolding(String symbol, int quantity) {
        this.symbol = symbol;
        this.quantity = quantity;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "StockHolding{" +
                "symbol='" + symbol + '\'' +
                ", quantity=" + quantity +
                '}';
    }

    // Generate random stock holdings
    public static List<StockHolding> generateRandomHoldings(int numHoldings) {
        List<StockHolding> holdings = new ArrayList<>();
        String[] symbols = {"AAPL", "GOOGL", "TSLA", "AMZN", "MSFT", "FB"};

        Random random = new Random();

        for (int i = 0; i < numHoldings; i++) {
            String symbol = symbols[random.nextInt(symbols.length)];
            int quantity = random.nextInt(100) + 1; // Random quantity between 1 and 100
            holdings.add(new StockHolding(symbol, quantity));
        }

        return holdings;
    }
}