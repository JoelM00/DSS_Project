package Data;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConfigDAO {
    protected static final String USERNAME = "root";
    protected static final String PASSWORD = "1234";
    protected static final String DATABASE = "DSS";
    protected static final String URL = "localhost";

    static Connection connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://" + URL
                    + "/" + DATABASE
                    + "?user=" + USERNAME
                    + "&password=" + PASSWORD
                    + "&allowMultiQueries=true");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    static void close(Connection connection) {
        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
