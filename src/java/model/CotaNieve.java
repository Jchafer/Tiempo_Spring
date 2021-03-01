package model;

import DAO.CotaNieveDAO;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Proyecto: El Tiempo (Aplicación que muestra el tiempo
 * actualizado en diferentes ciudades en los próximos 7 días. 
 * @author Jesus Chafer 
 * @since 25/11/2020
 * @version 2.0
 */
public class CotaNieve {

    private static int last_id = 0;
    private int idCotaNieve;
    private String periodo;

    public CotaNieve() {
        this.last_id++;
        this.idCotaNieve = last_id;
    }

    public CotaNieve(String periodo) {
        this.last_id++;
        this.idCotaNieve = last_id;
        this.periodo = periodo;
    }

    public void insertarCotaNieveBBDD(Connection con, Prediccion prediccion, Dia dia) throws SQLException, Exception {
        CotaNieveDAO cotaNieveDAO = new CotaNieveDAO(con, this);
        cotaNieveDAO.insertarCotaNieve(prediccion, dia);
    }

    public int getIdCotaNieve() {
        return idCotaNieve;
    }

    public void setIdCotaNieve(int idCotaNieve) {
        this.idCotaNieve = idCotaNieve;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    @Override
    public String toString() {
        return "Periodo = " + periodo;
    }

}
