package hu.alvicom.interview.transaction;

import hu.alvicom.interview.account.AccountManager;
import hu.alvicom.interview.model.Account;
import hu.alvicom.interview.model.Transaction;
import hu.alvicom.interview.report.ReportPrinter;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.Optional;

public class TransactionParserImpl implements TransactionParser {

    private AccountManager accountManager;
    private ObservableList<Transaction> parsedTransaction;
    private ReportPrinter reportPrinter;

    public TransactionParserImpl(ObservableList<Transaction> parsedTransaction, ReportPrinter reportPrinter) {
        this.parsedTransaction = parsedTransaction;
        this.reportPrinter = reportPrinter;
    }

    void init(ListChangeListener<Transaction> listener) {
        accountManager = AccountManager.getInstance();
        parsedTransaction.addListener(listener);
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
        return accountManager.getAccounts().stream()
                .filter(account -> account.isTransactionMatching(transaction))
                .findAny();
    }
}
