package it.progettoesame.ticketmasterunivpm.parser;

import it.progettoesame.ticketmasterunivpm.model.Event;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.time.LocalDate;
import java.util.ArrayList;


public class EventsParser {

    final private ArrayList<Event> notFilteredEvents = new ArrayList<>();

    public ArrayList<Event> getNotFilteredEvents() {
        return notFilteredEvents;
    }

    public int getNumEvents() {
        return notFilteredEvents.size();
    }

    //Metodo che costruisce il singolo evento
    public Event parseEvent(JSONObject jsonObject) {
        String name = (String) jsonObject.get("name");
        String id = (String) jsonObject.get("id");
        String url = (String) jsonObject.get("url");
        JSONObject dates = (JSONObject) jsonObject.get("dates");
            JSONObject start = (JSONObject) dates.get("start");
                String localDate = (String) start.get("localDate");
                LocalDate locDt = LocalDate.parse(localDate);
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
                JSONObject city = (JSONObject) venue.get("city");
                    String cityName = (String) city.get("name");
                JSONObject country = (JSONObject) venue.get("country");
                    String countryName = (String) country.get("name");
        return new Event(name, id, url, countryName, cityName, locDt, segmentName, genreName, subGenreName);
    }



    //Metodo che raggruppa gli eventi e li restituisce insieme al numero di eventi
    public void buildEventsArray(JSONObject o) {
        notFilteredEvents.clear();
        JSONObject embedded1 = (JSONObject) o.get("_embedded");
        if (embedded1 == null)
            return;
        JSONArray eventsArray = (JSONArray) embedded1.get("events");
        for (Object value : eventsArray) {
            JSONObject eventoTemp = (JSONObject) value;
            notFilteredEvents.add(parseEvent(eventoTemp));
        }
    }
}
