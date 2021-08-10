package clases.services.api.entities;

import java.util.List;

public class respuestaAirport {

    public pagination pagination;

    public List<Airport> data;

    public class pagination{
        int limit;
        int offset;
        int count;
        int total;

        public int getTotal() {
            return total;
        }

    }

    public List<Airport> getAeropuertos() {
        return data;
    }




}
