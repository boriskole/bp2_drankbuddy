package nl.adacademie.drankbuddy.repository.dao;

import nl.adacademie.drankbuddy.entity.Account;
import nl.adacademie.drankbuddy.repository.interfaces.AccountDaoInterface;
import nl.adacademie.drankbuddy.service.DatabaseConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class AccountDaoImpl implements AccountDaoInterface {

    private final Connection connection;

    public AccountDaoImpl() {
        this.connection = DatabaseConnectionProvider.getConnection();
    }

    @Override
    public Optional<Account> findByUsername(String username) {
        try {
            // Query aanmaken.
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM account WHERE username = ?");

            // Query parameters instellen.
            statement.setString(1, username);

            // Query uitvoeren.
            ResultSet result = statement.executeQuery();

            // Resultaat verwerken.
            if (result.next()) {
                return Optional.of(mapResultSetToEntity(result));
            } else {
                return Optional.empty();
            }

        } catch (SQLException exception) {
            throw new IllegalStateException("Er ging iets mis tijdens een database operatie.", exception);
        }
    }

    @Override
    public Account save(Account entity) {
        try {
            // Query maken.
            PreparedStatement statement = connection.prepareStatement("INSERT INTO account (username, password, name) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            // Query parameters instellen.
            statement.setString(1, entity.getUsername());
            statement.setString(2, entity.getPassword());
            statement.setString(3, entity.getName());

            // Query uitvoeren.
            statement.executeUpdate();

            // Haal de keys op
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                entity.setId(generatedKeys.getInt(1));
            }

            return entity;
        } catch (SQLException exception) {
            throw new RuntimeException("Fout bij opslaan account: " + exception.getMessage(), exception);
        }
    }

    private Account mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return new Account(
            resultSet.getInt("id"),
            resultSet.getString("username"),
            resultSet.getString("password"),
            resultSet.getString("name")
        );
    }

}
