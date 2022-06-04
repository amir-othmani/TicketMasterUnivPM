package it.progettoesame.ticketmasterunivpm.service;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.URL;


public class TicketMasterService {


    String url = "https://app.ticketmaster.com/discovery/v2/events.json?size=2&apikey=ytOGRTWK4lKDd4B9gvj8odbPaejuGh8V";

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
        this.JSONtoObject();
        return "Local data refreshed successfully.";
    }


    public void JSONtoObject() throws IOException {

        try {
            JSONParser parser = new JSONParser();
            Object obj = null;
            obj = parser.parse(new FileReader(response));
            JSONObject jsonObj = (JSONObject) obj;

        } catch ( ParseException e ) {
            e.printStackTrace();
        }
    }

    public JSONObject getNotFilteredEvents() {
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(response));
            JSONObject jsonObj = (JSONObject) obj;
            return jsonObj;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;    //se il programma arriva fino a qui, c'è un errore
    }
}
