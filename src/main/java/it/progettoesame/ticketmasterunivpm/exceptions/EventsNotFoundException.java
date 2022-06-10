package it.progettoesame.ticketmasterunivpm.exceptions;

public class EventsNotFoundException extends Exception {

    public EventsNotFoundException() {
        super("we're sorry, but there are no available events in this country");
    }
}
