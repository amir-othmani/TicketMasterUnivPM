package it.progettoesame.ticketmasterunivpm.filter;

import it.progettoesame.ticketmasterunivpm.model.Event;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Map;


public class EventsFilter {

    final private JSONObject filteredEvents = new JSONObject();
    final private ArrayList<Event> listFilteredEvents = new ArrayList<>();

    public ArrayList<Event> getListFilteredEvents() {
        return listFilteredEvents;
    }

    public boolean checkFilters(Map<String, String> param, Event e) {
        if (param.containsKey("city") && !e.getCity().equals(param.get("city")))
            return false;
        if (param.containsKey("local_date") && !e.getLocal_date().toString().equals(param.get("local_date")))
            return false;
        if (param.containsKey("segment") && !e.getSegment().equals(param.get("segment")))
            return false;
        if (param.containsKey("genre") && !e.getGenre().equals(param.get("genre")))
            return false;
        if (param.containsKey("subgenre") && !e.getSubgenre().equals(param.get("subgenre")))
            return false;
        return true;
    }

    public JSONObject getFilteredEvents(ArrayList<Event> eventsToFilter, Map<String, String> parameters) {
        for (Event event: eventsToFilter) {
            if (checkFilters(parameters, event))
                listFilteredEvents.add(event);
        }
        filteredEvents.put("list_events_filtered", listFilteredEvents);
        filteredEvents.put("num_events_filtered", listFilteredEvents.size());
        return filteredEvents;
    }
}

