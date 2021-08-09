package clases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

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


}


