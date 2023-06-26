package org.example;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class WareHouse {
    private static WareHouse istance;
    private static List<Car> carsList = new ArrayList();
    private static List<ClientHandler> clientList = new ArrayList<>();

    private WareHouse() {
        buildList();
    }

    public static WareHouse getInstance() {
        if (istance == null) {
            istance = new WareHouse();
        }
        return istance;
    }

    static void buildList() {
        carsList.add(new Car(123,"bmw",3594.9, 2));
        carsList.add(new Car(3634,"audi",38346.9, 1));
        carsList.add(new Car(135,"ferrari",130000.4, 10));
        System.out.println(carsList);
    }

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

    public String all() {
        Gson gson = new Gson();
        String s = gson.toJson(carsList);

        return s;
    }

    public String more_expensive() {
        double max=0;
        Car c_max = null;

        for(Car car : carsList)
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