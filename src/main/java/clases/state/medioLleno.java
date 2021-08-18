package clases.state;

import clases.vuelo;

import java.io.IOException;

public class medioLleno extends estadoAsientos{
    public medioLleno(clases.vuelo unVuelo) {
        super(unVuelo);
    }

    @Override
    public double precioPorEstado() throws IOException {

        if(vuelo.asientosOcupados()==135){
            vuelo.cambiarEstadoAsientos(new casiLleno(vuelo));
        }

        return vuelo.getPrecioBase();
    }

    @Override
    public void cancelarAsiento() {
        if(vuelo.asientosOcupados()==45){
            vuelo.cambiarEstadoAsientos(new vacio(vuelo));
        }
    }
}
