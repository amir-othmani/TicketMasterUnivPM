package it.progettoesame.ticketmasterunivpm.stats;


import it.progettoesame.ticketmasterunivpm.model.Event;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Map;

public class EventsStats {

    final private int[] counters = {0, 0, 0, 0, 0, 0, 0};
    final private String[] days = {"monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday"};
    final private JSONObject stats = new JSONObject();

    public JSONObject statsPerWeek(ArrayList<Event> events, Map<String, String> param) {
        for (Event e: events) {
            int dayValue = e.getLocal_date().getDayOfWeek().getValue();
            counters[dayValue-1]++;
        }
        int max = indexOfMax(counters);
        int min = indexOfMin(counters);
        double avr = Math.round(events.size()/7.0*100.0)/100.0;
        stats.put("country", events.get(0).getCountry());
        if (param.containsKey("city"))
            stats.put("city", events.get(0).getCity());
        stats.put("num_average_events", avr);
        stats.put("min_events", buildJsonObj(counters[min], days[min]));
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
