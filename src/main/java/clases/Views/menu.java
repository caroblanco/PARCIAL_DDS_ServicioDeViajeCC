package clases.Views;

import clases.*;
import clases.composite.itinerario;
import clases.composite.pasaje;
import clases.composite.pasajeCompuesto;
import clases.services.api.apiAviationStack;
import clases.services.api.entities.Airport;
import clases.services.api.entities.Ciudad;
import clases.services.api.entities.VueloApi;
import strategy.email;
import strategy.notificarStrategy;
import strategy.sms;
import strategy.whatsapp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static clases.clase.*;

public class menu {
    static apiAviationStack api = apiAviationStack.getInstancia();
    private static int opcion=1;
    static usuario unUsuario;
    static String idVuelo;
    static boolean verificado = false;
    static Sistema sistema = Sistema.getInstancia();

    public static void main(String[] args) throws IOException{
        Scanner scannerInt = new Scanner(System.in);
        Scanner scannerString = new Scanner(System.in);

        while(opcion != 0){

            System.out.println("1. Ver el estado de un vuelo");
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

                        //BUSCAR VUELO EN LA API!!!
                        menu.main(args);
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
                                System.out.println("Se inicio correctamente la sesion");
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
                        System.out.println("Usuario valido! ahora ingrese su contrasena");
                        String contrasenaProvisoria = scannerString.nextLine();
                        while(!sistema.validarContrasenia(usuario,contrasenaProvisoria)) {
                            System.out.println("Contrasena no valida, vuelva a intentarlo");
                            contrasenaProvisoria = scannerString.nextLine();
                        }

                        System.out.println("Ingrese un mail");
                        String mail = scannerString.nextLine();

                        unUsuario=sistema.crearUsuario(usuario,contrasenaProvisoria,mail);

                        System.out.println("Usuario creado exitosamente!");
                        menu.menuIniciado();
                        break;
                    case 0:
                        System.out.println("Ha seleccionado salir");
                        break;
                }
            }catch (InputMismatchException e) {
                System.out.println("Debe insertar un número");
                scannerInt.next();
            }
        }
    }
    public static void menuIniciado() throws IOException{
        Scanner scannerInt = new Scanner(System.in);
        Scanner scannerString = new Scanner(System.in);
        boolean valido;
        pasajero pasajero;

        while(opcion != 0) {

            System.out.println("1. Comprar itinerario");
            System.out.println("2. Cancelar itinerario");
            System.out.println("3. Modificar asiento de un vuelo");
            System.out.println("0. Salir");

            try {
                System.out.println("Seleccione una opcion:");
                opcion = scannerInt.nextInt();

                switch (opcion) {
                    case 0:
                        System.out.println("Se ha cerrado la sesion");
                        break;

                    case 1:
                        System.out.println("Como estara compuesto su itinerario?");
                        System.out.println("1. Un pasaje directo");
                        System.out.println("2. Mas de un vuelo directo");
                        int opcion = scannerInt.nextInt();
                        itinerario itinerario=null;
                        double precioFinal=0;

                        switch (opcion) {
                            case 1:
                                 itinerario = menu.crearPasaje();
                                precioFinal = itinerario.tarifaTotal();
                                break;
                            case 2:
                                System.out.println("Cuantos pasajes desea comprar?");
                                int cantidadPasajes = scannerInt.nextInt();
                                List <pasaje> pasajes= new ArrayList<>();

                                for (int i = 0; i < cantidadPasajes; i++) {
                                    pasaje unPasaje = menu.crearPasaje();
                                    pasajes.add(unPasaje);
                                }

                                pasajero = menu.crearPasajero();
                                itinerario = new pasajeCompuesto(pasajes,pasajero);
                                precioFinal = itinerario.tarifaTotal();
                                break;
                        }
                        pasajero = menu.crearPasajero();
                        pasajero.agregarItinerario(itinerario);

                        System.out.println("Ha adquirido un itinerario exitosamente. Su numero de itinerario es "+ itinerario.getNumItinerario()+"y el precio final es" + precioFinal);

                        menu.menuIniciado();
                        break;
                    case 2:
                        valido = false;
                        while (!valido) {

                            System.out.println("Ingrese su numero de documento");
                            String documento = scannerString.nextLine();
                            pasajero = sistema.buscarPasajeroPorDocumento(documento);

                            if(pasajero!=null){
                                System.out.println("Ingrese el numero de itinerario");
                                int numItinerario = scannerInt.nextInt();

                                if (pasajero.existeItinerario(numItinerario)){
                                    pasajero.cancelarItinerario(numItinerario);
                                    valido = true;
                                    System.out.println("Se ha cancelado su itinerario con exito");
                                } else {
                                    System.out.println("Itinerario incorrecto, vuelva a intentarlo");
                                }
                            }else{
                                System.out.println("No se ha encontrado el pasajero, intente de nuevo");
                            }
                        }
                        menu.menuIniciado();
                        break;
                    case 3:
                        valido = false;

                        while (!valido) {

                            System.out.println("Ingrese su numero de documento");
                            String documento = scannerString.nextLine();
                            pasajero = sistema.buscarPasajeroPorDocumento(documento);

                            if (pasajero!=null) {

                                System.out.println("Ingrese el numero de itinerario");
                                int numItinerario = scannerInt.nextInt();

                                if(pasajero.existeItinerario(numItinerario)){

                                    System.out.println("Ingrese el id del vuelo");
                                    String idVuelo = scannerString.nextLine();
                                    vuelo vuelo = sistema.buscarVueloPorID(idVuelo);

                                    if (vuelo.tieneAsientosLibres()){
                                        int nuevoAsiento = menu.mostrarAsientosLibresVueloSegunClase(vuelo.getAsientoPasajero(documento),vuelo);
                                        sistema.modificarAsiento(numItinerario, idVuelo, nuevoAsiento);
                                        valido = true;
                                        System.out.println("Se ha modificado su itinerario con exito");
                                    } else {
                                        System.out.println("No hay asientos disponibles, intente mas tarde");
                                    }
                                }else{
                                    System.out.println("No se ha encontrado el itinerario, ingrese la informacion nuevamente");
                                }

                            }else {
                                System.out.println("No se ha encontrado el pasajero, ingrese la informacion nuevamente");
                            }
                        }
                        menu.menuIniciado();
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                scannerInt.next();
            }
        }
    }

    private static int mostrarAsientosLibresVueloSegunClase(clase clase,vuelo vuelo) {
        List<asiento> asientos = vuelo.getAsientos().stream().filter(unA -> unA.getClase() == clase).collect(Collectors.toList());
        Scanner scannerInt = new Scanner(System.in);

        System.out.println("Seleccione el numero de asiento que desea");
        for(int i=1; i<=180;i++){
            if(asientos.get(i).estaDisponible()){
                System.out.println(asientos.get(i).getNumAsiento());
            }
        }
        return scannerInt.nextInt();
    }

    public static pasajero crearPasajero(){
        Scanner scannerString = new Scanner(System.in);
        Scanner scannerInt = new Scanner(System.in);
        pasajero pasajero;

        System.out.println("Ingrese su numero de documento");
        String documento = scannerString.nextLine();
        if(sistema.hayPasajeros() && sistema.existePasajero(documento)){
            pasajero = sistema.buscarPasajeroPorDocumento(documento);
        }else{
            System.out.println("Para continuar, ingrese su nombre");
            String nombre = scannerString.nextLine();
            System.out.println("Ingrese su numero de telefono");
            String telefono = scannerString.nextLine();
            System.out.println("Seleccione la forma de notificacion que prefiera");
            System.out.println("1- EMAIL");
            System.out.println("2- WHATSAPP");
            System.out.println("3- SMS");
            int opcion = scannerInt.nextInt();

            notificarStrategy formaNotif=null;
            switch (opcion){
                case 1:
                    formaNotif = new email();
                    break;
                case 2:
                    formaNotif = new whatsapp();
                    break;
                case 3:
                    formaNotif = new sms();
                    break;
            }
            pasajero = sistema.crearPasajero(documento,nombre,telefono,unUsuario, formaNotif);
        }
        return pasajero;
    }

    public static pasaje crearPasaje() throws IOException {
        Ciudad ciudadOrigen = menu.elegirCiudad("origen");
        Airport aeropuertoOrigen = menu.elegirAeropuerto("origen", ciudadOrigen);

        Ciudad ciudadDestino = menu.elegirCiudad("destino");
        Airport aeropuertoDestino = menu.elegirAeropuerto("destino", ciudadDestino);

        List<VueloApi> vuelos = api.dameVuelo(aeropuertoOrigen, aeropuertoDestino).getListaDeVuelos();

        if (vuelos.isEmpty()) {
            System.out.println("No existe un vuelo con esos parametros");
            return null;
        } else {
            VueloApi vuelo = menu.elegirFechas(vuelos);
            System.out.println("Ha seleccionado el vuelo numero: " + vuelo.getFlight_number());


            pasajero pasajero = menu.crearPasajero();
            vuelo vueloNuestro = sistema.crearNuestroVuelo(vuelo, pasajero);

            double tarifa = vueloNuestro.getEstadoAsientos().precioPorEstado();
            int tarifaBase = vueloNuestro.getPrecioBase();

            asiento asiento = menu.seleccionarAsiento(vueloNuestro, pasajero);
            int numAsiento = asiento.getNumAsiento();
            clase clase = null;

            if (numAsiento <= 20) {
                tarifa += tarifaBase * 0.20;
            } else if (numAsiento <= 50) {
                tarifa += tarifaBase * 0.15;
            } else if (numAsiento <= 180) {
            }

            return new pasaje(vueloNuestro, tarifa, asiento, pasajero);
        }
    }

    public static asiento seleccionarAsiento(vuelo vuelo,pasajero pasajero){
        System.out.println("Seleccione su asiento, teniendo en cuenta que...");
        System.out.println("*Los asientos numerados del 1 al 20 son primera clase, que aniade a la tarifa base un 20% extra");
        System.out.println("*Los asientos numerados del 21 al 50 son clase business, que aniade a la tarifa base un 15% extra");
        System.out.println("*Los asientos numerados del 51 al 180 son clase economy, que no aniade nada a la tarifa base");

        int asientoSeleccionado = menu.mostrarAsientosLibresVuelo(vuelo);
        vuelo.ocuparAsiento(asientoSeleccionado,pasajero);

        return vuelo.getAsientoX(asientoSeleccionado);
    }

    public static int mostrarAsientosLibresVuelo(vuelo vuelo){
        List<asiento> asientos = vuelo.getAsientos();
        Scanner scannerInt = new Scanner(System.in);

        System.out.println("Seleccione el numero de asiento que desea");
        for(int i=1; i<=180;i++){
            if(asientos.get(i).estaDisponible()){
                System.out.println(asientos.get(i).getNumAsiento());
            }
        }
        return scannerInt.nextInt();
    }

    public static VueloApi elegirFechas(List <VueloApi> vuelos) throws IOException{
        Scanner scannerString = new Scanner(System.in);
        Scanner scannerInt = new Scanner(System.in);

        System.out.println("Ingrese la fecha de salida asi \" AAAA-MM-DD \": ");
        String fechaSalida = scannerString.nextLine();

        List <VueloApi> vuelosSalida = vuelos.stream().filter(unV -> unV.getFlight_date().equalsIgnoreCase(fechaSalida)).collect(Collectors.toList());

        if(vuelosSalida.isEmpty()){
            System.out.println("NO EXISTE VUELO");
            return menu.elegirFechas(vuelos);
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
            return menu.elegirCiudad(que);
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
            return menu.elegirAeropuerto(que,ciudad);
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