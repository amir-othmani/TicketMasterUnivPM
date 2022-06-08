package it.progettoesame.ticketmasterunivpm.filter;

import it.progettoesame.ticketmasterunivpm.model.Event;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Map;


public class EventsFilter {

    private JSONObject filteredEvents = new JSONObject();


    public ArrayList<String> getKeys(Map<String, String> parameters, String[] totalParameters) {
        ArrayList<String> keys = new ArrayList<>();
        for (int i=0; i<totalParameters.length; i++) {
            if (parameters.containsKey(totalParameters[i]))
                keys.add(totalParameters[i]);
        }
        return keys;
    }

    public boolean checkFilters(ArrayList<String> keysToCheck, Event eventToCheck) {
        int verify=0;
        for (String key: keysToCheck) {
            if (key.equals("name") ||
                key.equals("city") ||
                key.equals("segment") ||
                key.equals("genre"))
                verify++;
        }
        if (verify== keysToCheck.size())
            return true;
        else
            return false;
    }

    public JSONObject getFilteredEvents(JSONObject objectToFilter, ArrayList<String> keys) {
        ArrayList<Event> eventsArrayList = new ArrayList<>();
        JSONArray eventsTofilter = (JSONArray) objectToFilter.get("list_events");
        for (Object eventTemp : eventsTofilter) {
            Event event = (Event) eventTemp;
            if (checkFilters(keys, event))
                eventsArrayList.add(event);
        }
        filteredEvents.put("list_filtered_events", eventsArrayList);
        filteredEvents.put("found_filtered_events", eventsArrayList.size());
        return filteredEvents;
    }
}

