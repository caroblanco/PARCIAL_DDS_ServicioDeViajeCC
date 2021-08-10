package clases.services.api;

import java.io.IOException;
import java.util.Scanner;

public class mainTrucho {

    public static void main(String[] args) throws IOException {

        apiAviationStack api = apiAviationStack.getInstancia();

        Scanner sc = new Scanner(System.in);

        while(true){

            System.out.println("Ingrese una ciudad: ");
            String origen = sc.nextLine();
            String iata = api.buscarAeropuerto(origen).iata_code;
            System.out.println(iata);

        }


    }

}
