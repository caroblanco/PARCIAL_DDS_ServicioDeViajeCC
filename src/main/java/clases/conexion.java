package clases;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexion {

    public static Connection newConnection() {
        Connection conn = null;
        try {
            String connectionUrl = "jdbc:mysql://localhost:3306/parcialdds2021";
            conn = DriverManager.getConnection(connectionUrl, "root", "");

            // Do something with the Connection
            //System.out.println("Conexión realizada");

            return conn;

        } catch (SQLException ex) {

            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return null;
        }
    }

}
