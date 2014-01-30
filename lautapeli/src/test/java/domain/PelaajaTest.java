package domain;

import ahaakkoset.domain.Pelaaja;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;

public class PelaajaTest {
    
    Pelaaja pelaaja;
    int enintaanKirjaimia = 7;
    
    @Before
    public void setUp() {
        pelaaja = new Pelaaja("John Doe", enintaanKirjaimia);
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
    public void lisaaKirjainToimiiTyhjaanListaan() {
        assertTrue(pelaaja.getOmatKirjaimet().isEmpty());
        assertTrue(pelaaja.lisaaKirjain(new Character('x')));
    }
    
    @Test
    public void kirjaintaEiVoiLisataJosListaTaynna() {
        for (int i = 0; i < enintaanKirjaimia; i++) {
            pelaaja.lisaaKirjain(new Character('x'));
        }
        
        pelaaja.lisaaKirjain(new Character('z'));
        assertTrue(pelaaja.getOmatKirjaimet().size() == enintaanKirjaimia);
    }
}