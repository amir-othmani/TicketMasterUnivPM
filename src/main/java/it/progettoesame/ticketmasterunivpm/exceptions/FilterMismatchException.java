package it.progettoesame.ticketmasterunivpm.exceptions;

/**
 * Questa eccezione viene lanciata quando non c'è nessun evento che corrisponde ai parametri inseriti dall'utente
 *
 * @author amir-othmani
 */
public class FilterMismatchException extends Exception {

    public FilterMismatchException() {
        super("there are no events that match the seleceted filters");
    }
}
