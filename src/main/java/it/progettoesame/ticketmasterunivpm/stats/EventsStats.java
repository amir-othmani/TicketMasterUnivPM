package it.progettoesame.ticketmasterunivpm.stats;

import it.progettoesame.ticketmasterunivpm.exceptions.FilterMismatchException;
import it.progettoesame.ticketmasterunivpm.model.Event;
import org.json.simple.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;


public class EventsStats {

    final private int[] counters = {0, 0, 0, 0, 0, 0, 0};
    final private String[] days = {"monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday"};

    /**
     * Questo metodo ricava l'indice dell'elemento dell'array dal valore masssimo
     *
     * @param arr l'array da cui bisogna ricavare l'indice
     * @return un numero intero che sarebbe appunto l'indice
     *
     * @author amir-othmani
     */
    private int indexOfMax(int[] arr)
    {
        int index=0;
        int max = arr[0];
        for (int i=1; i<arr.length; i++)
            if (arr[i] > max) {
                max = arr[i];
                index = i;
            }
        return index;
    }

    /**
     * Questo metodo ricava l'indice dell'elemento dell'array dal valore minimo
     *
     * @param arr l'array da cui bisogna ricavare l'indice
     * @return un numero intero che sarebbe appunto l'indice
     *
     * @author amir-othmani
     */
    private int indexOfMin(int[] arr)
    {
        int index=0;
        int min = arr[0];
        for (int i=1; i<arr.length; i++)
            if (arr[i] < min) {
                min = arr[i];
                index = i;
            }
        return index;
    }

    /**
     * Questo costruisce un JSONObject contenente il giorno in cui si verifica il numero massimo (oppure minimo) di
     * eventi insieme al numero stesso
     *
     * @param number il numero massimo (oppure minimo)
     * @param day il giorno
     * @return un JSONObject
     *
     * @author amir-othmani
     */
    private JSONObject buildJsonObj(int number, String day) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("num", number);
        jsonObject.put("day", day);
        return jsonObject;
    }

    /**
     * Questo metodo azzera l'array dei contatori settimanali
     * @param arr l'array da azzerare
     *
     * @author amir-othmani
     */
    private void countersReset(int[] arr) {
        for (int i=0; i<arr.length; i++)
            arr[i] = 0;
    }

    /**
     * Questo metodo ricava le statistiche settimanali di una determinata città basandosi sugli eventi che gli sono
     * stati passati come parametro.
     * Questo metodo si serve di un array di 7 contatori che incrementano in base al giorno della settimana in cui
     * occorrono gli eventi presi in esame.
     *
     * @param events gli eventi da cui ricavare le statistiche
     * @param paramCity il parametro che contiene la città di riferimento
     * @return un JSONObject che contiene le statistiche ricavate
     *
     * @author amir-othmani
     */
    public JSONObject statsPerWeek(ArrayList<Event> events, HashMap<String, String> paramCity) {
        JSONObject stats = new JSONObject();
        try {
            if (events.isEmpty()) {
                throw new FilterMismatchException();
            }
            for (Event e: events) {
                int dayValue = e.getLocal_date().getDayOfWeek().getValue();
                counters[dayValue-1]++;
            }
            if (paramCity.containsKey("countryCode"))
                stats.put("country", events.get(0).getCountry());
            stats.put("city", paramCity.get("city"));
            stats.put("num_events_found", events.size());
            double avr = Math.round(events.size()/7.0*100.0)/100.0;
            stats.put("num_average_events", avr);
            int min = indexOfMin(counters);
            stats.put("min_events", buildJsonObj(counters[min], days[min]));
            int max = indexOfMax(counters);
            stats.put("max_events", buildJsonObj(counters[max], days[max]));
        }
        catch ( Exception e ) {
            stats.put("events_not_found", e.getMessage());
        }
        countersReset(counters);
        return stats;
    }
}
