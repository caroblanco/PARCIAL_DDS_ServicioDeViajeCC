package clases.composite;

import clases.pasajero;
import clases.vuelo;
import java.util.Random;

import java.util.List;

public abstract class itinerario {
    int numItinerario;
    //pasajero pasajero;
    String docPasajero;

    public itinerario(){
        Random rand = new Random();
        this.numItinerario = rand.nextInt(1000000000);
    }

    public abstract double tarifaTotal();

    public int getNumItinerario() {
        return numItinerario;
    }

    public abstract void cambiarAsiento(String idVuelo, int nuevoAsiento);

    public abstract itinerario buscarPasaje(String idVuelo);

    public abstract void liberarAsiento();

    public abstract vuelo getVuelo(String idVuelo);

    public String getDocPasajero(){return docPasajero;}

    public abstract List<String> getListaIdVuelo();

}
