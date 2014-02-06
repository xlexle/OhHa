package ahaakkoset.domain;

import ahaakkoset.domain.Pelilauta;
import ahaakkoset.domain.Ruutu;
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
        assertTrue(lauta.haeRuutu(1).getVasen() == null);
        assertTrue(lauta.haeRuutu(1).getYla() == null);
    }
    
    @Test
    public void laudanEnsimmaisellaRuudullaOnOikeatAlaJaOikeaRuutu() {
        assertTrue(lauta.haeRuutu(1).getAla() == lauta.haeRuutu(sivunPituus+1)); // tarvitseeko Equals? näyttää toimivan sellaisenaan OK
        assertTrue(lauta.haeRuutu(1).getOikea() == lauta.haeRuutu(2));
    }

    @Test
    public void laudanViimeisellaRuudullaEiOikeaaEikaAlaRuutua() {
        assertTrue(lauta.haeRuutu(sivunPituus * sivunPituus).getOikea() == null);
        assertTrue(lauta.haeRuutu(sivunPituus * sivunPituus).getAla() == null);
    }
    
    @Test
    public void laudanViimeisellaRuudullaOikeatYlaJaVasenRuutu() {
        assertTrue(lauta.haeRuutu(sivunPituus * sivunPituus).getYla() == lauta.haeRuutu((sivunPituus * sivunPituus) - sivunPituus));
        assertTrue(lauta.haeRuutu(sivunPituus * sivunPituus).getVasen() == lauta.haeRuutu((sivunPituus * sivunPituus) - 1));
    }

//    @Test
//    public void maaritaVasenRuutuToimii() {
//        
//    }
//    
//    @Test
//    public void maaritaVasenRuutuEiMaaritaJosOllaanVasemmassaReunassa() {
//        
//    }
//    
//    @Test
//    public void maaritaYlaRuutuToimii() {
//        
//    }
//    
//    @Test
//    public void maaritaYlaRuutuEiMaaritaJosOllaanYlimmallaRivilla() {
//        
//    }
//    
//    @Test
//    public void maaritaOikeaksiRuuduksiToimii() {
//        
//    }
//    
//    @Test
//    public void maaritaOikeaksiRuuduksiEiMaaritaJosOllaanVasemmassaReunassa() {
//        
//    }
//    
//    @Test
//    public void maaritaAlaRuuduksiToimii() {
//        
//    }
//    
//    @Test
//    public void maaritaAlaRuuduksiEiMaaritaJosOllaanYlimmallaRivilla() {
//        
//    }
}