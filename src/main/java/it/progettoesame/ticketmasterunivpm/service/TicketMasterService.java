package it.progettoesame.ticketmasterunivpm.service;


import it.progettoesame.ticketmasterunivpm.filter.EventsFilter;
import it.progettoesame.ticketmasterunivpm.parser.EventsParser;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.util.Map;


@Service
public class TicketMasterService {

    final private JSONObject events = new JSONObject();
    final private String[] supportedParam = {"country", "city", "local_date", "segment", "genre", "subgenre"};
    private String currentCountry = " ";

    //Metodo che ricava l'url attraverso i parametri inseriti dall'utente
    public String getUrl(String c) {
        String urlBase = "https://app.ticketmaster.com/discovery/v2/events.json?apikey=ytOGRTWK4lKDd4B9gvj8odbPaejuGh8V";
        return urlBase + "&countryCode=" + c + "&size=200";
    }

    //Metodo che ricava gli eventi dalla chiamata API
    public void buildEventsFromURL(String selectedCountry) {
        try {
                EventsParser eventsParser = new EventsParser();
                InputStream input = new URL(getUrl(selectedCountry)).openStream();
                JSONParser parser = new JSONParser();
                JSONObject result = (JSONObject) parser.parse(new InputStreamReader(input));
                eventsParser.buildEventsArray(result);
                events.put("num_events_found", eventsParser.getNumEvents());
                events.put("list_events_found", eventsParser.getNotFilteredEvents());
                //TO-DO: forse c'è qualche modifica da fare qui (forse anche nella classe EventsParser)
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    public boolean areSupportedParam(Map<String, String> param) {
        if (param.size()>supportedParam.length)
            return false;
        else {
            int verify=0;
            for (String p : supportedParam) {
                if (param.containsKey(p))
                    verify++;
            }
            return verify == param.size();
        }
    }

    public JSONObject getEvents(Map<String, String> selectedParameters) {
        if (!currentCountry.equals(selectedParameters.get("country"))) {
            currentCountry = selectedParameters.get("country");
            buildEventsFromURL(currentCountry);
            selectedParameters.remove("country");
        }
        if (!selectedParameters.isEmpty()){
            EventsFilter eventsFilter = new EventsFilter();
            return  eventsFilter.getFilteredEvents(events, selectedParameters);
        }
        return events;
    }
}
