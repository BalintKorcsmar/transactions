package hu.alvicom.interview.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Transaction {
    private String accountNumber;
    private Currency currency;
    private double amount;
    private double exchangeRate;

    @Override
    public String toString() {
        return "Transaction [account=" + accountNumber +
                ", currency=" + currency +
                ", amount=" + amount +
                exchangeRateToString() + "]";
    }

    private String exchangeRateToString() {
        return exchangeRate == 0 ? "" : ", exchange rate=" + String.valueOf(exchangeRate);
    }

    public boolean isMatchingCurrency(Currency currency) {
        return this.currency.equals(currency);
    }
}
