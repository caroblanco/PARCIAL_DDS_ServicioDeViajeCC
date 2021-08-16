package clases;

import clases.services.api.entities.VueloApi;
import strategy.notificarStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Sistema {

    static List<usuario> usuarios = new ArrayList<>();
    public static usuario caroUser = new usuario("caro","Emalena1", "caro@hotmail.com");
    static aerolinea aerolinea;
    public static Sistema instancia = null;
    {
        usuarios.add(caroUser);
    }

    public static Sistema getInstancia(){

        if(instancia == null){
            instancia = new Sistema();
        }

        return instancia;

    }

    public static void agregarUsuario(usuario usuarioNuevo){
        usuarios.add(usuarioNuevo);
    }

    public static boolean validarContrasenia(String usuario, String contrasenia){
        return validador.validarContrasenia(usuario, contrasenia);
    }

    public static boolean validarUsuarioRegistrar(String usuario){
        return usuarios.stream().anyMatch(unU -> unU.getUsuario().equalsIgnoreCase(usuario));
    }

    public static List<vuelo> buscarVuelos(String destinoInicial, String destinoFinal){
        return aerolinea.buscarVuelos(destinoInicial,destinoFinal);
    }

    public static vuelo buscarVueloPorID(String idVuelo){
        return aerolinea.buscarVueloPorID(idVuelo);
    }

    public static boolean validarUsuario(String usuario, String contra){

        return usuarios.stream().anyMatch(unU -> unU.getUsuario().equalsIgnoreCase(usuario) && unU.getContrasenia().equalsIgnoreCase(contra));

    }

    public static usuario buscarUsuario(String usuario){
        return usuarios.stream().filter(unU -> unU.getUsuario().equalsIgnoreCase(usuario)).collect(Collectors.toList()).get(0);
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

}