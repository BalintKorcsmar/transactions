package hu.alvicom.interview.transaction;

import hu.alvicom.interview.model.Transaction;

import java.util.List;

public interface TransactionParser {

    static TransactionParser getInstance() {
        return new TransactionParserImpl();
    }

    void parse(List<Transaction> transactions);

}
