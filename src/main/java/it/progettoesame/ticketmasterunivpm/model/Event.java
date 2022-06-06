package it.progettoesame.ticketmasterunivpm.model;


//Il model che contiene tutti gli attributi dell'evento
public class Event {

    private String name;
    private String id;
    private String url;
    private String country;
    private String city;
    private String local_date;
    private String segment;
    private String genre;
    private String subgenre;

    public Event(String name, String id, String url, String country, String city, String local_date, String segment, String genre, String subgenre) {
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

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocal_date() {
        return local_date;
    }

    public void setLocal_date(String local_date) {
        this.local_date = local_date;
    }

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSubgenre() {
        return subgenre;
    }

    public void setSubgenre(String subgenre) {
        this.subgenre = subgenre;
    }

}
