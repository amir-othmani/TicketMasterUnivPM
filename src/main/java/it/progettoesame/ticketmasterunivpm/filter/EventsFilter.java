package it.progettoesame.ticketmasterunivpm.filter;


import it.progettoesame.ticketmasterunivpm.model.Event;

import java.util.ArrayList;
import java.util.Map;


public class EventsFilter {

    final private ArrayList<Event> listFilteredEvents = new ArrayList<>();

    private boolean checkFilters(Map<String, String> param, Event e) {
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

    public void buildFilteredEvents(ArrayList<Event> eventsToFilter, Map<String, String> parameters) {
        listFilteredEvents.clear();
        for (Event event: eventsToFilter) {
            if (checkFilters(parameters, event))
                listFilteredEvents.add(event);
        }
    }

    public ArrayList<Event> getListFilteredEvents() {
        return listFilteredEvents;
    }
}

