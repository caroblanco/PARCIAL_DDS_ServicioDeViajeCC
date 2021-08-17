package clases;
import strategy.notificarStrategy;

import java.util.ArrayList;
import java.util.List;

public class usuario {


    String nombreUsuario;
    private String contrasenia;
    String mail;
    int id;

    public String getMail() {
        return mail;
    }

    public usuario(String nombreUsuario, String contrasenia, String mail, int id) {

        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        this.mail = mail;
        this.id = id;
    }

    public String getUsuario(){ return nombreUsuario;}

    public String getContrasenia(){return contrasenia;}


    public boolean validarContrasenia(String contraseniaPasada){return contraseniaPasada.equals(contrasenia);}
}
