package it.progettoesame.ticketmasterunivpm.parser;

import it.progettoesame.ticketmasterunivpm.model.Event;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;


public class EventsParser {

    private ArrayList<Event> events = new ArrayList<>();

    private JSONObject tappetoVolante = new JSONObject();

    public JSONObject getTappetoVolante() {
        return tappetoVolante;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }

    //Metodo che costruisce il singolo evento
    public Event parseEvent(JSONObject jsonObject) {
        String name = (String) jsonObject.get("name");
        String id = (String) jsonObject.get("id");
        String url = (String) jsonObject.get("url");
        JSONObject dates = (JSONObject) jsonObject.get("dates");
            JSONObject start = (JSONObject) dates.get("start");
                String localDate = (String) start.get("localDate");
                String localTime = (String) start.get("localTime");
        JSONArray classifications = (JSONArray) jsonObject.get("classifications");
        JSONObject classificationsTemp = (JSONObject) classifications.get(0);
            JSONObject segment = (JSONObject) classificationsTemp.get("segment");
                String segmentName = (String) segment.get("name");
            JSONObject genre = (JSONObject) classificationsTemp.get("genre");
                String genreName = (String) genre.get("name");
            JSONObject subGenre = (JSONObject) classificationsTemp.get("subGenre");
                String subGenreName = (String) subGenre.get("name");
        JSONObject embedded2 = (JSONObject) jsonObject.get("_embedded");
            JSONArray venues = (JSONArray) embedded2.get("venues");
            JSONObject venue = (JSONObject) venues.get(0);
                String venueName = (String) venue.get("name");
                JSONObject city = (JSONObject) venue.get("city");
                    String cityName = (String) city.get("name");
                JSONObject country = (JSONObject) venue.get("country");
                    String countryName = (String) country.get("name");
        return new Event(name, id, url, countryName, cityName, venueName, localDate, localTime, segmentName, genreName, subGenreName);
    }

    //Metodo che raggruppa gli eventi
    public void parseEventsArray(Object o) {
        JSONObject jO= (JSONObject) o;
        JSONObject embedded1 = (JSONObject) jO.get("_embedded");
        JSONArray eventsArray = (JSONArray) embedded1.get("events");
        for (Object value : eventsArray) {
            JSONObject eventoTemp = (JSONObject) value;
            events.add(parseEvent(eventoTemp));
        }
        tappetoVolante.put("list events", events);
        JSONObject page = (JSONObject) jO.get("page");
            Number numberEvents = (Number) page.get("size");
        tappetoVolante.put("number events", numberEvents);

    }
}
