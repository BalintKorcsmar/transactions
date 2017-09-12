package hu.alvicom.interview.transaction;

import com.sun.org.apache.xml.internal.serializer.utils.SerializerMessages_zh_CN;
import hu.alvicom.interview.model.Currency;
import hu.alvicom.interview.model.Transaction;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TransactionReaderImpl implements TransactionReader {

    private static final String CSV_SPLIT = ",";

    private final InputStream csvInput;

    TransactionReaderImpl(String csvFile) {
        ClassLoader classLoader = getClass().getClassLoader();
        csvInput = classLoader.getResourceAsStream(csvFile);
    }

    @Override
    public List<Transaction> readTransactions() {
        BufferedReader bufferedReader = null;
        String line = "";

        List<Transaction> transactions = new ArrayList<>();

        int lineNumber = 1;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(csvInput));
            while ((line = bufferedReader.readLine()) != null) {
                String[] input = line.split(CSV_SPLIT);
                Transaction transaction = buildTransaction(input);
                transactions.add(transaction);
                lineNumber++;
            }
        } catch (IOException e) {
            System.out.println("An IO exception occurred.");
        } catch (NumberFormatException e) {
            System.out.println("Number format error in input file in line: " + lineNumber + ".");
            throw e;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid input file. Too few columns in line: " + lineNumber + ".");
            throw e;
        } catch (IllegalArgumentException e) {
            System.out.println("Unknown currency in input file in line: " + lineNumber + ".");
            System.out.println(e.getMessage());
            throw e;
        }
        finally {
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
