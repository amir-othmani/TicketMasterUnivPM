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
        JSONArray eventsArr = (JSONArray) embedded1.get("events");

        for (int i = 0; i < eventsArr.size(); i++)
        {
            JSONObject eventoTemp = (JSONObject) eventsArr.get(i);
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
                        String segmentName = (String) segment.get("name");
                    JSONObject genre = (JSONObject) classificationsTemp.get("genre");
                        String genreName = (String) genre.get("name");
                    JSONObject subGenre = (JSONObject) classificationsTemp.get("subGenre");
                        String subGenreName = (String) subGenre.get("name");
                JSONObject embedded2 = (JSONObject) eventoTemp.get("_embedded");
                    JSONArray venues = (JSONArray) embedded2.get("venues");
                    JSONObject venue = (JSONObject) venues.get(0);
                        String venueName = (String) venue.get("name");
                        JSONObject city = (JSONObject) venue.get("city");
                            String cityName = (String) city.get("name");
                        JSONObject country = (JSONObject) venue.get("country");
                            String countryName = (String) city.get("name");
            events.add(new Event(name, id, url, countryName, cityName, venueName, localDate, localTime, segmentName, genreName, subGenreName));
        }
    }
}
