package ahaakkoset.sovelluslogiikka;

import ahaakkoset.domain.Kirjainvarasto;
import ahaakkoset.domain.Pelaaja;
import ahaakkoset.domain.Pelilauta;
import java.util.List;

public class EnnenPeliaInteraktiot {

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
        lisaaPelaajat();
        arvoPelaajienAloitusKirjaimet();
        
    }

    private void lisaaPelaajat() {
        int montako = 2;
        while (pelaajat.size() < montako) {
//            String nimi = 
            lisaaPelaaja("nimi", kirjaimia);
            lisaaPelaaja("nimi2", kirjaimia); //testiä varten
        }
    }

    private boolean lisaaPelaaja(String nimi, int kirjaimia) {
        if (pelaajat.contains(new Pelaaja(nimi, kirjaimia))) {
            return false;
        }
        pelaajat.add(new Pelaaja(nimi, kirjaimia));
        return true;
    }

    private void arvoPelaajienAloitusKirjaimet() {
        for (int i = 0; i < kirjaimia; i++) {
            for (int j = 0; j < pelaajat.size(); j++) {
                pelaajat.get(j).lisaaKirjain(vapaatKirjaimet.arvoKirjain());
            }
        }
    }

    //luotu testejä varten
    public List<Pelaaja> getPelaajat() {
        return pelaajat;
    }
}
