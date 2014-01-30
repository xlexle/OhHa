package ahaakkoset.sovelluslogiikka;

import ahaakkoset.domain.Kirjainvarasto;
import ahaakkoset.domain.Pelaaja;
import ahaakkoset.domain.Pelilauta;
import java.util.List;

public class PelinInteraktiot extends Pelisessio {
    private boolean peliLoppuu = false;
    private int kirjaimia;
    private Pelilauta pelilauta;
    private List<Pelaaja> pelaajat;
    private Kirjainvarasto vapaatKirjaimet;

    public PelinInteraktiot(int kirjaimia, Pelilauta pelilauta, List pelaajat, Kirjainvarasto vapaatKirjaimet) {
        this.kirjaimia = kirjaimia;
        this.pelilauta = pelilauta;
        this.pelaajat = pelaajat;
        this.vapaatKirjaimet = vapaatKirjaimet;
    }

    public void suorita() {
//        while (!peliLoppuu) {
////             pelaaja on yhteydessä käyttöliittymään; looppi loppuu kun session kirjainvarasto on tyhjä tai pelaaja päättää lopettaa pelin
//        }
    }

    public void lopetaPeli() {
        this.peliLoppuu = true;
    }
}
