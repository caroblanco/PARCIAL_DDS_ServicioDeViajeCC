package clases;

public class asiento {
    int numAsiento;
    boolean ocupado = false;
    pasajero pasajero;

    public asiento(int i) { //TODO ACA CAPAZ LE PASAMOS LA CLASE ??
        this.numAsiento = i;
    }

    public void ocuparAsientoPor(pasajero Pasajero){
        ocupado= true;
        pasajero = pasajero;
    }

    public void desocuparAsiento(){
        ocupado = false;
        //TODO VER SI PODEMOS BORRAR EL PASAJER
    }

    public boolean estaDisponible(){
        return ocupado;
    }

    public int getNumAsiento(){
        return numAsiento;
    }
}