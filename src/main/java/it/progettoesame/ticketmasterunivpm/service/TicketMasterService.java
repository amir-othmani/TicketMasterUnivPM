package it.progettoesame.ticketmasterunivpm.service;


import it.progettoesame.ticketmasterunivpm.exceptions.EventParseExcpetion;
import it.progettoesame.ticketmasterunivpm.exceptions.EventsNotFoundException;
import it.progettoesame.ticketmasterunivpm.exceptions.FilterMismatchException;
import it.progettoesame.ticketmasterunivpm.exceptions.StatsException;
import it.progettoesame.ticketmasterunivpm.filter.EventsFilter;
import it.progettoesame.ticketmasterunivpm.model.Event;
import it.progettoesame.ticketmasterunivpm.parser.EventsParser;

import it.progettoesame.ticketmasterunivpm.stats.EventsStats;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.util.*;


@Service
public class TicketMasterService {

    final private JSONObject events = new JSONObject();
    final private JSONObject filteredEvents = new JSONObject();
    final private JSONObject allStats = new JSONObject();
    final private JSONArray statsArray = new JSONArray();
    final private String[] supportedEventsParam = {"countryCode", "city", "local_date", "segment", "genre", "subgenre"};
    final private String[] supportedStatsParam = {"countryCode", "city"};
    final private String[] supportedCountries = {"AL", "AT", "BE", "BG", "CH", "CY", "CZ", "DE", "DK", "EE", "ES", "FO",
            "FI", "FR", "GB", "GR", "HR", "HU", "IE", "IS", "IT", "LT", "LU", "MC", "ME", "MT", "ND", "NL", "NO", "PL",
            "PT", "RO", "RS", "SE", "SK", "SI", "TR", "UA"};
    final private EventsParser eventsParser = new EventsParser();
    final private EventsFilter eventsFilter = new EventsFilter();
    final private EventsStats eventsStats = new EventsStats();
    private String currentCountry = " ";

    private String getUrl(String c) {
        String urlBase = "https://app.ticketmaster.com/discovery/v2/events.json?apikey=ytOGRTWK4lKDd4B9gvj8odbPaejuGh8V";
        return urlBase + "&countryCode=" + c + "&size=200";
    }

    private void buildEventsFromURL(String selectedCountry) throws EventParseExcpetion {
        try {
                events.clear();
                InputStream input = new URL(getUrl(selectedCountry)).openStream();
                JSONParser parser = new JSONParser();
                JSONObject result = (JSONObject) parser.parse(new InputStreamReader(input));
                eventsParser.buildEventsArray(result);
                if (eventsParser.getNotFilteredEvents().isEmpty())
                    throw new EventsNotFoundException();
                else {
                    events.put("num_events_found", eventsParser.getNotFilteredEvents().size());
                    events.put("list_events_found", eventsParser.getNotFilteredEvents());
                }
        }
        catch ( ParseException | IOException | EventsNotFoundException e ) {
            events.put("events_not_found", e.getMessage());
        }
    }

    private JSONObject filterEvents (HashMap<String, String> selectedParam) {
        try {
            filteredEvents.clear();
            eventsFilter.buildFilteredEvents(eventsParser.getNotFilteredEvents(), selectedParam);
            if (eventsFilter.getListFilteredEvents().isEmpty())
                throw new FilterMismatchException();
            filteredEvents.put("list_filtered_events", eventsFilter.getListFilteredEvents());
            filteredEvents.put("num_filtered_events", eventsFilter.getListFilteredEvents().size());
            return filteredEvents;
        }
        catch ( FilterMismatchException e ) {
            filteredEvents.put("filtered_events_not_found", e.getMessage());
            return filteredEvents;
        }
    }

    private ArrayList<String> getAllCities(ArrayList<Event> events) {
        ArrayList<String> containerCities = new ArrayList<>();
        for (Event e: events) {
            if (!containerCities.contains(e.getCity()))
                containerCities.add(e.getCity());
        }
        return containerCities;
    }

    public String[] getSupportedEventsParam() {
        return supportedEventsParam;
    }

    public String[] getSupportedStatsParam() {
        return supportedStatsParam;
    }

    public boolean areSupportedParam(HashMap<String, String> param, String[] supportedParam) {
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

    public boolean isSupportedCountry(String country) {
        for (String c: supportedCountries) {
            if (c.equals(country))
                return true;
        }
        return false;
    }

    public JSONObject getEvents(HashMap<String, String> selectedParam) {
        try {
            if (!currentCountry.equals(selectedParam.get("countryCode"))) {
                currentCountry = selectedParam.get("countryCode");
                buildEventsFromURL(currentCountry);
            }
        }
        catch ( Exception e ) {
            events.put("events_not_found", e.getMessage());
        }
        selectedParam.remove("countryCode");
        if (!selectedParam.isEmpty() && !eventsParser.getNotFilteredEvents().isEmpty())
            return filterEvents(selectedParam);
        return events;
    }

    public JSONObject getStats(HashMap<String, String> selectedParam) {
        try {
            allStats.clear();
            if (!currentCountry.equals(selectedParam.get("countryCode"))) {
                currentCountry = selectedParam.get("countryCode");
                buildEventsFromURL(currentCountry);
            }
            if (selectedParam.containsKey("city")) {
                eventsFilter.buildFilteredEvents(eventsParser.getNotFilteredEvents(), selectedParam);
                return eventsStats.statsPerWeek(eventsFilter.getListFilteredEvents(), selectedParam);
            }
            statsArray.clear();
            if (eventsParser.getNotFilteredEvents().isEmpty())
                throw new StatsException();
            for (String city: getAllCities(eventsParser.getNotFilteredEvents())) {
                HashMap<String, String> paramCity = new HashMap<>();
                paramCity.put("city", city);
                eventsFilter.buildFilteredEvents(eventsParser.getNotFilteredEvents(), paramCity);
                statsArray.add(eventsStats.statsPerWeek(eventsFilter.getListFilteredEvents(), paramCity));
            }
            allStats.put("country", eventsParser.getNotFilteredEvents().get(0).getCountry());
            allStats.put("all_cities", statsArray);
        }
        catch ( Exception e ) {
            allStats.put("events_not_found", e.getMessage());
        }
        return allStats;
    }
}
