package clases;
import clases.composite.itinerario;
import strategy.notificarStrategy;
import strategy.sms;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class pasajero {
    String nombre;
    static String telefono;
    String documento;
    static notificarStrategy formaNotif;
    static usuario usuario;
    static List<itinerario> itinerarios = new ArrayList<>();

    public pasajero(String nombre, String telefono, String documento, notificarStrategy formaNotif, usuario unUsuario) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.formaNotif = formaNotif;
        this.usuario = unUsuario;
        this.documento = documento;
    }

    public boolean existeItinerario(int numItinerario){
        return itinerarios.stream().anyMatch(unI -> unI.getNumItinerario() == numItinerario);
    }

    public String getNombre(){return nombre;}

    public static void serNotificado(String mensaje){
        formaNotif.notificar(mensaje, telefono, usuario.getMail());
    }

    public static void agregarItinerario(itinerario itinerario){
        itinerarios.add(itinerario);
        pasajero.serNotificado("Ha comprado un itinerario, numero: " + itinerario.getNumItinerario());
    }

    public void cancelarItinerario(int numItinerario){
        itinerario itinerario = this.buscarItinerario(numItinerario);
        itinerarios.remove(itinerario);
        itinerario.liberarAsiento();
        this.serNotificado("Se ha cancelado su itinerario numero: "+ numItinerario);
    }

    public void modificarAsiento(int numItinerario, String idVuelo, int nuevoAsiento){
        itinerario itinerario = this.buscarItinerario(numItinerario);
        itinerario.cambiarAsiento(idVuelo, nuevoAsiento);
        this.serNotificado("se ha modificado el vuelo: "+ idVuelo + "de su itinerario: "+numItinerario );
    }

    public itinerario buscarItinerario(int numItinerario){
        return itinerarios.stream().filter(unIt -> unIt.getNumItinerario() == numItinerario).collect(Collectors.toList()).get(0);
    }
/*
    public estadoState verEstado(int numItinerario, String idVuelo){
        itinerario itinerario = this.buscarItinerario(numItinerario);
        vuelo vuelo = itinerario.getVuelo(idVuelo);
        return vuelo.getEstado();
    }
*/
    public List<itinerario> getItinerarios(){return itinerarios;}

    public boolean tieneItinerario(int numItinerario){
        return itinerarios.stream().anyMatch(unIt -> unIt.getNumItinerario() == numItinerario);
    }

    public String getDocumento(){ return documento;}
}
