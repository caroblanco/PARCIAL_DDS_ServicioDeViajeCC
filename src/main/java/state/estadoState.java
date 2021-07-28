package state;
import clases.usuario;
import clases.vuelo;

public abstract class estadoState {
    vuelo vuelo;

    public estadoState(vuelo unVuelo){
        this.vuelo = unVuelo;
    }

    public abstract void onDelayed();
    public abstract void onCancelled();
    public abstract void onTiempo();
}
