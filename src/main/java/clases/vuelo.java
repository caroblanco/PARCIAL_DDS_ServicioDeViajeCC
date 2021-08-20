package clases;
import clases.state.estadoAsientos;
import clases.state.vacio;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class vuelo {
    int precioBase = 30000;
    String idVuelo;
    int asientosTotales = 180;
    String origen;
    String destino;
    String dia;
    String horario;
    estadoAsientos estadoAsientos;
    List<asiento> asientos = new ArrayList<>();
    int delay;

    public vuelo(String id, int asientosTotales, String origen, String destino, String dia, String horario,  int delay){
        this.idVuelo = id;
        this.asientosTotales=asientosTotales;
        this.origen=origen;
        this.destino=destino;
        this.dia=dia;
        this.horario=horario;
        this.estadoAsientos = new vacio(this);
        this.crearAsientos();
        this.delay = delay;
    }

    public String getOrigen(){return origen;}
    public String getDestino(){return destino;}
    public asiento getAsientoX(int asientoSeleccionado){
        return asientos.get(asientoSeleccionado);
    }
    public String getIdVuelo() {
        return idVuelo;
    }
    public estadoAsientos getEstadoAsientos() {return estadoAsientos; }
    public List<asiento> getAsientos(){return asientos;}
    public String getHorario(){
        return this.horario;
    }
    public String getFecha(){
        return this.dia;
    }
    public int getPrecioBase() { return precioBase;   }

    public clase getAsientoPasajero(String documento) {
        return asientos.stream().filter(unA -> unA.getDocPasajero().equals(documento)).collect(Collectors.toList()).get(0).getClase();
    }

    public void crearAsientos(){
        for(int i = 1 ; i <= asientosTotales ; i++){
            asiento asiento = new asiento(i);
            asientos.add(asiento);
        }
    }

    public asiento buscarAsiento(int numAsiento){
        return asientos.stream().filter(unIt -> unIt.getNumAsiento() == numAsiento).collect(Collectors.toList()).get(0);
    }

    public List<asiento> asientosDisponibles(){
        return asientos.stream().filter(unAsiento -> unAsiento.estaDisponible()).collect(Collectors.toList());
    }

    public void resetAsientos(){
        asientos.forEach(unA -> unA.desocuparAsiento());
    }

    public boolean cumpleDestinos(String destinoInicial, String destinoFinal){
        return (origen.equalsIgnoreCase(destinoInicial)) && (destino.equalsIgnoreCase(destinoFinal));
    }

    public boolean asientoLibre(int asientoNuevo){
        return asientos.stream().anyMatch(unA -> unA.getNumAsiento() == asientoNuevo && unA.estaDisponible());
    }

    public void ocuparAsiento(int asientoSeleccionado,String docPasajero){
        asientos.get(asientoSeleccionado).ocuparAsientoPor(docPasajero);
    }

    public boolean tieneAsientosLibres() {
        return !this.asientosDisponibles().isEmpty();
    }

    public int asientosOcupados(){
        return asientos.stream().filter(unA -> !unA.estaDisponible()).collect(Collectors.toList()).size();
    }

    public void cambiarEstadoAsientos(estadoAsientos estado){
        estadoAsientos=estado;
    }

    public void asientoCancelado() {
        estadoAsientos.cancelarAsiento();
    }
}