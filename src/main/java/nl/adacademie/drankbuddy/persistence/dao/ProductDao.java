package nl.adacademie.drankbuddy.persistence.dao;

import nl.adacademie.drankbuddy.entity.Category;
import nl.adacademie.drankbuddy.entity.Product;
import nl.adacademie.drankbuddy.persistence.interfaces.IProductDao;

import java.util.List;
import java.util.Optional;

public class ProductDao implements IProductDao {

    @Override
    public Optional<Product> findById(int id) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    @Override
    public List<Product> findAll() {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    @Override
    public Product save(Product entity) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    @Override
    public void update(Product entity) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    @Override
    public void deleteById(int id) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    @Override
    public void delete(Product entity) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

}
