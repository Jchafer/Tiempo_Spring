package DAO;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Proyecto: El Tiempo (Aplicación que muestra el tiempo
 * actualizado en diferentes ciudades en los próximos 7 días. 
 * @author Jesus Chafer 
 * @since 25/11/2020
 * @version 2.0
 */
public class Conexion_DB {

    public Connection abrirConexion() throws ClassNotFoundException, SQLException {
        Connection con = null;

        Class.forName("com.mysql.cj.jdbc.Driver");//cargar el driver

        String urlOdbc = "jdbc:mysql://localhost:3306/predicciondb";
        String user = "root";
        String passwd = "";
        con = (java.sql.DriverManager.getConnection(urlOdbc, user, passwd));

        return con;
    }

    public void cerrarConexion(Connection con) throws SQLException {
        if (con != null) {
            con.close();
        }
    }

}
