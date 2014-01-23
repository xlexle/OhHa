package domain;

import ahaakkoset.domain.Pelaaja;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;

public class PelaajaTest {
    
    Pelaaja pelaaja;
    
    @Before
    public void setUp() {
        pelaaja = new Pelaaja("John Doe");
    }
    
    @Test
    public void konstruktoriAsettaaNimenOikein() {
        assertEquals("John Doe: 0 pistettä", pelaaja.toString());
    }
    
    @Test
    public void lisaaPisteitaLisaaOikeanMaaranPisteita() {
        pelaaja.lisaaPisteita(5);
        assertEquals("John Doe: 5 pistettä", pelaaja.toString());
    }
    
    @Test
    public void asetaAlkuKirjaimetAsettaaOikeatKirjaimet() {
        String kirjaimet = "ABCDEFG";
        List<Character> kirjainLista = new ArrayList<>();
        for (int i = 0; i < kirjaimet.length(); i++) {
            kirjainLista.add(kirjaimet.charAt(i));
        }
        
        pelaaja.asetaAlkuKirjaimet(kirjainLista);
        
        String pelaajanKirjaimet = "";
        for (Character c : pelaaja.getOmatKirjaimet()) {
            pelaajanKirjaimet += c;
        }
        
        assertEquals("ABCDEFG", pelaajanKirjaimet);
    }
}