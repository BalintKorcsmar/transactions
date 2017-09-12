package hu.alvicom.interview.transaction;

import hu.alvicom.interview.model.Currency;
import hu.alvicom.interview.model.Transaction;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class TransactionReaderTest {

    @Test
    public void testReadTransactions() throws Exception {
        TransactionReader reader = TransactionReader.getInstance("test_transactions.csv");
        List<Transaction> transactions = reader.readTransactions();

        assertEquals(3, transactions.size());

        assertEquals("11111111-22222222", transactions.get(0).getAccountNumber());
        assertEquals(Currency.HUF, transactions.get(0).getCurrency());
        assertEquals(1000, transactions.get(0).getAmount(), 0.001);
        assertEquals(300, transactions.get(1).getExchangeRate(), 0.001);
    }

    @Test(expected = NumberFormatException.class)
    public void testNumberFormatErrorInInput() throws Exception {
        TransactionReader reader = TransactionReader.getInstance("numberformat.csv");
        reader.readTransactions();
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testTooFewColumnsInInput() throws Exception {
        TransactionReader reader = TransactionReader.getInstance("too_few_columns.csv");
        reader.readTransactions();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUnknownCurrencyInInput() throws Exception {
        TransactionReader reader = TransactionReader.getInstance("unknown_currency.csv");
        reader.readTransactions();
    }
}
