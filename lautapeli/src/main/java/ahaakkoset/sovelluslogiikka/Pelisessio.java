package ahaakkoset.sovelluslogiikka;

import ahaakkoset.domain.Kirjainvarasto;
import ahaakkoset.domain.Pelaaja;
import ahaakkoset.domain.Pelilauta;
import java.util.List;
import java.util.ArrayList;

public class Pelisessio {

    private int kirjaimia = 7;
    private Pelilauta pelilauta = new Pelilauta(11);
    private List<Pelaaja> pelaajat = new ArrayList<>();
    private Kirjainvarasto vapaatKirjaimet = new Kirjainvarasto();

    public Pelisessio() {
    }

    public void kaynnista() {
        EnnenPeliaInteraktiot alku = new EnnenPeliaInteraktiot(kirjaimia, pelilauta, pelaajat, vapaatKirjaimet);
        alku.suorita();
//        GUI: aseta piirtoalustalle pelaajien kirjaimet
        PelinInteraktiot peli = new PelinInteraktiot(kirjaimia, pelilauta, pelaajat, vapaatKirjaimet);
        peli.suorita();
//        Loppuyhteenveto loppu = new Loppuyhteenveto();
    }
    
    

//    public Pelaaja haePelaaja(String nimi) {
//        for (Pelaaja pelaaja : pelaajat) {
//            if (pelaaja.getNimi().equals(nimi)) {
//                return pelaaja;
//            }
//        }
//
//        return null;
//    }

//    private void suoritaLoppu() {
//        Loppuyhteenveto loppu = new Loppuyhteenveto();
//        kutsu metodia joka tulostaa loppuskriinin GUI:een
//    }
}
