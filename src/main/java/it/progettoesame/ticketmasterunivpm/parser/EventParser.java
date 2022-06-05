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


    public void objectParse(Iterator<Map.Entry> itr) {
        String name = null;
        String id = null;
        String url = null;
        while (itr.hasNext()) {
            Map.Entry pair = itr.next();
            switch (pair.getKey().toString()){
                case "name":
                    name = pair.getValue().toString();
                    break;
                case "id":
                    id = pair.getValue().toString();
                    break;
                case "url":
                    url = pair.getValue().toString();
                    break;
            }
        }
        events.add(new Event(name, id, url));
    }

    public void arrayParse(JSONArray jsonArr) {

        Iterator<Map.Entry> itr1 = null;
        Iterator itr2 = jsonArr.iterator();
        while (itr2.hasNext())
        {
            itr1 = ((Map) itr2.next()).entrySet().iterator();
            objectParse(itr1);
        }
    }
}
