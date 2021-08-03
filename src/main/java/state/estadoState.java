package state;
import clases.usuario;
import clases.vuelo;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;


public abstract class estadoState {
    vuelo vuelo;

    public estadoState(vuelo unVuelo){
        this.vuelo = unVuelo;
    }

    public abstract void onDelayed();
    public abstract void onCancelled();
    public abstract void onTiempo();

    public String obtenerTiempoActual(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        //LocalDateTime nowArg = now.minusHours(3)
        String tiempo = dtf.format(now);
       // String tiempo = dateTime.substring(11,19);

        return tiempo;
    }

    public String obtenerFechaActual(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        //LocalDateTime nowArg = now.minusHours(3)
        String tiempo = dtf.format(now);
        // String tiempo = dateTime.substring(11,19);

        return tiempo;
    }

    public boolean estaATiempo(String horarioVuelo, String fechaVuelo){
        int diaActual = Integer.parseInt(this.obtenerFechaActual().substring(0,2));// dia y mes
        int mesActual = Integer.parseInt(this.obtenerFechaActual().substring(3,5));// dia y mes
        int horaActual = Integer.parseInt(this.obtenerTiempoActual().substring(0,2)) ;

        int diaVuelo  = Integer.parseInt(this.obtenerFechaActual().substring(0,2));// dia
        int mesvuelo  = Integer.parseInt(this.obtenerFechaActual().substring(3,5));// mes
        int horaVuelo = Integer.parseInt(this.obtenerTiempoActual().substring(0,2));

        return mesActual <= mesvuelo && diaActual<= diaVuelo && horaActual<= horaVuelo;

    }

    public int diferenciaHoras(int horaActual,int horaVuelo){
        if((horaActual-horaVuelo) <= 0){
            return 0; //on time
        }else{
            return horaActual-horaVuelo;
        }
    }
}
