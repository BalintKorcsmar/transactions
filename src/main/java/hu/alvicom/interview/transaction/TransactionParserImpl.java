package hu.alvicom.interview.transaction;

import hu.alvicom.interview.model.Account;
import hu.alvicom.interview.model.Transaction;
import hu.alvicom.interview.report.ReportPrinter;
import hu.alvicom.interview.utility.AccountGenerator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.Optional;

public class TransactionParserImpl implements TransactionParser {

    private List<Account> accounts;
    private ObservableList<Transaction> parsedTransaction;
    private ReportPrinter reportPrinter;

    void init() {
        accounts = AccountGenerator.generateAccounts();
        parsedTransaction = FXCollections.observableArrayList();
        reportPrinter = new ReportPrinter(parsedTransaction);
        parsedTransaction.addListener(reportPrinter.new ParsedTransactionListener());
    }

    @Override
    public void parse(List<Transaction> transactions) {
        transactions.forEach(transaction -> {
            Optional<Account> matchingAccount = accounts.stream()
                    .filter(account -> account.getAccountNumber().equals(transaction.getAccountNumber()))
                    .findAny();
            if(matchingAccount.isPresent()) {
                matchingAccount.get().performTransaction(transaction);
                parsedTransaction.add(transaction);
            } else {
                System.out.println("Warning! Bank account not found: " + transaction.getAccountNumber());
            }
        });
    }
}
