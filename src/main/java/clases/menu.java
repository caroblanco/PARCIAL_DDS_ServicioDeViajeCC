package clases;

import clases.services.api.apiAviationStack;
import clases.services.api.entities.Airport;
import clases.services.api.entities.Ciudad;
import clases.services.api.entities.VueloApi;
import clases.services.api.mainTrucho;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class menu {
    static apiAviationStack api = apiAviationStack.getInstancia();
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
                    System.out.println("2. Con escala");
                    int opcion = scannerInt.nextInt();

                    switch (opcion){
                        case 1:
                            //
                            break;
                        case 2:
                            //
                            break;
                    }

                        Ciudad ciudadOrigen = mainTrucho.elegirCiudad("origen");
                        Airport aeropuertoOrigen = mainTrucho.elegirAeropuerto("origen", ciudadOrigen);

                        Ciudad ciudadDestino = mainTrucho.elegirCiudad("destino");
                        Airport aeropuertoDestino = mainTrucho.elegirAeropuerto("destino", ciudadDestino);

                        List<VueloApi> vuelos = api.dameVuelo(aeropuertoOrigen,aeropuertoDestino).getListaDeVuelos();

                        if(vuelos.isEmpty()){
                            System.out.println("No existe un vuelo con esos parametros");
                        }else{
                            VueloApi vuelo = mainTrucho.elegirFechas(vuelos);
                            System.out.println("Ha seleccionado el vuelo numero: "+vuelo.getFlight_number());
                        }

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

    public static VueloApi elegirFechas(List <VueloApi> vuelos) throws IOException{
        Scanner scannerString = new Scanner(System.in);
        Scanner scannerInt = new Scanner(System.in);

        System.out.println("Ingrese la fecha de salida asi \" AAAA-MM-DD \": ");
        String fechaSalida = scannerString.nextLine();

        List <VueloApi> vuelosSalida = vuelos.stream().filter(unV -> unV.getFlight_date().equalsIgnoreCase(fechaSalida)).collect(Collectors.toList());

        if(vuelosSalida.isEmpty()){
            System.out.println("NO EXISTE VUELO");
            return null;
        }else{
            System.out.println("Los vuelos encontrados fueron los siguientes: ");
            int contador=1;
            for(int i = 0; i< vuelosSalida.size(); i++){
                System.out.println(contador + "- " + vuelosSalida.get(i).getFlight_number());
                contador++;
            }

            System.out.println("Elija el numero de su vuelo, o \"0\" si no quiere ninguna de las opciones: ");
            int indice = scannerInt.nextInt();

            return vuelosSalida.get(indice-1);
        }
    }

    public static Ciudad elegirCiudad(String que) throws IOException {
        Scanner sc = new Scanner(System.in);
        Scanner si = new Scanner(System.in);

        int contador = 1;
        System.out.println("Ingrese la ciudad de " + que + " :");
        String origen = sc.nextLine();

        List<Ciudad> ciudades = api.buscarCiudades(origen);

        if(ciudades.isEmpty()){
            System.out.println("NO HAY CIUDADES DISPONIBLES");
            return null;
        }else{
            System.out.println("Las ciudades disponibles son: ");
            for(int i = 0; i< ciudades.size(); i++){
                System.out.println(contador + "- " + ciudades.get(i).getTimezone());
                contador++;
            }

            System.out.println("Elija el numero de su ciudad: ");
            int indice = si.nextInt();

            return ciudades.get(indice-1);
        }
    }

    public static Airport elegirAeropuerto(String que, Ciudad ciudad) throws IOException {
        Scanner sc = new Scanner(System.in);
        Scanner si = new Scanner(System.in);

        int contador = 1;

        List<Airport> aeropuertos = api.buscarAeropuertos(ciudad);

        if(aeropuertos.isEmpty()){
            System.out.println("NO HAY AEROPUERTOS DISPONIBLES");
            return null;
        }else{
            System.out.println("Los aeropuertos disponibles para el " + que + " son: ");
            for(int i = 0; i< aeropuertos.size(); i++){
                System.out.println(contador + "- " + aeropuertos.get(i).getAirport_name());
                contador++;
            }

            System.out.println("Elija el numero de aeropuerto: ");
            int indice = si.nextInt();

            return aeropuertos.get(indice-1);
        }
    }
}
