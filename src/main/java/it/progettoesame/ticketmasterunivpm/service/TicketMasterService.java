package it.progettoesame.ticketmasterunivpm.service;

import it.progettoesame.ticketmasterunivpm.model.Event;
import it.progettoesame.ticketmasterunivpm.parser.EventsParser;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;


@Service
public class TicketMasterService {

    final private String urlBase = "https://app.ticketmaster.com/discovery/v2/events.json?";
    final private String apiKey = "apikey=ytOGRTWK4lKDd4B9gvj8odbPaejuGh8V";

    //Metodo che ricava l'url attraverso i parametri inseriti dall'utente
    public String getUrl(Map<String, String> rP) {
        String urlTemp = urlBase;
        if (rP.containsKey("countryCode"))
            urlTemp += "countryCode=" + rP.getOrDefault("countryCode", "DE") + "&";
        if (rP.containsKey("size"))
            urlTemp += "size=" + rP.get("size") + "&";
        return urlTemp + apiKey;
    }

    //Metodo che ricava gli eventi dalla chiamata API
    public ArrayList<Event> getEventsFromURL(String url) {
        try {
            EventsParser eventsParser = new EventsParser();
            InputStream input = new URL(url).openStream();
            JSONParser parser = new JSONParser();
            JSONObject result = (JSONObject) parser.parse(new InputStreamReader(input));
            return eventsParser.buildEventsArray(result);  //Qui viene richiamato il metodo che costruisce la lista degli eventi
        }
        catch ( Exception e ) {
            e.printStackTrace();
            return null;    //TO-DO: eccezione da gestire
        }
    }
}
