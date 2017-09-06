package hu.alvicom.interview.model;

import lombok.Data;

@Data
public class Account {
    private String accountNumber;
    private Currency currency;
    private double balance;

    public Account(String accountNumber, Currency currency, double balance) {
        this.accountNumber = accountNumber;
        this.currency = currency;
        this.balance = balance;
    }

    public void execute(Transaction transaction) {
        if(transaction.getCurrency().equals(currency)) {
            balance += transaction.getAmount();
        } else {
            balance += transaction.getAmount() * transaction.getExchangeRate();
        }
    }

    public boolean isTransactionMatching(Transaction transaction) {
        return getAccountNumber().equals(transaction.getAccountNumber());
    }
}
