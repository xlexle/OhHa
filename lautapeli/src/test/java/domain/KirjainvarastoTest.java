package domain;

import ahaakkoset.domain.Kirjainvarasto;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;

public class KirjainvarastoTest {
    
    Kirjainvarasto varasto;
    
    @Before
    public void setUp() {
        varasto = new Kirjainvarasto();
    }
    
    @Test
    public void kirjainVarastossaAlussa64Kirjainta() {
        assertEquals(64, varasto.getKirjainSailio().size());
    }
    
    @Test
    public void kirjainVarastossaAlussa4KappalettaAITNES() {
        int kpl = 4;
        assertEquals(kpl, kplVarastossa('A'));
        assertEquals(kpl, kplVarastossa('I'));
        assertEquals(kpl, kplVarastossa('T'));
        assertEquals(kpl, kplVarastossa('N'));
        assertEquals(kpl, kplVarastossa('E'));
        assertEquals(kpl, kplVarastossa('S'));
    }
    
    @Test
    public void kirjainVarastossaAlussa3KappalettaLOKUAM() {
        int kpl = 3;
        assertEquals(kpl, kplVarastossa('L'));
        assertEquals(kpl, kplVarastossa('O'));
        assertEquals(kpl, kplVarastossa('K'));
        assertEquals(kpl, kplVarastossa('U'));
        assertEquals(kpl, kplVarastossa('Ä'));
        assertEquals(kpl, kplVarastossa('M'));
    }
    
    @Test
    public void kirjainVarastossaAlussa2KappalettaVRJHYPDÖ() {
        int kpl = 2;
        assertEquals(kpl, kplVarastossa('V'));
        assertEquals(kpl, kplVarastossa('R'));
        assertEquals(kpl, kplVarastossa('J'));
        assertEquals(kpl, kplVarastossa('H'));
        assertEquals(kpl, kplVarastossa('Y'));
        assertEquals(kpl, kplVarastossa('P'));
        assertEquals(kpl, kplVarastossa('D'));
        assertEquals(kpl, kplVarastossa('Ö'));
    }
    
    @Test
    public void kirjainVarastossaAlussa1KappaleGBFCWQ() {
        int kpl = 1;
        assertEquals(kpl, kplVarastossa('G'));
        assertEquals(kpl, kplVarastossa('B'));
        assertEquals(kpl, kplVarastossa('F'));
        assertEquals(kpl, kplVarastossa('C'));
        assertEquals(kpl, kplVarastossa('W'));
        assertEquals(kpl, kplVarastossa('Q'));
    }
    
    @Test
    public void otaAlkuKirjaimetPalauttaaOikeanKokoisenListan() {
        int listanPituus = 7;
        assertEquals(7, varasto.arvoAlkuKirjaimet(listanPituus).size());
    }
    
    private int kplVarastossa (char kirjain) {
        String kirjaimet = "";
        
        for (Character c : varasto.getKirjainSailio()) {
            if (c.charValue() == kirjain) {
                kirjaimet += c;
            }
        }
        
        return kirjaimet.length();
    }
}