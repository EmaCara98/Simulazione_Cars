package org.example;        //il package è la cartella nella quale si trovano i file .java

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class WareHouse {
    private static WareHouse istance;                                   // Creiamo un attributo "static" della classe in cui ci troviamo
    private static List<Car> carsList = new ArrayList();                // Creiamo un attributo "static" che conterrà la lista delle macchine
    private static List<ClientHandler> clientList = new ArrayList<>();  // Creiamo un attributo "static" che conterrà la lista dei client che si connettono

    private WareHouse() {
        buildList();
    }       // Creiamo un costruttore "private" che richiama il metodo buildList()

    public static WareHouse getInstance() {     // Creiamo un metodo "static" per creare l'oggetto WareHouse se non esiste
        if (istance == null) {                  // Se esiste già l'oggetto non viene ricreato
            istance = new WareHouse();
        }
        return istance;
    }

    static void buildList() {       // Nel metodo buildList mettiamo i dati di esempio inserendoli nella lista di macchine
        carsList.add(new Car(123,"bmw",3594.9, 2));
        carsList.add(new Car(3634,"audi",38346.9, 1));
        carsList.add(new Car(135,"ferrari",130000.4, 10));
        System.out.println(carsList);
    }

    // Metodi per gestire la lista di client connessi (add, remove, nOfClients)
    void add(ClientHandler clientHandler)
    {
        this.clientList.add(clientHandler);
    }

    void remove(ClientHandler clientHandler)
    {
        this.clientList.remove(clientHandler);
    }

    int nOfClients()
    {
        return this.clientList.size();
    }

    public String all() {       // Metodo che converte tutta la lista di macchine in una stringa Json
        Gson gson = new Gson();
        String s = gson.toJson(carsList);       // Viene creato un oggetto Gson (l'oggetto Json di Google)
                                                // che converte la lista di macchine in una stringa Json
        return s;
    }

    public String more_expensive() {            // Metodo che converte la macchina con Prezzo maggiore in una stringa Json
        double max=0;
        Car c_max = null;

        for(Car car : carsList)                 // Viene trovata la macchina con prezzo maggiore
        {
            if(car.getPrezzo()>max)
            {
                max=car.getPrezzo();
                c_max=car;
            }
        }

        Gson gson = new Gson();                 // La macchina c_max viene convertita in una stringa Json
        String s = gson.toJson(c_max);

        return s;
    }

    public String all_sorted() {            // Metodo che ordina la lista di macchine e la converte in una stringa Json

        List<Car> carList_sorted = new ArrayList(carsList);     // Creiamo una lista di macchine identica a quella in memoria

        carList_sorted.sort((o1, o2) -> {                       // Facciamo l'ordinamento
            return o1.getNome().compareTo(o2.getNome());
        });

        Gson gson = new Gson();                                 // La lista ordinata viene convertita in una stringa Json
        String s = gson.toJson(carList_sorted);

        return s;
    }


}