package state;
import clases.vuelo;
import clases.usuario;

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
        //mandar notif onTime
    }
}
