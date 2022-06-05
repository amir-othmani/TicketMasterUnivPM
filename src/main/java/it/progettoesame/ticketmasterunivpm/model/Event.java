package it.progettoesame.ticketmasterunivpm.model;


public class Event {

    private String name;
    private String id;
    private String url;
    private String localDate;
    private String localTime;
    private String segment;
    private String genre;
    private String subGenre;

    public Event(String name, String id, String url, String localDate, String localTime, String segment, String genre, String subGenre) {
        this.name = name;
        this.id = id;
        this.url = url;
        this.localDate = localDate;
        this.localTime = localTime;
        this.segment = segment;
        this.genre = genre;
        this.subGenre = subGenre;
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

    public String getLocalDate() {
        return localDate;
    }

    public void setLocalDate(String localDate) {
        this.localDate = localDate;
    }

    public String getLocalTime() {
        return localTime;
    }

    public void setLocalTime(String localTime) {
        this.localTime = localTime;
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

    public String getSubGenre() {
        return subGenre;
    }

    public void setSubGenre(String subGenre) {
        this.subGenre = subGenre;
    }

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
}
