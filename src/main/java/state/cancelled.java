package state;

import clases.usuario;
import clases.vuelo;

public class cancelled extends estadoState{

    public cancelled(clases.vuelo vuelo){
        super(vuelo);
    }

    @Override
    public void onDelayed() {

    }

    @Override
    public void onCancelled() {
            //cambiar fecha
    }

    @Override
    public void onTiempo() {

    }
}
