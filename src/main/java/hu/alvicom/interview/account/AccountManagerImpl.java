package hu.alvicom.interview.account;

import hu.alvicom.interview.model.Account;
import hu.alvicom.interview.model.Currency;
import lombok.Getter;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class AccountManagerImpl implements AccountManager {

    @Getter
    private List<Account> accounts;

    public void init() {
        accounts = AccountGenerator.generateAccounts();
    }

    @Override
    public Currency getAccountCurrency(String accountNumber) {
         return accounts.stream().filter(account -> account.getAccountNumber().equals(accountNumber))
                 .map(Account::getCurrency).collect(toList()).get(0);
    }
}
