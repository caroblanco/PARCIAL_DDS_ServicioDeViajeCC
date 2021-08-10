package clases;


public class onTime extends estadoState{

    public onTime(vuelo vuelo){
        super(vuelo);
    }

    @Override
    public void onDelayed() {

    }

    @Override
    public void onCancelled() {

    }

    @Override
    public void onTiempo() {
        int comparacion = 1;
        //String tiempoActual = this.obtenerTiempoActual();
        String horarioVuelo = vuelo.getHorario();
        String fechaVuelo = vuelo.getFecha();
        //int comparacion = tiempoActual.compareTo(horarioVuelo);
        if( this.estaATiempo(horarioVuelo, fechaVuelo)){
            //pasajero.serNotificado("Vuelo on Time");
        }else if(comparacion == 1){
            //pasajero.serNotificado("Vuelo delayed");
            vuelo.cambiarEstado(new delayed(vuelo));
        }
    }
}