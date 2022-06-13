# TicketMasterUnivPM
Questo programma consiste in un'applicazione SpringBoot che si occupa di richiamare gli eventi che avranno luogo in Europa gestiti dal sito di prenotazione biglietti [TicketMaster](https://www.ticketmaster.it). In particolare, attraverso l'Application Programming Interface (API), il programma restituirà gli eventi in base ai parametri inseriti dall'utente (che verranno riportati in seguito). Il programma può inoltre restituire delle statistiche riguardante il numero degli eventi che si verificano in ogni città e la loro distribuzione lungo il periodo settimanale.

## Indice
* Rotte disponibili
  * /events
  * /stats
* Eccezioni che il programma può restituire
  * quando il paese selezionato non è europeo
  * quando il paese selezionato non contiene eventi
  * quando il paese selezionato è incompatibile con il modello del programma
  * quando l'utente seleziona dei parametri non validi
  * quando non ci sono eventi che corripsondono ai parametri selezionati
* Credits

## Rotte disponibili
Per poter utilizzare il programma è sufficiente clonare la repository in una cartella locale ed eseguirlo tramite un IDE che sia in grado di leggere il linguaggio Java e che supporti SpringBoot insieme alle sue dipendenze. Dopodiché, si possono utilizzare le rotte disponibili aprendo l'applicazione Postman tramite delle richieste di tipo GET all'indirizzo "localhost:8080".

### Rotta "/events"
Questa rotta permette di ricavare gli eventi di un determinato paese europeo e lo si può scegliere dichiarandolo come parametro countryCode (**ATTENZIONE**: l'URL con la quale si effettua la richiesta è case sensitive) come nel seguente esempio:

#### localhost:8080?countryCode=DE

    {
       "num_events_found": 200,
       "list_events_found": [
             {
                 "name": "Imagine Dragons | Ultimate Imagine Dragons Fan Experience",
                 "id": "Z698xZC2Z178z7M",
                 "url": "https://www.ticketmaster.de/event/imagine-dragons-%7C-ultimate-imagine-dragons-fan-experience-tickets/438137?language=en-us",
                 "country": "Germany",
                 "city": "Hannover",
                 "local_date": "2022-06-14",
                 "segment": "Music",
                 "genre": "Rock",
                 "subgenre": "Pop"
             },
             {
                 "name": "Tampa Bay Buccaneers vs. Seattle Seahawks",
                 "id": "Z7r9jZ1Ad8Oay",
                 "url": "https://www.ticketmaster.com/event/Z7r9jZ1Ad8Oay",
                 "country": "Germany",
                 "city": "Munich",
                 "local_date": "2022-11-13",
                 "segment": "Sports",
                 "genre": "Football",
                 "subgenre": "NFL"
             },
             {
                 "name": "ARISE Grand Show",
                 "id": "Z698xZC2Z17oFPv",
                 "url": "https://www.ticketmaster.de/event/arise-grand-show-tickets/448941?language=en-us",
                 "country": "Germany",
                 "city": "Berlin",
                 "local_date": "2022-06-14",
                 "segment": "Arts & Theatre",
                 "genre": "Spectacular",
                 "subgenre": "Spectacular"
             },
             { (ecc.)
             },
    }    
    
I paesi selezionabili sonon riportati di seguito in questa tabella:
|**countryCode**|**paese di riferimento**|
|-|-|
|AL|Albania|



{"AL", "AT", "BE", "BG", "CH", "CY", "CZ", "DE", "DK", "EE", "ES", "FO",
            "FI", "FR", "GB", "GR", "HR", "HU", "IE", "IS", "IT", "LT", "LU", "MC", "ME", "MT", "ND", "NL", "NO", "PL",
            "PT", "RO", "RS", "SE", "SK", "SI", "TR", "UA"}

L'utente, se lo desidera, può cercare gli eventi in base ai parametri che gli interessano
