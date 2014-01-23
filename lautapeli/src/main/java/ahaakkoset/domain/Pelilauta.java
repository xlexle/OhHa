package ahaakkoset.domain;

import java.util.ArrayList;
import java.util.List;

public class Pelilauta {
    private String kirjaimet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private List<Ruutu> ruudut = new ArrayList<>();

    public Pelilauta(int sivunPituus) {
        luoRuudukko(sivunPituus);
    }

    private void luoRuudukko(int sivunPituus) {
        for (int i = 1; i < sivunPituus; i++) {
            ruudut.add(new Ruutu(i, kirjaimet.charAt(i)));
        }
    }
    
    
}
