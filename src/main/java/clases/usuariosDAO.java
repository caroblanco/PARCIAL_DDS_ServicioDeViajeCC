package clases;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static clases.conexion.newConnection;

public class usuariosDAO {
    private Connection conn;

    public List<String> dameTodosLosNombresDeUsuarios() {

        try {

            // generacion de query
            String consulta = "SELECT nombre_usuario FROM usuarios";

            // Conexión
            this.conn = newConnection();

            // Ejecución
            Statement stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery(consulta);

            // Recorrer y usar cada línea retornada
            List<String> nombresUsuarios = new ArrayList<>();

            while (rs.next()) {

                // get titulo
                String nombre_usuario = rs.getString("nombre_usuario");


                nombresUsuarios.add(nombre_usuario);
            }

            return nombresUsuarios;

        } catch (SQLException ex) {

            // handle any errors
            System.out.println("Error en Select");
            return null;
        }
    }

}
