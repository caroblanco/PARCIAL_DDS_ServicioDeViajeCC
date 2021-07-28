package clases;
import strategy.notificarStrategy;

import java.util.ArrayList;
import java.util.List;

public class usuario {

    String nombre;
    String nombreUsuario;
    String contrasenia;
    String mail;
    String telefono;
    notificarStrategy formaNotif;
    tarjeta tarjeta;
    //List<itinerario> itinerarios = new ArrayList<>();

    public usuario(String nombre, String nombreUsuario, String contrasenia, String mail, String telefono, notificarStrategy formaNotif, tarjeta tarjeta) {
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        this.mail = mail;
        this.telefono = telefono;
        this.formaNotif = formaNotif;
        this.tarjeta = tarjeta;
    }

    public void serNotificado(String mensaje){
        formaNotif.notificar(mensaje, telefono,mail);
    }



}
