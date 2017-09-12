package hu.alvicom.interview.transaction;

import hu.alvicom.interview.model.Transaction;

import java.io.IOException;
import java.util.List;

public interface TransactionReader {

    static TransactionReader getInstance(String pathToCsv) {
        return new TransactionReaderImpl(pathToCsv);
    }

    List<Transaction> readTransactions() throws IOException;
}
