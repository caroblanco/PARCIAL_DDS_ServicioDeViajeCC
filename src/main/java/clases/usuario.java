package clases;
import strategy.notificarStrategy;

import java.util.ArrayList;
import java.util.List;

public class usuario {
    String nombreUsuario;
    String contrasenia;


    public String getMail() {
        return mail;
    }

    String mail;


    public usuario(String nombreUsuario, String contrasenia, String mail) {

        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        this.mail = mail;
    }

    public String getUsuario(){ return nombreUsuario;}

    public String getContrasenia(){return contrasenia;}



}
