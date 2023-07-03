package com.demo.crypto.web.rest;

import com.demo.crypto.service.CryptoCurrencyTrackerService;
import com.demo.crypto.service.CustomResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class CryptoTrackerController {

    @Autowired
    CryptoCurrencyTrackerService cryptoCurrencyTrackerService;

    private final Logger log = LoggerFactory.getLogger(CryptoTrackerController.class);

    /**
     * get: API to get cryptoCurrency Details
     * @param cryptoCurrency
     * Example : Bitcoin
     */
    @GetMapping("/cryptoTracker")
    public CustomResponseDTO cryptoCurrencyTracker(@RequestParam(name = "cryptoCurrency") String cryptoCurrency)
    {
        log.info("Rest request for tracking cryptocurrency details:",cryptoCurrency);
        CustomResponseDTO customResponseDTO;
        customResponseDTO = cryptoCurrencyTrackerService.trackCryptoCurrency(cryptoCurrency);
        return customResponseDTO;
    }
}
