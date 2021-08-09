package clases;


public class delayed extends estadoState{

    public delayed(vuelo vuelo){
        super(vuelo);
    }

    @Override
    public void onDelayed() {
        //pasajero.serNotificado("Use el siguiente codigo para un cafecito en la terminal mientras espera: %d", codigoCafecito);
        String tiempoActual = this.obtenerTiempoActual();
        String sHoraActual = tiempoActual.substring(0,2);
        int horaActual = Integer.parseInt(sHoraActual);

        String horarioVuelo = vuelo.getHorario();
        String sHoraVuelo = horarioVuelo.substring(0,2);
        int horaVuelo = Integer.parseInt(sHoraVuelo);


    }

    public String obtenerTiempoActual() {
        return "unTiempo";
    }

    @Override
    public void onCancelled() {
        String tiempoActual = this.obtenerTiempoActual();
    }

    @Override
    public void onTiempo() {

    }



}
