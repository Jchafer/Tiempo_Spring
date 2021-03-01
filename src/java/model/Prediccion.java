package model;

import java.util.ArrayList;

/**
 * Proyecto: El Tiempo (Aplicación que muestra el tiempo
 * actualizado en diferentes ciudades en los próximos 7 días. 
 * @author Jesus Chafer 
 * @since 25/11/2020
 * @version 2.0
 */
public class Prediccion {

    private static int last_id = 0;
    private int idPrediccion;
    private String nombre;
    private String web;
    private ArrayList<Dia> dias;

    public Prediccion() {
        this.last_id++;
        this.idPrediccion = last_id;
        this.dias = new ArrayList<>();
    }

    public Prediccion(String nombre, String web) {
        this.last_id++;
        this.idPrediccion = last_id;
        this.nombre = nombre;
        this.web = web;
        this.dias = new ArrayList<>();
    }

    public int getIdPrediccion() {
        return idPrediccion;
    }

    public void setIdPrediccion(int idPrediccion) {
        this.idPrediccion = idPrediccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public ArrayList<Dia> getDias() {
        return dias;
    }

    public void setDias(ArrayList<Dia> dias) {
        this.dias = dias;
    }

    @Override
    public String toString() {
        String datos = "//////////////////////////////////////////////////////////////\n"
                + "--- Prediccion de " + this.nombre + " ---> En la WEB (" + this.web + ")\n";

        for (Dia dia : dias) {
            datos += dia + "\n";
        }
        return datos;
    }

}
