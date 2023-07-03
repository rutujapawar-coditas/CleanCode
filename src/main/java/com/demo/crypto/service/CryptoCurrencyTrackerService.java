package com.demo.crypto.service;

import org.springframework.stereotype.Service;

@Service
public interface CryptoCurrencyTrackerService {

    /**
     * This method will show the current price, market cap, and volume for a given cryptocurrency.
     * Display price changes over various time periods
     * @param cryptoCurrency -> example Bitcoin
     */
    public CustomResponseDTO trackCryptoCurrency(String cryptoCurrency);
}
