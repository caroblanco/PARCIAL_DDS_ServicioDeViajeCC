package clases.state;
import clases.usuario;
import clases.vuelo;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public abstract class estadoAsientos {

    public vuelo vuelo;

    public estadoAsientos(vuelo unVuelo){
        this.vuelo = unVuelo;
    }

    public abstract double precioPorEstado() throws IOException;

    public abstract void cancelarAsiento();
}
