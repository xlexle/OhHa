package domain;

import ahaakkoset.domain.Ruutu;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;

public class RuutuTest {
    
    Ruutu ruutu;
    
    @Before
    public void setUp() {
        ruutu = new Ruutu(1, 'A');
    }
    
    
}