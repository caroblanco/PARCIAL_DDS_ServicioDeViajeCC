package clases.Security;

public class reglas {
    static int largoMinimo = 8;
    /*La misma debe tener mas de 8 caracteres e incluir un numero y una mayuscula,
    el nombre de clases.usuario debe ser distinto de la contraseña.*/

    static boolean cumpleLasReglas(String usuario, String contrasenia){
        return reglas.cumpleMinimoLargo(contrasenia) && reglas.cumpleSintaxis(contrasenia) && reglas.distintoAUsuario(usuario,contrasenia);
    }

    static boolean cumpleMinimoLargo(String contrasenia){
        int largo = contrasenia.length();
        return largo >= largoMinimo;
    }

    static boolean cumpleSintaxis(String contrasenia) {
        char clave;
        int contNumero = 0, contLetraMay = 0, contLetraMin = 0;

        for (int i = 0; i < contrasenia.length(); i++) {
            clave = contrasenia.charAt(i);
            String passValue = String.valueOf(clave);
            if (passValue.matches("[A-Z]")) {
                contLetraMay++;
            } else if (passValue.matches("[a-z]")) {
                contLetraMin++;
            } else if (passValue.matches("[0-9]")) {
                contNumero++;
            }
        }

        return (contNumero != 0) && (contLetraMay != 0) && (contLetraMin != 0);
    }

    static boolean distintoAUsuario(String usuario, String contrasenia){
        return !(usuario.equals(contrasenia));
    }
}
