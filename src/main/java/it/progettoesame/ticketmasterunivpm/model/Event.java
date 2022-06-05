package it.progettoesame.ticketmasterunivpm.model;


import java.util.ArrayList;

public class Event {

    private String name;
    private String id;
    private String url;



    public Event(String name, String id, String url) {
        this.name = name;
        this.id = id;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String toString() {
        String message =
                "Name: " + name + "\n" +
                "Id: " + id + "\n" +
                "Url: " + url + "\n\n";
        return message;
    }
}
