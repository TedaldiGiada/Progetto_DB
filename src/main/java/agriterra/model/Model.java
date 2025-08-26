package agriterra.model;

import java.sql.Connection;

public interface Model {
    Connection getConnection();
   
    static Model fromConnection(Connection connection) {
        return new DBModel(connection);
    }
}