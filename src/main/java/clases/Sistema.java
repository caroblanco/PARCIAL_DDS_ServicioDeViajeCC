package clases;

import clases.services.api.entities.VueloApi;
import strategy.notificarStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Sistema {

    //static List<usuario> usuarios = new ArrayList<>();
    static aerolinea aerolinea;
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
        return aerolinea.buscarVuelos(destinoInicial,destinoFinal);
    }

    public static vuelo buscarVueloPorID(String idVuelo){
        return aerolinea.buscarVueloPorID(idVuelo);
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
        return aerolinea.buscarPasajeroPorItinerario(numItinerario);
   }

    public static void modificarAsiento(int numItinerario, String idVuelo, int nuevoAsiento) {

        List<pasajero> pasajero = buscarPasajeroPorItinerario(numItinerario);

        pasajero.get(0).modificarAsiento(numItinerario, idVuelo, nuevoAsiento);
    }

    public static boolean existePasajero(String documento){
        return aerolinea.existePasajero(documento);
    }

    public static boolean hayPasajeros(){
        return aerolinea.hayPasajeros();
    }

    public static vuelo crearNuestroVuelo(VueloApi vuelo,int tarifa, pasajero pasajero){

        return aerolinea.crearNuestroVuelo(vuelo,tarifa,pasajero);
    }

    public static pasajero buscarPasajeroPorDocumento(String documento){
        return aerolinea.buscarPasajeroPorDocumento(documento);
    }

    public static pasajero crearPasajero(String documento, String nombre, String telefono, usuario unUsuario, notificarStrategy formaNotif){
        return aerolinea.crearPasajero(nombre, telefono, documento, formaNotif, unUsuario);
    }

    public static boolean asientoLibre(String idVuelo,int nuevoAsiento){
        return aerolinea.asientoLibre(idVuelo, nuevoAsiento);
    }

    public static usuario crearUsuario(String nombreUsuario, String contrasenia, String email){

        usuarioDAO usuarioDAO = new usuarioDAO();
        int id = usuarioDAO.insert(nombreUsuario,contrasenia,email);

        usuario unUsuario = new usuario(nombreUsuario, contrasenia, email,id);

        return unUsuario;
    }

}