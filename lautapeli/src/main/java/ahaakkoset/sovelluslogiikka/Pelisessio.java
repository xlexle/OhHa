package ahaakkoset.sovelluslogiikka;

import ahaakkoset.domain.Kirjainvarasto;
import ahaakkoset.domain.Pelaaja;
import ahaakkoset.domain.Pelilauta;
//import ahaakkoset.sovelluslogiikka.Loppuyhteenveto;
import java.util.List;
import java.util.ArrayList;

public class Pelisessio {
    // Pelisessio sisältää pelin palaset ja hallinnoi niitä; toimii yliluokkana
    // interaktio-luokille jotka ovat käyttöliittyman ja pelaajan välikappaleita
    protected int pelaajallaKirjaimia = 7;
    protected Pelilauta pelilauta = new Pelilauta(16);
    protected List<Pelaaja> pelaajat = new ArrayList<>();
    protected Kirjainvarasto vapaatKirjaimet = new Kirjainvarasto();

    public Pelisessio() {
    }
    
    public void aloita() {
        EnnenPeliaInteraktiot alku = new EnnenPeliaInteraktiot();
        alku.suorita();
        PelinInteraktiot peli = new PelinInteraktiot();
        peli.suorita();
//        Loppuyhteenveto loppu = new Loppuyhteenveto();
//        kutsu metodia joka tulostaa loppuskriinin GUI:een
    }
    
    public Pelaaja haePelaaja(String nimi) {
        Pelaaja haettava = new Pelaaja(nimi);
        
        if (!pelaajat.contains(haettava)) {
            return null; //mitä tähän?!
        }
        
        return haettava;
    }
    
    public void arvoPelaajienAloitusKirjaimet() { //muutetaan metodiksi joka arpoo yhden kirjaimen kerrallaan per pelaaja...
        for (int i = 0; i < pelaajat.size(); i++) {
            pelaajat.get(i).asetaAlkuKirjaimet(vapaatKirjaimet.arvoAlkuKirjaimet(pelaajallaKirjaimia));
        }
    }
    
//    private void suoritaLoppu() {
//        Loppuyhteenveto loppu = new Loppuyhteenveto();
//        kutsu metodia joka tulostaa loppuskriinin GUI:een
//    }
}
