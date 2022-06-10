package it.progettoesame.ticketmasterunivpm.service;


import it.progettoesame.ticketmasterunivpm.exceptions.EventsNotFoundException;
import it.progettoesame.ticketmasterunivpm.exceptions.FilterMismatchException;
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;


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

    private void buildEventsFromURL(String selectedCountry) {
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
        catch ( ParseException | IOException e )  {
            e.printStackTrace();
        }
        catch ( EventsNotFoundException e ) {
            events.put("events_not_found", e.getMessage());
        }
    }

    private JSONObject filterEvents (Map<String, String> selectedParam) {
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

    public boolean isSupportedCountry(String country) {
        for (String c: supportedCountries) {
            if (c.equals(country))
                return true;
        }
        return false;
    }

    public JSONObject getEvents(Map<String, String> selectedParam) {
        if (!currentCountry.equals(selectedParam.get("countryCode"))) {
            currentCountry = selectedParam.get("countryCode");
            buildEventsFromURL(currentCountry);
        }
        selectedParam.remove("countryCode");
        if (!selectedParam.isEmpty() && !eventsParser.getNotFilteredEvents().isEmpty())
            return filterEvents(selectedParam);
        return events;
    }

    public JSONObject getStats(Map<String, String> selectedParam) {
        if (!currentCountry.equals(selectedParam.get("countryCode"))) {
            currentCountry = selectedParam.get("countryCode");
            buildEventsFromURL(currentCountry);
        }
        if (selectedParam.containsKey("city")) {
            eventsFilter.buildFilteredEvents(eventsParser.getNotFilteredEvents(), selectedParam);
            return eventsStats.statsPerWeek(eventsFilter.getListFilteredEvents(), selectedParam.get("city"));
        }
        statsArray.clear();
        for (String city: getAllCities(eventsParser.getNotFilteredEvents())) {
            Map<String, String> paramCity = new Map<>() {
                @Override
                public int size() {
                    return 0;
                }

                @Override
                public boolean isEmpty() {
                    return false;
                }

                @Override
                public boolean containsKey(Object key) {
                    return false;
                }

                @Override
                public boolean containsValue(Object value) {
                    return false;
                }

                @Override
                public String get(Object key) {
                    return null;
                }

                @Override
                public String put(String key, String value) {
                    return null;
                }

                @Override
                public String remove(Object key) {
                    return null;
                }

                @Override
                public void putAll(Map<? extends String, ? extends String> m) {

                }

                @Override
                public void clear() {

                }

                @Override
                public Set<String> keySet() {
                    return null;
                }

                @Override
                public Collection<String> values() {
                    return null;
                }

                @Override
                public Set<Entry<String, String>> entrySet() {
                    return null;
                }
            };
            paramCity.put("city", city);
            eventsFilter.buildFilteredEvents(eventsParser.getNotFilteredEvents(), paramCity);
            statsArray.add(eventsStats.statsPerWeek(eventsFilter.getListFilteredEvents(), city));
        }
        allStats.put("all_cities", statsArray);
        return allStats;
    }
}
