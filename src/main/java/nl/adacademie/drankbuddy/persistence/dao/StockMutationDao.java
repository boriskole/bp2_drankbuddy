package nl.adacademie.drankbuddy.persistence.dao;

import nl.adacademie.drankbuddy.entity.Product;
import nl.adacademie.drankbuddy.entity.mutation.StockMutation;
import nl.adacademie.drankbuddy.persistence.interfaces.IStockMutationDao;

import java.util.List;
import java.util.Optional;

public class StockMutationDao implements IStockMutationDao {

    @Override
    public Optional<StockMutation> findById(int id) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    @Override
    public List<StockMutation> findAll() {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    @Override
    public StockMutation save(StockMutation entity) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    @Override
    public void update(StockMutation entity) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    @Override
    public void deleteById(int id) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    @Override
    public void delete(StockMutation entity) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

}
