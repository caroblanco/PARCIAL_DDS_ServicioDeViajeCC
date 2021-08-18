package clases.state;

import clases.vuelo;

import java.io.IOException;

public class lleno extends estadoAsientos{
    public lleno(clases.vuelo unVuelo) {
        super(unVuelo);
    }

    @Override
    public double precioPorEstado() throws IOException {
        return 0;
    }

    @Override
    public void cancelarAsiento() {
        if(vuelo.asientosOcupados()==179){
            vuelo.cambiarEstadoAsientos(new casiLleno(vuelo));
        }
    }
}
