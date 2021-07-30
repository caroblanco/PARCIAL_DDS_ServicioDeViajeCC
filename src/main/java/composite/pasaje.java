package composite;
import clases.clase;
import clases.pasajero;
import clases.vuelo;
import clases.asiento;

public class pasaje extends itinerario {
    vuelo vuelo;
    clase clase;
    public int tarifa;
    asiento asiento;

    public int getTarifa() {
        return tarifa;
    }

    @Override
    public int tarifaTotal(){
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

}