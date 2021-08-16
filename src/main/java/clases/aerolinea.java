package clases;

import clases.services.api.entities.VueloApi;
import strategy.notificarStrategy;
import strategy.sms;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static clases.Sistema.caroUser;


public class aerolinea {

    List<vuelo> vuelos = new ArrayList<>();
    static List<pasajero> pasajeros = new ArrayList<>();
    pasajero caro = new pasajero("CAROLINA bLANCO", "1540282287", "42878204", new sms(), caroUser);
    private static aerolinea aerolinea = null;

    {
        pasajeros.add(caro);
    }

    private aerolinea() {
    }

    public static aerolinea getInstance() {
        if (aerolinea == null)
            aerolinea = new aerolinea();
        return aerolinea;
    }

    public List<vuelo> buscarVuelos(String destinoInicial, String destinoFinal) {
        return vuelos.stream().filter(unVuelo -> unVuelo.cumpleDestinos(destinoInicial, destinoFinal)).collect(Collectors.toList());
    }

    public vuelo buscarVueloPorID(String idVuelo) {
        return vuelos.stream().filter(unV -> unV.getIdVuelo().equalsIgnoreCase(idVuelo)).collect(Collectors.toList()).get(0);
    }

    public void agregarPasajero(pasajero pasajero) {
        pasajeros.add(pasajero);
    }

    public static List<pasajero> buscarPasajeroPorItinerario(int numItinerario) {
        return pasajeros.stream().filter(unP -> unP.tieneItinerario(numItinerario)).collect(Collectors.toList());
    }

    public boolean existePasajero(String documento){
        return pasajeros.stream().anyMatch(unP -> unP.getDocumento().equalsIgnoreCase(documento));
    }

    public boolean hayPasajeros(){return !pasajeros.isEmpty();}

    public boolean existeVuelo(String idVuelo){
        return vuelos.stream().anyMatch(unP -> unP.getIdVuelo().equalsIgnoreCase(idVuelo));
    }

    public vuelo crearNuestroVuelo(VueloApi unVuelo, int tarifa, pasajero pasajero){
        vuelo vueloNuestro;
        String id = unVuelo.flight.number;

        if (aerolinea.existeVuelo(id)){
            vueloNuestro = Sistema.buscarVueloPorID(id);
        }
        else{
            vueloNuestro = aerolinea.crearVuelo(unVuelo, tarifa);
            vuelos.add(vueloNuestro);
        }

        return vueloNuestro;
    }

    public pasajero crearPasajero(String nombre, String telefono, String documento, notificarStrategy formaNotif, usuario unUsuario){
        pasajero pasajero = new pasajero(nombre, telefono, documento, formaNotif, unUsuario);
        pasajeros.add(pasajero);
        return pasajero;
    }

    public vuelo crearVuelo(VueloApi vuelo, int tarifa){
        String id = vuelo.flight.number;
        String origen = vuelo.getOrigen();
        String destino = vuelo.getDestino();
        String fecha = vuelo.getFlight_date();
        String horario = vuelo.getTime();
        int delay = vuelo.getDelay();
        return new vuelo(id, 180, origen,destino, tarifa, fecha, horario, delay);
    }

    public pasajero buscarPasajeroPorDocumento(String documento){
        return pasajeros.stream().filter(unP -> unP.getDocumento().equalsIgnoreCase(documento)).collect(Collectors.toList()).get(0);
    }

    public static boolean asientoLibre(String idVuelo,int nuevoAsiento){
        return aerolinea.buscarVueloPorID(idVuelo).asientoLibre(nuevoAsiento);
    }
}


