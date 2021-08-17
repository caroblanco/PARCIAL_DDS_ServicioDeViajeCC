package clases;
import java.sql.*;
import static clases.conexion.newConnection;

public class usuarioDAO {

    private Connection conn;

    public int insert(String nombre_usuario, String contrasenia, String email) {
        String consulta = "INSERT INTO usuarios (nombre_usuario, contrasenia, email) VALUES ('" + nombre_usuario + "','" + contrasenia + "','" + email + "');";

        try {

            this.conn = newConnection();

            // Ejecución
            PreparedStatement stmt = this.conn.prepareStatement(consulta, Statement.RETURN_GENERATED_KEYS);

            // execute the preparedstatement
            stmt.executeUpdate();

            // obtener último id generado
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next())
                return generatedKeys.getInt(1);
            else
                return 0;


        } catch (SQLException ex) {

            // handle any errors
            System.out.println("Error en Insert");
            return 0;
        }

    }

    public usuario buscarUsuario(String nombre_usuario) {
        String consulta = "SELECT * FROM usuarios WHERE nombre_usuario = '"+ nombre_usuario+"';";

        try {

            this.conn = newConnection();

            // Ejecución
            Statement stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery(consulta);
            rs.next();

            String contrasenia = rs.getString("contrasenia");
            String email = rs.getString("email");
            int id_usuario = rs.getInt("id_usuario");

            usuario unUsuario = new usuario(nombre_usuario,contrasenia,email,id_usuario);

            return unUsuario;

        } catch (SQLException ex) {

            // handle any errors
            System.out.println("No se encontro el usuario");
            return null;
        }

    }
}
