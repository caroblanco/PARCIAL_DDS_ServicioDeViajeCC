package clases.composite;
import clases.vuelo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class pasajeCompuesto extends itinerario {
    List<itinerario> itinerarios = new ArrayList<>();

    public pasajeCompuesto(clases.pasajero pasajero) {
        super();
    }

    @Override
    public double tarifaTotal() {
        return itinerarios.stream().mapToDouble(unI -> unI.tarifaTotal()).sum()*0.95;
    }

    @Override
    public void cambiarAsiento(String idVuelo, int nuevoAsiento) {
        itinerario itinerario = this.buscarPasaje(idVuelo);
        itinerario.cambiarAsiento(idVuelo, nuevoAsiento);
    }

    @Override
    public itinerario buscarPasaje(String idVuelo){
        return itinerarios.stream().filter(unI -> unI.getListaIdVuelo().stream().anyMatch(unS -> unS.equalsIgnoreCase(idVuelo))).collect(Collectors.toList()).get(0);
    }

    @Override
    public void liberarAsiento() {
        itinerarios.forEach(unP -> unP.liberarAsiento());
    }

    @Override
    public vuelo getVuelo(String idVuelo) {
        itinerario itinerario = this.buscarPasaje(idVuelo);
        return itinerario.getVuelo(idVuelo);
    }

    public List<String> getListaIdVuelo(){
        return itinerarios.stream().map(unI -> unI.getListaIdVuelo().get(0)).collect(Collectors.toList());

    }

    public void add (itinerario unItinerario){
        itinerarios.add(unItinerario);
    }

}