package ahaakkoset.sovelluslogiikka;

import ahaakkoset.domain.Kirjainvarasto;
import ahaakkoset.domain.Pelaaja;
import ahaakkoset.domain.Pelilauta;
import ahaakkoset.sovelluslogiikka.PelinInteraktiot;
import ahaakkoset.sovelluslogiikka.Pelisessio;
import java.util.ArrayList;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;

public class PelinInteraktiotTest {
    
    PelinInteraktiot peli;
    
    @Before
    public void setUp() {
        peli = new PelinInteraktiot(7, new Pelilauta(10), new ArrayList<Pelaaja>(), new Kirjainvarasto());
    }
    
}