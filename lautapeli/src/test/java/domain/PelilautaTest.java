package domain;

import ahaakkoset.domain.Pelilauta;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;

public class PelilautaTest {
    
    Pelilauta lauta;
    int sivunPituus = 10;
    
    @Before
    public void setUp() {
        lauta = new Pelilauta(sivunPituus);
    }
    
    @Test
    public void ruudukossaOikeaMaaraRuutuja() {
        assertEquals(sivunPituus * sivunPituus, lauta.getRuudut().size());
    }
    
//    @Test
//    public void haeRuutuPalauttaaHalutunRuudun() {
//        
//    }
//    
//    @Test
//    public void haeVasenRuutuHakeeHalutunRuudun() {
//        
//    }
//    
//    @Test
//    public void haeYlaRuutuHakeeHalutunRuudun() {
//        
//    }
//    
//    @Test
//    public void haeOikeaRuutuHakeeHalutunRuudun() {
//        
//    }
//    
//    @Test
//    public void haeAlaRuutuHakeeHalutunRuudun() {
//        
//    }
//    
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