package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import model.Prediccion;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import vista.App;

/**
 * Proyecto: El Tiempo (Aplicación que muestra el tiempo
 * actualizado en diferentes ciudades en los próximos 7 días. 
 * @author Jesus Chafer 
 * @since 25/11/2020
 * @version 2.0
 */
public class PrediccionController {

    static final String ET_ROOT = "root";
    static final String ET_NOMBRE = "nombre";
    static final String ET_ORIGEN = "origen";
    static final String ET_PREDICCION = "prediccion";

    private File ficXML = null;
    private Prediccion prediccion = null;
    private Document doc = null;

    public PrediccionController() {
        this.prediccion = new Prediccion();
    }

    public PrediccionController(File fichXML) {
        this.ficXML = fichXML;
        this.prediccion = new Prediccion();
    }

    public PrediccionController(Prediccion prediccion, File fichXML) {
        this.prediccion = prediccion;
        this.ficXML = fichXML;
    }

    public Prediccion getPrediccion() {
        return prediccion;
    }

    public void setFicXML(File ficXML) {
        this.ficXML = ficXML;
    }

    public Document recuperarXML() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            this.doc = builder.parse(this.ficXML);
            doc.normalize();
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.doc;
    }

    public void leerDOM() throws ParseException {
        Element prediccion = this.doc.getDocumentElement();

        NodeList elementos = prediccion.getChildNodes();
        this.prediccion.getDias().clear();

        for (int i = 0; i < elementos.getLength(); i++) {
            if (elementos.item(i).getNodeType() == Node.ELEMENT_NODE) {
                if (elementos.item(i).getNodeName().equalsIgnoreCase(ET_NOMBRE)) {
                    this.prediccion.setNombre(elementos.item(i).getTextContent());
                } else if (elementos.item(i).getNodeName().equalsIgnoreCase(ET_ORIGEN)) {
                    this.prediccion.setWeb((DiaController.leerWeb((Element) elementos.item(i))).getTextContent());
                } else if (elementos.item(i).getNodeName().equalsIgnoreCase(ET_PREDICCION)) {
                    this.prediccion.setDias((DiaController.leerDias((Element) elementos.item(i))));
                }
            }
        }
    }

    public String obtenerCiudad() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException{
       
       Document doc = (Document) DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(ficXML);
       
       XPathFactory xpf = XPathFactory.newInstance();
       XPath xpath = xpf.newXPath();
       
       Node ciudad = (Node)xpath.evaluate("/root/nombre",doc,XPathConstants.NODE);
       Node provincia = (Node)xpath.evaluate("/root/provincia",doc,XPathConstants.NODE);       
       
       return "\tCiudad -> " + ciudad.getTextContent() + " (" + provincia.getTextContent() + ")";
        
    }
    
    public void obtenerXMLOnline() {
        ArrayList<URL> direcciones = new ArrayList<>();
        File carpetaFicheros = new File("FicherosXML");
        if (!carpetaFicheros.exists()) {
            carpetaFicheros.mkdir();
        }
        try {
            // Url de la población
            URL urlXativa = new URL("http://www.aemet.es/xml/municipios/localidad_46145.xml");
            direcciones.add(urlXativa);

            URL urlBeniganim = new URL("http://www.aemet.es/xml/municipios/localidad_46062.xml");
            direcciones.add(urlBeniganim);

            URL urlGenoves = new URL("http://www.aemet.es/xml/municipios/localidad_46132.xml");
            direcciones.add(urlGenoves);

            URL urlCarcaixent = new URL("http://www.aemet.es/xml/municipios/localidad_46083.xml");
            direcciones.add(urlCarcaixent);

            URL urlAlzira = new URL("http://www.aemet.es/xml/municipios/localidad_46017.xml");
            direcciones.add(urlAlzira);

            for (URL direccion : direcciones) {
                // Establecemos conexion
                URLConnection urlCon = direccion.openConnection();

                FileOutputStream fos;
                try ( // Se obtiene el inputStream del xml y se abre el fichero local
                        InputStream is = urlCon.getInputStream()) {
                    fos = new FileOutputStream("FicherosXML/" + direccion.getPath().substring(direccion.getPath().length() - 9));
                    // Lectura del xml de la web y escritura en fichero local
                    byte[] array = new byte[1000]; // buffer temporal de lectura.
                    int leido = is.read(array);
                    while (leido > 0) {
                        fos.write(array, 0, leido);
                        leido = is.read(array);
                    }
                    // cierre de conexion y fichero.
                }
                fos.close();
            }
        } catch (IOException e) {
        }
    }
}
