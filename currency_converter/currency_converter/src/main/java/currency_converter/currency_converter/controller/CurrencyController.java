package currency_converter.currency_converter.controller;

import currency_converter.currency_converter.model.ExchangeRateResponse;
import currency_converter.currency_converter.service.CurrencyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class CurrencyController {

    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    /**
     * Fetch exchange rates with a base currency (default: USD)
     */
    @GetMapping("/rates")
    public ResponseEntity<ExchangeRateResponse> getExchangeRates(
            @RequestParam(defaultValue = "USD") String base) {
        ExchangeRateResponse rates = currencyService.getExchangeRates(base);
        return ResponseEntity.ok(rates);
    }

    /**
     * Convert currency from one to another
     */
    @PostMapping("/convert")
    public ResponseEntity<Map<String, Object>> convertCurrency(@RequestBody Map<String, Object> request) {
        if (!request.containsKey("from") || !request.containsKey("to") || !request.containsKey("amount")) {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid request parameters"));
        }

        String from = (String) request.get("from");
        String to = (String) request.get("to");
        double amount = ((Number) request.get("amount")).doubleValue();

        double convertedAmount = currencyService.convertCurrency(from, to, amount);

        return ResponseEntity.ok(Map.of(
                "from", from,
                "to", to,
                "amount", amount,
                "convertedAmount", convertedAmount
        ));
    }
}
// package currency_converter.currency_converter.controller;

// import org.springframework.web.bind.annotation.*;
// import org.springframework.web.client.RestTemplate;
// import java.util.Map;

// @RestController
// @RequestMapping("/api")
// public class CurrencyController {

//     private final String API_URL = "https://api.exchangerate-api.com/v4/latest/";

//     @SuppressWarnings("unchecked")
//     @GetMapping("/rates")
//     public Map<String, Object> getExchangeRates(@RequestParam(defaultValue = "USD") String base) {
//         RestTemplate restTemplate = new RestTemplate();
//         String url = API_URL + base;
//         return restTemplate.getForObject(url, Map.class);
//     }
// }
