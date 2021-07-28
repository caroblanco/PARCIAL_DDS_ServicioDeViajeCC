package clases;
import state.estadoState;
import java.util.ArrayList;
import java.util.List;

public class vuelo {
    String idVuelo;
    int asientosTotales;
    int asientosOcupados;
    String origen;
    String destino;
    int tarifa;
    String dia;
    String horario;
    estadoState estado;
    List<pasajero> pasajeros = new ArrayList<>();

    public vuelo(String id, int asientosTotales, int asientosOcupados,String origen, String destino,int tarifa, String dia, String horario, estadoState estadoVuelo){
        this.idVuelo = id;
        this.asientosTotales=asientosTotales;
        this.asientosOcupados=asientosOcupados;
        this.origen=origen;
        this.destino=destino;
        this.tarifa=tarifa;
        this.dia=dia;
        this.horario=horario;
        this.estado = estadoVuelo;

    }

    public estadoState validarEstado(){
        return estado;
    }

    public void cambiarEstado(estadoState nuevoEstado){
        estado = nuevoEstado;
    }


    public void restarAsientos(int cantidad){
        asientosOcupados -= cantidad;
    }

    public void sumarAsientos(int cantidad){
        asientosOcupados += cantidad;
    }

    public void resetAsientos(){
        asientosOcupados=0;
    }

    public boolean cumpleDestinos(String destinoInicial, String destinoFinal){
        return (origen == destinoInicial) && (destino == destinoFinal);
    }
}
