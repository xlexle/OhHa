package ahaakkoset.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Kirjainvarasto {

    private List<Character> kirjainSailio = luoKirjainSailio(); // 64 kirjainta
    private Random arpoja = new Random();

    public Kirjainvarasto() {
    }

    private List luoKirjainSailio() {
        String kirjaimet = "AITNESLOKUÄMVRJHYPDÖGBFCWQ";
        List<Character> uusiSailio = new ArrayList<>();

        for (int i = 0; i < kirjaimet.length(); i++) {
            uusiSailio.add(kirjaimet.charAt(i));
        }
        for (int i = 0; i < 20; i++) {
            uusiSailio.add(kirjaimet.charAt(i));
        }
        for (int i = 0; i < 12; i++) {
            uusiSailio.add(kirjaimet.charAt(i));
        }
        for (int i = 0; i < 6; i++) {
            uusiSailio.add(kirjaimet.charAt(i));
        }

        return uusiSailio;
    }

    public Character arvoKirjain() {
        int arvottuIndeksi = arpoja.nextInt(kirjainSailio.size());
        Character kirjain = kirjainSailio.get(arvottuIndeksi);
        kirjainSailio.remove(kirjain);
        return kirjain;
    }

    public void arvoUudetKirjaimet(Pelaaja pelaaja) { // ei testattu
        int montakoPuuttuu = pelaaja.getEnintaanKirjaimia() - pelaaja.getOmatKirjaimet().size();
        for (int i = 0; i < montakoPuuttuu; i++) {
            pelaaja.lisaaKirjain(arvoKirjain());
        }
    }

    //Alla olevat luotu JUnit-testejä varten... järkeä?
    public List<Character> getKirjainSailio() {
        return kirjainSailio;
    }
}