package ahaakkoset.sovelluslogiikka;

import ahaakkoset.domain.Pelaaja;
import java.util.List;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;

public class PelisessioTest {

    private Pelisessio sessio;
    private Pelaaja masa;
    private Pelaaja matti;

    // TESTIT EI VÄLTTÄMÄTTÄ AJAN TASALLA...
    @Before
    public void setUp() {
        this.sessio = new Pelisessio();
        sessio.asetaPelaajallaKirjaimia("Rokataan");
        sessio.asetaPelinPituus("Normaali");
        sessio.lisaaPelaaja("Masa");
        sessio.lisaaPelaaja("Matti");
        sessio.luoKirjainVarasto();
        this.masa = sessio.getPelaajat().get(0);
        this.matti = sessio.getPelaajat().get(1);
    }

    @Test
    public void asettaaPelinPituudenOikein() {
        assertTrue(sessio.asetaPelinPituus("Normaali"));
        assertEquals(1, sessio.getPelinPituus());
        assertTrue(sessio.asetaPelinPituus("Marathon"));
        assertEquals(2, sessio.getPelinPituus());
    }

    @Test
    public void eiAsetaPelillePituuttaJosNullTaiMuu() {
        Pelisessio sessio2 = new Pelisessio();
        assertTrue(!sessio2.asetaPelinPituus(null));
        assertTrue(!sessio2.asetaPelinPituus("asdfg"));
        assertEquals(0, sessio2.getPelinPituus());
    }

    @Test
    public void asetaPelaajallaKirjaimiaToimii() {
        assertTrue(sessio.asetaPelaajallaKirjaimia("Pala kakkua"));
        assertEquals(9, sessio.getPelaajallaKirjaimia());
        assertTrue(sessio.asetaPelaajallaKirjaimia("Rokataan"));
        assertEquals(8, sessio.getPelaajallaKirjaimia());
        assertTrue(sessio.asetaPelaajallaKirjaimia("Täältä pesee"));
        assertEquals(7, sessio.getPelaajallaKirjaimia());
        assertTrue(sessio.asetaPelaajallaKirjaimia("Däämn oon hyvä"));
        assertEquals(6, sessio.getPelaajallaKirjaimia());
    }

    @Test
    public void eiAsetaPelaajallaKirjaimiaJosNullTaiMuu() {
        Pelisessio sessio2 = new Pelisessio();
        assertTrue(!sessio2.asetaPelaajallaKirjaimia(null));
        assertTrue(!sessio2.asetaPelaajallaKirjaimia("asdfg"));
        assertEquals(0, sessio2.getPelaajallaKirjaimia());
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
        assertEquals(sessio.getPelaajallaKirjaimia(), masa.getOmatKirjaimet().size());
        assertEquals(sessio.getPelaajallaKirjaimia(), matti.getOmatKirjaimet().size());
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
        assertEquals(sessio.getPelaajallaKirjaimia(), masa.getOmatKirjaimet().size());
    }

    @Test
    public void arvoPelaajalleKirjaimiaToimiiVajaaseenListaan() {
        sessio.seuraavaPelaaja(); // masa aktiivinen
        masa.lisaaKirjain('A');
        sessio.arvoPelaajalleKirjaimia();
        assertEquals(sessio.getPelaajallaKirjaimia(), masa.getOmatKirjaimet().size());
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
    
    @Test
    public void vaihdaPelaajanKirjaimetSailyttaaPelaajanAlkuperaisenKirjainmaaran() {
        sessio.seuraavaPelaaja(); // masa aktiivinen
        sessio.arvoPelaajalleKirjaimia();
        
        int maara = masa.getOmatKirjaimet().size();
        sessio.vaihdaPelaajanKirjaimet();
        assertEquals(maara, masa.getOmatKirjaimet().size());
    }
    
    @Test
    public void vaihdaPelaajanKirjaimetVaihtaaKaikkiJosVarastossaRiittaa() {
        sessio.seuraavaPelaaja(); // masa aktiivinen
        for (int i = 0; i < masa.getEnintaanKirjaimia(); i++) {
            masa.lisaaKirjain('X'); // ei löydy varastosta
        }
        
        sessio.vaihdaPelaajanKirjaimet();
        
        for (Character c : masa.getOmatKirjaimet()) {
            assertTrue(!c.equals('X'));
        }
    }
    
    @Test
    public void vaihdaPelaajanKirjaimetEiTeeMitaanJosVarastoTyhja() {
        sessio.seuraavaPelaaja(); // masa aktiivinen
        sessio.arvoPelaajalleKirjaimia();
        
        String poisarvotut = "";
        while (sessio.getVapaatKirjaimet().getKirjainSailio().size() > 0) {            
            poisarvotut += sessio.getVapaatKirjaimet().arvoKirjain();
        }
        
        List kirjaimetNyt = masa.getOmatKirjaimet();
        sessio.vaihdaPelaajanKirjaimet();
        assertEquals(kirjaimetNyt, masa.getOmatKirjaimet());
    }
    
    @Test
    public void tarkistaKirjaimetPalauttaaFalseJosYksikinKirjainPuuttuuPelaajaalta() {
        sessio.seuraavaPelaaja(); // masa aktiivinen
        for (int i = 0; i < masa.getEnintaanKirjaimia(); i++) {
            masa.lisaaKirjain('X');
        }
        
        assertTrue(!sessio.tarkistaKirjaimet("XXXXY"));
    }
    
    @Test
    public void tarkistaKirjaimetPalauttaaTrueJosKaikkiLoytyy() {
        sessio.seuraavaPelaaja(); // masa aktiivinen
        for (int i = 0; i < masa.getEnintaanKirjaimia(); i++) {
            masa.lisaaKirjain('X');
        }
        
        assertTrue(sessio.tarkistaKirjaimet("XXXXX"));
    }
}