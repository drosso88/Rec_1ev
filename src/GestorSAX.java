import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 *
 * @author rocio
 */
public class GestorSAX {
    SAXParser parser;
    ManejadorSAX sh;
    File miXML;

    public int abrirMiSAX(File fichero) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            parser = factory.newSAXParser();
            sh = new ManejadorSAX();
            miXML = fichero;

            return 1;
        } catch (Exception e) {
            return -1;
        }
    }

    public String recorreSAX() {
        try {
            sh.salida="";
            parser.parse(miXML,sh);
            return sh.salida;
        } catch (Exception e) {
            return "No se ha podido recorrer SAX, intentalo mas tarde";
        }
    }

   
    }
    

