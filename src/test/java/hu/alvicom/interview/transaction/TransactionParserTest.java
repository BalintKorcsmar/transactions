package hu.alvicom.interview.transaction;

import hu.alvicom.interview.model.Currency;
import hu.alvicom.interview.model.Transaction;
import hu.alvicom.interview.report.ReportPrinter;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import lombok.Builder;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TransactionParserTest {

    private ObservableList<Transaction> parsedTransactions = FXCollections.observableArrayList();
    private TransactionParserImpl parser;

    private ReportPrinterMock reportPrinterMock = new ReportPrinterMock(parsedTransactions);

    private Transaction transactionHUF = Transaction.builder()
            .accountNumber("11111111-22222222")
            .amount(100)
            .currency(Currency.HUF)
            .exchangeRate(0)
            .build();

    private Transaction transactionUSD = Transaction.builder()
            .accountNumber("22222222-33333333")
            .amount(100)
            .currency(Currency.USD)
            .exchangeRate(0)
            .build();

    private Transaction transactionUnknownAccount = Transaction.builder()
            .accountNumber("22222222-22222222")
            .amount(100)
            .currency(Currency.USD)
            .exchangeRate(0)
            .build();

    @Before
    public void initParser() {
        parser = new TransactionParserImpl(parsedTransactions, reportPrinterMock);
        parser.init(reportPrinterMock.new ParsedTransactionListener());
    }

    @Test
    public void testParsedTransactionSize() {
        List<Transaction> transactions = new ArrayList<>(Arrays.asList(transactionHUF, transactionUSD, transactionUnknownAccount));
        parser.parse(transactions);
        assertEquals(2, parsedTransactions.size());
    }

    @Test
    public void testParseTransactionsEmptyList() {
        parser.parse(Collections.EMPTY_LIST);
        assertFalse(reportPrinterMock.isReportPrinted());
    }

    @Test
    public void testParseTransactionsReportPrinted() {
        parser.parse(Collections.singletonList(transactionHUF));
        assertTrue(reportPrinterMock.isReportPrinted());
    }

    @Test
    public void testParseTransactionsWarningPrinted() {
        parser.parse(Collections.singletonList(transactionUnknownAccount));
        assertTrue(reportPrinterMock.isWarningPrinted());
    }
}
