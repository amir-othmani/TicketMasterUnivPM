package it.progettoesame.ticketmasterunivpm.stats;


import it.progettoesame.ticketmasterunivpm.model.Event;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Map;

public class EventsStats {

    final private int[] counters = {0, 0, 0, 0, 0, 0, 0};
    final private String[] days = {"monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday"};

    public JSONObject statsPerWeek(ArrayList<Event> events, Map<String, String> param) {
        JSONObject stats = new JSONObject();
        if (events.isEmpty()) {
            stats.put("events_not_found", "there are no statistics to get");
            return stats;
        }
        for (Event e: events) {
            int dayValue = e.getLocal_date().getDayOfWeek().getValue();
            counters[dayValue-1]++;
        }
        stats.put("country", events.get(0).getCountry());
        if (param.containsKey("city"))
            stats.put("city", param.get("city"));
        stats.put("num_events_found", events.size());
        double avr = Math.round(events.size()/7.0*100.0)/100.0;
        stats.put("num_average_events", avr);
        int min = indexOfMin(counters);
        stats.put("min_events", buildJsonObj(counters[min], days[min]));
        int max = indexOfMax(counters);
        stats.put("max_events", buildJsonObj(counters[max], days[max]));
        return stats;
    }

    public int indexOfMax(int[] arr)
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

    public int indexOfMin(int[] arr)
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

    public JSONObject buildJsonObj(int number, String day) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("num", number);
        jsonObject.put("day", day);
        return jsonObject;
    }
}
