package clases;
import composite.itinerario;
import composite.pasaje;
import state.estadoState;
import strategy.notificarStrategy;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class pasajero {
    String nombre;
    String telefono;
    notificarStrategy formaNotif;
    tarjeta tarjeta;
    usuario usuario;
    List<itinerario> itinerarios = new ArrayList<>();

    public pasajero(String nombre, String telefono, notificarStrategy formaNotif, tarjeta tarjeta) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.formaNotif = formaNotif;
        this.tarjeta = tarjeta;
    }

    public void serNotificado(String mensaje){
        formaNotif.notificar(mensaje, telefono, usuario.getMail());
    }

    public void comprarItinerario(){

    }

    public void cancelarItinerario(int numItinerario){
        itinerario itinerario = this.buscarItinerario(numItinerario);
        itinerarios.remove(itinerario);
        itinerario.liberarAsiento();
    }

    public void modificarAsiento(int numItinerario, String idVuelo, int nuevoAsiento){
        itinerario itinerario = this.buscarItinerario(numItinerario);
        itinerario.cambiarAsiento(idVuelo, nuevoAsiento);
    }

    public itinerario buscarItinerario(int numItinerario){
        return itinerarios.stream().filter(unIt -> unIt.getNumItinerario() == numItinerario).collect(Collectors.toList()).get(0);
    }

    public estadoState verEstado(int numItinerario, String idVuelo){
        itinerario itinerario = this.buscarItinerario(numItinerario);
        vuelo vuelo = itinerario.getVuelo(idVuelo);
        return vuelo.getEstado();
    }

}
