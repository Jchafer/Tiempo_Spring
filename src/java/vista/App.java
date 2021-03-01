package vista;

import DAO.Conexion_DB;
import controller.PrediccionController;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import model.CotaNieve;
import model.Dia;

/**
 * Proyecto: El Tiempo (Aplicación que muestra el tiempo
 * actualizado en diferentes ciudades en los próximos 7 días. 
 * @author Jesus Chafer 
 * @since 25/11/2020
 * @version 2.0
 */
public class App {

    public static void main(String[] args) throws TransformerException, ParserConfigurationException, ParseException, ClassNotFoundException, SQLException, Exception {

        System.out.println("------------------------------------------------------");
        System.out.println("Origen fichero XML - recuperarXML() - leerDOM()");
        System.out.println("------------------------------------------------------");

        PrediccionController pd = new PrediccionController();
        Dia dia;

        // Obtener ficheros XML
        pd.obtenerXMLOnline();
        File carpeta = new File("FicherosXML");
        File[] ficherosXml = carpeta.listFiles();

        for (File file : ficherosXml) {

            // Añadimos file a PrediccionController          
            pd.setFicXML(file);

            // Creamos estructura DOM a partir del xml
            pd.recuperarXML();

            // Leemos DOM
            pd.leerDOM();

            // Se muestra prediccion
            System.out.println("\n\tDatos del fichero XML -> " + file);
            System.out.println(pd.obtenerCiudad() + "\n");
            System.out.println(pd.getPrediccion() + "\n");

            // Se abre conexión con la base de datos
            Conexion_DB conexion_DB = new Conexion_DB();
            System.out.println("Abrir conexión");
            Connection con = conexion_DB.abrirConexion();

            // Se recorre por cada fichero, los días de su predicción
            for (int i = 0; i < pd.getPrediccion().getDias().size(); i++) {
                dia = pd.getPrediccion().getDias().get(i);

                /*  Se comprueba si ya existe en la base de datos un dia con el mismo 
                    nombre de poblacion y la misma fecha, insertando el dia en el
                    caso de que sea False la condición */
                if (!dia.comprobarDiaBBDD(con, pd.getPrediccion())) {
                    dia.insertarDiaBBDD(con, pd.getPrediccion());

                    // Se recorre por cada día, sus cotas de nieve y se insertan
                    for (CotaNieve cotanieve : dia.getCotanieve()) {
                        cotanieve.insertarCotaNieveBBDD(con, pd.getPrediccion(), dia);
                    }
                }
            }

            // Se cierra conexión con la base de datos
            System.out.println("Cerrar conexion");
            conexion_DB.cerrarConexion(con);
            System.out.println("Conexion cerrada");

        }

    }

}
