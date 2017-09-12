package hu.alvicom.interview.account;

import hu.alvicom.interview.model.Account;
import hu.alvicom.interview.model.Currency;
import lombok.Getter;

import java.util.List;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

public class AccountManagerImpl implements AccountManager {

    private static AccountManagerImpl accountManager = null;

    public static AccountManagerImpl getInstance() {
        if(accountManager == null) {
            accountManager = new AccountManagerImpl();
            accountManager.init();
        }
        return accountManager;
    }

    @Getter
    private List<Account> accounts;

    private AccountManagerImpl() {}

    private void init() {
        accounts = AccountGenerator.generateAccounts();
    }


    @Override
    public Currency getAccountCurrency(String accountNumber) {
         return accounts.stream().filter(findAccountByNumber(accountNumber))
                 .map(Account::getCurrency).collect(toList()).get(0);
    }

    private Predicate<Account> findAccountByNumber(String accountNumber) {
        return account -> account.getAccountNumber().equals(accountNumber);
    }

    @Override
    public double getAccountBalance(String accountNumber) {
        return accounts.stream().filter(findAccountByNumber(accountNumber))
                .map(Account::getBalance).collect(toList()).get(0);
    }
}
