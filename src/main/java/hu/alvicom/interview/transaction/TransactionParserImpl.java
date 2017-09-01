package hu.alvicom.interview.transaction;

import hu.alvicom.interview.model.Account;
import hu.alvicom.interview.model.Transaction;

import java.util.List;

public class TransactionParserImpl implements TransactionParser {

    List<Account> accounts;


    @Override
    public void parse(List<Transaction> transactions) {
        transactions.forEach(System.out::println);
    }
}
