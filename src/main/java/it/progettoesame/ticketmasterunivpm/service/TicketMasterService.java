package it.progettoesame.ticketmasterunivpm.service;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.*;
import java.net.URL;

public class TicketMasterService {

    private final String url = "https://app.ticketmaster.com/discovery/v2/events.json?size=1&apikey=ytOGRTWK4lKDd4B9gvj8odbPaejuGh8V";

    private File response = new File("./resources/response.json");

    public String getJSONfromURL() {
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

    public void JSONtoObject()  {

        System.out.println("\n\nMETODO IN MANUTENZIONE\n");
    }
}
