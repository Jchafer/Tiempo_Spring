package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.CotaNieve;
import model.Dia;
import model.Prediccion;

/**
 * Proyecto: El Tiempo (Aplicación que muestra el tiempo
 * actualizado en diferentes ciudades en los próximos 7 días. 
 * @author Jesus Chafer 
 * @since 25/11/2020
 * @version 2.0
 */
public class CotaNieveDAO {

    private Connection con;
    private CotaNieve cotaNieve;

    public CotaNieveDAO(Connection con, CotaNieve cotaNieve) {
        this.con = con;
        this.cotaNieve = cotaNieve;
    }

    /*public void actualiza(Dia dia) throws SQLException{
        PreparedStatement stmt = null;
        
        stmt = con.prepareStatement("UPDATE cotaNieve SET idCotaNieve=?, periodo=?, idDia=?");
        stmt.setInt(1, cotaNieve.getIdCotaNieve());
        stmt.setString(2, cotaNieve.getPeriodo());
        stmt.setInt(3, dia.getIdDia());
        
    }*/
    public void insertarCotaNieve(Prediccion prediccion, Dia dia) throws SQLException, Exception {
        int idDia = obtenerIdDia(prediccion, dia);
        PreparedStatement stmt = null;
        stmt = con.prepareStatement("INSERT INTO cotaNieve (periodo, idDia) VALUES(?,?)");
        try {
            stmt.setString(1, cotaNieve.getPeriodo());
            stmt.setInt(2, idDia);

            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al insertar la cota de nieve");
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }

    }

    public int obtenerIdDia(Prediccion prediccion, Dia dia) throws SQLException, Exception {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("SELECT * FROM dia WHERE poblacion=? AND fecha=?");
            stmt.setString(1, prediccion.getNombre());
            stmt.setString(2, dia.fechaAString());
            rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("idDia");
            }
            return 0;
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
