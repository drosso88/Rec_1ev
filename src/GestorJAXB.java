
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
            System.out.println(miFile.getAbsolutePath());

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

    public String sustituirCampos(String contenidoAntiguo, String contenidoNuevo,
            String comprobarIdFactura) {

        String cadena_resultado = "";
        //el identificador que nunca se va a repetir es factura id, que me seleccionara un determinado perro. 
        //Yo quiero dar opcion a rectificar el nombre del perro y los datos del propietario

        List<ClinicaVeterinaria.Perro> iPerros = miClinica.getPerro();
        for (int i = 0; i < iPerros.size(); i++) {
            System.out.println(iPerros.get(i).getConsulta().getFacturaId());
            System.out.println(comprobarIdFactura + "<=====");
            System.out.println(iPerros.get(i).getNombre());
            System.out.println(contenidoAntiguo);
            System.out.println(contenidoNuevo);
            System.out.println(iPerros.get(i).getConsulta().getFacturaId().equals(comprobarIdFactura));
            System.out.println((iPerros.get(i).getConsulta().getFacturaId()+"//"+comprobarIdFactura));
            System.out.println(iPerros.get(i).getNombre().equals(contenidoAntiguo));
            System.out.println(iPerros.get(i).getConsulta().getFacturaId().equals(comprobarIdFactura) && iPerros.get(i).getNombre().equals(contenidoAntiguo));
            if (!iPerros.get(i).getConsulta().getFacturaId().equals(comprobarIdFactura) && iPerros.get(i).getNombre().equals(contenidoAntiguo)) {
                System.out.println("hola que ase");
                iPerros.get(i).setNombre(contenidoNuevo);

            }
//                    iCoche.get(i).setMatricula(contenidoNuevo);)
//                } else if (cocheAuxiliar.getFechaEntrada().equals(contenidoAntiguo) && propietarioAuxiliar.getDni().equals(dniComprobar)) {
//                    iCoche.get(i).setFechaEntrada(contenidoNuevo);
//                } else if (cocheAuxiliar.getMarca().equals(contenidoAntiguo) && propietarioAuxiliar.getDni().equals(dniComprobar)) {
//                    iCoche.get(i).setMarca(contenidoNuevo);
//                } else if (cocheAuxiliar.getPrecio().equals(contenidoAntiguo) && propietarioAuxiliar.getDni().equals(dniComprobar)) {
//                    iCoche.get(i).setPrecio(contenidoNuevo);
//                } else if (propietarioAuxiliar.getNombre().equals(contenidoAntiguo) && propietarioAuxiliar.getDni().equals(dniComprobar)) {
//                    iPropietario.get(j).setNombre(contenidoNuevo);
//                } else if (propietarioAuxiliar.getDni().equals(contenidoAntiguo) && propietarioAuxiliar.getDni().equals(dniComprobar)) {
//                    iPropietario.get(j).setDni(contenidoNuevo);
//                } else if (propietarioAuxiliar.getPrimerApellido().equals(contenidoAntiguo) && propietarioAuxiliar.getDni().equals(dniComprobar)) {
//                    iPropietario.get(j).setPrimerApellido(contenidoNuevo);
//                } else if (propietarioAuxiliar.getSegundoApellido().equals(contenidoAntiguo) && propietarioAuxiliar.getDni().equals(dniComprobar)) {
//                    iPropietario.get(j).setSegundoApellido(contenidoNuevo);
//                }
//
        }

//        for (int i = 0; i < iCoche.size(); i++) {
//            ClinicaVeterinaria.Coche cocheAuxiliar = iCoche.get(i);
//            List<ClinicaVeterinaria.Coche.Propietario> iPropietario = iCoche.get(i).getPropietario();
//            ClinicaVeterinaria.Coche.Asunto iAsunto = iCoche.get(i).getAsunto();
//
//            cadena_resultado = cadena_resultado + "\nCliente nuevo\n" + "\nVehiculo: " + cocheAuxiliar.getMatricula();
//            cadena_resultado = cadena_resultado + "\nfecha de la entrada: " + cocheAuxiliar.getFechaEntrada();
//            cadena_resultado = cadena_resultado + "\nLa marca del vehiculo es: " + cocheAuxiliar.getMarca();
//            for (int j = 0; j < iPropietario.size(); j++) {
//                cadena_resultado = cadena_resultado + "\nPropietario: \n" + "\nDNI: " + iPropietario.get(j).getDni();
//                cadena_resultado = cadena_resultado + "\nNombre: " + iPropietario.get(j).getNombre();
//                cadena_resultado = cadena_resultado + "\nPrimer Apellido: " + iPropietario.get(j).getPrimerApellido();
//                cadena_resultado = cadena_resultado + "\nSegundo Apellido: " + iPropietario.get(j).getSegundoApellido();
//            }
//            cadena_resultado = cadena_resultado + "Asunto: \n" + "\nMecanico: " + iAsunto.getMecanico().getValue();
//            cadena_resultado = cadena_resultado + "\nCon el Id: " + iAsunto.getMecanico().getId();
//            cadena_resultado = cadena_resultado + "\nDescripcion: " + iAsunto.getDescripcion();
//            cadena_resultado=cadena_resultado+ "\nPrecio"+cocheAuxiliar.getPrecio();
//            cadena_resultado = cadena_resultado + "\nFecha de recogida de vehiculo: " + cocheAuxiliar.getFechaSalida()
//                    + "\n--|--|--|--|--|--|--|--|--|--|--|--|--|--|--|--|--|--|--|--|--|--|--\n";
//        }
//        System.out.println(cadena_resultado);
//        return cadena_resultado;
        return "chachi piruli";
    }

}
