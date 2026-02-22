package nl.adacademie.drankbuddy.repository.dao;

import nl.adacademie.drankbuddy.entity.Account;
import nl.adacademie.drankbuddy.entity.Product;
import nl.adacademie.drankbuddy.entity.mutation.CorrectionMutation;
import nl.adacademie.drankbuddy.entity.mutation.DeliveryMutation;
import nl.adacademie.drankbuddy.entity.mutation.SaleMutation;
import nl.adacademie.drankbuddy.entity.mutation.StockMutation;
import nl.adacademie.drankbuddy.repository.interfaces.StockMutationDaoInterface;
import nl.adacademie.drankbuddy.service.DatabaseConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockMutationDaoImpl implements StockMutationDaoInterface {

    private final Connection connection;

    public StockMutationDaoImpl() {
        this.connection = DatabaseConnectionProvider.getConnection();
    }

    @Override
    public List<StockMutation> findAllByAccount(Account account) {
        try {
            // Query maken.
            String sql = "SELECT stock_mutation.id AS id, stock_mutation.date AS mutation_stamp, product.name AS product_name, stock_mutation.mutation_type AS mutation_type, stock_mutation.stock_change AS stock_change FROM stock_mutation INNER JOIN product ON stock_mutation.product_id = product.id INNER JOIN category ON product.category_id = category.id WHERE category.account_id = ? ORDER BY stock_mutation.date DESC;";

            // Statement maken.
            PreparedStatement statement = connection.prepareStatement(sql);

            // Parameters instellen.
            statement.setInt(1, account.getId());

            // Statement uitvoeren.
            ResultSet resultSet = statement.executeQuery();

            // Resultaat verwerken.
            List<StockMutation> stockMutations = new ArrayList<>();

            while (resultSet.next()) {
                StockMutation stockMutation = switch (resultSet.getString("mutation_type")) {
                    case "SALE" -> new SaleMutation(
                        resultSet.getInt("id"),
                        resultSet.getInt("stock_change"),
                        resultSet.getTimestamp("mutation_stamp").toLocalDateTime(),
                        new Product(
                            resultSet.getString("product_name"),
                            null // Categorie is niet nodig hier, want die data laten we toch niet zien.
                        )
                    );
                    case "DELIVERY" -> new DeliveryMutation(
                        resultSet.getInt("id"),
                        resultSet.getInt("stock_change"),
                        resultSet.getTimestamp("mutation_stamp").toLocalDateTime(),
                        new Product(
                            resultSet.getString("product_name"),
                            null // Categorie is niet nodig hier, want die data laten we toch niet zien.
                        )
                    );
                    case "CORRECTION" -> new CorrectionMutation(
                        resultSet.getInt("id"),
                        resultSet.getInt("stock_change"),
                        resultSet.getTimestamp("mutation_stamp").toLocalDateTime(),
                        new Product(
                            resultSet.getString("product_name"),
                            null // Categorie is niet nodig hier, want die data laten we toch niet zien.
                        )
                    );
                    default -> throw new IllegalStateException("Unexpected value: " + resultSet.getString("mutation_type"));
                };

                stockMutations.add(stockMutation);
            }

            return stockMutations;
        } catch (SQLException exception) {
            throw new IllegalStateException("Er ging iets mis tijdens een database operatie.", exception);
        }
    }

}
