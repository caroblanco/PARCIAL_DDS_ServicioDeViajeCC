package clases.services.api;

import clases.services.api.entities.Airport;
import clases.services.api.entities.Ciudad;
import clases.services.api.entities.VueloApi;
import clases.services.api.entities.respuesta;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class mainTrucho {
    static apiAviationStack api = apiAviationStack.getInstancia();

    public static void main(String[] args) throws IOException {

        while(true){
            Ciudad ciudadOrigen = mainTrucho.elegirCiudad("origen");
            Airport aeropuertoOrigen = mainTrucho.elegirAeropuerto("origen", ciudadOrigen);

            Ciudad ciudadDestino = mainTrucho.elegirCiudad("destino");
            Airport aeropuertoDestino = mainTrucho.elegirAeropuerto("destino", ciudadDestino);

            List <VueloApi> vuelos = api.dameVuelo(aeropuertoOrigen,aeropuertoDestino).getListaDeVuelos();

            if(vuelos.isEmpty()){
                System.out.println("No existe un vuelo con esos parametros");
            }else{
                VueloApi vuelo = mainTrucho.elegirFechas(vuelos);
                System.out.println("Ha seleccionado el vuelo numero: "+vuelo.getFlight_number());
            }
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
