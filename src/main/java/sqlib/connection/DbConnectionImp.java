package sqlib.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static common.exception.ExceptionMessageBuilder.messageBuilder;

/**
 * author: Hadi Najafi
 */

public class DbConnectionImp implements DbConnection {

    private final String urlConnection;

    public DbConnectionImp(String urlConnection) {
        this.urlConnection = urlConnection;
    }

    @Override
    public Connection connect() {
        try (Connection conn = DriverManager.getConnection(urlConnection)) {
            return conn;
        } catch (SQLException ex) {
            System.err.println(messageBuilder(ex.getErrorCode(), ex.getMessage()));
            return null;
        }
    }

    @Override
    public Connection connect(String userName, String password) {
        try (Connection conn = DriverManager.getConnection(urlConnection, userName, password)) {
            return conn;
        } catch (SQLException ex) {
            System.err.println(messageBuilder(ex.getErrorCode(), ex.getMessage()));
            return null;
        }
    }
}
