package nl.adacademie.drankbuddy.repository.testdao;

import nl.adacademie.drankbuddy.entity.Account;
import nl.adacademie.drankbuddy.entity.Category;
import nl.adacademie.drankbuddy.entity.Product;
import nl.adacademie.drankbuddy.repository.interfaces.ProductDaoInterface;

import java.util.ArrayList;
import java.util.List;

public class TestProductDao implements ProductDaoInterface {

    private static final List<Product> ENTITIES = new ArrayList<>();

    @Override
    public List<Product> findAllByAccount(Account account) {
        return ENTITIES.stream().filter(entity -> entity.getCategory().getOwningAccount().equals(account)).toList();
    }

    @Override
    public List<Product> findAllByCategory(Category category) {
        return ENTITIES.stream().filter(entity -> entity.getCategory().equals(category)).toList();
    }

    @Override
    public void save(Product product) {
        ENTITIES.add(product);
    }

    @Override
    public void update(int id, String name, Category category) {
        ENTITIES.stream().filter(entity -> entity.getId() == id).findFirst().ifPresent(entity -> {
            entity.setName(name);
            entity.setCategory(category);
        });
    }

    @Override
    public void delete(Product product) {
        ENTITIES.remove(product);
    }

    public static void addAll(List<Product> products) {
        ENTITIES.addAll(products);
    }

    public static void clearAll() {
        ENTITIES.clear();
    }

}
