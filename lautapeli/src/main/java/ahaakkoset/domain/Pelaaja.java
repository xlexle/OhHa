package ahaakkoset.domain;

import java.util.ArrayList;
import java.util.List;

public class Pelaaja {

    private String nimi;
    private int enintaanKirjaimia;
    private int pisteet = 0;
    private int vuoroja = 0;
    private List<Character> omatKirjaimet;
    private List<Sana> luodutSanat;

    public Pelaaja(String nimi, int kirjaimia) {
        this.nimi = nimi;
        this.enintaanKirjaimia = kirjaimia;
        this.omatKirjaimet = new ArrayList<>();
        this.luodutSanat = new ArrayList<>();
    }

    public void lisaaPisteita(int paljonko) { //tarvitsee suojat väärille inputeille, täällä vai käyttöliittymässä?
        pisteet += paljonko;
    }

    public boolean lisaaKirjain(Character kirjain) {
        if (omatKirjaimet.size() >= enintaanKirjaimia) {
            return false;
        }

        omatKirjaimet.add(kirjain);
        return true;
    }

    public void lisaaVuoro() {
        vuoroja++;
    }

    public void lisaaSana(Sana sana) {
        luodutSanat.add(sana);
    }

    @Override
    public String toString() {
        return nimi + ": " + pisteet + " pistettä"; //Placeholder
    }
    
    public Character otaKirjain(Character kirjain) { // ei testattu
        Character palautettava = null;
        if (omatKirjaimet.contains(kirjain)) {
            palautettava = kirjain;
            omatKirjaimet.remove(kirjain);
        }
        return palautettava;
    }
    
    public String getNimi() {
        return nimi;
    }

    public int getVuorot() {
        return vuoroja;
    }

    public List<Sana> getLuodutSanat() {
        return luodutSanat;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }

        Pelaaja verrattava = (Pelaaja) o;
        String[] nimet = new String[2];
        nimet[0] = verrattava.nimi;
        nimet[1] = this.nimi;

        for (int i = 0; i < nimet.length; i++) {
            nimet[i] = nimet[i].toUpperCase();
            nimet[i] = nimet[i].trim();
        }

        if (!nimet[0].equals(nimet[1])) {
            return false;
        }

        return true;
    }

//    luotu testejä varten
    public List<Character> getOmatKirjaimet() {
        return omatKirjaimet;
    }

    public int getEnintaanKirjaimia() {
        return enintaanKirjaimia;
    }
}
