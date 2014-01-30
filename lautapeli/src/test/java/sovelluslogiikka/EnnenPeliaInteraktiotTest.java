package sovelluslogiikka;

import ahaakkoset.domain.Kirjainvarasto;
import ahaakkoset.domain.Pelaaja;
import ahaakkoset.domain.Pelilauta;
import ahaakkoset.sovelluslogiikka.EnnenPeliaInteraktiot;
import ahaakkoset.sovelluslogiikka.Pelisessio;
import java.util.ArrayList;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;

public class EnnenPeliaInteraktiotTest {

    EnnenPeliaInteraktiot alku;
    
    @Before
    public void setUp() {
        alku = new EnnenPeliaInteraktiot(7, new Pelilauta(10), new ArrayList<Pelaaja>(), new Kirjainvarasto());
    }
    
    @Test
    public void aloitaPeliAsettaaPeliAlkaaTrue() {
        alku.aloitaPeli();
        assertTrue(alku.isPeliAlkaa());
    }
}