
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author rocio
 */
class ManejadorSAX extends DefaultHandler {

    String salida = "";

    public String getSalida() {
        return salida;
    }

    public void setSalida(String salida) {
        this.salida = salida;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        for (int i = start; i < length + start; i++) {
            salida = salida + ch[i];
        }
        salida = salida.trim() + "\n";
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("perro")) {
            salida = salida + "---------------------------------";
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("perro")) {
            //datos perro
            salida = salida + "El chip del perro es: " + attributes.getValue(attributes.getQName(0).trim());
        } else if (qName.equals("nombre")) {
            salida = salida + "Nombre de la mascota: ".trim();
        } else if (qName.equals("raza")) {
            salida = salida + "Raza: ".trim();
        } else if (qName.equals("fecha_nacimiento")) {
            salida = salida + "Fecha de nacimento: ";
            salida= salida + "\n";

            //datos propietario
        } else if (qName.equals("propietario")) {
            salida = salida + "Propietario con DNI: " + attributes.getValue(attributes.getQName(0).trim());
        } else if (qName.equals("nombredueno")) {
            salida = salida + ("Nombre: ").trim();
        } else if (qName.equals("apellidos")) {
            salida = salida + ("Apellidos: ").trim();
            salida= salida + "\n";

            //datos consulta
        } else if (qName.equals("consulta")) {
            salida = salida + "Nº factura de la consulta: " + attributes.getValue(attributes.getQName(0).trim());
        } else if (qName.equals("fecha_consulta")) {
            salida = salida + "Fecha de la consulta: ";
        } else if (qName.equals("descripcion")) {
            salida = salida + "Motivo de la consulta: ".trim();
            salida= salida + "\n";

        }
    }
}
