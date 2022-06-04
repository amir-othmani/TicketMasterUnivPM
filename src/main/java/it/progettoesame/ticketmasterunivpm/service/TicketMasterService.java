package it.progettoesame.ticketmasterunivpm.service;


import it.progettoesame.ticketmasterunivpm.model.Event;
import it.progettoesame.ticketmasterunivpm.parser.EventParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;


public class TicketMasterService {

    String url = "https://app.ticketmaster.com/discovery/v2/events.json?size=2&apikey=ytOGRTWK4lKDd4B9gvj8odbPaejuGh8V";



    private File response = new File("./resources/response.json");

    public File getResponse() {
        return response;
    }

    public void setResponse(File response) {
        this.response = response;
    }

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


    public ArrayList<Event> getNotFilteredEvents(EventParser p) {
        return p.getEvents();
    }
}
