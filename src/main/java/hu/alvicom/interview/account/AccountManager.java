package hu.alvicom.interview.account;

import hu.alvicom.interview.model.Account;
import hu.alvicom.interview.model.Currency;

import java.util.List;

public interface AccountManager {

    static AccountManager getInstance() {
       return AccountManagerImpl.getInstance();
    }

    List<Account> getAccounts();

    Currency getAccountCurrency(String accountNumber);

    double getAccountBalance(String accountNumber);
}
