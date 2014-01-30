package ahaakkoset.sovelluslogiikka;

import ahaakkoset.domain.Kirjainvarasto;
import ahaakkoset.domain.Pelaaja;
import ahaakkoset.domain.Pelilauta;
import java.util.List;

public class EnnenPeliaInteraktiot {
    private boolean peliAlkaa = false;
    private int kirjaimia;
    private Pelilauta pelilauta;
    private List<Pelaaja> pelaajat;
    private Kirjainvarasto vapaatKirjaimet;

    public EnnenPeliaInteraktiot(int kirjaimia, Pelilauta pelilauta, List pelaajat, Kirjainvarasto vapaatKirjaimet) {
        this.kirjaimia = kirjaimia;
        this.pelilauta = pelilauta;
        this.pelaajat = pelaajat;
        this.vapaatKirjaimet = vapaatKirjaimet;
    }

    public void suorita() {
//        while (!peliAlkaa) {            
////            pelaaja on yhteydessä käyttöliittymään; looppi loppuu kun kaikki alkuparametrit on asetettu
////            lisaaPelaaja kutsutaan 2 kertaa
//        }

        //testikoodia
        lisaaPelaaja("Pietari");
        lisaaPelaaja("Teräsmies");
    }

    public void lisaaPelaaja(String nimi) {
        Pelaaja pelaaja = new Pelaaja(nimi, kirjaimia);
        pelaajat.add(pelaaja);
    }
    
    public boolean isPeliAlkaa() {
        return peliAlkaa;
    }

    public void aloitaPeli() {
        this.peliAlkaa = true;
    }
}
