package currency_converter.currency_converter.service;

import currency_converter.currency_converter.model.ExchangeRateResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



@Service
public class CurrencyService {

    @Value("${exchange.rate.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public ExchangeRateResponse getExchangeRates(String base) {
        try {
            String url = apiUrl + base;
            return restTemplate.getForObject(url, ExchangeRateResponse.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch exchange rates. Please try again later.");
        }
    }

    public double convertCurrency(String from, String to, double amount) {
        ExchangeRateResponse rates = getExchangeRates(from);
        if (rates != null && rates.getConversion_rates().containsKey(to)) {
            return amount * rates.getConversion_rates().get(to);
        }
        throw new IllegalArgumentException("Invalid currency code: " + to);
    }

}
