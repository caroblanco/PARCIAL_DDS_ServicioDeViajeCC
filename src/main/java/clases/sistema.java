package clases;

import clases.services.api.entities.VueloApi;

import java.util.ArrayList;
import java.util.List;

public class sistema {

    static List<usuario> usuarios = new ArrayList<>();
    static aerolinea aerolinea;

    public sistema() {
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

        boolean encontre= usuarios.stream().anyMatch(unU -> unU.getUsuario() == usuario && unU.getContrasenia() == contra);

        return encontre;
    }

    public static usuario buscarUsuario(String usuario){
        return (usuario) usuarios.stream().filter(unU -> unU.getUsuario() == usuario);
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

    public static void agregarItinerarioAPasajero(VueloApi vuelo, String documento, int tarifa){

        aerolinea.agregarItinerarioAPasajero(vuelo,documento, tarifa);
    }

}