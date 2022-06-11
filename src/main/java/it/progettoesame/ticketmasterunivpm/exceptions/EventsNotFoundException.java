package it.progettoesame.ticketmasterunivpm.exceptions;

/**
 * Questa eccezione viene lanciata quando dalla chiamata API risulta non esserci nessun evento nel paese selezionato
 *
 * @author amir-othmani
 */
public class EventsNotFoundException extends Exception {

    public EventsNotFoundException() {
        super("we're sorry, but there are no available events in this country");
    }
}
