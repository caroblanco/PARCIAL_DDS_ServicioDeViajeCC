package state;
import clases.vuelo;
import clases.usuario;

public class delayed extends estadoState{

    public delayed(vuelo vuelo){
        super(vuelo);
    }

    @Override
    public void onDelayed() {
        //cafecito
    }

    @Override
    public void onCancelled() {

    }

    @Override
    public void onTiempo() {

    }
}
