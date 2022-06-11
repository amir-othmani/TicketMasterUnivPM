package it.progettoesame.ticketmasterunivpm.model;

import java.time.LocalDate;


public class Event {

    private String name;
    private String id;
    private String url;
    private String country;
    private String city;
    private LocalDate local_date;
    private String segment;
    private String genre;
    private String subgenre;

    public Event(String name, String id, String url, String country, String city, LocalDate local_date, String segment, String genre, String subgenre) {
        this.name = name;
        this.id = id;
        this.url = url;
        this.country = country;
        this.city = city;
        this.local_date = local_date;
        this.segment = segment;
        this.genre = genre;
        this.subgenre = subgenre;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public LocalDate getLocal_date() {
        return local_date;
    }

    public String getSegment() {
        return segment;
    }

    public String getGenre() {
        return genre;
    }

    public String getSubgenre() {
        return subgenre;
    }
}
