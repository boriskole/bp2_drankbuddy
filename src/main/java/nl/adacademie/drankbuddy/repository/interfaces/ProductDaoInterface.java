package nl.adacademie.drankbuddy.repository.interfaces;

import nl.adacademie.drankbuddy.entity.Account;
import nl.adacademie.drankbuddy.entity.Category;
import nl.adacademie.drankbuddy.entity.Product;

import java.util.List;

public interface ProductDaoInterface {

    List<Product> findAllByAccount(Account account);

    List<Product> findAllByCategory(Category category);

}
