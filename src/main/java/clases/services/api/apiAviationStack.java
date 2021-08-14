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
    //String apiKey = "b172ecdda2995d41f66cee4f9f99bbe6";//ESTA MURIO POR ESTE MES
    //String apiKey = "541f2ce5ead8eb232fd37fdc05c5e059";
    //String apiKey = "50514ec596a64cee106654d5c9c0a661";
    //String apiKey = "4f8dccbdc68f8572776718e0481acc8d";
    String apiKey = "ab467150dc239196dd65dde8a0d6740d";
    //String apiKey = "458472f570b0ef0b645a4cdb91f0db47";
    //String apiKey = "d112c93bb0cb62f964988ca032e9314b";
    //String apiKey = "8552d194bf1d1893a4eb704b2c98d300";
    //String apiKey = "5d4f50e60e9c71c9af4855b1a4fedd37";
    //String apiKey = "f3bb42f55d541626b6574395422675c1";
    //String apiKey = "17daaa1891d355f36daeb5342ecf3e83";
    //String apiKey = "498595cf0e93153d3f4ef96dd35572fd";
    //String apiKey = "72f06b104cfdc3f6d6fc1511e6f5d932";
    //String apiKey = "18b35bcff9029d15e3b8a6462fae64a7";
    //String apiKey = "79737cbad367383694748dcc0f9d00e6";
    //String apiKey = "02c360e1b37a75dba3b5e347d1b972a3";

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
/*
    public respuesta dameVuelo(String origen, String destino) throws IOException {

        String origen_iata = this.dameIata(origen);
        String destino_iata = this.dameIata(destino);

        apiService flightService = this.retrofit.create(apiService.class);
        Call<respuesta> requestVuelo = flightService.vuelos(apiKey,origen_iata, destino_iata, "scheduled");
        Response<respuesta> responseVuelos = requestVuelo.execute();

        return responseVuelos.body();
    }
*/
    public respuesta dameVuelo(Airport origen, Airport destino) throws IOException {

        String origen_iata = origen.getIata_code();
        String destino_iata = destino.getIata_code();

        apiService flightService = this.retrofit.create(apiService.class);
        Call<respuesta> requestVuelo = flightService.vuelos(apiKey,origen_iata, destino_iata, "scheduled");
        Response<respuesta> responseVuelos = requestVuelo.execute();

        return responseVuelos.body();
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

    public List <Ciudad> buscarCiudades(String nombreCiudad) throws IOException{
        int total = this.dameTotalCiudades();
        //boolean laEncontre = false;
        //Ciudad ciudad = null;
        List<Ciudad> ciudadesConNombre = new ArrayList<>();

        for(int i = 0; i <= total - 100; i+=100) {
            List<Ciudad> ciudades = this.dameCiudades(i);
            List<Ciudad> ciudadesFiltradas = ciudades.stream().filter(unaCiudad -> unaCiudad.city_name.equalsIgnoreCase(nombreCiudad)).collect(Collectors.toList());

            if (!ciudadesFiltradas.isEmpty()) {
                ciudadesConNombre.addAll(ciudadesFiltradas);
            }
        }

        return ciudadesConNombre;

    }

    public List <Airport> buscarAeropuertos(Ciudad ciudad) throws IOException{
        int total = this.dameTotalAeropuertos();
        String iataCiudad = ciudad.getIata_code();
        List <Airport> aeropuertosElegidos = new ArrayList<>();

        for(int i =0; i<=total ; i+=100){
            List<Airport>aeropuertos = this.dameAeropuertos(i);
            List<Airport>aeropuertosFiltrados = aeropuertos.stream().filter(airport -> airport.tieneIata(iataCiudad)).collect(Collectors.toList());

            if(! aeropuertosFiltrados.isEmpty()){
                aeropuertosElegidos.addAll(aeropuertosFiltrados);
            }
            if(aeropuertosElegidos.size() == 2)break;
        }

        return aeropuertosElegidos;

    }



}
