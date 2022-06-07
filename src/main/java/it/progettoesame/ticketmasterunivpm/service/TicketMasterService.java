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

    final private String urlBase = "https://app.ticketmaster.com/discovery/v2/events.json?";
    final private String apiKey = "apikey=ytOGRTWK4lKDd4B9gvj8odbPaejuGh8V";

    //Metodo che ricava l'url attraverso i parametri inseriti dall'utente
    public String getUrl(String paramSize, String paramCountry) {
        String country = "countryCode=" + paramCountry + "&";
        String size = "size=" + paramSize + "&";
        return urlBase + country + size + apiKey;
    }

    //Metodo che ricava gli eventi dalla chiamata API
    public ArrayList<Event> getEventsFromURL(String paramSize, String paramCountry) {
        try {
                EventsParser eventsParser = new EventsParser();
                InputStream input = new URL(getUrl(paramSize, paramCountry)).openStream();
                JSONParser parser = new JSONParser();
                JSONObject result = (JSONObject) parser.parse(new InputStreamReader(input));
                eventsParser.buildEventsArray(result);
                return eventsParser.getEvents();
        }

        catch ( Exception e ) {
            e.printStackTrace();
            return null;    //TO-DO: eccezione da gestire
        }
    }
}
