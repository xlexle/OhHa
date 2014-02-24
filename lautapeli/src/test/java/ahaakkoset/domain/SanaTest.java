package ahaakkoset.domain;

import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;

public class SanaTest {
    
    Sana sana;
    
    @Before
    public void setUp() {
        sana = new Sana("Sana", "Merkitys on merkittävä", 5);
    }
    
    @Test
    public void konstruktoriAsettaaArvotOikein() {
        assertEquals("Sana\nMerkitys: Merkitys on merkittävä\nPisteet: 5", sana.toString());
    }
}