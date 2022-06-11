package it.progettoesame.ticketmasterunivpm.exceptions;

/**
 * Questa eccezione viene lanciata nel momento in cui gli eventi trovati dalla chiamata API non possono essere
 * restituiti dal programma perché incompatibili rispetto agli attributi del model
 *
 * @author amir-othmani
 */
public class EventParseExcpetion extends Exception {

    public EventParseExcpetion() {
        super("We're sorry, but we could not get the events in this country");
    }
}
