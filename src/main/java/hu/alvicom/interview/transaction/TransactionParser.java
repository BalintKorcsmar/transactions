package hu.alvicom.interview.transaction;

import hu.alvicom.interview.model.Transaction;

import java.util.List;

public interface TransactionParser {

    static TransactionParser getInstance() {
        TransactionParserImpl parser = new TransactionParserImpl();
        parser.init();
        return parser;
    }

    void parse(List<Transaction> transactions);

}
