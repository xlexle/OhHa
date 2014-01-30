package ahaakkoset.sovelluslogiikka;

import ahaakkoset.domain.Kirjainvarasto;
import ahaakkoset.domain.Pelaaja;
import ahaakkoset.domain.Pelilauta;
import java.util.List;
import java.util.ArrayList;

public class Pelisessio {

    private int kirjaimia = 7;
    private Pelilauta pelilauta = new Pelilauta(10);
    private List<Pelaaja> pelaajat = new ArrayList<>();
    private Kirjainvarasto vapaatKirjaimet = new Kirjainvarasto();

    public Pelisessio() {
    }

    public void aloita() {
        EnnenPeliaInteraktiot alku = new EnnenPeliaInteraktiot(kirjaimia, pelilauta, pelaajat, vapaatKirjaimet);
        alku.suorita();
        arvoPelaajienAloitusKirjaimet();
//        PelinInteraktiot peli = new PelinInteraktiot(kirjaimia, pelilauta, pelaajat, vapaatKirjaimet);
//        peli.suorita();
//        Loppuyhteenveto loppu = new Loppuyhteenveto();
//        kutsu metodia joka tulostaa loppuskriinin GUI:een
    }

    public boolean lisaaPelaaja(Pelaaja pelaaja) {
        if (pelaajat.contains(pelaaja)) {
            return false;
        }
        
        pelaajat.add(pelaaja);
        return true;
    }

    public Pelaaja haePelaaja(String nimi) {
        for (Pelaaja pelaaja : pelaajat) {
            if (pelaaja.getNimi().equals(nimi)) {
                return pelaaja;
            }
        }

        return null;
    }

    public void arvoPelaajienAloitusKirjaimet() {
        for (int i = 0; i < kirjaimia; i++) {
            for (int j = 0; j < pelaajat.size(); j++) {
                pelaajat.get(j).lisaaKirjain(vapaatKirjaimet.arvoKirjain());
            }
        }
    }

//    private void suoritaLoppu() {
//        Loppuyhteenveto loppu = new Loppuyhteenveto();
//        kutsu metodia joka tulostaa loppuskriinin GUI:een
//    }
    
//    luotu testejä varten
    public List<Pelaaja> getPelaajat() {
        return pelaajat;
    }
}
