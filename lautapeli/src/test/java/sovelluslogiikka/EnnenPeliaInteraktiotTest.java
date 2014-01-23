package sovelluslogiikka;

import ahaakkoset.domain.Pelaaja;
import ahaakkoset.sovelluslogiikka.EnnenPeliaInteraktiot;
import ahaakkoset.sovelluslogiikka.Pelisessio;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;

public class EnnenPeliaInteraktiotTest {

    EnnenPeliaInteraktiot alku;
    
    @Before
    public void setUp() {
        alku = new EnnenPeliaInteraktiot();
    }
    
    @Test
    public void LuokkaOnPelisessionAliluokka() {
        Pelisessio sessio = new Pelisessio();
        assertTrue(alku.getClass().getSuperclass().equals(sessio.getClass()));
    }
    
    @Test
    public void aloitaPeliAsettaaPeliAlkaaTrue() {
        alku.aloitaPeli();
        assertTrue(alku.isPeliAlkaa());
    }
    
//    @Test
//    public void lisaaPelaajaLaittaaUudenPelaajanSessioon() {
//        Pelisessio sessio = new Pelisessio();
//        alku.lisaaPelaaja("John Doe");
//        assertEquals("John Doe: 0 pistettä", alku.haePelaaja("uusiPelaaja").toString());
//    }
}