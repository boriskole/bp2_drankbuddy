package nl.adacademie.drankbuddy.persistence.dao;

import nl.adacademie.drankbuddy.entity.Account;
import nl.adacademie.drankbuddy.persistence.interfaces.IAccountDao;

import java.util.List;
import java.util.Optional;

public class AccountDao implements IAccountDao {

    @Override
    public Optional<Account> findByUsername(String username) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    @Override
    public Optional<Account> findById(int id) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    @Override
    public List<Account> findAll() {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    @Override
    public Account save(Account entity) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    @Override
    public void update(Account entity) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    @Override
    public void deleteById(int id) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    @Override
    public void delete(Account entity) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

}
