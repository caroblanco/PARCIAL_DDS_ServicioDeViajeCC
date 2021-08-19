package clases;

import clases.BD.usuarioDAO;
import clases.BD.usuariosDAO;
import clases.Security.validador;
import clases.services.api.entities.VueloApi;
import strategy.notificarStrategy;

import java.util.List;

public class Sistema {

    //static List<usuario> usuarios = new ArrayList<>();
    static agencia agencia;
    public static Sistema instancia = null;


    public static Sistema getInstancia(){

        if(instancia == null){
            instancia = new Sistema();
        }

        return instancia;

    }

    public static boolean validarContrasenia(String usuario, String contrasenia){
        return validador.validarContrasenia(usuario, contrasenia);
    }

    public static boolean validarUsuarioRegistrar(String usuario){

        usuariosDAO usuariosDAO = new usuariosDAO();
        List<String> nombresUsuarios = usuariosDAO.dameTodosLosNombresDeUsuarios();

        return nombresUsuarios.stream().anyMatch(unNombre -> unNombre.equalsIgnoreCase(usuario));
    }

    public static List<vuelo> buscarVuelos(String destinoInicial, String destinoFinal){
        return agencia.buscarVuelos(destinoInicial,destinoFinal);
    }

    public static vuelo buscarVueloPorID(String idVuelo){
        return agencia.buscarVueloPorID(idVuelo);
    }

    public static boolean validarUsuario(String usuario, String contra){

        usuario unUsuario = Sistema.buscarUsuario(usuario);

        return unUsuario.validarContrasenia(contra);

    }

    public static usuario buscarUsuario(String usuario){

        usuarioDAO usuarioDAO = new usuarioDAO();

        return usuarioDAO.buscarUsuario(usuario);
    }

    public static void cancelarItinerario(int numItinerario) {

        List<pasajero> pasajero = buscarPasajeroPorItinerario(numItinerario);

        pasajero.get(0).cancelarItinerario(numItinerario);
    }

   public static List<pasajero> buscarPasajeroPorItinerario(int numItinerario){
        return agencia.buscarPasajeroPorItinerario(numItinerario);
   }

    public static void modificarAsiento(int numItinerario, String idVuelo, int nuevoAsiento) {

        List<pasajero> pasajero = buscarPasajeroPorItinerario(numItinerario);

        pasajero.get(0).modificarAsiento(numItinerario, idVuelo, nuevoAsiento);
    }

    public static boolean existePasajero(String documento){
        return agencia.existePasajero(documento);
    }

    public static boolean hayPasajeros(){
        return agencia.hayPasajeros();
    }

    public static vuelo crearNuestroVuelo(VueloApi vuelo, pasajero pasajero){

        return agencia.crearNuestroVuelo(vuelo,pasajero);
    }

    public static pasajero buscarPasajeroPorDocumento(String documento){
        return agencia.buscarPasajeroPorDocumento(documento);
    }

    public static pasajero crearPasajero(String documento, String nombre, String telefono, usuario unUsuario, notificarStrategy formaNotif){
        return agencia.crearPasajero(nombre, telefono, documento, formaNotif, unUsuario);
    }

    public static boolean asientoLibre(String idVuelo,int nuevoAsiento){
        return agencia.asientoLibre(idVuelo, nuevoAsiento);
    }

    public static usuario crearUsuario(String nombreUsuario, String contrasenia, String email){

        usuarioDAO usuarioDAO = new usuarioDAO();
        int id = usuarioDAO.insert(nombreUsuario,contrasenia,email);

        usuario unUsuario = new usuario(nombreUsuario, contrasenia, email,id);

        return unUsuario;
    }

}