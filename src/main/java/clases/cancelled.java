package clases;

public class cancelled extends estadoState{

    public cancelled(vuelo vuelo){
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
