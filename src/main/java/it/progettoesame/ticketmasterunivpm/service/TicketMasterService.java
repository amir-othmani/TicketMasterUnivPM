package it.progettoesame.ticketmasterunivpm.service;

import it.progettoesame.ticketmasterunivpm.model.Event;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;

public class TicketMasterService {

    private final String url = "https://app.ticketmaster.com/discovery/v2/events.json?size=1&apikey=ytOGRTWK4lKDd4B9gvj8odbPaejuGh8V";

    private File response = new File("./resources/response.json");

    ArrayList<Event> events = new ArrayList<>();

    public String getJSONfromURL() throws IOException {
        try {
            InputStream input = new URL(url).openStream();
            JSONParser parser = new JSONParser();
            JSONObject result = (JSONObject) parser.parse(new InputStreamReader(input));
            FileWriter fileOut = new FileWriter(response);
            fileOut.write(result.toString());
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to retrieve data from URL";
        }
        this.JSONtoObject();
        return "Local data refreshed successfully.";
    }

    public void JSONtoObject() throws IOException {
        /*
        PARTE IN MANUTENZIONE:

        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(response));
            JSONObject jsonObj = (JSONObject) obj;
            JSONArray jsonArr = (JSONArray) jsonObj.get("data");
            events.clear();

            for (int i = 0; i < jsonArr.size(); i++) {
                JSONObject temp = (JSONObject) jsonArr.get(i);
                String name = (String) temp.get("name");
                if (temp.get("name") == null) {
                    events.add(new Event(name));
                } else {
                    break;
                }
            }
        }
        catch ( FileNotFoundException | ParseException e ) {
            throw new IOException();
        }


         */
    }
}
