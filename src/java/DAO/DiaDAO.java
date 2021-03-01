package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Dia;
import model.Prediccion;

/**
 * Proyecto: El Tiempo (Aplicación que muestra el tiempo
 * actualizado en diferentes ciudades en los próximos 7 días. 
 * @author Jesus Chafer 
 * @since 25/11/2020
 * @version 2.0
 */
public class DiaDAO {

    private Connection con;
    private Dia dia;

    public DiaDAO(Connection con, Dia dia) {
        this.con = con;
        this.dia = dia;
    }

    /*public void actualiza() throws SQLException{
        PreparedStatement stmt = null;
        stmt = con.prepareStatement("UPDATE dia SET poblacion=?, fecha=?, tempMaxima=?, tempMinima=?");
        
        stmt.setString(1, prediccion.getNombre());
        stmt.setString(2, dia.fechaAString());
        stmt.setString(3, dia.getTempMaxima());
        stmt.setString(4, dia.getTempMinima());
        
    }*/
    public void insertarDia(Prediccion prediccion) throws SQLException, Exception {
        PreparedStatement stmt = null;
        stmt = con.prepareStatement("INSERT INTO dia (poblacion, fecha, tempMaxima, tempMinima) VALUES(?,?,?,?)");
        try {
            stmt.setString(1, prediccion.getNombre());
            stmt.setDate(2, java.sql.Date.valueOf(dia.fechaAString()));
            stmt.setString(3, dia.getTempMaxima());
            stmt.setString(4, dia.getTempMinima());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al insertar el dia");
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public boolean compruebaDia(Prediccion prediccion) throws SQLException, Exception {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("SELECT * FROM dia WHERE poblacion=? AND fecha=?");
            stmt.setString(1, prediccion.getNombre());
            stmt.setString(2, dia.fechaAString());
            rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            }
            return false;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al buscar el dia");
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }

    }
}
