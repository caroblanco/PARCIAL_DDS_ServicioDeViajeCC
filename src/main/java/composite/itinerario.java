package composite;

import clases.pasajero;
import clases.vuelo;

public abstract class itinerario {
    int numItinerario;
    String origenInicial;
    String destinoFinal;
    pasajero pasajero;

    public abstract int tarifaTotal();

    public int getNumItinerario() {
        return numItinerario;
    }

    public abstract void cambiarAsiento(String idVuelo, int nuevoAsiento);

    public abstract pasaje buscarPasaje(String idVuelo);

    public abstract void liberarAsiento();

    public abstract vuelo getVuelo(String idVuelo);

}
