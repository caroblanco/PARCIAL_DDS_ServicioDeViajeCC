package clases;

import java.util.ArrayList;
import java.util.List;

public class sistema {

    List<usuario> usuarios = new ArrayList<>();
    aerolinea aerolinea;

    public sistema() {
    }

    public boolean validarContrasenia(String usuario, String contrasenia){
        return validador.validarContrasenia(usuario, contrasenia);
    }

    //public List<Vuelo> buscarVuelos(String destinoInicial, String destinoFinal){
      //  return [];
    //}
}
