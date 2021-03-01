package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Prediccion;

/**
 * Proyecto: El Tiempo (Aplicación que muestra el tiempo
 * actualizado en diferentes ciudades en los próximos 7 días. 
 * @author Jesus Chafer 
 * @since 25/11/2020
 * @version 2.0
 */
public class PrediccionDAO {

    private Connection con;
    private Prediccion prediccion;

    public PrediccionDAO(Connection con, Prediccion prediccion) {
        this.con = con;
        this.prediccion = prediccion;
    }

    public void actualiza() throws SQLException {
        PreparedStatement stmt = null;

        stmt = con.prepareStatement("UPDATE prediccion SET idPrediccion=?, nombre=?, web=?");
        stmt.setInt(1, prediccion.getIdPrediccion());
        stmt.setString(2, prediccion.getNombre());
        stmt.setString(3, prediccion.getWeb());

    }

    public void insertarPrediccion() throws SQLException {
        PreparedStatement stmt = null;
        stmt = con.prepareStatement("INSERT INTO prediccion (idPrediccion, nombre, web) VALUES(?,?,?)");

        stmt.setInt(1, prediccion.getIdPrediccion());
        stmt.setString(2, prediccion.getNombre());
        stmt.setString(3, prediccion.getWeb());

        stmt.executeUpdate();

    }

}
