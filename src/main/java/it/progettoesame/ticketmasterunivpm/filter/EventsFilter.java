package it.progettoesame.ticketmasterunivpm.filter;

import it.progettoesame.ticketmasterunivpm.exceptions.FilterMismatchException;

import it.progettoesame.ticketmasterunivpm.model.Event;

import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Classe che si occupa del filtraggio degli eventi
 *
 * @author amir-othmani
 */
public class EventsFilter {

    final private JSONObject filteredEvents = new JSONObject();
    final private ArrayList<Event> listFilteredEvents = new ArrayList<>();

    /**
     * Questo metodo controlla se i parametri inseriti dall'utente corrispondono a quelli dell'evento preso in esame
     * (di modo che l'evento possa essere aggiunto alla lista degli eventi filtrati)
     *
     * @param param i parametri inseriti dall'utente
     * @param e l'evento preso in esame
     * @return boolean
     *
     * @author amir-othmani
     */
    private boolean checkFilters(HashMap<String, String> param, Event e) {
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

    /**
     * Questo metodo costruisce la lista degli eventi filtrati in base ai parametri selezionati dall'utente
     *
     * @param eventsToFilter gli eventi da filtrare
     * @param parameters i parametri inseriti dall'utente
     *
     * @author amir-othmani
     */
    public void buildFilteredEvents(ArrayList<Event> eventsToFilter, HashMap<String, String> parameters) {
        listFilteredEvents.clear();
        for (Event event: eventsToFilter) {
            if (checkFilters(parameters, event))
                listFilteredEvents.add(event);
        }
    }

    /**
     * Questo metodo restituisce un JSONObject contenente la lista degli eventi filtrati e il loro numero.
     * Se però non c'è nessun evento che corrisponde ai parametri selezionati, il JSONObject conterrà un messaggio
     * d'errore
     *
     * @param selectedParam i parametri inseriti dall'utente
     * @param eventsToFilter gli eventi da filtrare
     * @return un JSONObject contente gli eventi filtrati o un messaggio d'errore
     *
     * @author amir-othmani
     */
    public JSONObject filterEvents (HashMap<String, String> selectedParam, ArrayList<Event> eventsToFilter) {
        try {
            filteredEvents.clear();
            buildFilteredEvents(eventsToFilter, selectedParam);
            if (listFilteredEvents.isEmpty())
                throw new FilterMismatchException();
            filteredEvents.put("list_filtered_events", listFilteredEvents);
            filteredEvents.put("num_filtered_events", listFilteredEvents.size());
            return filteredEvents;
        }
        catch ( Exception e ) {
            filteredEvents.put("filtered_events_not_found", e.getMessage());
            return filteredEvents;
        }
    }

    /**
     * Questo metodo restituisce la lista deggli eventi filtrati
     *
     * @return un ArrayList degli eventi filtrati
     *
     * @author amir-othmani
     */
    public ArrayList<Event> getListFilteredEvents() {
        return listFilteredEvents;
    }
}

