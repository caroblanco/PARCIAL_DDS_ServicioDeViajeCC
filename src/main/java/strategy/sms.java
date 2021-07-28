package strategy;
import clases.usuario;

public class sms extends notificarStrategy{

    @Override
    public void notificar(String mensaje, String telefono, String email){
        //twilio.mandarSMS(telefono, mensaje);
    }
}
