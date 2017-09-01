package hu.alvicom.interview.transaction;

import hu.alvicom.interview.model.Currency;
import hu.alvicom.interview.model.Transaction;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TransactionReaderImpl implements TransactionReader{

    private static final String CSV_SPLIT = ",";

    private final String csvFile;

    TransactionReaderImpl(String csvFile) {
        this.csvFile = csvFile;
    }

    @Override
    public List<Transaction> generateTransactions() {
        String csvFile = "transactions.csv";
        BufferedReader bufferedReader = null;
        String line = "";

        List<Transaction> transactions = new ArrayList<>();

        try {
            bufferedReader = new BufferedReader(new FileReader(csvFile));
            while ((line = bufferedReader.readLine()) != null) {
                String[] input = line.split(CSV_SPLIT);
                Transaction transaction = buildTransaction(input);
                transactions.add(transaction);
            }
        } catch (FileNotFoundException e) {
            System.out.println("The input file was not found.");
        } catch (IOException e) {
            System.out.println("An IO exception occurred.");
        } finally {
            closeBufferedReader(bufferedReader);
        }
        return transactions;
    }

    private void closeBufferedReader(BufferedReader bufferedReader) {
        if (bufferedReader != null) {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Transaction buildTransaction(String[] input) {
        return Transaction.builder()
                .accountNumber(input[0])
                .currency(Currency.valueOf(input[1]))
                .amount(Double.valueOf(input[2]))
                .exchangeRate(Double.valueOf(input[3]))
                .build();
    }
}
