package it.progettoesame.ticketmasterunivpm.parser;

import it.progettoesame.ticketmasterunivpm.exceptions.EventParseExcpetion;
import it.progettoesame.ticketmasterunivpm.model.Event;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Classe che si occupa del parsing degli eventi
 */
public class EventsParser {

    final private ArrayList<Event> notFilteredEvents = new ArrayList<>();
    private boolean parsingSuccess;

    /**
     * Questo metodo si occupa del parsing del singolo evento risultante dalla chiamata API originale
     *
     * @param jsonEvent il singolo evento da estrapolare
     * @return un nuovo oggetto della classe Event contenente i dati dell'evento appena ottenuto
     *
     * @author amir-othmani
     */
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

    /**
     * Questo metodo costruisce la lista di tutti gli eventi estrapolati dalla chiamata API originale e da restituire
     * all'utente
     *
     * @param jsonObject il JSONObject ottenuto dalla chiamata API
     * @throws EventParseExcpetion se accadono problemi durante il parsing degli eventi a causa dell'incompatitbilità
     *                              con il model
     *
     * @author amir-othmani
     */
    public void buildEventsArray(JSONObject jsonObject) throws EventParseExcpetion {
        parsingSuccess = true;
        notFilteredEvents.clear();
        JSONObject embedded1 = (JSONObject) jsonObject.get("_embedded");
        if (embedded1 == null)
            return;
        JSONArray eventsArray = (JSONArray) embedded1.get("events");
        for (Object value : eventsArray) {
            JSONObject eventoTemp = (JSONObject) value;
            if (parseEvent((eventoTemp)) == null) {
                notFilteredEvents.clear();
                parsingSuccess = false;
                throw new EventParseExcpetion();
            }
            notFilteredEvents.add(parseEvent(eventoTemp));
        }
    }

    /**
     * Questo metodo restituisce tutti gli eventi trovati in un singolo paese
     *
     * @return un ArrayList degli eventi trovati
     *
     * @author amir-othmani
     */
    public ArrayList<Event> getNotFilteredEvents() {
        return notFilteredEvents;
    }

    /**
     * Metodo che all'occorrenza dichiara se il parsing ha avuto successo oppure no
     *
     * @return un valore booleano
     *
     * @author amir-othmani
     */
    public boolean isParsingSuccess() {
        return parsingSuccess;
    }
}
