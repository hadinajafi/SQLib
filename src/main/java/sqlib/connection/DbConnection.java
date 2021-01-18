package sqlib.connection;

import java.sql.Connection;

public interface DbConnection {

    Connection connect();

    Connection connect(String userName, String password);
}
