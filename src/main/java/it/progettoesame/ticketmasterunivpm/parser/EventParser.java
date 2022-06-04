package it.progettoesame.ticketmasterunivpm.parser;

import it.progettoesame.ticketmasterunivpm.model.Event;
import it.progettoesame.ticketmasterunivpm.service.TicketMasterService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class EventParser {

    private ArrayList<Event> events = new ArrayList<>();

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }

    public void keyValueParse(JSONArray jsonArr) {
        Iterator<Map.Entry> itr1 = null;
        Iterator itr2 = jsonArr.iterator();
        while (itr2.hasNext())
        {
            itr1 = ((Map) itr2.next()).entrySet().iterator();
            String name = null;
            String type = null;
            while (itr1.hasNext()) {
                Map.Entry pair = itr1.next();
                if(pair.getKey().toString().equals("name")){
                    name = pair.getValue().toString();
                }
                if(pair.getKey().toString().equals("type")){
                    type = pair.getValue().toString();
                }
            }
            events.add(new Event(name, type));

        }

    }

    public void JSONtoObject(TicketMasterService s) throws IOException {

        try {
            JSONParser parser = new JSONParser();
            Object obj = null;
            obj = parser.parse(new FileReader(s.getResponse()));
            JSONObject tmp = (JSONObject) obj;
            JSONObject jsonObject = (JSONObject) tmp.get("_embedded");
            JSONArray jsonArray = (JSONArray) jsonObject.get("events");
            keyValueParse(jsonArray);
        }
        catch ( ParseException e ) {
            e.printStackTrace();
        }
    }
}
