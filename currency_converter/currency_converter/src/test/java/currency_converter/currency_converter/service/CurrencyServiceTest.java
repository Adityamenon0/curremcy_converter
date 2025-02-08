package currency_converter.currency_converter.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import currency_converter.currency_converter.model.ExchangeRateResponse;


class CurrencyServiceTest {

    @Mock
    private CurrencyService currencyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testConvertCurrency() {
        // Mock API response
        ExchangeRateResponse mockResponse = new ExchangeRateResponse();
        mockResponse.setBase_code("USD");
        mockResponse.setConversion_rates(Map.of("EUR", 0.94));

        when(currencyService.getExchangeRates("USD")).thenReturn(mockResponse);
        when(currencyService.convertCurrency("USD", "EUR", 100)).thenReturn(94.0);

        double convertedAmount = currencyService.convertCurrency("USD", "EUR", 100);
        assertEquals(94.0, convertedAmount);
    }
}
