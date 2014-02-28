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

    /*
     * Sisältää Kirjainvarastossa olevat kirjaimet.
     */
    private List<Character> kirjainSailio;
    /*
     * Satunnaislukugeneraattori jota hyödynnetään kirjainten satunnaisessa 
     * valitsemisessa kirjainSailiosta.
     */
    private Random arpoja = new Random();

    /**
     * Konstruktori pohjustaa kirjainvaraston kutsumalla metodia
     * luoKirjainSailio, parametrina kokonaislukukerroin joka määrittää 
     * kirjainSailion lopullisen koon.
     *
     */
    public Kirjainvarasto(int kerroin) {
        luoKirjainSailio(kerroin);
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
    private void luoKirjainSailio(int kerroin) {
        this.kirjainSailio = new ArrayList<>();
        String lisaaNelja = "AITNES";
        String lisaaKolme = "LOKUÄM";
        String lisaaKaksi = "VRJHYPDÖ";
        String lisaaYksi = "GBFCWQ";

        for (int i = 0; i < lisaaNelja.length(); i++) {
            for (int j = 0; j < 4 * kerroin; j++) {
                kirjainSailio.add(lisaaNelja.charAt(i));
            }
        }
        
        for (int i = 0; i < lisaaKolme.length(); i++) {
            for (int j = 0; j < 3 * kerroin; j++) {
                kirjainSailio.add(lisaaKolme.charAt(i));
            }
        }
        
        for (int i = 0; i < lisaaKaksi.length(); i++) {
            for (int j = 0; j < 2 * kerroin; j++) {
                kirjainSailio.add(lisaaKaksi.charAt(i));
            }
        }
        
        for (int i = 0; i < lisaaYksi.length(); i++) {
            for (int j = 0; j < kerroin; j++) {
                kirjainSailio.add(lisaaYksi.charAt(i));
            }
        }
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

    public List<Character> getKirjainSailio() {
        return kirjainSailio;
    }
}