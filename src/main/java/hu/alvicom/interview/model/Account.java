package hu.alvicom.interview.model;

import lombok.Getter;

public class Account {
    private @Getter String accountNumber;
    private @Getter Currency currency;
    private @Getter double balance;

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
        return accountNumber.equals(transaction.getAccountNumber());
    }
}
