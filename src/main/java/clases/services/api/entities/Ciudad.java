package clases.services.api.entities;

public class Ciudad {

    public String city_name;
    public String iata_code;
    public String country_iso2;
    public String latitud;
    public String longitude;
    public String timezone;
    public String gmt;
    public String geoname_id;


    public String getIata_code() {
        return iata_code;
    }
}
