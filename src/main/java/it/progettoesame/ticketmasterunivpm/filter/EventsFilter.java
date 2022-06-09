package it.progettoesame.ticketmasterunivpm.filter;

import it.progettoesame.ticketmasterunivpm.model.Event;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Map;


public class EventsFilter {

    private JSONObject filteredEvents = new JSONObject();
    private ArrayList<Event> listFilterEvents = new ArrayList<>();

    public boolean checkFilters(Map<String, String> param, Event e) {
        if (param.containsKey("city") && !e.getCity().equals(param.get("city")))
            return false;
        if (param.containsKey("local_date") && !e.getLocal_date().equals(param.get("local_date")))
            return false;
        if (param.containsKey("segment") && !e.getSegment().equals(param.get("segment")))
            return false;
        if (param.containsKey("genre") && !e.getGenre().equals(param.get("genre")))
            return false;
        if (param.containsKey("subgenre") && !e.getSubgenre().equals(param.get("subgenre")))
            return false;
        return true;
    }

    public JSONObject getFilteredEvents(JSONObject objectToFilter, Map<String, String> parameters) {
        ArrayList<Event> eventsToFilter = (ArrayList<Event>) objectToFilter.get("list_events_found");
        for (Event event: eventsToFilter) {
            if (checkFilters(parameters, event))
                listFilterEvents.add(event);
        }
        filteredEvents.put("list_events_filtered", listFilterEvents);
        filteredEvents.put("num_events_filtered", listFilterEvents.size());
        return filteredEvents;
    }
}

