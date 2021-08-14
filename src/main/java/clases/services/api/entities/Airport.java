package clases.services.api.entities;

public class Airport {

    public String airport_name;
    public String iata_code;
    public String icao_code;
    public String latitude;
    public String geoname_id;
    public String timezone;
    public String gmt;
    public String phone_number;
    public String country_name;
    public String country_iso2;
    public String city_iata_code;


    public boolean tieneIata(String iataCiudad) {
        return city_iata_code.equalsIgnoreCase(iataCiudad);
    }
    public String getAirport_name(){return airport_name;}
    public String getIata_code(){return iata_code;}
}
