package nl.adacademie.drankbuddy.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Verantwoordelijk voor het maken en geven van een verbinding met de database.
 */
public class DatabaseConnectionProvider {

    private static final String DATABASE_CONNECTION_URL = "jdbc:mysql://localhost:3306/bp2_drankbuddy";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "admin";

    // Private zodat je niet zelf instanties kan maken van deze klasse.
    private DatabaseConnectionProvider() { }

    /**
     * Geeft een verbinding terug naar de database.
     * @return Een instantie van de Connection klasse.
     */
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(
                DATABASE_CONNECTION_URL,
                DATABASE_USERNAME,
                DATABASE_PASSWORD
            );
        } catch (SQLException exception) {
            throw new RuntimeException("Kon niet verbinden met de database.", exception);
        }
    }

}
