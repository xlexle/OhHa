package ahaakkoset.domain;

import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;

public class PelilautaTest {
    
    Pelilauta lauta;
    int sivunPituus = 11;
    
    @Before
    public void setUp() {
        lauta = new Pelilauta(sivunPituus);
    }
    
    @Test
    public void ruudukossaOikeaMaaraRuutuja() {
        assertEquals(sivunPituus * sivunPituus, lauta.getRuudut().size());
    }
    
    @Test
    public void laudanEnsimmaisellaRuudullaEiVasentaEikaYla() {
        assertEquals(null, lauta.haeRuutu(1).getVasen());
        assertEquals(null, lauta.haeRuutu(1).getYla());
    }
    
    @Test
    public void laudanEnsimmaisellaRuudullaOnOikeatAlaJaOikeaRuutu() {
        assertEquals(lauta.haeRuutu(sivunPituus+1), lauta.haeRuutu(1).getAla());
        assertEquals(lauta.haeRuutu(2), lauta.haeRuutu(1).getOikea());
    }

    @Test
    public void laudanViimeisellaRuudullaEiOikeaaEikaAlaRuutua() {
        assertEquals(null, lauta.haeRuutu(sivunPituus * sivunPituus).getOikea());
        assertEquals(null, lauta.haeRuutu(sivunPituus * sivunPituus).getAla());
    }
    
    @Test
    public void laudanViimeisellaRuudullaOikeatYlaJaVasenRuutu() {
        assertEquals(lauta.haeRuutu((sivunPituus * sivunPituus) - sivunPituus), lauta.haeRuutu(sivunPituus * sivunPituus).getYla());
        assertEquals(lauta.haeRuutu((sivunPituus * sivunPituus) - 1), lauta.haeRuutu(sivunPituus * sivunPituus).getVasen());
    }
}