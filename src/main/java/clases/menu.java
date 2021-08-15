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
    private static int opcion=1;
    static usuario unUsuario;
    static String idVuelo;
    static boolean verificado = false;

    public static void main(String[] args) throws IOException{
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

                        //BUSCAR VUELO EN LA API!!!

                        break;
                    case 2:
                        while(!verificado){
                            System.out.println("Ingrese un Usuario: ");
                            String usuario= scannerString.nextLine();

                            System.out.println("Ingrese una contrasenia: ");
                            String contra = scannerString.nextLine();

                            verificado = sistema.validarUsuario(usuario,contra);

                            if(!verificado){
                                System.out.println("Intente de nuevo");
                            }else{
                                unUsuario = sistema.buscarUsuario(usuario);
                                System.out.println("se inicio correctamente la sesion");
                            }
                        }

                        menu.menuIniciado();

                        break;
                    case 3:
                        System.out.println("Bienvenido! Cree un usuario");

                        System.out.println("Ingrese un nombre de usuario: ");
                        String usuario= scannerString.nextLine();
                        while(sistema.validarUsuarioRegistrar(usuario)) {
                            System.out.println("Nombre de usuario no valido, vuelva a intentarlo");
                            usuario = scannerString.nextLine();
                        }
                        System.out.println("Ingrese Contrasena");
                        String contrasenaProvisoria = scannerString.nextLine();
                        while(sistema.validarContrasenia(usuario,contrasenaProvisoria)) {
                            System.out.println("Contrasena no valida, vuelva a intentarlo");
                            contrasenaProvisoria = scannerString.nextLine();
                        }

                        System.out.println("Ingrese un mail");
                        String mail = scannerString.nextLine();

                        usuario miUsuario = new usuario(usuario, contrasenaProvisoria, mail);
                        sistema.agregarUsuario(miUsuario);
                        unUsuario=miUsuario;

                        System.out.println("Usuario creado exitosamente!");
                        menu.menuIniciado();
                        break;

                    case 0:
                        System.out.println("Usuario creado exitosamente!");
                        break;
                }
            }catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                scannerInt.next();
            }
        }
    }
    public static void menuIniciado() throws IOException{
        Scanner scannerInt = new Scanner(System.in);
        Scanner scannerString = new Scanner(System.in);
        boolean valido;

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
                    break;

                case 1:
                    System.out.println("Que tipo de vuelo desea: ");
                    System.out.println("1. Directo");
                    System.out.println("2. Con escala");
                    int opcion = scannerInt.nextInt();

                    switch (opcion){
                        case 1:
                            Ciudad ciudadOrigen = menu.elegirCiudad("origen");
                            Airport aeropuertoOrigen = menu.elegirAeropuerto("origen", ciudadOrigen);

                            Ciudad ciudadDestino = menu.elegirCiudad("destino");
                            Airport aeropuertoDestino = menu.elegirAeropuerto("destino", ciudadDestino);

                            List<VueloApi> vuelos = api.dameVuelo(aeropuertoOrigen,aeropuertoDestino).getListaDeVuelos();

                            if(vuelos.isEmpty()){
                                System.out.println("No existe un vuelo con esos parametros");
                            }else{
                                VueloApi vuelo = menu.elegirFechas(vuelos);
                                System.out.println("Ha seleccionado el vuelo numero: "+vuelo.getFlight_number());
                                int tarifa = 0;
                                menu.crearPasajero(vuelo, unUsuario, tarifa);
                                //VEMOS
                            }
                            break;
                        case 2:
                            System.out.println("trolo");//
                            break;
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

    public static void crearPasajero(VueloApi vuelo, usuario unUsuario, int tarifa){
        Scanner scannerString = new Scanner(System.in);
        Scanner scannerInt = new Scanner(System.in);

        System.out.println("Para continuar, ingrese su nombre");
        String nombre = scannerString.nextLine();
        System.out.println("Ingrese su numero de documento");
        String documento = scannerString.nextLine();
        System.out.println("Ingrese su numero de telefono");
        String telefono = scannerString.nextLine();

        if(sistema.existePasajero(documento)){
            sistema.agregarItinerarioAPasajero(vuelo,documento, tarifa);
        }else{

        }

        //AGREGAR VUELO NUEVO A LISTA DE VUELOS
        //AGREGAR VUELO AL PASAJERO
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