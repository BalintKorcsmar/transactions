package hu.alvicom.interview.model;

import lombok.Data;

@Data
public class Account {
    private String accountNumber;
    private Currency currency;
    private double balance;
}
