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
    public String getUrl() {
        //TO-DO: TUTTO DA RISCRIVERE (questo metodo è da scrivere in funzione della classe EventsFilter)
        return null;
    }

    //Metodo che ricava gli eventi dalla chiamata API
    public ArrayList<Event> getEventsFromURL(Map<String, String> param) {
        try {
                EventsParser eventsParser = new EventsParser();
                InputStream input = new URL(getUrl()).openStream();
                JSONParser parser = new JSONParser();
                JSONObject result = (JSONObject) parser.parse(new InputStreamReader(input));
                eventsParser.buildEventsArray(result);
                return eventsParser.getEvents();
                //TO-DO: forse c'è qualche modifica da fare qui (forse anche nella classe EventsParser)
        }
        catch ( Exception e ) {
            e.printStackTrace();
            return null;    //TO-DO: eccezioni da gestire
        }
    }
}
