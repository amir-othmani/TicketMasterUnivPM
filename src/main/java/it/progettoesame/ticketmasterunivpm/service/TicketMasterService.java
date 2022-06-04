package it.progettoesame.ticketmasterunivpm.service;


import it.progettoesame.ticketmasterunivpm.model.Event;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;


public class TicketMasterService {

    String url = "https://app.ticketmaster.com/discovery/v2/events.json?apikey=ytOGRTWK4lKDd4B9gvj8odbPaejuGh8V";

    ArrayList<Event> events = new ArrayList<>();

    private File response = new File("./resources/response.json");

    public String getJSONfromURL() throws IOException {
        try {
            InputStream input = new URL(url).openStream();
            JSONParser parser = new JSONParser();
            JSONObject result = (JSONObject) parser.parse(new InputStreamReader(input));
            FileWriter fileOut = new FileWriter(response);
            fileOut.write(result.toString());
            fileOut.close();
        } catch ( Exception e ) {
            e.printStackTrace();
            return "Failed to retrieve data from URL";
        }
        return "Local data refreshed successfully.";
    }


    public void JSONtoObject() throws IOException {

        try {
            JSONParser parser = new JSONParser();
            Object obj = null;
            obj = parser.parse(new FileReader(response));
            JSONObject tmp = (JSONObject) obj;
            JSONObject jsonObj = (JSONObject) tmp.get("_embedded");
            JSONArray jsonArr = (JSONArray) jsonObj.get("events");

            Iterator<Map.Entry> itr1 = null;
            Iterator itr2 = jsonArr.iterator();

            while (itr2.hasNext())
            {
                itr1 = ((Map) itr2.next()).entrySet().iterator();
                while (itr1.hasNext()) {
                    Map.Entry pair = itr1.next();
                    if(pair.getKey().toString().equals("name")){
                        events.add(new Event(pair.getValue().toString()));
                    }

                }
            }

        } catch ( ParseException e ) {
            e.printStackTrace();
        }
    }


    public ArrayList<Event> getNotFilteredEvents() {
        return events;
    }
}
