package it.progettoesame.ticketmasterunivpm.exceptions;

public class MissingCountryException extends Exception {

    public MissingCountryException() {
        super("Please select a european country");
    }
}
