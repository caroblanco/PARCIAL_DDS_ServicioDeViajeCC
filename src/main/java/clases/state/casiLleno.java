package clases.state;

import clases.vuelo;

import java.io.IOException;

public class casiLleno extends estadoAsientos{
    public casiLleno(clases.vuelo unVuelo) {
        super(unVuelo);
    }

    @Override
    public double precioPorEstado() throws IOException {

        if(vuelo.asientosOcupados()==179){
            vuelo.cambiarEstadoAsientos(new medioLleno(vuelo));
        }


        return vuelo.getPrecioBase()*1.05;
    }

    @Override
    public void cancelarAsiento() {
        if(vuelo.asientosOcupados()==135){
            vuelo.cambiarEstadoAsientos(new medioLleno(vuelo));
        }
    }
}
