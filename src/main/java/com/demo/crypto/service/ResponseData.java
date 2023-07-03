package com.demo.crypto.service;

public class ResponseData {

    private CryptoCurrencyInfo cryptoCurrencyInfo;
    private Double priceDifference;

    public CryptoCurrencyInfo getCryptoCurrencyInfo() {
        return cryptoCurrencyInfo;
    }

    public void setCryptoCurrencyInfo(CryptoCurrencyInfo cryptoCurrencyInfo) {
        this.cryptoCurrencyInfo = cryptoCurrencyInfo;
    }

    public Double getPriceDifference() {
        return priceDifference;
    }

    public void setPriceDifference(Double priceDifference) {
        this.priceDifference = priceDifference;
    }
}

