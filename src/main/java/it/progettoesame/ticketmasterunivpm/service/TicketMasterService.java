package it.progettoesame.ticketmasterunivpm.service;

import it.progettoesame.ticketmasterunivpm.parser.EventsParser;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.net.URL;


public class TicketMasterService {

    final private String url = "https://app.ticketmaster.com/discovery/v2/events.json?size=13aA&apikey=ytOGRTWK4lKDd4B9gvj8odbPaejuGh8V";
    final private EventsParser eventsParser = new EventsParser();

    //Metodo che ricava gli eventi dalla chiamata API
    public void getEventsFromURL() {
        try {
            InputStream input = new URL(url).openStream();
            JSONParser parser = new JSONParser();
            JSONObject result = (JSONObject) parser.parse(new InputStreamReader(input));
            eventsParser.parseEventsArray(result);  //Qui viene richiamato il metodo che costruisce la lista degli eventi
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    public JSONObject getNotFilteredEvents (){
            return eventsParser.getTappetoVolante();
    }
}
