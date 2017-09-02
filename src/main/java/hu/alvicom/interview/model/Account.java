package hu.alvicom.interview.model;

import lombok.Builder;
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

    public void performTransaction(Transaction transaction) {
//        System.out.println(transaction);
    }
}
