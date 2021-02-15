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
    public int crearNuevoRegistro(String matricula, String fecha_entrada, String marca,
        String propietarioId,String nombre, String primerApellido, String segundoApellido,
        String idMecanico,String nombreApellidosMecanico,String descripcion,String precio,
        String fecha_salida) {
            try {
                Node nfecha_entrada=doc.createElement("fecha_entrada");
                Node valorFecha_entrada=doc.createTextNode(fecha_entrada);
                nfecha_entrada.appendChild(valorFecha_entrada);
                
                Node nmarca=doc.createElement("marca");
                Node valorMarca=doc.createTextNode(marca);
                nmarca.appendChild(valorMarca);
                
                Node npropietario=doc.createElement("propietario");
                
                Node nnombre=doc.createElement("nombre");
                Node valorNombre=doc.createTextNode(nombre);
                nnombre.appendChild(valorNombre);
                
                Node nprimerApellido=doc.createElement("primerApellido");
                Node valorPrimerApellido=doc.createTextNode(primerApellido);
                nprimerApellido.appendChild(valorPrimerApellido);
                
                Node nsegundoApellido=doc.createElement("segundoApellido");
                Node valorSegundoApellido=doc.createTextNode(segundoApellido);
                nsegundoApellido.appendChild(valorSegundoApellido);
                
                ((Element)npropietario).setAttribute("dni",propietarioId);
                npropietario.appendChild(nnombre);
                npropietario.appendChild(nprimerApellido);
                npropietario.appendChild(nsegundoApellido);
                
                Node nasunto=doc.createElement("asunto");
                ((Element)nasunto).setAttribute("id", idMecanico);
                
                Node nmecanico=doc.createElement("mecanico");
                Node valorMecanico=doc.createTextNode(nombreApellidosMecanico);
                nmecanico.appendChild(valorMecanico);
                
                Node ndescripcion=doc.createElement("descripcion");
                Node valorDescripcion=doc.createTextNode(descripcion);
                ndescripcion.appendChild(valorDescripcion);
                
                nasunto.appendChild(nmecanico);
                nasunto.appendChild(ndescripcion);
                
                Node nprecio=doc.createElement("precio");
                Node valorPrecio=doc.createTextNode(precio);
                nprecio.appendChild(valorPrecio);
                
                Node nfecha_salida=doc.createElement("fecha_salida");
                Node valorFechaSalida=doc.createTextNode(fecha_salida);
                nfecha_salida.appendChild(valorFechaSalida);
                
                //añado al nodo coche los demas nodos
                Node nCoche=doc.createElement("coche");
                ((Element)nCoche).setAttribute("matricula", matricula);
                
                nCoche.appendChild(nfecha_entrada);
                nCoche.appendChild(nmarca);
                nCoche.appendChild(npropietario);
                nCoche.appendChild(nasunto);
                nCoche.appendChild(nprecio);
                nCoche.appendChild(nfecha_salida);
                
                Node raiz=doc.getFirstChild();
                raiz.appendChild(nCoche);
               
                return 1; 
            } catch (Exception e) {
                return -1;
            }
           
        }
    public int guardarDomComoXml(){
            try {
                File archivo_xml=new File("perros_definitivo.xml");
                OutputFormat format=new OutputFormat(doc);
                format.setIndenting(true);
                XMLSerializer serializer=new XMLSerializer(new FileOutputStream(archivo_xml),format);
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
        Node nNombre=doc.createElement("nombre");
        Node valorNombre=doc.createTextNode(nombre);
        nNombre.appendChild(valorNombre);
        
         Node nRaza=doc.createElement("raza");
        Node valorRaza=doc.createTextNode(raza);
        nNombre.appendChild(valorRaza);
        
        Node nFechaNac=doc.createElement("fecha_nacimiento");
        Node valorFechaNac=doc.createTextNode(fecha_nacimiento);
        nNombre.appendChild(valorFechaNac);
        
        Node nPropietario=doc.createElement("propietario");
        ((Element)nPropietario).setAttribute("dni",dni);
        
        Node nNombreProp=doc.createElement("nombredueno");
        Node valorNombreProp=doc.createTextNode(nombredueno);
        nNombreProp.appendChild(valorNombreProp);
        
        Node nApellidos=doc.createElement("apellidos");
        Node valorApellidos=doc.createTextNode(apellidos);
        nApellidos.appendChild(valorApellidos);
        
         Node nConsulta=doc.createElement("consulta");
        ((Element)nConsulta).setAttribute("factura_id",factura_id);
        
        Node nFechaCon=doc.createElement("fecha_consulta");
        Node valorFechaCon=doc.createTextNode(fechaconsulta);
        nFechaCon.appendChild(valorFechaCon);
        
        Node nDescrips=doc.createElement("descripcion");
        Node valorDes=doc.createTextNode(descripcion);
        nDescrips.appendChild(valorDes);
        
        Node nPerro=doc.createElement("perro");
        ((Element)nPerro).setAttribute("chip",chip);
        
        //vamos a añadir al nodoPerro el contenido
        
        nPerro.appendChild(nNombre);
        nPerro.appendChild(nRaza);
        nPerro.appendChild(nFechaNac);
        nPerro.appendChild(nNombreProp);
        nPerro.appendChild(nApellidos);
        nPerro.appendChild(nFechaCon);
        nPerro.appendChild(nDescrips);
        
        Node raiz=doc.getFirstChild();
        raiz.appendChild(nPerro);
    }  
}
