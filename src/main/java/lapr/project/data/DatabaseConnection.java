package lapr.project.data;

import oracle.jdbc.pool.OracleDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static lapr.project.ui.console.console_utils.Utils.print;

/**
 *
 */
public class DatabaseConnection {
    private Connection connection;
    private SQLException error;

    public DatabaseConnection(String url, String username, String password) {
        try {
            OracleDataSource oracleDataSource = new OracleDataSource();

            oracleDataSource.setURL(url);

            connection = oracleDataSource.getConnection(username, password);

        } catch (SQLException e) {
            Logger.getLogger(DatabaseConnection.class.getName())
                    .log(Level.SEVERE, null, e);
            print(String.format("SQL State: %s%n%s", e.getSQLState(),
                    e.getMessage()));
        }
    }

    public Connection getConnection() {
        if (connection == null) {
            throw new IllegalArgumentException("Connection does not exit");
        }
        return connection;
    }

    public void registerError(SQLException error) {
        this.error = error;
    }

    public SQLException getLastError() {
        SQLException lastError = this.error;
        //Clear after reading.
        registerError(null);
        return lastError;
    }
}