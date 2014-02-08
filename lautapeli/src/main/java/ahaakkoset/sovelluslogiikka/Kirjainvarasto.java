package ahaakkoset.sovelluslogiikka;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Tämä luokka on Pelisession käytössä oleva apuluokka. Luokan tehtävänä on
 * tarjota sessiolle ennaltamäärätty määrä kirjaimia joiden joukosta pelaajien
 * kirjaimia täydennetään arpomalla.
 *
 * @author Ville Lehtinen
 */
public class Kirjainvarasto {

    private List<Character> kirjainSailio = luoKirjainSailio(); // 64 kirjainta
    private Random arpoja = new Random();

    /**
     *
     */
    public Kirjainvarasto() {
    }

    /**
     * Metodi alustaa kirjainvaraston metodissa määritellyillä kirjaimilla ja
     * määrillä. Metodin muuttuja String kirjaimet perustuu Kielikellossa 1/1991
     * julkaistuun kirjainmerkkien esiintymistaajuuksien tutkimukseen(1).
     * Kirjainvarastoon lisätään 6 yleisintä kirjainta 4 kertaa, 6 seuraavaksi
     * yleisintä 3 kertaa, 8 seuraavaksi yleisintä 2 kertaa ja 6 harvinaisinta
     * kerran. 
     *
     * 1. https://www.cs.tut.fi/~jkorpela/kielikello/kirjtil.html
     *
     */
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

    /**
     * Metodi arpoo kirjaimen kirjainvaraston Character-olioita sisältävältä
     * listalta. Kirjain poistetaan listalta ja palautetaan.
     *
     * @return arvottu kirjain
     */
    public Character arvoKirjain() {
        int arvottuIndeksi = arpoja.nextInt(kirjainSailio.size());
        Character kirjain = kirjainSailio.get(arvottuIndeksi);
        kirjainSailio.remove(kirjain);
        return kirjain;
    }

    /**
     * Metodi on testejä varten.
     *
     * @return
     */
    public List<Character> getKirjainSailio() {
        return kirjainSailio;
    }
}