package clases.services.api.entities;

public class VueloApi {

    public Airline airline;
    public Flight flight;
    public String flight_date;
    public String flight_status;
    public Departure departure;
    public Arrival arrival;
    public Aircraft aircraft;
    public Live live;

    public String getFlight_date(){return flight_date;}

    public String getFlight_number(){return flight.getNumber();}

    public String getTime(){ return departure.getTimeDeparture();}

    public String getOrigen(){ return departure.getIata();}
    public String getDestino(){ return arrival.getIata();}

    public int getDelay(){return departure.getDelay();}

}
