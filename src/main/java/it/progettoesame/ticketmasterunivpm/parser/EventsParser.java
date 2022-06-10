package it.progettoesame.ticketmasterunivpm.parser;


import it.progettoesame.ticketmasterunivpm.exceptions.EventParseExcpetion;
import it.progettoesame.ticketmasterunivpm.model.Event;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.time.LocalDate;
import java.util.ArrayList;


public class EventsParser {

    final private ArrayList<Event> notFilteredEvents = new ArrayList<>();

    private Event parseEvent(JSONObject jsonEvent) {
        try {
            String name = (String) jsonEvent.get("name");
            String id = (String) jsonEvent.get("id");
            String url = (String) jsonEvent.get("url");
            JSONObject dates = (JSONObject) jsonEvent.get("dates");
            JSONObject start = (JSONObject) dates.get("start");
            String localDate = (String) start.get("localDate");
            LocalDate locDt = LocalDate.parse(localDate);
            JSONArray classifications = (JSONArray) jsonEvent.get("classifications");
            JSONObject classificationsTemp = (JSONObject) classifications.get(0);
            JSONObject segment = (JSONObject) classificationsTemp.get("segment");
            String segmentName = (String) segment.get("name");
            JSONObject genre = (JSONObject) classificationsTemp.get("genre");
            String genreName = (String) genre.get("name");
            JSONObject subGenre = (JSONObject) classificationsTemp.get("subGenre");
            String subGenreName = (String) subGenre.get("name");
            JSONObject embedded2 = (JSONObject) jsonEvent.get("_embedded");
            JSONArray venues = (JSONArray) embedded2.get("venues");
            JSONObject venue = (JSONObject) venues.get(0);
            JSONObject city = (JSONObject) venue.get("city");
            String cityName = (String) city.get("name");
            JSONObject country = (JSONObject) venue.get("country");
            String countryName = (String) country.get("name");
            return new Event(name, id, url, countryName, cityName, locDt, segmentName, genreName, subGenreName);
        }
        catch ( Exception e ) {
            return null;
        }
    }

    public void buildEventsArray(JSONObject jsonObject) throws EventParseExcpetion {
        notFilteredEvents.clear();
        JSONObject embedded1 = (JSONObject) jsonObject.get("_embedded");
        if (embedded1 == null)
            return;
        JSONArray eventsArray = (JSONArray) embedded1.get("events");
        for (Object value : eventsArray) {
            JSONObject eventoTemp = (JSONObject) value;
            if (parseEvent((eventoTemp)) == null)
                throw new EventParseExcpetion();
            notFilteredEvents.add(parseEvent(eventoTemp));
        }
    }

    public ArrayList<Event> getNotFilteredEvents() {
        return notFilteredEvents;
    }
}
