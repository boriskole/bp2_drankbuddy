package nl.adacademie.drankbuddy.repository.dao;

import nl.adacademie.drankbuddy.entity.Account;
import nl.adacademie.drankbuddy.entity.Category;
import nl.adacademie.drankbuddy.entity.Product;
import nl.adacademie.drankbuddy.repository.interfaces.ProductDaoInterface;
import nl.adacademie.drankbuddy.service.DatabaseConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDaoInterface {

    private final Connection connection;

    public ProductDaoImpl() {
        this.connection = DatabaseConnectionProvider.getConnection();
    }

    @Override
    public List<Product> findAllByAccount(Account account) {
        try {

            String sql = "SELECT product.id AS product_id, product.name AS product_name, category.name AS category_name, COALESCE(SUM(stock_mutation.stock_change), 0) AS current_stock FROM product INNER JOIN category ON product.category_id = category.id LEFT JOIN stock_mutation on product.id = stock_mutation.product_id WHERE category.account_id = ? GROUP BY product.id, product.name, category.name";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, account.getId());
            ResultSet resultSet = statement.executeQuery();

            List<Product> products = new ArrayList<>();

            while (resultSet.next()) {
                Category category = new Category(resultSet.getString("category_name"), account);

                Product product = new Product(resultSet.getString("product_name"), category, resultSet.getInt("current_stock"));

                products.add(product);
            }

            return products;

        } catch (SQLException exception) {
            throw new IllegalStateException("Er ging iets mis tijdens een database operatie.", exception);
        }
    }

    @Override
    public List<Product> findAllByCategory(Category category) {
        try {
            String sql = "SELECT * FROM product WHERE category_id = ?;";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, category.getId());
            ResultSet resultSet = statement.executeQuery();

            List<Product> products = new ArrayList<>();

            while (resultSet.next()) {
                Product product = new Product(
                    resultSet.getString("name"),
                    category
                );
                products.add(product);
            }

            return products;

        } catch (SQLException exception) {
            throw new IllegalStateException("Er ging iets mis tijdens een database operatie.", exception);
        }
    }

}
