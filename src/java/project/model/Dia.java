package project.model;
// Generated 06-feb-2021 12:39:09 by Hibernate Tools 4.3.1


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Dia generated by hbm2java
 */
public class Dia  implements java.io.Serializable {


     private Integer idDia;
     private String poblacion;
     private Date fecha;
     private String tempMaxima;
     private String tempMinima;
     private Set<Cotanieve> cotanieves = new HashSet<Cotanieve>(0);

    public Dia() {
    }

	
    public Dia(String poblacion, Date fecha, String tempMaxima, String tempMinima) {
        this.poblacion = poblacion;
        this.fecha = fecha;
        this.tempMaxima = tempMaxima;
        this.tempMinima = tempMinima;
    }
    public Dia(String poblacion, Date fecha, String tempMaxima, String tempMinima, Set<Cotanieve> cotanieves) {
       this.poblacion = poblacion;
       this.fecha = fecha;
       this.tempMaxima = tempMaxima;
       this.tempMinima = tempMinima;
       this.cotanieves = cotanieves;
    }
   
    public Integer getIdDia() {
        return this.idDia;
    }
    
    public void setIdDia(Integer idDia) {
        this.idDia = idDia;
    }
    public String getPoblacion() {
        return this.poblacion;
    }
    
    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public String getTempMaxima() {
        return this.tempMaxima;
    }
    
    public void setTempMaxima(String tempMaxima) {
        this.tempMaxima = tempMaxima;
    }
    public String getTempMinima() {
        return this.tempMinima;
    }
    
    public void setTempMinima(String tempMinima) {
        this.tempMinima = tempMinima;
    }
    public Set<Cotanieve> getCotanieves() {
        return this.cotanieves;
    }
    
    public void setCotanieves(Set<Cotanieve> cotanieves) {
        this.cotanieves = cotanieves;
    }

    public void setFechaDesdeString(String fecha) throws ParseException{
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
        this.fecha = date;
    }

    public String getFechaAString(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        return sdf.format(this.fecha);
    }

}


