# TicketMasterUnivPM
Questo programma consiste in un'applicazione SpringBoot che si occupa di richiamare gli eventi che avranno luogo in Europa gestiti dal sito di prenotazione biglietti [TicketMaster](https://www.ticketmaster.it). In particolare, attraverso l'Application Programming Interface (API), il programma restituirà gli eventi in base ai parametri inseriti dall'utente (che verranno riportati in seguito). Il programma può inoltre restituire delle statistiche riguardante il numero degli eventi che si verificano in ogni città e la loro distribuzione lungo il periodo settimanale.

## Indice
* Rotte disponibili
  * /events
  * /stats
* Eccezioni che il programma può restituire
  * Quando il paese selezionato non è europeo
  * Quando il paese selezionato non contiene eventi
  * Quando il paese selezionato è incompatibile con il modello del programma
  * Quando l'utente seleziona dei parametri non validi
  * Quando non ci sono eventi che corripsondono ai parametri selezionati
* Credits

## Rotte disponibili
Per poter utilizzare il programma è sufficiente clonare la repository in una cartella locale ed eseguirlo tramite un IDE che sia in grado di leggere il linguaggio Java e che supporti SpringBoot insieme alle sue dipendenze. Dopodiché, si possono utilizzare le rotte disponibili aprendo l'applicazione Postman tramite delle richieste di tipo GET all'indirizzo "localhost:8080".

