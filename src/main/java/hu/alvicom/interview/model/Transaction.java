package hu.alvicom.interview.model;

import lombok.Builder;
import lombok.Getter;

@Builder
public class Transaction {
    private @Getter String accountNumber;
    private @Getter Currency currency;
    private @Getter double amount;
    private @Getter double exchangeRate;

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
