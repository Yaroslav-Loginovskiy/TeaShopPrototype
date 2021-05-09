package com.company.Repositories;

import java.util.HashMap;
import java.util.Map;

public class Cennik {
    private static Cennik instance = null;
    private Map<String, double[]> cennik = new HashMap<String,double[]>();
    private Cennik()
    {
    }
    public static Cennik pobierzCennik() {
        if (instance == null)
        {
           instance = new Cennik();
        }
        return instance;
    }
    public void dodaj(String rodzaj, String smak, double cena) {
        cennik.put(smak, new double[]{cena, cena});
    }

    public void dodaj(String rodzaj, String smak, double poczCena, double koncCena,  double waga)
    {
        if(poczCena != koncCena) {
            cennik.put(smak, new double[]{poczCena, koncCena});
        }
        else dodaj(rodzaj,smak,poczCena);
    }
    public double[] pobierzCene(String key) {
        if(czyIstnieje(key)) {
            return cennik.get(key);
        }
        else {
            double[] defaultVal = {-1, -1};
            return defaultVal;
        }
    }
    public boolean czyIstnieje(String key)
    {
        return cennik.get(key) == null? false : true;
    }
}
