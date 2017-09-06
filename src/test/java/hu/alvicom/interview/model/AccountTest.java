package hu.alvicom.interview.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AccountTest {

    private final String testAccountNumber = "12345678-00000000";
    private Account testAccount = new Account(testAccountNumber, Currency.HUF, 1000);

    @Test
    public void testPerformTransactionOwnCurrency() {
        Transaction transaction = buildTestTransactionHUF();
        testAccount.execute(transaction);
        assertEquals(1500, testAccount.getBalance(), 0.01);
    }

    private Transaction buildTestTransactionHUF() {
        return Transaction.builder()
                .accountNumber(testAccountNumber)
                .amount(500)
                .currency(Currency.HUF)
                .build();
    }

    @Test
    public void testPerformTransactionOtherCurrency() {
        Transaction transaction = Transaction.builder()
                .accountNumber(testAccountNumber)
                .amount(10)
                .currency(Currency.USD)
                .exchangeRate(300)
                .build();
        testAccount.execute(transaction);
        assertEquals(4000, testAccount.getBalance(), 0.01);
    }

    @Test
    public void testIsTransactionMatchingTrue() {
        Transaction transaction = buildTestTransactionHUF();
        assertTrue(testAccount.isTransactionMatching(transaction));
    }

    @Test
    public void testIsTrancactionMatchingFalse() {
        Transaction transaction = Transaction.builder()
                .accountNumber("11111111-22211244")
                .amount(500)
                .currency(Currency.HUF)
                .build();
        assertFalse(testAccount.isTransactionMatching(transaction));
    }
}
