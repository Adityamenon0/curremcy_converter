package currency_converter.currency_converter.model;

import lombok.Data;
import java.util.Map;

@Data
public class ExchangeRateResponse {
    private String base_code;
    private Map<String, Double> conversion_rates;
}
