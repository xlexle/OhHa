package domain;

import ahaakkoset.domain.Pelaaja;
import ahaakkoset.domain.Sana;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;

public class SanaTest {
    
    Sana sana;
    
    @Before
    public void setUp() {
        sana = new Sana("Sana", "Merkitys on merkittävä", new Pelaaja("John Doe", 7));
    }
    
    @Test
    public void konstruktoriAsettaaArvotOikein() {
        assertEquals("Sana: Merkitys on merkittävä (pelaajalle John Doe 0 pistettä)", sana.toString());
    }
}