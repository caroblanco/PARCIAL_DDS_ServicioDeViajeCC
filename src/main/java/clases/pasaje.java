package clases;

public class pasaje extends itinerario {
    vuelo vuelo;
    clase clase;
    public double tarifa;
    asiento asiento;

    public pasaje(vuelo vuelo, clase clase, double tarifa, asiento asiento,pasajero pasajero) {
        this.vuelo = vuelo;
        this.clase = clase;
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
    }

    public String getIdVuelo(){
        return vuelo.getIdVuelo();
    }

    @Override
    public vuelo getVuelo(String idVuelo){ return vuelo; }

}