package it.progettoesame.ticketmasterunivpm.filter;

import it.progettoesame.ticketmasterunivpm.model.Event;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Map;


public class EventsFilter {

    private JSONObject filteredEvents = new JSONObject();
    private ArrayList<Event> gatheredEvents = new ArrayList<>();

    public boolean checkFilters(Map<String, String> param, Event e) {
        if (param.containsKey("name") && !e.getName().equals(param.get("name")))
                return false;
        if (param.containsKey("city") && !e.getCity().equals(param.get("city")))
                return false;
        if (param.containsKey("segment") && !e.getSegment().equals(param.get("segment")))
            return false;
        if (param.containsKey("genre") && !e.getGenre().equals(param.get("genre")))
            return false;
        return true;
    }

    public JSONObject getFilteredEvents(JSONObject objectToFilter, Map<String, String> parameters) {
        ArrayList<Event> eventsToFilter = (ArrayList<Event>) objectToFilter.get("list_events_found");
        for (Event event: eventsToFilter) {
            if (checkFilters(parameters, event))
                gatheredEvents.add(event);
        }
        filteredEvents.put("list_events_filtered", gatheredEvents);
        filteredEvents.put("num_events_filtered", gatheredEvents.size());
        return filteredEvents;
    }
}

