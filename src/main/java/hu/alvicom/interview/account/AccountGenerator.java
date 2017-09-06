package hu.alvicom.interview.account;

import hu.alvicom.interview.model.Account;
import hu.alvicom.interview.model.Currency;

import java.util.ArrayList;
import java.util.List;

public class AccountGenerator {

    public static List<Account> generateAccounts() {
        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account("11111111-22222222", Currency.HUF, 150000));
        accounts.add(new Account("22222222-33333333", Currency.USD, 1230));
        return accounts;
    }
}
