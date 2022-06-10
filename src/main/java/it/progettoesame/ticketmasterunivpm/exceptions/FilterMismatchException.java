package it.progettoesame.ticketmasterunivpm.exceptions;

public class FilterMismatchException extends Exception {

    public FilterMismatchException() {
        super("there are no events that match the seleceted filters");
    }
}
