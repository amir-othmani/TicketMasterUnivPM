package it.progettoesame.ticketmasterunivpm.service;

import it.progettoesame.ticketmasterunivpm.model.Event;
import it.progettoesame.ticketmasterunivpm.parser.EventsParser;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;


@Service
public class TicketMasterService {

    private EventsParser eventsParser = null;

    public String buildUrl(String p) {
        String urlBase = "https://app.ticketmaster.com/discovery/v2/events.json?";
        String apiKey = "apikey=ytOGRTWK4lKDd4B9gvj8odbPaejuGh8V";
        return urlBase + "countryCode=" + p + "&" + apiKey;
    }

    //Metodo che ricava gli eventi dalla chiamata API
    public void getEventsFromURL(String param) {
        try {
            eventsParser = new EventsParser();
            InputStream input = new URL(buildUrl(param)).openStream();
            JSONParser parser = new JSONParser();
            JSONObject result = (JSONObject) parser.parse(new InputStreamReader(input));
            eventsParser.buildEventsArray(result);  //Qui viene richiamato il metodo che costruisce la lista degli eventi
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    public ArrayList<Event> getNotFilteredEvents (){
            return eventsParser.getEvents();
    }
}
