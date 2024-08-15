package Action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseUtil {
    static final String JDBC_URL = "jdbc:mysql://localhost:3306/JDBC_CRUD_PROJECT_PRACTICE";
    static final String USER = "ROOT";
    static final String PASSWORD = "123456";

    public static Connection connection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL,USER,PASSWORD);
    }
}
