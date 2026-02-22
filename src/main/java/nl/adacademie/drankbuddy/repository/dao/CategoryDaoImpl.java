package nl.adacademie.drankbuddy.repository.dao;

import nl.adacademie.drankbuddy.entity.Account;
import nl.adacademie.drankbuddy.entity.Category;
import nl.adacademie.drankbuddy.repository.interfaces.CategoryDaoInterface;
import nl.adacademie.drankbuddy.service.DatabaseConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements CategoryDaoInterface {

    private final Connection connection;

    public CategoryDaoImpl() {
        this.connection = DatabaseConnectionProvider.getConnection();
    }

    @Override
    public List<Category> findAllByAccount(Account account) {
        try {
            // Query maken.
            String sql = "SELECT category.id AS category_id, category.name AS category_name, COUNT(product.id) AS amount_of_products FROM category LEFT JOIN product ON category.id = product.category_id WHERE account_id = ? GROUP BY category.id";

            // Statement maken.
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, account.getId()); // Parameter instellen.
            ResultSet resultSet = statement.executeQuery(); // Statement uitvoeren.

            List<Category> categories = new ArrayList<>();

            while (resultSet.next()) { // Zolang er resultaten zijn.
                Category category = new Category(resultSet.getString("category_name"), account); // Nieuwe categorie aanmaken met query data.
                category.setAmountOfProducts(resultSet.getInt("amount_of_products"));
                category.setId(resultSet.getInt("category_id"));
                categories.add(category); // Toevoegen aan lijst.
            }

            return categories;

        } catch (SQLException exception) {
            throw new IllegalStateException("Er ging iets mis tijdens een database operatie.", exception);
        }
    }

    @Override
    public void save(Category category) {
         try {
             // Query maken.
             String sql = "INSERT INTO category (name, account_id) VALUES (?, ?)";

             // Statement maken.
             PreparedStatement statement = connection.prepareStatement(sql);

             // Parameters instellen.
             statement.setString(1, category.getName());
             statement.setInt(2, category.getOwningAccount().getId());

             // Statement uitvoeren.
             statement.executeUpdate();
         } catch (SQLException exception) {
             throw new IllegalStateException("Er ging iets mis tijdens een database operatie.", exception);
         }
    }

}
