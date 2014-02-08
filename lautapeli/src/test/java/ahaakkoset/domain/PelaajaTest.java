package ahaakkoset.domain;

import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;

public class PelaajaTest {

    Pelaaja pelaaja;
    int enintaanKirjaimia = 7;

    @Before
    public void setUp() {
        pelaaja = new Pelaaja("John Doe", enintaanKirjaimia);
        for (int i = 0; i < enintaanKirjaimia; i++) {
            pelaaja.lisaaKirjain(new Character('A'));
        }
    }

    @Test
    public void konstruktoriAsettaaArvotOikein() {
        assertEquals("John Doe", pelaaja.getNimi());
        assertEquals(enintaanKirjaimia, pelaaja.getEnintaanKirjaimia());
    }

    @Test
    public void lisaaKirjainToimiiTyhjaanListaan() {
        Pelaaja p = new Pelaaja("TyhjanListanOmaava", enintaanKirjaimia);
        assertTrue(p.getOmatKirjaimet().isEmpty());
        assertTrue(p.lisaaKirjain(new Character('x')));
    }

    @Test
    public void kirjaintaEiVoiLisataJosListaTaynna() {
        pelaaja.lisaaKirjain(new Character('B'));
        assertTrue(pelaaja.getOmatKirjaimet().size() == enintaanKirjaimia);
    }

    @Test
    public void otaKirjainPalauttaaNullJosPelaajaEiOmista() {
        assertEquals(null, pelaaja.otaKirjain('B'));
    }

    @Test
    public void otaKirjainEiVahennaJosPelaajaEiOmistaKirjainta() {
        String sana = "";
        sana += pelaaja.otaKirjain('B');
        assertEquals(enintaanKirjaimia, pelaaja.getOmatKirjaimet().size());
    }

    @Test
    public void otaKirjainVahentaaKirjaimenJosLoytyy() {
        String sana = "";
        sana += pelaaja.otaKirjain('A');
        assertEquals(enintaanKirjaimia - 1, pelaaja.getOmatKirjaimet().size());
    }

    @Test
    public void equalsPalauttaaFalseJosPelaajatErinimisia() {
        Pelaaja pelaaja2 = new Pelaaja("Jane Doe", enintaanKirjaimia);
        assertTrue(!pelaaja.equals(pelaaja2));
    }

    @Test
    public void equalsPalauttaaTrueJosPelaajatSamannimisia() {
        Pelaaja pelaaja2 = new Pelaaja("John Doe", enintaanKirjaimia);
        assertTrue(pelaaja.equals(pelaaja2));
    }
    
    @Test
    public void laskePisteetPalauttaaNollaJosEiPisteita() {
        assertEquals(0, pelaaja.laskePisteet());
    }

    @Test
    public void laskePisteetPalauttaaOikeanPistemaaran() {
        Pelaaja pelaaja2 = new Pelaaja("John Doe", enintaanKirjaimia);
        lisaaPelaajalleSanat(pelaaja2);
        assertEquals(10, pelaaja2.laskePisteet());
    }

    @Test
    public void laskePisteetEdelliseenIndeksiinAstiPalauttaaOikeanPistemaaran() {
        Pelaaja pelaaja2 = new Pelaaja("John Doe", enintaanKirjaimia);
        lisaaPelaajalleSanat(pelaaja2);
        assertEquals(6, pelaaja2.laskePisteet(pelaaja2.getLuodutSanat().size() - 1));
    }

    private void lisaaPelaajalleSanat(Pelaaja pelaaja) {
        pelaaja.lisaaSana(new Sana("Eka", "Merkitys", 1));
        pelaaja.lisaaSana(new Sana("Eka", "Merkitys", 2));
        pelaaja.lisaaSana(new Sana("Eka", "Merkitys", 3));
        pelaaja.lisaaSana(new Sana("Eka", "Merkitys", 4));
    }
}