
import java.awt.TextField;
import java.io.File;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import perros_binding.ClinicaVeterinaria;

/**
 *
 * @author rocio
 */
public class GestorJAXB {
    //lista

    ClinicaVeterinaria miClinica;
    Unmarshaller u;
    JAXBContext contexto;

    public int abrirXML_JAXB() {
        try {
            contexto = JAXBContext.newInstance(ClinicaVeterinaria.class);
            u = contexto.createUnmarshaller();

            miClinica = (ClinicaVeterinaria) u.unmarshal(new File("perros_definitivo.xml"));
            File miFile = new File("perros_definitivo.xml");

            return 1;
        } catch (Exception e) {

            return -1;
        }

    }

    public int cerrarXML_JAXB() {
        try {
            Marshaller m = contexto.createMarshaller();
            m.marshal(miClinica, new File("perros_definitivo.xml"));
            return 1;
        } catch (Exception e) {
        }
        return -1;
    }

    public String recorrerJAXB() {
        String cadena_resultado = "";
        List<ClinicaVeterinaria.Perro> iPerro = miClinica.getPerro();

        for (int i = 0; i < iPerro.size(); i++) {
            //como mi xml no tiene solo 1 perro, al sacar la lista, cada perro es auxiliar
            ClinicaVeterinaria.Perro perroAux = iPerro.get(i);
            //List<ClinicaVeterinaria.Coche.Propietario> iPropietario = iCoche.get(i).getPropietario();
            //ClinicaVeterinaria.Perro.

            //datos perro
            cadena_resultado = cadena_resultado + "\n El chip de la mascota es: " + perroAux.getChip();
            cadena_resultado = cadena_resultado + "\n El nombew del perro es: " + perroAux.getNombre();
            cadena_resultado = cadena_resultado + "\n Raza: " + perroAux.getRaza();
            cadena_resultado = cadena_resultado + "\n Fecha de nacimiento: " + perroAux.getFechaNacimiento();

            //datos propietario
            cadena_resultado = cadena_resultado + "\n DNI del propietario: " + perroAux.getPropietario().getDni();
            cadena_resultado = cadena_resultado + "\n Nombre: " + perroAux.getNombredueno();
            cadena_resultado = cadena_resultado + "\n Apellidos: " + perroAux.getApellidos();
            //datos consulta

            cadena_resultado = cadena_resultado + "\n Factura Nº: " + perroAux.getConsulta().getFacturaId();
            cadena_resultado = cadena_resultado + "\n Fecha: " + perroAux.getFechaConsulta();
            cadena_resultado = cadena_resultado + "\n Descripcion: " + perroAux.getDescripcion();
            cadena_resultado = cadena_resultado + "\n-----------------------------\n";
        }
        return cadena_resultado;
    }

    //metodo que nos ayudará a buscar un registro por factura para poder modificar
    public String[] dameDatos(int facturaID) {
        String[] d = new String[4]; //ponemos 4 porque son los registros antiguos ya creados
        List<ClinicaVeterinaria.Perro> iPerros = miClinica.getPerro();
        for (int i = 0; i < iPerros.size(); i++) {
            if (facturaID == iPerros.get(i).getConsulta().getFacturaId()) {
                d[0] = iPerros.get(i).getNombre();
                d[1] = iPerros.get(i).getPropietario().getDni();
                d[2] = iPerros.get(i).getNombredueno();
                d[3] = iPerros.get(i).getApellidos();
            }
        }
        return d;
    }

    void modificaDatos(String[] informacionNueva, int facturaID) {
        List<ClinicaVeterinaria.Perro> iPerros = miClinica.getPerro();
        for (int i = 0; i < iPerros.size(); i++) {
            if (facturaID == iPerros.get(i).getConsulta().getFacturaId()) {
                iPerros.get(i).setNombre(informacionNueva[0]);
                iPerros.get(i).getPropietario().setDni(informacionNueva[1]);
                iPerros.get(i).setNombredueno(informacionNueva[2]);
                iPerros.get(i).setApellidos(informacionNueva[3]);

            }
        }
    }
}
