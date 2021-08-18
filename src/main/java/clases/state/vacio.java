package clases.state;

import clases.vuelo;

import java.io.IOException;

public class vacio extends estadoAsientos{

    public vacio(clases.vuelo unVuelo) {
        super(unVuelo);
    }

    @Override
    public double precioPorEstado() throws IOException {

        if(vuelo.asientosOcupados()==45){
            vuelo.cambiarEstadoAsientos(new medioLleno(vuelo));
        }

        return vuelo.getPrecioBase()*0.95;
    }

    @Override
    public void cancelarAsiento() {

    }


}
