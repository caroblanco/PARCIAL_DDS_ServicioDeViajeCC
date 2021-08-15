package clases;

import clases.services.api.entities.VueloApi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class aerolinea {

    List<vuelo> vuelos = new ArrayList<>();
    static List<pasajero> pasajeros = new ArrayList<>();
    private static aerolinea aerolinea = null;

    private aerolinea() {
    }

    public static aerolinea getInstance() {
        if (aerolinea == null)
            aerolinea = new aerolinea();
        return aerolinea;
    }

    public List<vuelo> buscarVuelos(String destinoInicial, String destinoFinal) {
        return (List<vuelo>) vuelos.stream().filter(unVuelo -> unVuelo.cumpleDestinos(destinoInicial, destinoFinal));
    }

    public vuelo buscarVueloPorID(String idVuelo) {
        return (vuelo) vuelos.stream().filter(unV -> unV.getIdVuelo() == idVuelo);
    }

    public void agregarPasajero(pasajero pasajero) {
        pasajeros.add(pasajero);
    }

    public static List<pasajero> buscarPasajeroPorItinerario(int numItinerario) {
        return (List<pasajero>) pasajeros.stream().filter(unP -> unP.tieneItinerario(numItinerario));
    }

    public boolean existePasajero(String documento){
        return pasajeros.stream().anyMatch(unP -> unP.getDocumento().equalsIgnoreCase(documento));
    }

    public boolean existeVuelo(String idVuelo){
        return vuelos.stream().anyMatch(unP -> unP.getIdVuelo().equalsIgnoreCase(idVuelo));
    }

    public void agregarItinerarioAPasajero(VueloApi unVuelo, String documento, int tarifa){
        vuelo vueloNuestro;
        String id = unVuelo.flight.number;

        if (aerolinea.existeVuelo(id)){
            vueloNuestro = sistema.buscarVueloPorID(id);
        }
        else{
            vueloNuestro = aerolinea.crearVuelo(unVuelo, tarifa);
        }
        //TODO QUEDAMOS ACA REYNASSSSSSS, FALTA CREAR EL ITINERARIO CON EL WELO :D

        pasajero pasajero = aerolinea.buscarPasajeroPorDocumento(documento);
    }

    public vuelo crearVuelo(VueloApi vuelo, int tarifa){
        String id = vuelo.flight.number;
        String origen = vuelo.getOrigen();
        String destino = vuelo.getDestino();
        String fecha = vuelo.getFlight_date();
        String horario = vuelo.getTime();
        int delay = vuelo.getDelay();
        vuelo vueloCreado = new vuelo(id, 180, origen,destino, tarifa, fecha, horario, delay);
        return vueloCreado;
    }

    public pasajero buscarPasajeroPorDocumento(String documento){
        return pasajeros.stream().filter(unP -> unP.getDocumento().equalsIgnoreCase(documento)).collect(Collectors.toList()).get(0);
    }
}


