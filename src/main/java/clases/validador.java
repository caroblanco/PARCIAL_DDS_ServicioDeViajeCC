package clases;

import clases.reglas;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class validador {

    public static boolean validarContrasenia(String usuario, String contrasenia){
        return reglas.cumpleLasReglas(usuario,contrasenia);
    }

    public static void main(String[] args) throws FileNotFoundException {

        //para leer la consola -> CIN
        Scanner entrada;
        entrada = new Scanner(System.in);

        System.out.println("Ingrese un Usuario: ");
        String usuario= entrada.nextLine();

        System.out.println("Ingrese una contrasenia: ");
        String contra = entrada.nextLine();

        while(!(validador.validarContrasenia(contra, usuario))){
            System.out.println("Ingrese una nueva contrasenia, la anterior no cumple las condiciones: ");
            contra = entrada.nextLine();
        }
    }
}
