package sovelluslogiikka;

import ahaakkoset.domain.Pelaaja;
import ahaakkoset.sovelluslogiikka.Pelisessio;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;

public class PelisessioTest {
    private int kirjaimia = 7;
    Pelisessio sessio;
    private Pelaaja testiPelaaja = new Pelaaja("John Doe", kirjaimia);

    @Before
    public void setUp() {
        sessio = new Pelisessio();
    }

    @Test
    public void lisaaPelaajaLaittaaUudenPelaajanSessioon() {
        assertTrue(sessio.getPelaajat().isEmpty());
        sessio.lisaaPelaaja(testiPelaaja);
        assertTrue(sessio.getPelaajat().size() == 1);
    }
    
    @Test
    public void eiVoiLisataKahtaSamannimistaPelaajaa() {
        sessio.lisaaPelaaja(testiPelaaja);
        Pelaaja sama = new Pelaaja(testiPelaaja.getNimi(), testiPelaaja.getEnintaanKirjaimia());
        assertTrue(!sessio.lisaaPelaaja(sama));
        assertEquals(1, sessio.getPelaajat().size());
    }
    
    @Test
    public void haePelaajaHakeeOikeanPelaajan() {
        sessio.lisaaPelaaja(testiPelaaja);
        assertEquals(testiPelaaja.getNimi(), sessio.haePelaaja(testiPelaaja.getNimi()).getNimi());
    }
    
    @Test
    public void haePelaajaPalauttaaNullJosEiLoydy() {
        assertEquals(null, sessio.haePelaaja("Olli Olematon"));
    }

    @Test
    public void arvoPelaajienAloitusKirjaimetLisaaOikeanMaaranKirjaimia() {
        sessio.lisaaPelaaja(testiPelaaja);
        sessio.arvoPelaajienAloitusKirjaimet();
        assertEquals(kirjaimia, sessio.haePelaaja(testiPelaaja.getNimi()).getOmatKirjaimet().size());
    }
}