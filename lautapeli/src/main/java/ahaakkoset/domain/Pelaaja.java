package ahaakkoset.domain;

import java.util.ArrayList;
import java.util.List;

public class Pelaaja {
    private String nimi;
    private int pisteet = 0;
    private List<Character> omatKirjaimet;
    private List<Sana> sanat = new ArrayList<>();

    public Pelaaja(String nimi) {
        this.nimi = nimi;
    }
    
    public void lisaaPisteita(int paljonko) { //tarvitsee suojat väärille inputeille, täällä vai käyttöliittymässä?
        pisteet += paljonko;
    }
    
    public void asetaAlkuKirjaimet(List kirjaimet) {
        omatKirjaimet = kirjaimet;
    }

    @Override
    public String toString() {
        return nimi + ": " + pisteet + " pistettä"; //Placeholder
    }
    
    //Luotu JUnit testiä varten, järkeä?
    public List<Character> getOmatKirjaimet() {
        return omatKirjaimet;
    }
}
