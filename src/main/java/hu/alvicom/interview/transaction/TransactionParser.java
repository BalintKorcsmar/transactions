package hu.alvicom.interview.transaction;

import hu.alvicom.interview.model.Transaction;
import hu.alvicom.interview.report.ReportPrinter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public interface TransactionParser {

    static TransactionParser getInstance() {
        ObservableList<Transaction> parsedTransactions = FXCollections.observableArrayList();
        ReportPrinter reportPrinter = new ReportPrinter(parsedTransactions);
        TransactionParserImpl parser = new TransactionParserImpl(parsedTransactions, reportPrinter);
        parser.init(reportPrinter.new ParsedTransactionListener());
        return parser;
    }

    void parse(List<Transaction> transactions);

}
