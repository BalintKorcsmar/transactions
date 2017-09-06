package hu.alvicom.interview.transaction;

import hu.alvicom.interview.account.AccountManager;
import hu.alvicom.interview.model.Account;
import hu.alvicom.interview.model.Transaction;
import hu.alvicom.interview.report.ReportPrinter;
import hu.alvicom.interview.account.AccountGenerator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.Optional;

public class TransactionParserImpl implements TransactionParser {

    private AccountManager accounts;
    private ObservableList<Transaction> parsedTransaction;
    private ReportPrinter reportPrinter;

    void init() {
        accounts = AccountManager.getInstance();
        parsedTransaction = FXCollections.observableArrayList();
        reportPrinter = new ReportPrinter(parsedTransaction);
        parsedTransaction.addListener(reportPrinter.new ParsedTransactionListener());
    }

    @Override
    public void parse(List<Transaction> transactions) {
        transactions.forEach(transaction -> {
            Optional<Account> matchingAccount = findMatchingAccount(transaction);
            if(matchingAccount.isPresent()) {
                matchingAccount.get().execute(transaction);
                parsedTransaction.add(transaction);
            } else {
                reportPrinter.printWarning(transaction);
            }
        });
    }


    private Optional<Account> findMatchingAccount(Transaction transaction) {
        return accounts.getAccounts().stream()
                .filter(account -> account.isTransactionMatching(transaction))
                .findAny();
    }
}
