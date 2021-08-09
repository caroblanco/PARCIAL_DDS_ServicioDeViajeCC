package strategy;

public class whatsapp extends notificarStrategy{

    @Override
    public void notificar(String mensaje, String telefono, String email){
        twilio.mandarWhatsapp(email, mensaje);
    }
}
