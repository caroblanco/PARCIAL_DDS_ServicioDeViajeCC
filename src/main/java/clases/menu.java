package clases;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class menu {

    usuario unUsuario;
    int opcion;
    String idVuelo;
    boolean verificado = false;

    public void iniciar() throws IOException{
        Scanner scannerInt = new Scanner(System.in);
        Scanner scannerString = new Scanner(System.in);

        while(opcion != 0){

            System.out.println("1. Ver estado vuelo");
            System.out.println("2. Iniciar Sesion");
            System.out.println("3. Registrarse");
            System.out.println("0. Salir");

            try{
                System.out.println("Seleccione una opcion:");
                opcion = scannerInt.nextInt();

                switch(opcion){
                    case 1:
                        System.out.println("Ingrese ID del vuelo que desea ver");
                        idVuelo = scannerString.nextLine();

                        vuelo vuelo = sistema.buscarVueloPorID(idVuelo);
                        System.out.println(vuelo.getEstado());
                        break;
                    case 2:
                        while(!verificado){
                            System.out.println("Ingrese un Usuario: ");
                            String usuario= scannerString.nextLine();

                            System.out.println("Ingrese una contrasenia: ");
                            String contra = scannerString.nextLine();

                            verificado = sistema.validarUsuario(usuario,contra);

                            if(!verificado){
                                System.out.println("intente de nuevo");
                            }else{
                                unUsuario = sistema.buscarUsuario(usuario);
                                System.out.println("se inicio correctamente la sesion");
                            }
                        }

                        this.menuIniciado();

                        break;
                    case 3:

                        break;

                    case 0:

                        break;

                }
            }catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                scannerInt.next();
        }
    }
}
    public void menuIniciado() throws IOException{
        Scanner scannerInt = new Scanner(System.in);
        Scanner scannerString = new Scanner(System.in);
        boolean valido = false;

        while(opcion != 0){

            System.out.println("1. Comprar itinerario");
            System.out.println("2. Cancelar Itinerario");
            System.out.println("3. Modificar Asiento de su vuelo");
            System.out.println("0. Salir");
    }
        try{
            System.out.println("Seleccione una opcion:");
            opcion = scannerInt.nextInt();

            switch(opcion){
                case 0:
                    System.out.println("Se ha cerrado la sesion");
                    this.iniciar();
                    break;

                case 1:
                    System.out.println("Ingrese el origen");
                    String origen = scannerString.nextLine();

                    System.out.println("Ingrese el destino");
                    String destino = scannerString.nextLine();

                    System.out.println("Que tipo de vuelo desea: ");
                    System.out.println("1. Directo");
                    System.out.println("2. Con escalas");
                    int opcion = scannerInt.nextInt();



                    break;
                case 2:
                    valido = false;
                        while(!valido){

                            System.out.println("Ingrese el numero de itinerario");
                            int numItinerario = scannerInt.nextInt();

                            if(sistema.buscarPasajeroPorItinerario(numItinerario).size() != 0 ){
                                sistema.cancelarItinerario(numItinerario);
                                valido = true;
                            }else{
                                System.out.println("Itinerario incorrecto, ingrese otro numero");
                            }
                        }
                    break;
                case 3:
                    valido = false;

                    while(!valido){

                        System.out.println("Ingrese el numero de itinerario");
                        int numItinerario = scannerInt.nextInt();

                        if(sistema.buscarPasajeroPorItinerario(numItinerario).size() != 0 ){
                            System.out.println("Ingrese el id del vuelo");
                            String idVuelo = scannerString.nextLine();
                            System.out.println("Ingrese el nuevo asiento");

                            int nuevoAsiento = scannerInt.nextInt();
                            sistema.modificarAsiento(numItinerario, idVuelo, nuevoAsiento);
                            valido = true;
                        }else{
                            System.out.println("Itinerario incorrecto, ingrese otro numero");
                        }
                    }
                    break;
            }
        }catch (InputMismatchException e) {
            System.out.println("Debes insertar un número");
            scannerInt.next();
        }
    }
}
