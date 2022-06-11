package it.progettoesame.ticketmasterunivpm.filter;

import it.progettoesame.ticketmasterunivpm.exceptions.FilterMismatchException;
import it.progettoesame.ticketmasterunivpm.model.Event;
import org.json.simple.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;


public class EventsFilter {

    final private JSONObject filteredEvents = new JSONObject();
    final private ArrayList<Event> listFilteredEvents = new ArrayList<>();

    private boolean checkFilters(HashMap<String, String> param, Event e) {
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

    public void buildFilteredEvents(ArrayList<Event> eventsToFilter, HashMap<String, String> parameters) {
        listFilteredEvents.clear();
        for (Event event: eventsToFilter) {
            if (checkFilters(parameters, event))
                listFilteredEvents.add(event);
        }
    }

    public JSONObject filterEvents (HashMap<String, String> selectedParam, ArrayList<Event> eventsToFilter) {
        try {
            filteredEvents.clear();
            buildFilteredEvents(eventsToFilter, selectedParam);
            if (listFilteredEvents.isEmpty())
                throw new FilterMismatchException();
            filteredEvents.put("list_filtered_events", listFilteredEvents);
            filteredEvents.put("num_filtered_events", listFilteredEvents.size());
            return filteredEvents;
        }
        catch ( Exception e ) {
            filteredEvents.put("filtered_events_not_found", e.getMessage());
            return filteredEvents;
        }
    }

    public ArrayList<Event> getListFilteredEvents() {
        return listFilteredEvents;
    }
}

