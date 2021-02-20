/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import java.io.File;
import java.io.FileOutputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 *
 * @author rocio
 */
public class GestorDOM {

    static Document doc;

    public static Document getDoc() {
        return doc;
    }

    public static void setDoc(Document doc) {
        GestorDOM.doc = doc;
    }

    public int abrirMiXml(File file) {
        doc = null;
        try {
            //del factory sacamos un nuevo documento
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setIgnoringComments(true);
            factory.setIgnoringElementContentWhitespace(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            System.out.println(file.getName());
            doc = builder.parse(file);
            System.out.println(doc.getElementsByTagName("perro") + "=========");
            return 1;
        } catch (Exception e) {
            return -1;
        }
    }

    public int guardarDomComoXml() {
        try {
            File archivo_xml = new File("perros_definitivo.xml");
            OutputFormat format = new OutputFormat(doc);
            format.setIndenting(true);
            XMLSerializer serializer = new XMLSerializer(new FileOutputStream(archivo_xml), format);
            serializer.serialize(doc);
            return 1;
        } catch (Exception e) {
            return -1;
        }

    }

    void nuevaMascota(String chip, String nombre, String raza, String fecha_nacimiento,
            String dni, String nombredueno, String apellidos, String factura_id, String fechaconsulta, String descripcion) {

        //esto se lee de dentro hacia fuera, por eso entramos directamente al tag
        //nombre 
        Node nNombre = doc.createElement("nombre");
        Node valorNombre = doc.createTextNode(nombre);
        nNombre.appendChild(valorNombre);

        Node nRaza = doc.createElement("raza");
        Node valorRaza = doc.createTextNode(raza);
        nRaza.appendChild(valorRaza);

        Node nFechaNac = doc.createElement("fecha_nacimiento");
        Node valorFechaNac = doc.createTextNode(fecha_nacimiento);
        nFechaNac.appendChild(valorFechaNac);

        Node nPropietario = doc.createElement("propietario");
        ((Element) nPropietario).setAttribute("dni", dni);

        Node nNombreProp = doc.createElement("nombredueno");
        Node valorNombreProp = doc.createTextNode(nombredueno);
        nNombreProp.appendChild(valorNombreProp);

        Node nApellidos = doc.createElement("apellidos");
        Node valorApellidos = doc.createTextNode(apellidos);
        nApellidos.appendChild(valorApellidos);

        Node nConsulta = doc.createElement("consulta");
        ((Element) nConsulta).setAttribute("factura_id", factura_id);

        Node nFechaCon = doc.createElement("fecha_consulta");
        Node valorFechaCon = doc.createTextNode(fechaconsulta);
        nFechaCon.appendChild(valorFechaCon);

        Node nDescrips = doc.createElement("descripcion");
        Node valorDes = doc.createTextNode(descripcion);
        nDescrips.appendChild(valorDes);

        Node nPerro = doc.createElement("perro");
        ((Element) nPerro).setAttribute("chip", chip);

        //vamos a añadir al nodoPerro el contenido
        nPerro.appendChild(nNombre);
        nPerro.appendChild(nRaza);
        nPerro.appendChild(nFechaNac);
        nPerro.appendChild(nPropietario);
        nPerro.appendChild(nNombreProp);
        nPerro.appendChild(nApellidos);
        nPerro.appendChild(nConsulta);
        nPerro.appendChild(nFechaCon);
        nPerro.appendChild(nDescrips);

        Node raiz = doc.getFirstChild();
        raiz.appendChild(nPerro);
    }
}
