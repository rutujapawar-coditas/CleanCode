package com.demo.crypto.service;

import java.util.ArrayList;
import java.util.List;

public class CryptoCurrencyInfo {

    private String name;
    private String currentPrice;
    private String marketCapital;
    private String volume;

    private List<Double> priceData = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(String currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getMarketCapital() {
        return marketCapital;
    }

    public void setMarketCapital(String marketCapital) {
        this.marketCapital = marketCapital;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public List<Double> getPriceData() {
        return priceData;
    }

    public void setPriceData(List<Double> priceData) {
        this.priceData = priceData;
    }
}
