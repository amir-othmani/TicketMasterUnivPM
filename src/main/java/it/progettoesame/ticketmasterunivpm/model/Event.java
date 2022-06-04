package it.progettoesame.ticketmasterunivpm.model;

import java.util.ArrayList;

public class Event {

    private String name;
    private String type;

    public Event(String name, String segment) {
        this.name = name;
        this.type = segment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String segment) {
        this.type = segment;
    }

    public String toString() {
        String message =
                "Name: " + name + "\n" +
                "Type:" + type + "\n\n";
        return message;
    }
}
