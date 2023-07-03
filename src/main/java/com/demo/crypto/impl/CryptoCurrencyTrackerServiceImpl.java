package com.demo.crypto.impl;

import com.demo.crypto.service.CryptoCurrencyInfo;
import com.demo.crypto.service.CryptoCurrencyTrackerService;
import com.demo.crypto.service.CustomResponseDTO;
import com.demo.crypto.service.ResponseData;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Objects;

@Service
public class CryptoCurrencyTrackerServiceImpl implements CryptoCurrencyTrackerService {

    private final Logger log = LoggerFactory.getLogger(CryptoCurrencyTrackerServiceImpl.class);

    // CoinGecko API URL
    private static final String API_URL = "https://api.coingecko.com/api/v3";

    @Override
    public CustomResponseDTO trackCryptoCurrency(String cryptoCurrency) {
        CustomResponseDTO customResponseDTO = new CustomResponseDTO();
        try {
            // Fetch data from the API
            String data = fetchData(cryptoCurrency);
            customResponseDTO = processAndDisplayData(data);
        } catch (
                IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return customResponseDTO;
    }

    private static String fetchData(String cryptocurrency) throws IOException {
        URL url = new URL(API_URL + "/coins/markets?vs_currency=usd&ids=" + cryptocurrency);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            return response.toString();
        } else {
            throw new IOException("Error: " + responseCode);
        }
    }

    private CustomResponseDTO processAndDisplayData(String data) {
        ResponseData responseData = new ResponseData();
        // Parse the JSON data and extract the required information
        Gson gson = new Gson();
        // API returns an array of CryptocurrencyInfo objects
        CryptoCurrencyInfo[] cryptocurrencyInfoArray = gson.fromJson(data, CryptoCurrencyInfo[].class);

        if (cryptocurrencyInfoArray.length > 0) {
            CryptoCurrencyInfo cryptocurrencyInfo = cryptocurrencyInfoArray[0];
            double priceDifference = calculatePriceChange(cryptocurrencyInfo.getPriceData());
            responseData.setCryptoCurrencyInfo(cryptocurrencyInfo);
            responseData.setPriceDifference(priceDifference);
            return updateCustomResponseDTO(200, "Data Retrieved successfully", "Success", responseData);
        }
        return updateCustomResponseDTO(400, "Data not Present", "Failure", null);

    }

    public static double calculatePriceChange(List<Double> priceData) {
        if (priceData.size() < 2) {
            throw new IllegalArgumentException("Price data must contain at least two values.");
        }
        double initialPrice = priceData.get(0);
        double finalPrice = priceData.get(priceData.size() - 1);
        return finalPrice - initialPrice;
    }

    public static CustomResponseDTO updateCustomResponseDTO(int statusCode, String message, String status, Object o) {
        CustomResponseDTO customResponseDTO = new CustomResponseDTO();
        customResponseDTO.setStatusCode(statusCode);
        customResponseDTO.setMessage(message);
        customResponseDTO.setStatus(status);
        customResponseDTO.setData(o);
        return customResponseDTO;
    }
}