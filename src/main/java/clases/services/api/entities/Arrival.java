package clases.services.api.entities;

public class Arrival {

    public String airport;
    public String timezone;
    public String iata;
    public String icao;
    public String terminal;
    public String gate;
    public String baggage;
    public int delay;
    public String scheduled;
    public String estimated;
    public String actual;
    public String estimated_runway;
    public String actual_runway;

    public String getIata(){return iata;}
}
