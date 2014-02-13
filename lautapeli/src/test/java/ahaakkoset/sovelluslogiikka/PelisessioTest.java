package ahaakkoset.sovelluslogiikka;

import ahaakkoset.domain.Pelaaja;
import ahaakkoset.sovelluslogiikka.Pelisessio;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;

public class PelisessioTest {

    private Pelisessio sessio;
    private Pelaaja masa;
    private Pelaaja matti;

    @Before
    public void setUp() {
        sessio = new Pelisessio();
        sessio.lisaaPelaaja("Masa");
        sessio.lisaaPelaaja("Matti");
        this.masa = sessio.getPelaajat().get(0);
        this.matti = sessio.getPelaajat().get(1);
    }

    @Test
    public void eiLisaaPelaajaaJonkaNimiVaaranPituinen() {
        Pelisessio sessio2 = new Pelisessio();
        assertTrue(!sessio2.lisaaPelaaja("xd"));
        assertTrue(!sessio2.lisaaPelaaja("kirjaimiaonliikaa"));
    }

    @Test
    public void lisaaPelaajanTyhjaanListaan() {
        Pelisessio sessio2 = new Pelisessio();
        assertTrue(sessio2.getPelaajat().isEmpty());
        sessio2.lisaaPelaaja("testi");
        assertEquals(1, sessio2.getPelaajat().size());
    }

    @Test
    public void lisaaEriNimisenPelaajan() {
        Pelisessio sessio2 = new Pelisessio();
        sessio2.lisaaPelaaja("Masa");
        sessio2.lisaaPelaaja("Matti");
        assertEquals(2, sessio2.getPelaajat().size());
    }

    @Test
    public void eiLisaaSamanNimistaPelaajaa() {
        sessio.lisaaPelaaja("Masa");
        assertTrue(!sessio.lisaaPelaaja("Masa"));
        assertEquals(2, sessio.getPelaajat().size());
    }

    @Test
    public void arvoAloitusKirjaimetArpooOikeanMaaranMolemmille() {
        sessio.arvoPelaajienAloitusKirjaimet();
        assertEquals(sessio.getKirjaimia(), masa.getOmatKirjaimet().size());
        assertEquals(sessio.getKirjaimia(), matti.getOmatKirjaimet().size());
    }

    @Test
    public void alussaEiAktiivistaPelaajaa() {
        assertEquals(null, sessio.getAktiivinenPelaaja());
    }

    @Test
    public void seuraavaPelaajaAsettaaListanEnsimmaisenAktiiviseksi() {
        sessio.seuraavaPelaaja(); // masa aktiivinen
        assertEquals(masa, sessio.getAktiivinenPelaaja());
    }

    @Test
    public void seuraavaPelaajaLisaaVuoronJaSiirtaaAktiivisenSeuraavalle() {
        sessio.seuraavaPelaaja(); // masa aktiivinen
        assertEquals(0, masa.getVuorot());
        sessio.seuraavaPelaaja(); // matti aktiivinen
        assertEquals(1, masa.getVuorot());
        assertEquals(matti, sessio.getAktiivinenPelaaja());
    }

    @Test
    public void seuraavaPelaajaPalauttaaAktiivisenAlkuun() {
        sessio.seuraavaPelaaja(); // masa aktiivinen
        sessio.seuraavaPelaaja(); // matti aktiivinen
        sessio.seuraavaPelaaja(); // masa aktiivinen
        assertEquals(masa, sessio.getAktiivinenPelaaja());
    }

    @Test
    public void kaikillaVahintaanYksiVuoroPalauttaaFalseEnsimmaisenVuoronJalkeen() {
        sessio.seuraavaPelaaja(); // masa aktiivinen
        sessio.seuraavaPelaaja(); // matti aktiivinen, masalle lisätty vuoro
        assertTrue(!sessio.kaikillaVahintaanYksiVuoro());
    }

    @Test
    public void kaikillaVahintaanYksiVuoroToimii() {
        sessio.seuraavaPelaaja(); // masa aktiivinen
        sessio.seuraavaPelaaja(); // matti aktiivinen, masalle lisätty vuoro
        sessio.seuraavaPelaaja(); // masa aktiivinen, matillekin lisätty vuoro
        assertTrue(sessio.kaikillaVahintaanYksiVuoro());
    }
    
    @Test
    public void pelaajallaEiKirjaimiaToimii() {
        sessio.seuraavaPelaaja(); // masa aktiivinen
        assertTrue(sessio.pelaajallaEiKirjaimia());
        masa.lisaaKirjain(new Character('A'));
        assertTrue(!sessio.pelaajallaEiKirjaimia());
    }
    
    @Test
    public void arvoPelaajalleKirjaimiaToimiiTyhjaanListaan() {
        sessio.seuraavaPelaaja(); // masa aktiivinen
        sessio.arvoPelaajalleKirjaimia();
        assertEquals(sessio.getKirjaimia(), masa.getOmatKirjaimet().size());
    }
    
    @Test
    public void arvoPelaajalleKirjaimiaToimiiVajaaseenListaan() {
        sessio.seuraavaPelaaja(); // masa aktiivinen
        masa.lisaaKirjain('A');
        sessio.arvoPelaajalleKirjaimia();
        assertEquals(sessio.getKirjaimia(), masa.getOmatKirjaimet().size());
    }
    
    @Test
    public void arvoPelaajalleKirjaimiaEiTeeMitaanTayteenListaan() {
        sessio.arvoPelaajienAloitusKirjaimet();
        sessio.seuraavaPelaaja(); // masa aktiivinen
        String kirjaimet = masa.getOmatKirjaimet().toString();
        sessio.arvoPelaajalleKirjaimia();
        String kirjaimetAfter = masa.getOmatKirjaimet().toString();
        assertEquals(kirjaimetAfter, kirjaimet);
    }
}