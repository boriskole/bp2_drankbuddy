package nl.adacademie.drankbuddy.repository.testdao;

import nl.adacademie.drankbuddy.entity.Account;
import nl.adacademie.drankbuddy.entity.Category;
import nl.adacademie.drankbuddy.repository.interfaces.CategoryDaoInterface;

import java.util.ArrayList;
import java.util.List;

public class TestCategoryDao implements CategoryDaoInterface {

    private static final List<Category> ENTITIES = new ArrayList<>();

    @Override
    public List<Category> findAllByAccount(Account account) {
        return ENTITIES.stream().filter(entity -> entity.getOwningAccount().equals(account)).toList();
    }

    @Override
    public void save(Category category) {
        ENTITIES.add(category);
    }

    @Override
    public void update(int id, String name) {
        ENTITIES.stream().filter(entity -> entity.getId() == id).findFirst().ifPresent(entity -> entity.setName(name));
    }

    public static void addAll(List<Category> accounts) {
        ENTITIES.addAll(accounts);
    }

    public static void clearAll() {
        ENTITIES.clear();
    }

}
