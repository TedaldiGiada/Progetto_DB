package agriterra.model;

import java.sql.Connection;
import java.util.Objects;


public final class DBModel implements Model {

    private final Connection connection;

    public DBModel(Connection connection) {
        Objects.requireNonNull(connection, "Model created with null connection");
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }
}