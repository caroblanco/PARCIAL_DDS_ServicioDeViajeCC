package clases;

import static clases.clase.*;

public class asiento {
    int numAsiento;
    boolean ocupado = false;
    String docPasajero;
    clase claseAsiento;

    public asiento(int i) {
        this.numAsiento = i;
        this.claseAsiento = this.setearClase();
    }

    public clase setearClase(){

        clase claseAsiento=null;

        if (numAsiento <= 20) {
            claseAsiento = PRIMERA_CLASE;
        } else if (numAsiento <= 50) {
            claseAsiento = BUSSINESS;
        } else if (numAsiento <= 180) {
            claseAsiento = ECONOMY;
        }
        return claseAsiento;
    }

    public void ocuparAsientoPor(String docPasajero){
        this.ocupado= true;
        this.docPasajero = docPasajero;
    }

    public void desocuparAsiento(){
        this.ocupado = false;
        docPasajero = null;
    }

    public boolean estaDisponible(){
        return !ocupado;
    }

    public int getNumAsiento(){
        return numAsiento;
    }

    public String getDocPasajero() { return docPasajero;
    }

    public clase getClase() { return claseAsiento;
    }
}