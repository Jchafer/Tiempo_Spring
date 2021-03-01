package model;

import DAO.DiaDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Proyecto: El Tiempo (Aplicación que muestra el tiempo
 * actualizado en diferentes ciudades en los próximos 7 días. 
 * @author Jesus Chafer 
 * @since 25/11/2020
 * @version 2.0
 */
public class Dia {

    private static int last_id = 0;
    private int idDia;
    private Date fecha;
    private String tempMaxima;
    private String tempMinima;
    private ArrayList<CotaNieve> cotanieve;

    public Dia() {
        this.last_id++;
        this.idDia = last_id;
        this.cotanieve = new ArrayList();
    }

    public Dia(Date fecha, String tempMaxima, String tempMinima) {
        this.last_id++;
        this.idDia = last_id;
        this.fecha = fecha;
        this.tempMaxima = tempMaxima;
        this.tempMinima = tempMinima;
        this.cotanieve = new ArrayList();
    }

    public void insertarDiaBBDD(Connection con, Prediccion prediccion) throws SQLException, Exception {
        DiaDAO diaDao = new DiaDAO(con, this);
        diaDao.insertarDia(prediccion);
    }

    public boolean comprobarDiaBBDD(Connection con, Prediccion prediccion) throws SQLException, Exception {
        DiaDAO diaDao = new DiaDAO(con, this);
        return diaDao.compruebaDia(prediccion);
    }

    public int getIdDia() {
        return idDia;
    }

    public void setIdDia(int idDia) {
        this.idDia = idDia;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public ArrayList<CotaNieve> getCotanieve() {
        return cotanieve;
    }

    public void setCotanieve(ArrayList<CotaNieve> cotanieve) {
        this.cotanieve = cotanieve;
    }

    public String getTempMaxima() {
        return tempMaxima;
    }

    public void setTempMaxima(String tempMaxima) {
        this.tempMaxima = tempMaxima;
    }

    public String getTempMinima() {
        return tempMinima;
    }

    public void setTempMinima(String tempMinima) {
        this.tempMinima = tempMinima;
    }

    public String fechaAString() {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String fechaFormat = formato.format(this.fecha);
        return fechaFormat;
    }

    @Override
    public String toString() {

        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String fechaFormat = formato.format(this.fecha);

        String datos = "   Dia (" + fechaFormat + ")\n"
                + "\tTemperatura máxima: " + tempMaxima + "\n"
                + "\tTemperatura mínima: " + tempMinima + "\n";
        if (!cotanieve.isEmpty()) {
            for (CotaNieve cotaDeNieve : cotanieve) {
                datos += "\tCota de nieve: " + cotaDeNieve + "\n";
            }
        }

        return datos;
    }

}
