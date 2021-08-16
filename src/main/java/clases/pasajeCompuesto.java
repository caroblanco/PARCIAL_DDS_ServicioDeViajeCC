package clases;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class pasajeCompuesto extends itinerario{
    List<pasaje> pasajes = new ArrayList<>();

    @Override
    public double tarifaTotal() {
        return pasajes.stream().mapToDouble(pasaje::getTarifa).sum();
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
        return pasajes.stream().filter(unP -> unP.getIdVuelo().equalsIgnoreCase(idVuelo)).collect(Collectors.toList()).get(0);
    }

    @Override
    public void liberarAsiento() {
        pasajes.forEach(unP -> unP.liberarAsiento());
    }

    @Override
    public vuelo getVuelo(String idVuelo) {
        pasaje pasaje=pasajes.stream().filter(unP -> unP.getIdVuelo().equalsIgnoreCase(idVuelo)).collect(Collectors.toList()).get(0);
        return pasaje.getVuelo(idVuelo);
    }
}