package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import model.Dia;
import model.CotaNieve;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Proyecto: El Tiempo (Aplicación que muestra el tiempo
 * actualizado en diferentes ciudades en los próximos 7 días. 
 * @author Jesus Chafer 
 * @since 25/11/2020
 * @version 2.0
 */
public class DiaController {

    //Constantes con el nombre de las etiquetas, excepto la raiz.
    static final String ET_NOMBRE = "nombre";
    static final String ET_ORIGEN = "origen";
    static final String ET_WEB = "web";
    static final String ET_PREDICCION = "prediccion";
    static final String ET_DIA = "dia";
    static final String ET_FECHA = "fecha";
    static final String ET_COTA_NIEVE_PROV = "cota_nieve_prov";
    static final String ET_PERIODO = "periodo";
    static final String ET_TEMPERATURA = "temperatura";
    static final String ET_MAXIMA = "maxima";
    static final String ET_MINIMA = "minima";

    public static String getValorEtiqueta(String etiqueta, Element element) {
        Node nValue = element.getElementsByTagName(etiqueta).item(0);
        return nValue.getChildNodes().item(0).getNodeValue();
    }

    public static Element getElementEtiqueta(String etiqueta, Element element) {
        return (Element) element.getElementsByTagName(etiqueta).item(0);
    }

    public static Element leerWeb(Element elemOrigen) {

        NodeList elementos = elemOrigen.getChildNodes();

        for (int i = 0; i < elementos.getLength(); i++) {
            if (elementos.item(i).getNodeType() == Node.ELEMENT_NODE) {
                if (elementos.item(i).getNodeName().equalsIgnoreCase(ET_WEB)) {
                    return (Element) elementos.item(i);
                }
            }
        }
        return null;
    }

    public static ArrayList<Dia> leerDias(Element elemPrediccion) throws ParseException {
        ArrayList<Dia> listaDias = new ArrayList<>();

        NodeList elementos = elemPrediccion.getChildNodes();

        for (int i = 0; i < elementos.getLength(); i++) {
            if (elementos.item(i).getNodeType() == Node.ELEMENT_NODE) {
                listaDias.add(leerDia((Element) elementos.item(i)));
            }
        }
        return listaDias;
    }

    public static Dia leerDia(Element elemenDia) throws ParseException {
        Dia dia = new Dia();

        // Obtener fecha
        String fecha = elemenDia.getAttribute(ET_FECHA);
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaDate = formato.parse(fecha);
        dia.setFecha(fechaDate);

        // Obtener elemento temperatura
        Element elemenTemperatura = getElementEtiqueta(ET_TEMPERATURA, elemenDia);

        // Obtener temperatura máxima        
        String tempMax = getValorEtiqueta(ET_MAXIMA, elemenTemperatura);
        dia.setTempMaxima(tempMax);

        // Obtener temperatura mínima
        String tempMin = getValorEtiqueta(ET_MINIMA, elemenTemperatura);
        dia.setTempMinima(tempMin);

        // Obtener cota de nieve (list)
        NodeList elementos = elemenDia.getChildNodes();

        for (int i = 0; i < elementos.getLength(); i++) {
            if (elementos.item(i).getNodeType() == Node.ELEMENT_NODE) {
                if (elementos.item(i).getNodeName().equalsIgnoreCase(ET_COTA_NIEVE_PROV)) {
                    dia.getCotanieve().add(leerCotaNieve((Element) elementos.item(i)));
                }
            }
        }
        return dia;
    }

    public static CotaNieve leerCotaNieve(Element elemenCota) {
        CotaNieve cotaNieve = new CotaNieve();
        cotaNieve.setPeriodo(elemenCota.getAttribute(ET_PERIODO));
        return cotaNieve;
    }
}
