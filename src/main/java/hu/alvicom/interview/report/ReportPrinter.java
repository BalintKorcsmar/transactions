package hu.alvicom.interview.report;

import hu.alvicom.interview.account.AccountManager;
import hu.alvicom.interview.model.Transaction;
import javafx.collections.ListChangeListener;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

public class ReportPrinter {

    private List<Transaction> parsedTransactions;
    private AccountManager accountManager;
    private int printLimit;

    public ReportPrinter(List<Transaction> parsedTransactions) {
        this.parsedTransactions = parsedTransactions;
        this.accountManager = AccountManager.getInstance();
        printLimit = 10;
    }

    public class ParsedTransactionListener implements ListChangeListener<Transaction> {
        @Override
        public void onChanged(Change<? extends Transaction> c) {
            if(isPrintLimitReached()) {
                printReport();
            }
        }

        private boolean isPrintLimitReached() {
            return parsedTransactions.size() % printLimit == 0;
        }
    }

    private void printReport() {
        printReportHeader();
        printReportBody();
        printReportFooter();
    }

    private void printReportHeader() {
        System.out.println("\nPrinting report, number of total transactions: " + parsedTransactions.size());
    }

    private void printReportBody() {
        Map<String, List<Transaction>> transactionMap = parsedTransactions.stream().collect(groupingBy(Transaction::getAccountNumber));
        transactionMap.forEach((accountNumber, transactions) -> {
            printReportAccountHeader(accountNumber, transactions.size());
            transactions.forEach(this::printTransaction);
            printReportAccountFooter(accountNumber);
        });
    }

    private void printReportAccountHeader(String accountNumber, int numberOfTransactions) {
        System.out.println("\nPrinting report for account: " + accountNumber +
                ", Currency: " + accountManager.getAccountCurrency(accountNumber) +
                ", number of transactions: " + numberOfTransactions);
    }

    private void printReportAccountFooter(String accountNumber) {
        System.out.println("Available balance after executing the transactions: " +
                String.format(Locale.US, "%.2f", accountManager.getAccountBalance(accountNumber)));
    }

    private void printReportFooter() {
        System.out.println("End of report.\n");
    }

    public void printWarning(Transaction transaction) {
        System.out.println("Warning! Bank account not found: " + transaction.getAccountNumber());
    }

    private void printTransaction(Transaction transaction) {
        System.out.println("Transaction[ Account number: " + transaction.getAccountNumber() +
                ", Currency: " + transaction.getCurrency() +
                ", Amount (in account currency): " + calculateAmount(transaction) +
                exchangeRateToString(transaction) + "]");
    }

    private String exchangeRateToString(Transaction transaction) {
        return transaction.getExchangeRate() == 0 ? "" : ", Exchange rate: " + transaction.getExchangeRate();
    }

    private String calculateAmount(Transaction transaction) {
        double amount;
        if(transaction.isMatchingCurrency(accountManager.getAccountCurrency(transaction.getAccountNumber()))) {
            amount = transaction.getAmount();
        } else {
            amount = transaction.getAmount() * transaction.getExchangeRate();
        }
        return String.format(Locale.US,"%.2f", amount);
    }
}
