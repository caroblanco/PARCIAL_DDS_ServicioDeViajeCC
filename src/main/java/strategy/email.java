package strategy;

public class email extends notificarStrategy{

    @Override
    public void notificar(String mensaje, String telefono, String email){
        //twilio.mandarEmail(email, mensaje);
    }
}
