package hu.alvicom.interview.transaction;

import hu.alvicom.interview.model.Transaction;
import hu.alvicom.interview.report.ReportPrinter;
import javafx.collections.ListChangeListener;
import lombok.Getter;

import java.util.List;

public class ReportPrinterMock extends ReportPrinter {

    private @Getter boolean reportPrinted;
    private @Getter boolean warningPrinted;

    public ReportPrinterMock(List<Transaction> parsedTransactions) {
        super(parsedTransactions);
    }

    class ParsedTransactionListener implements ListChangeListener<Transaction> {
        @Override
        public void onChanged(Change<? extends Transaction> c) {
            reportPrinted = true;
        }
    }

    @Override
    public void printWarning(Transaction transaction) {
        warningPrinted = true;
    }

}

