package nl.adacademie.drankbuddy.persistence.interfaces;

import nl.adacademie.drankbuddy.entity.Account;

import java.util.Optional;

public interface IAccountDao extends IGenericDao<Account> {

    Optional<Account> findByUsername(String username);

}
