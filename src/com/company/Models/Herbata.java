package com.company.Models;

public abstract class Herbata  {
    private double waga;
    private String smak;
    private String rodzaj;
    public Herbata(String smak,String rodzaj, double waga ) {
        this.waga = waga;
        this.smak = smak;
        this.rodzaj = rodzaj;
    }
    public double getWaga()
    {
        return this.waga;
    }
    public void setWaga()
    {
        this.waga -=0.5;
    }
    public String getSmak()
    {
        return this.smak;
    }
    public String getRodzaj()
    {
        return this.rodzaj;
    }
}
