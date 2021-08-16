package clases;

import clases.reglas;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class validador {

    public static boolean validarContrasenia(String usuario, String contrasenia){
        return reglas.cumpleLasReglas(usuario,contrasenia);
    }

    public static void main(String[] args) throws FileNotFoundException {
        /*System.out.println("ingrese usuario");
        Scanner scanner = new Scanner(System.in);
        String usuario = scanner.nextLine();
    System.out.println("ingrese contrasenia");

    String contra = scanner.nextLine();

        while(!(validador.validarContrasenia(usuario, contra))){
            System.out.println("Ingrese una nueva contrasenia, la anterior no cumple las condiciones: ");
            contra = scanner.nextLine();
        }*/
    }
}
