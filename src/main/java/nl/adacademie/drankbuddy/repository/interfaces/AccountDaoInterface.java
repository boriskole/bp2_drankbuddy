package nl.adacademie.drankbuddy.repository.interfaces;

import nl.adacademie.drankbuddy.entity.Account;

import java.util.Optional;

public interface AccountDaoInterface {

    Optional<Account> findByUsername(String username);

    Account save(Account account);

}
