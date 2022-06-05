package it.progettoesame.ticketmasterunivpm.service;

import it.progettoesame.ticketmasterunivpm.model.Event;
import it.progettoesame.ticketmasterunivpm.parser.EventParser;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;


public class TicketMasterService {

    final private String url = "https://app.ticketmaster.com/discovery/v2/events.json?size=3&apikey=ytOGRTWK4lKDd4B9gvj8odbPaejuGh8V";
    final private EventParser p = new EventParser();

    //Metodo che ricava gli eventi dalla chiamata API
    public void getEventsFromURL() {
        try {
            InputStream input = new URL(url).openStream();
            JSONParser parser = new JSONParser();
            JSONObject result = (JSONObject) parser.parse(new InputStreamReader(input));
            p.parseEventsArray(result);
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    public ArrayList<Event> getNotFilteredEvents (){
            return p.getEvents();
    }
}
