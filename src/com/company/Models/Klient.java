package com.company.Models;

import com.company.HerbalTest;
import com.company.Repositories.Cennik;
import com.company.Repositories.Koszyk;
import com.company.Repositories.ListaZakupow;

public class Klient {
    Cennik cennik ;
    private String imie;
    private int portfel;
    private ListaZakupow listaZakupow ;
    private Koszyk koszyk;

    public Klient(String imie, int portfel) {
        this.imie = imie;
        this.portfel = portfel;
        listaZakupow = new ListaZakupow(this);
        cennik = Cennik.pobierzCennik();

    }
    public String getImie()
    {
        return this.imie;
    }

    public void dodaj(Herbata herbata) {
        listaZakupow.getLista().add(herbata);
    }

    public ListaZakupow pobierzListeZakupow() {
        return this.listaZakupow;
    }

    public Koszyk pobierzKoszyk() {
        return koszyk;
    }

    public void przepakuj(Koszyk koszyk) {
        this.koszyk = koszyk;
        int index = 0;
        for (int i = 0; i < listaZakupow.getLista().size(); i++) {
            if (cennik.czyIstnieje(listaZakupow.getLista().get(index).getSmak())) { //sprawdzamy czy ta herbata jest w cenniku
                koszyk.getLista().add(listaZakupow.getLista().get(i));//jeśli jest to dodajemy do koszyka i usuwamy z listy zakupow
                listaZakupow.getLista().remove(i);
                i--;
            } else {//w innym przypadku zostajemy ta herbate w liscie zakupow nie dodawając do koszyka
                index++;
            }
        }
    }
    public void zaplac(String typPlatnosci) {
        double nadwyzkaDoCeny=0;
        double koncSuma = 0;
        double zwrotCena = 0;
        //sprawdzamy ille bedzie w sumie
        for (var item:koszyk.getLista()
             ) {
            koncSuma += HerbalTest.wartosc(koszyk,item.getSmak());
        }
        int index = 0;
        boolean czyWystarczy = false;
        if(portfel - koncSuma < 0)// czy wystarczy nam zadeklarowanej sumy?
        {
            //powtorne sprawdzanie uwzglednajac nasza zmienna czyWystaczy
            for (var item: koszyk.getLista()
                 ) {
                if (czyWystarczy)
                {
                    koszyk.getLista().remove(item);
                    break;
                }
                //przypisujemy do i wartosc wagi i odejmujemy 0.5 za kadzym razem
                for(double i = koszyk.getLista().get(index).getWaga(); i > 0; i--)
                {
                    zwrotCena =  0.5 * cennik.pobierzCene(item.getSmak())[0];
                    koszyk.getLista().get(index).setWaga();
                    koncSuma  -= zwrotCena;
                    //sprawdzamy za kazdym razem czy wystarcz juz
                    if(portfel - koncSuma > 0)
                    {
                        //jesli wystarczy to wychodzimy z petli i ustawiamy czyWystarczy na true
                        czyWystarczy = true;
                        break;
                    }
                }
                index++;
            }
        }
        //jesli wystarczy to usuwamy wszysktie elementy z koszyka
        else {
            koszyk.getLista().clear();
        }
        //sprawdzamy typ platnosci i przypisujemy 1% od sumy w przypadku platnosci karta
        nadwyzkaDoCeny  = typPlatnosci=="przelew"? 0 : koncSuma*0.01;
        portfel -= (koncSuma+nadwyzkaDoCeny);
    }
    public double pobierzPortfel() {
        return this.portfel;
    }
}
