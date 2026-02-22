package nl.adacademie.drankbuddy.repository.interfaces;

import nl.adacademie.drankbuddy.entity.Account;
import nl.adacademie.drankbuddy.entity.Category;

import java.util.List;

public interface CategoryDaoInterface {

    List<Category> findAllByAccount(Account account);

    void save(Category category);

}
