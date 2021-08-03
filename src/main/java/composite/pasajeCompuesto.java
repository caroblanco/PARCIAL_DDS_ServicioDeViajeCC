package composite;
import clases.clase;
import clases.pasajero;
import clases.vuelo;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class pasajeCompuesto extends itinerario{
    List<pasaje> pasajes = new ArrayList<>();

    @Override
    public int tarifaTotal() {
        return pasajes.stream().mapToInt(pasaje::getTarifa).sum();
    }

    @Override
    public void cambiarAsiento(String idVuelo, int nuevoAsiento) {
        pasaje pasaje = this.buscarPasaje(idVuelo);
        pasaje.cambiarAsiento(idVuelo,nuevoAsiento);
    }

    public void agregarPasaje(pasaje pasaje){
        pasajes.add(pasaje);
    }

    @Override
    public pasaje buscarPasaje(String idVuelo){
        return pasajes.stream().filter(unP -> unP.getIdVuelo() == idVuelo).collect(Collectors.toList()).get(0);
    }

    @Override
    public void liberarAsiento() {
        pasajes.forEach(unP -> unP.liberarAsiento());
    }

    @Override
    public vuelo getVuelo(String idVuelo) {
        pasaje pasaje=pasajes.stream().filter(unP -> unP.getIdVuelo() == idVuelo).collect(Collectors.toList()).get(0);
        return pasaje.getVuelo(idVuelo);
    }
}