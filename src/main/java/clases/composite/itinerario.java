package clases.composite;

import clases.pasajero;
import clases.vuelo;

public abstract class itinerario {
    int numItinerario;
    clases.pasajero pasajero;

    public abstract double tarifaTotal();

    public int getNumItinerario() {
        return numItinerario;
    }

    public abstract void cambiarAsiento(String idVuelo, int nuevoAsiento);

    public abstract pasaje buscarPasaje(String idVuelo);

    public abstract void liberarAsiento();

    public abstract vuelo getVuelo(String idVuelo);

    public pasajero getPasajero(){return pasajero;}

}
