package clases;

import clases.services.api.entities.VueloApi;
import strategy.notificarStrategy;
import strategy.sms;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class agencia {

    List<vuelo> vuelos = new ArrayList<>();
    static List<pasajero> pasajeros = new ArrayList<>();
    private static agencia agencia = null;

    private agencia() {
    }

    public static agencia getInstance() {
        if (agencia == null)
            agencia = new agencia();
        return agencia;
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
        return pasajeros.stream().filter(unP -> unP.existeItinerario(numItinerario)).collect(Collectors.toList());
    }

    public boolean existePasajero(String documento){
        return pasajeros.stream().anyMatch(unP -> unP.getDocumento().equalsIgnoreCase(documento));
    }

    public boolean hayPasajeros(){return !pasajeros.isEmpty();}

    public boolean existeVuelo(String idVuelo){
        return vuelos.stream().anyMatch(unP -> unP.getIdVuelo().equalsIgnoreCase(idVuelo));
    }

    public vuelo crearNuestroVuelo(VueloApi unVuelo, pasajero pasajero){
        vuelo vueloNuestro;
        String id = unVuelo.flight.number;

        if (agencia.existeVuelo(id)){
            vueloNuestro = Sistema.buscarVueloPorID(id);
        }
        else{
            vueloNuestro = agencia.crearVuelo(unVuelo);
            vuelos.add(vueloNuestro);
        }

        return vueloNuestro;
    }

    public pasajero crearPasajero(String nombre, String telefono, String documento, notificarStrategy formaNotif, usuario unUsuario){
        pasajero pasajero = new pasajero(nombre, telefono, documento, formaNotif, unUsuario);
        pasajeros.add(pasajero);
        return pasajero;
    }

    public vuelo crearVuelo(VueloApi vuelo){
        String id = vuelo.flight.number;
        String origen = vuelo.getOrigen();
        String destino = vuelo.getDestino();
        String fecha = vuelo.getFlight_date();
        String horario = vuelo.getTime();
        int delay = vuelo.getDelay();
        return new vuelo(id, 180, origen,destino, fecha, horario, delay);
    }

    public pasajero buscarPasajeroPorDocumento(String documento){
        return pasajeros.stream().filter(unP -> unP.getDocumento().equalsIgnoreCase(documento)).collect(Collectors.toList()).get(0);
    }

    public static boolean asientoLibre(String idVuelo,int nuevoAsiento){
        return agencia.buscarVueloPorID(idVuelo).asientoLibre(nuevoAsiento);
    }
}


