package ahaakkoset.sovelluslogiikka;

import ahaakkoset.domain.Kirjainvarasto;
import ahaakkoset.domain.Pelaaja;
import ahaakkoset.domain.Pelilauta;
import ahaakkoset.sovelluslogiikka.EnnenPeliaInteraktiot;
import java.util.ArrayList;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;

public class EnnenPeliaInteraktiotTest {
    private EnnenPeliaInteraktiot alku;
    private int kirjaimia;

    @Before
    public void setUp() {
        alku = new EnnenPeliaInteraktiot(7, new Pelilauta(11), new ArrayList<Pelaaja>(), new Kirjainvarasto());
        kirjaimia = 7;
    }

    @Test
    public void lisaaPelaajatLisaaKaksiEriNimistaPelaajanPeliin() {
        assertTrue(alku.getPelaajat().isEmpty());
        alku.suorita();
        assertEquals(2, alku.getPelaajat().size());
        assertTrue(alku.getPelaajat().get(0) != alku.getPelaajat().get(1));
    }
    
    @Test
    public void arvoPelaajienAloitusKirjaimetLisaaOikeanMaaranKirjaimia() {
        alku.suorita();
        assertEquals(kirjaimia, alku.getPelaajat().get(0).getOmatKirjaimet().size());
        assertEquals(kirjaimia, alku.getPelaajat().get(1).getOmatKirjaimet().size());
    }
}