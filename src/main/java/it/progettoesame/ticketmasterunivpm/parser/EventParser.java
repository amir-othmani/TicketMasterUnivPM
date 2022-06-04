package it.progettoesame.ticketmasterunivpm.parser;

import it.progettoesame.ticketmasterunivpm.model.Event;

import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class EventParser {

    private ArrayList<Event> events = new ArrayList<>();

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }

    public void keyValueParse(JSONArray jsonArr) {
        Iterator<Map.Entry> itr1 = null;
        Iterator itr2 = jsonArr.iterator();
        while (itr2.hasNext())
        {
            itr1 = ((Map) itr2.next()).entrySet().iterator();
            String name = null;
            String type = null;
            while (itr1.hasNext()) {
                Map.Entry pair = itr1.next();
                if(pair.getKey().toString().equals("name")){
                    name = pair.getValue().toString();
                }
                if(pair.getKey().toString().equals("type")){
                    type = pair.getValue().toString();
                }
            }
            events.add(new Event(name, type));
        }
    }
}
