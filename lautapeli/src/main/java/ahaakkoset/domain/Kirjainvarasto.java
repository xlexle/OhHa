package ahaakkoset.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Kirjainvarasto {   
    private List<Character> kirjainSailio = luoKirjainSailio();
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
    
    public List arvoAlkuKirjaimet(int montako) {
        List<Character> kirjaimet = new ArrayList<>();
        
        for (int i = 0; i < montako; i++) {
            kirjaimet.add(arvottuKirjain());
        }

        return kirjaimet;
    }
    
    private Character arvottuKirjain() {
        int arvottuIndeksi = arpoja.nextInt(kirjainSailio.size());
        char kirjain = kirjainSailio.get(arvottuIndeksi);
//        kirjainSailio.remove(kirjain); // tarvitsee equalsin ennen kuin toimii?
        return kirjain;
    }
    
    //Alla olevat luotu JUnit-testejä varten... järkeä?
    public List<Character> getKirjainSailio() {
        return kirjainSailio;
    }
}
