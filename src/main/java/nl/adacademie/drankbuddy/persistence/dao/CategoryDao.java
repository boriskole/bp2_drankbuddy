package nl.adacademie.drankbuddy.persistence.dao;

import nl.adacademie.drankbuddy.entity.Account;
import nl.adacademie.drankbuddy.entity.Category;
import nl.adacademie.drankbuddy.persistence.interfaces.ICategoryDao;

import java.util.List;
import java.util.Optional;

public class CategoryDao implements ICategoryDao {

    @Override
    public Optional<Category> findById(int id) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    @Override
    public List<Category> findAll() {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    @Override
    public Category save(Category entity) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    @Override
    public void update(Category entity) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    @Override
    public void deleteById(int id) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    @Override
    public void delete(Category entity) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

}
