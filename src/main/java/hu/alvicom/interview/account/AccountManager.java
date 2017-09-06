package hu.alvicom.interview.account;

import hu.alvicom.interview.model.Account;
import hu.alvicom.interview.model.Currency;

import java.util.List;
import java.util.Optional;

public interface AccountManager {

    static AccountManager getInstance() {
        AccountManagerImpl accountManager = new AccountManagerImpl();
        accountManager.init();
        return accountManager;
    }

    List<Account> getAccounts();

    Currency getAccountCurrency(String accountNumber);
}