### Rotta "/events"
Questa rotta permette di ricavare gli eventi di un determinato paese europeo e lo si può scegliere dichiarandolo come parametro countryCode (**ATTENZIONE**: l'URL con la quale si effettua la richiesta è case sensitive) come nel seguente esempio.

#### Esempio: localhost:8080?countryCode=DE

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
    
I paesi selezionabili sono riportati di seguito in questa tabella:

|**countryCode**|**Paese di riferimento**|
|-|-|
|AT|Austria|
|BE|Belgio|
|BG|Bulgaria|
|CH|Svizzera|
|CY|Cipro|
|CZ|Repubblica Ceca|
|DE|Germania|
|DK|Danimarca|
|EE|Estonia|
|ES|Spagna|
|FI|Finlandia|
|FO|Isole Faroe|
|FR|Francia|
|GB|Gran Bretagna|
|GR|Grecia|
|HR|Croazia|
|HU|Ungheria|
|IE|Irlanda|
|IS|Islanda|
|IT|Italia|
|LT|Lituania|
|LU|Lussemburgo|
|MC|Monaco|
|ME|Montenegro|
|MT|Malta|
|ND|Irlanda del Nord|
|NL|Paesi Bassi|
|NO|Norvegia|
|PL|Polonia|
|PT|Portogallo|
|RO|Romania|
|RS|Serbia|
|SE|Svezia|
|SK|Slovacchia|
|SI|Slovenia|
|TR|Turchia|
|UA|Ucraina|

Per sapere in quali di questi paesi avvengono degli eventi gestiti da TicketMaster, può essere utile la seguente immagine:
![image](https://user-images.githubusercontent.com/99722713/173312709-663161a6-aef8-4af0-8a3e-cf39a05b4449.png)



L'utente, se lo desidera, può cercare gli eventi in base ai parametri che gli interessano, questi possono essere (tutto case senstive mi raccomando): "city", "local_date" (che sarebbe la data nel formato Anno/Mese/Giorno), "segment", "genre", "subgenre".
NB: di questi parametri se ne può scegliere un numero arbitrario e possono essere concatenati tramite il carattere &.

#### Esempio: localhost:8080/events?countryCode=BE&city=Brussels&segment=Music&genre=Rock
    {
        "list_filtered_events": [
            {
                "name": "The Dandy Warhols",
                "id": "Z698xZG2Za1vg",
                "url": "https://www.ticketmaster.be/event/the-dandy-warhols-tickets/41077",
                "country": "Belgium",
                "city": "Brussels",
                "local_date": "2022-06-14",
                "segment": "Music",
                "genre": "Rock",
                "subgenre": "Pop"
            },
            {
                "name": "Nick Mason's Saucerful Of Secrets",
                "id": "Z698xZG2ZaaOK",
                "url": "https://www.ticketmaster.be/event/nick-masons-saucerful-of-secrets-tickets/38931",
                "country": "Belgium",
                "city": "Brussels",
                "local_date": "2022-06-17",
                "segment": "Music",
                "genre": "Rock",
                "subgenre": "Pop"
            },
            {
                "name": "Agnes Obel",
                "id": "Z698xZG2ZaFU_",
                "url": "https://www.ticketmaster.be/event/agnes-obel-tickets/36249",
                "country": "Belgium",
                "city": "Brussels",
                "local_date": "2022-06-27",
                "segment": "Music",
                "genre": "Rock",
                "subgenre": "Pop"
            },
            { (ecc.)
            },
        ],
        "num_filtered_events": 9
    }        

### Rotta "/stats"
Questa rotta permette di visualizzare le statistiche riguardanti il numero di eventi che occorrono in un certo paese:
nello specifico, una volta selezionato il paese (da inserire come parametro come nella rotta "/event"), il programma conterà il numero di eventi che intercorrono in ogni giorno della settimana e ne restituirà il numero totale, il numero medio, il giorno in cui avviene il numero massimo insieme al numero stesso e il giorno in cui avviene il numero minimo sempre insieme al numero stesso.
Tutte queste statistiche sono calcolate città per città, quindi le restituirà in un elenco di tutte le città presenti nel paese e che sono state individuate dal programma, come nel seguente esempio.

#### Esempio: localhost:8080/stats?countryCode=IE
    {
        "country": "Ireland",
        "all_cities": [
            {
                "num_average_events": 18.43,
                "city": "Dublin",
                "min_events": {
                    "num": 13,
                    "day": "monday"
                },
                "max_events": {
                    "num": 30,
                    "day": "saturday"
                },
                "num_events_found": 129
            },
            {
                "num_average_events": 2.29,
                "city": "Cork",
                "min_events": {
                    "num": 2,
                    "day": "monday"
                },
                "max_events": {
                    "num": 3,
                    "day": "tuesday"
                },
                "num_events_found": 16
            },
            {
                "num_average_events": 1.29,
                "city": "Limerick City",
                "min_events": {
                    "num": 0,
                    "day": "tuesday"
                },
                "max_events": {
                    "num": 3,
                    "day": "wednesday"
                },
                "num_events_found": 9
            },
            { (ecc.)
            }
       ]
    }       
    
È possibile selezionare la città inserendolo come parametro aggiuntivo ricavando così le statistiche di una singola città del paese selezionato.

#### Esempio: localhost:8080/stats?countryCode=SE&city=Stockholm
    {
        "country": "Sweden",
        "num_average_events": 10.86,
        "city": "Stockholm",
        "min_events": {
            "num": 2,
            "day": "monday"
        },
        "max_events": {
            "num": 19,
            "day": "saturday"
        },
        "num_events_found": 76
    }

## Eccezioni che il programma può restituire

Ovviamente, che si parli della rotta degli eventi o quella delle statistiche, non tutti i parametri possono restituire il risultato desiderato.
Riporto di seguito 5 tipi di eccezione che possono accadere nel momento in cui si fa la chiamata GET.
<sub> (attenzione: reato di quando) </sub>

### Quando il paese selezionato non è europeo

In realtà questo caso si estende a tutti i casi in cui il codice inserito come parametro non è di nessun paese o si lascia vuoto lo spazio in cui inserire il valore del parametro.

#### Esempi: localhost:8080/events?countryCode=US, localhost:8080/stats?countryCode=nbieuifiu, localhost:8080/events

Tutti e tre restituiranno il seguente messaggio come stringa (notare che su Postman lo status risulterà essere "400 Bad Request"):
            
    Please insert a european (and valid) country-code
    
### Quando il paese selezionato non contiene eventi

Come si può vedere dall'immagine, non tutti i paesi contengono eventi gestiti da TicketMaster. In tutti questi casi verrà restituito un JSON strutturato come segue.

#### Esempio: localhost:8080/stats?countryCode=IT

(notare che su Postman lo status risulterà essere "200 OK" in quanto si tratta di un errore interno al programma e non di inserimento da parte dell'utente)

    {
        "events_not_found": "we're sorry, but there are no available events in this country"
    }
    
### Quando il paese selezionato è incompatibile con il modello del programma

Nella rotta "/events" è visibile il modello con cui è stato strutturato ogni evento (quindi name, id, url, local_date, segment, genre, subgenre).
In alcuni paesi però, gli eventi non contengono tutte le informazioni, pertanto il programma non è in grado di elaborarli correttamente.
Verrà dunque restituito un JSON come nell'esempio di seguito.

#### Esempio: localhost:8080/events?countryCode=CH

    {
          "events_not_found": "We're sorry, but we could not get the events in this country"
    }
    
### Quando l'utente seleziona dei parametri non validi

Se l'utente inserisce dei parametri non supportati dal programma, quest'ultimo restituirà il seguente messaggio d'errore in formato stringa.

#### Esempio: localhost:8080/stats?countryCode=FR&invalidKey=invalidValue

    Not supported parameters
    
### Quando non ci sono eventi che corripsondono ai parametri selezionati

Se il programma non è in grado di trovare nessun evento che corrisponda ai parametri selezionati dall'utente. Verrà restituito un JSON come di seguito.

#### Esempio: localhost:8080/events?countryCode=DE&city=Hannover&segment=Sports&genre=Golf

    {
          "filtered_events_not_found": "there are no events that match the seleceted filters"
    }
    
## Credits

Gli strumenti utilizzati per realizzare il programma sono riportati di seguito:
 * Intellij (IDE)
 * SpringBoot (Framework)
 * Maven (Dipendenze necessarie al funzionamento del programma)
 * Postman (testare l'API)


Autore
 * Othmani Amir
