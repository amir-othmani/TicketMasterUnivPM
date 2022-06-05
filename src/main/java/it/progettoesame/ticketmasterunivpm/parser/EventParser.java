package it.progettoesame.ticketmasterunivpm.parser;

import it.progettoesame.ticketmasterunivpm.model.Event;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;


public class EventParser {

    private ArrayList<Event> events = new ArrayList<>();

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }

    public void parseEvents(Object o) {
        JSONObject jO= (JSONObject) o;
        JSONObject embedded1 = (JSONObject) jO.get("_embedded");
        JSONArray events1 = (JSONArray) embedded1.get("events");

        for (int i = 0; i < events1.size(); i++)
        {
            JSONObject eventoTemp = (JSONObject) events1.get(i);

            String name = (String) eventoTemp.get("name");
            String id = (String) eventoTemp.get("id");
            String url = (String) eventoTemp.get("url");
            JSONObject dates = (JSONObject) eventoTemp.get("dates");
                JSONObject start = (JSONObject) dates.get("start");
                    String localDate = (String) start.get("localDate");
                    String localTime = (String) start.get("localTime");

            JSONArray classifications = (JSONArray) eventoTemp.get("classifications");
            JSONObject classificationsTemp = (JSONObject) classifications.get(0);
                JSONObject segment = (JSONObject) classificationsTemp.get("segment");
                    String nameSegment = (String) segment.get("name");
                JSONObject genre = (JSONObject) classificationsTemp.get("genre");
                    String nameGenre = (String) genre.get("name");
            JSONObject subGenre = (JSONObject) classificationsTemp.get("subGenre");
            String nameSubGenre = (String) subGenre.get("name");

            events.add(new Event(name, id, url, localDate, localTime, nameSegment, nameGenre, nameSubGenre));
        }
    }
}
