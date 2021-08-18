package clases.composite;

import clases.asiento;
import clases.clase;
import clases.vuelo;

public class pasaje extends itinerario {
    clases.vuelo vuelo;
    public double tarifa;
    clases.asiento asiento;
    String origenInicial;
    String destinoFinal;

    public pasaje(vuelo vuelo, double tarifa, asiento asiento, clases.pasajero pasajero) {
        this.vuelo = vuelo;
        this.tarifa = tarifa;
        this.asiento = asiento;
        this.origenInicial = vuelo.getOrigen();
        this.destinoFinal = vuelo.getDestino();
        //TODO this.numItinerario= random
        this.pasajero = pasajero;
    }

    public double getTarifa() {
        return tarifa;
    }

    @Override
    public double tarifaTotal(){
        return tarifa;
    }

    @Override
    public void cambiarAsiento(String idVuelo, int nuevoAsiento){
        asiento.desocuparAsiento();
        asiento = vuelo.buscarAsiento(nuevoAsiento);
        asiento.ocuparAsientoPor(pasajero);
    }

    @Override
    public pasaje buscarPasaje(String idVuelo) {
        return this;
    }

    @Override
    public void liberarAsiento() {

        asiento.desocuparAsiento();
        vuelo.asientoCancelado();
    }

    public String getIdVuelo(){
        return vuelo.getIdVuelo();
    }

    @Override
    public vuelo getVuelo(String idVuelo){ return vuelo; }

}