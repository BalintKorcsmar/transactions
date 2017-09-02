package hu.alvicom.interview.report;

import hu.alvicom.interview.model.Transaction;
import javafx.collections.ListChangeListener;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

public class ReportPrinter {

    private List<Transaction> parsedTransactions;

    public ReportPrinter(List<Transaction> parsedTransaction) {
        this.parsedTransactions = parsedTransaction;
    }

    public class ParsedTransactionListener implements ListChangeListener<Transaction> {
        @Override
        public void onChanged(Change<? extends Transaction> c) {
            if(parsedTransactions.size() % 10 == 0) {
                printReport();
            }
        }
    }

    private void printReport() {
        printReportHeader();
        Map<String, List<Transaction>> transactionMap = parsedTransactions.stream().collect(groupingBy(Transaction::getAccountNumber));
        transactionMap.forEach((accountNumber, transactions) -> {
            printReportAccountHeader(accountNumber, transactions.size());
            transactions.forEach(System.out::println);
        });
    }

    private void printReportAccountHeader(String accountNumber, int numberOfTransactions) {
        System.out.println("Printing report for account: " + accountNumber + ", number of transactions: " + numberOfTransactions);
    }

    private void printReportHeader() {
        System.out.println("Printing report, number of total transactions: " + parsedTransactions.size());
    }
}
