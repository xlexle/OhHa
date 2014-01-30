package ahaakkoset.domain;

import java.util.ArrayList;
import java.util.List;

public class Pelaaja {

    private String nimi;
    private int enintaanKirjaimia;
    private int pisteet = 0;
    private List<Character> omatKirjaimet;
    private List<Sana> sanat;

    public Pelaaja(String nimi, int kirjaimia) {
        this.nimi = nimi;
        this.enintaanKirjaimia = kirjaimia;
        this.omatKirjaimet = new ArrayList<>();
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

    @Override
    public String toString() {
        return nimi + ": " + pisteet + " pistettä"; //Placeholder
    }

    public String getNimi() {
        return nimi;
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
