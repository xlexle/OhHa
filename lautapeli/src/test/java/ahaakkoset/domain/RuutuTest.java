package ahaakkoset.domain;

import ahaakkoset.domain.Ruutu;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;

public class RuutuTest {
    
    Ruutu ruutu;
    
    @Before
    public void setUp() {
        ruutu = new Ruutu(null, null);
    }
    
    @Test
    public void uudellaRuudullaEiKirjainta() {
        kirjainLoytyy(false);
    }
    
    private void kirjainLoytyy(boolean haluttuTulos) {
        boolean loytyy = true;
        try {
            char x = ruutu.getKirjain();
        } catch (NullPointerException e) {
            loytyy = false;
        }
        
        assertTrue(loytyy == haluttuTulos);
    }
}