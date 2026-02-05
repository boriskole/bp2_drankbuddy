package nl.adacademie.drankbuddy.repository.testdao;

import nl.adacademie.drankbuddy.entity.Account;
import nl.adacademie.drankbuddy.repository.interfaces.AccountDaoInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TestAccountDao implements AccountDaoInterface {

    private static final List<Account> ENTITIES = new ArrayList<>();

    @Override
    public Optional<Account> findByUsername(String username) {
        return ENTITIES.stream().filter(entity -> entity.getUsername().equals(username)).findFirst();
    }

    @Override
    public Account save(Account account) {
        ENTITIES.add(account);
        return account;
    }

    public static void addAll(List<Account> accounts) {
        ENTITIES.addAll(accounts);
    }

    public static void clearAll(List<Account> accounts) {
        ENTITIES.removeAll(accounts);
    }

}
