package it.progettoesame.ticketmasterunivpm.service;


import it.progettoesame.ticketmasterunivpm.filter.EventsFilter;
import it.progettoesame.ticketmasterunivpm.parser.EventsParser;

import it.progettoesame.ticketmasterunivpm.stats.EventsStats;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.util.Map;


@Service
public class TicketMasterService {

    final private JSONObject events = new JSONObject();
    final private JSONObject filteredEvents = new JSONObject();
    final private String[] supportedEventsParam = {"country", "city", "local_date", "segment", "genre", "subgenre"};
    final private String[] supportedStatsParam = {"country", "city"};
    final private EventsParser eventsParser = new EventsParser();
    final private EventsFilter eventsFilter = new EventsFilter();
    final private EventsStats eventsStats = new EventsStats();
    private String currentCountry = " ";

    public String[] getSupportedEventsParam() {
        return supportedEventsParam;
    }

    public String[] getSupportedStatsParam() {
        return supportedStatsParam;
    }

    //Metodo che ricava l'url attraverso i parametri inseriti dall'utente
    private String getUrl(String c) {
        String urlBase = "https://app.ticketmaster.com/discovery/v2/events.json?apikey=ytOGRTWK4lKDd4B9gvj8odbPaejuGh8V";
        return urlBase + "&countryCode=" + c + "&size=200";
    }

    //Metodo che ricava gli eventi dalla chiamata API
    private void buildEventsFromURL(String selectedCountry) {
        try {
                events.clear();
                InputStream input = new URL(getUrl(selectedCountry)).openStream();
                JSONParser parser = new JSONParser();
                JSONObject result = (JSONObject) parser.parse(new InputStreamReader(input));
                eventsParser.buildEventsArray(result);
                if (eventsParser.getNotFilteredEvents().isEmpty())
                    events.put("events_not_found", "we're sorry, but there are no available events in this country");
                else {
                    events.put("num_events_found", eventsParser.getNumEvents());
                    events.put("list_events_found", eventsParser.getNotFilteredEvents());
                }
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    public boolean areSupportedParam(Map<String, String> param, String[] supportedParam) {
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

    public JSONObject getEvents(Map<String, String> selectedParam) {
        if (!currentCountry.equals(selectedParam.get("country"))) {
            currentCountry = selectedParam.get("country");
            buildEventsFromURL(currentCountry);
        }
        selectedParam.remove("country");
        if (!selectedParam.isEmpty() && !eventsParser.getNotFilteredEvents().isEmpty()) {
            filteredEvents.clear();
            eventsFilter.buildFilteredEvents(eventsParser.getNotFilteredEvents(), selectedParam);
            if (eventsFilter.getListFilteredEvents().isEmpty()) {
                filteredEvents.put("filtered_events_not_found", "there are no events that match the seleceted filters");
                return filteredEvents;
            }
            filteredEvents.put("list_filtered_events", eventsFilter.getListFilteredEvents());
            filteredEvents.put("num_filtered_events", eventsFilter.getListFilteredEvents().size());
            return filteredEvents;
        }
        return events;
    }

    public JSONObject getStats(Map<String, String> selectedParam) {
        if (!currentCountry.equals(selectedParam.get("country"))) {
            currentCountry = selectedParam.get("country");
            buildEventsFromURL(currentCountry);
        }
        if (selectedParam.containsKey("city")) {
            eventsFilter.buildFilteredEvents(eventsParser.getNotFilteredEvents(), selectedParam);
            return eventsStats.statsPerWeek(eventsFilter.getListFilteredEvents(), selectedParam);
        }
        return eventsStats.statsPerWeek(eventsParser.getNotFilteredEvents(), selectedParam);
    }
}
