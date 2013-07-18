import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDriverTest {

    public static void main(String[] argv) {
        String username = "root";
        String password = "XXXXXX"; // Change PASSWORD!
        String db = "test";

        System.out.println("-------- MySQL JDBC Connection Testing ------------");

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
        }

        System.out.println("MySQL JDBC Driver Registered!");
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db, username, password);
        } catch (SQLException e) {
            System.out.println("Connection Failed!");
            e.printStackTrace();
        }

        if (connection != null)
            System.out.println("You made it, take control your database now!");
        else System.out.println("Failed to make connection!");

    }
}