package hu.alvicom.interview;

import hu.alvicom.interview.model.Transaction;
import hu.alvicom.interview.transaction.TransactionParser;
import hu.alvicom.interview.transaction.TransactionReader;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        TransactionReader reader = TransactionReader.getInstance("transactions.csv");
        List<Transaction> transactions = reader.readTransactions();
        
        TransactionParser parser = TransactionParser.getInstance();
        parser.parse(transactions);
    }
}
