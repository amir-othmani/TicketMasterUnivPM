package it.progettoesame.ticketmasterunivpm.exceptions;

/**
 * Questa eccezione viene lanciata quando l'utente inserisce il codice di un paese al di fuori dell'Europa
 * oppure un codice che non corrisponde a nessun paese
 *
 * @author amir-othmani
 */
public class NotSupportedCountryException extends Exception {

    public NotSupportedCountryException() {
        super("Please insert a european (and valid) country-code");
    }
}
