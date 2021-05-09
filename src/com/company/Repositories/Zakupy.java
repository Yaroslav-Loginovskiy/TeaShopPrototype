package com.company.Repositories;

import com.company.Models.Herbata;
import com.company.Models.Klient;

import java.util.ArrayList;
import java.util.List;

public class Zakupy {
     protected List<Herbata> lista;
     protected Klient klient;
    public Zakupy(Klient klient)
    {
        lista = new ArrayList<>();
        this.klient = klient;
    }
    public List<Herbata> getLista()
    {
        return this.lista;
    }
    public String toString()
    {
        Cennik cennik = Cennik.pobierzCennik();
        double cena = 0;
        String value = klient.getImie() + ":" ;
        if(lista.size() != 0 ){//sprawdzamy czy nasza lista zawiera w sobie jakies produkty
            for (var a: lista
            ) {
                if(a.getSmak()=="imbir" && a.getWaga() >=4) {
                    cena = cennik.pobierzCene(a.getSmak())[1];
                }
                else {
                    cena = cennik.pobierzCene(a.getSmak())[0];
                }
                value += "\n" + a.getRodzaj() +", smak: " + a.getSmak() +", ilość " + a.getWaga()+" kg, " +"cena " + cena ;
            }
            return value + "\n";
        }
        return value + " -- pusto"; // w innym przypadku pokazujemy komunikat ze lista jest pusta
    }
}
