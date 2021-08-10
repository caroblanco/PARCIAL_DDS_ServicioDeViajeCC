package clases.services.api;

import clases.services.api.entities.respuesta;
import clases.services.api.entities.respuestaCiudades;
import clases.services.api.entities.respuestaAirport;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;


public interface apiService {



    @GET("flights")
    Call<respuesta> vuelos(@Query("access_key") String apiKey, @Query("dep_iata") String dep_iata, @Query("arr_iata") String arr_iata, @Query("flight_status") String status);

    @GET("cities")
    Call<respuestaCiudades> ciudad(@Query("access_key") String apiKey, @Query("offset") int offset);

    @GET("airports")
    Call<respuestaAirport> aeropuerto(@Query("access_key") String apiKey, @Query("offset") int offset);


}
