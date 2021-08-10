package clases.services.api;

import clases.services.api.entities.Ciudad;
import clases.services.api.entities.Airport;
import clases.services.api.entities.respuesta;
import clases.services.api.entities.respuestaAirport;
import clases.services.api.entities.respuestaCiudades;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class apiAviationStack {

    private static apiAviationStack instancia = null;
    private static final String urlAPI = "http://api.aviationstack.com/v1/";
    private Retrofit retrofit;

    //String apiKey = "4b434e3592c9cf33528b2e0bc336df73"; //ESTA MURIO POR ESTE MES
    String apiKey = "b172ecdda2995d41f66cee4f9f99bbe6";
    //String apiKey = "541f2ce5ead8eb232fd37fdc05c5e059";

    public apiAviationStack(){
        this.retrofit = new Retrofit.Builder()
                .baseUrl(urlAPI)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static apiAviationStack getInstancia(){
        if(instancia == null){
            instancia = new apiAviationStack();
        }
        return instancia;
    }

    public respuesta dameVuelo(String origen, String destino) throws IOException {

        String origen_iata = this.dameIata(origen);
        String destino_iata = this.dameIata(destino);

        apiService flightService = this.retrofit.create(apiService.class);
        Call<respuesta> requestVuelo = flightService.vuelos(apiKey,origen_iata, destino_iata, "scheduled");
        Response<respuesta> responseVuelos = requestVuelo.execute();

        return responseVuelos.body();
    }

    public String dameIata(String ciudad) throws IOException {
        //List<Ciudad> ciudades = this.dameTodasLasCiudades();

        Ciudad miCiudad = this.buscarCiudad(ciudad);

        return miCiudad.getIata_code();
    }

    public List<Ciudad> dameCiudades(int offset) throws IOException{
        apiService ciudadesService = this.retrofit.create(apiService.class);
        Call<respuestaCiudades> requestCiudad = ciudadesService.ciudad(apiKey,offset);
        Response<respuestaCiudades> responseCiudades = requestCiudad.execute();
        return responseCiudades.body().getListaDeVuelos();
    }

    public List<Airport> dameAeropuertos(int offset) throws IOException{
        apiService ciudadesService = this.retrofit.create(apiService.class);
        Call<respuestaAirport> requestCiudad = ciudadesService.aeropuerto(apiKey,offset);
        Response<respuestaAirport> responseCiudades = requestCiudad.execute();
        return responseCiudades.body().getAeropuertos();
    }

    public int dameTotalCiudades() throws IOException{
        apiService ciudadesService = this.retrofit.create(apiService.class);
        Call<respuestaCiudades> requestCiudad = ciudadesService.ciudad(apiKey,0);
        Response<respuestaCiudades> responseCiudades = requestCiudad.execute();
        return responseCiudades.body().pagination.getTotal();
    }

    public int dameTotalAeropuertos() throws IOException{
        apiService ciudadesService = this.retrofit.create(apiService.class);
        Call<respuestaAirport> requestCiudad = ciudadesService.aeropuerto(apiKey,0);
        Response<respuestaAirport> responseCiudades = requestCiudad.execute();
        return responseCiudades.body().pagination.getTotal();
    }

    public List<Ciudad> dameTodasLasCiudades() throws IOException{

        int total = this.dameTotalCiudades();
        List<Ciudad>ciudades = new ArrayList<>();

        for(int i = 0; i <= total - 100; i+=100){
            ciudades.addAll(this.dameCiudades(i));
        }
        return ciudades;
    }

    public Ciudad buscarCiudad(String nombreCiudad) throws IOException{
        int total = this.dameTotalCiudades();
        int i =0;
        boolean laEncontre = false;
        Ciudad ciudad = null;

        while(!laEncontre){
            List<Ciudad>ciudades = this.dameCiudades(i);
            List<Ciudad>ciudadesFiltradas = ciudades.stream().filter(unaCiudad -> unaCiudad.city_name.equalsIgnoreCase(nombreCiudad)).collect(Collectors.toList());

            if(! ciudadesFiltradas.isEmpty()){
                ciudad = ciudadesFiltradas.get(0);
                laEncontre=true;
            }

            i+=100;
        }

        return ciudad;

    }

    public Airport buscarAeropuerto(String nombreCiudad) throws IOException{
        int total = this.dameTotalAeropuertos();
        int i =0;
        boolean laEncontre = false;
        Airport aeropuerto = null;
        String iataCiudad = this.dameIata(nombreCiudad);

        while(!laEncontre){
            List<Airport>aeropuertos = this.dameAeropuertos(i);
            List<Airport>aeropuertosFiltrados = aeropuertos.stream().filter(airport -> airport.tieneIata(iataCiudad)).collect(Collectors.toList());

            if(! aeropuertosFiltrados.isEmpty()){
                aeropuerto = aeropuertosFiltrados.get(0);
                laEncontre=true;
            }

            i+=100;
        }

        return aeropuerto;

    }



}
