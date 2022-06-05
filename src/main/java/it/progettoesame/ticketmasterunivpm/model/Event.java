package it.progettoesame.ticketmasterunivpm.model;


public class Event {

    private String name;
    private String id;
    private String url;
    private String country;
    private String city;
    private String venue;
    private String localdate;
    private String localtime;
    private String segment;
    private String genre;
    private String subgenre;

    public Event(String name, String id, String url, String country, String city, String venue, String localdate, String localtime, String segment, String genre, String subgenre) {
        this.name = name;
        this.id = id;
        this.url = url;
        this.country = country;
        this.city = city;
        this.venue = venue;
        this.localdate = localdate;
        this.localtime = localtime;
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

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getLocaldate() {
        return localdate;
    }

    public void setLocaldate(String localdate) {
        this.localdate = localdate;
    }

    public String getLocaltime() {
        return localtime;
    }

    public void setLocaltime(String localtime) {
        this.localtime = localtime;
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

    /*
    public String toString() {
        String message =
                "Name: " + name + "\n" +
                "Id: " + id + "\n" +
                "Url: " + url + "\n" +
                "localDate: " + localDate + "\n" +
                "localTime: " + localTime + "\n" +
                "Segment: " + segment + "\n" +
                "Genre: " + genre + "\n" +
                "SubGenre: " + subGenre + "\n\n";
        return message;
    }

     */
}
