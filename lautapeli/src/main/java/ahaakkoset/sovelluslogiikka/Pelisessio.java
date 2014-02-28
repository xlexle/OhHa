package ahaakkoset.sovelluslogiikka;

import ahaakkoset.domain.Pelaaja;
import ahaakkoset.domain.Sana;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Pelisessio-luokka toimii välikappaleena käyttöliittymän ja pelin
 * domain-luokkien interaktiossa. Sovelluslogiikasta vastuussa olevana tämä
 * luokka käsittelee käyttöliittymästä saapuvia metodikutsuja ja joko muuttaa
 * oliomuuttujiensa ominaisuuksia tai palauttaa käyttöliittymälle informaatiota
 * oliomuuttujiensa ominaisuuksista.
 *
 * @author Ville Lehtinen
 */
public class Pelisessio {

    /*
     * Kullakin pelaajalla maksimissaan käytössä olevien kirjainten lukumäärä.
     */
    private int pelaajallaKirjaimia;
    /*
     * Käytetään kertoimena Kirjainvarastossa.
     */
    private int pelinPituus;
    /*
     * Muistissa pidettävä pelaajat-listan indeksi, käytetään vuoron 
     * vaihtamiseen.
     */
    private int indeksi = -1;
    /*
     * Vuorossa oleva pelaaja.
     */
    private Pelaaja aktiivinenPelaaja;
    /*
     * Pelissä osallisina olevat Pelaajat.
     */
    private List<Pelaaja> pelaajat = new ArrayList<>();
    /*
     * Pelissä vapaana olevat kirjaimet kapseloituna Kirjainvarasto-olioon.
     */
    private Kirjainvarasto vapaatKirjaimet;
    /*
     * Sana, jonka viimeksi vuorossa ollut Pelaaja loi.
     */
    private Sana viimeisinLuotu;

    public Pelisessio() {
    }

    /**
     * Metodi lisää pelaajat-listaan uuden Pelaajan jonka nimi saadaan
     * parametrina käyttöliittymästä. Pelaajalle asetetaan maksimikirjainmäärä
     * ennaltamäärätyn oliomuuttujan 'kirjaimia' perusteella. Uutta Pelaajaa ei
     * lisätä mikäli nimi on väärän pituinen tai sessiosta löytyy jo
     * samanniminen Pelaaja.
     *
     * @param pituus
     * @return pelin pituuden asettamisen onnistuminen
     */
    public boolean asetaPelinPituus(String pituus) {
        if (pituus == null) {
            return false;
        } else {
            if (pituus.equals("Normaali")) {
                this.pelinPituus = 1;
                return true;
            } else if (pituus.equals("Marathon")) {
                this.pelinPituus = 2;
                return true;
            }
        }

        return false;
    }

    /**
     * Metodi määrittää pelissä kullakin pelaajalla olevan kirjainten
     * maksimimäärän pelaajan käyttöliittymässä päättämän vaikeustason
     * perusteella. Vaikeampi vaikeustaso vastaa pienempää määrää kirjaimia.
     * Boolean-arvolla käyttöliittymä tarkistaa onko pelaaja valinnut
     * vaikeustasoa.
     *
     * @param vaikeustaso
     * @return pelaajan kirjainten asettamisen onnistuminen
     */
    public boolean asetaPelaajallaKirjaimia(String vaikeustaso) {
        if (vaikeustaso == null) {
            return false;
        } else {
            switch (vaikeustaso) {
                case "Pala kakkua":
                    this.pelaajallaKirjaimia = 9;
                    return true;
                case "Rokataan":
                    this.pelaajallaKirjaimia = 8;
                    return true;
                case "Täältä pesee":
                    this.pelaajallaKirjaimia = 7;
                    return true;
                case "Däämn oon hyvä":
                    this.pelaajallaKirjaimia = 6;
                    return true;
            }
        }

        return false;
    }

    /**
     * Metodi lisää peliin uuden Pelaajan jolla on parametrina saatu nimi.
     * Käyttöliittymä tarkistaa boolean-arvosta onnistuiko Pelaajan lisäys;
     * lisäys ei onnistu mikäli nimi on null (esim. klikataan käyttöliittymässä
     * "cancel"), väärän pituinen, tai peli sisältää jo pelaajan jolla on sama
     * nimi.
     *
     * @param nimi
     * @return pelaajan lisäyksen onnistuminen
     */
    public boolean lisaaPelaaja(String nimi) {
        if (nimi == null || nimi.length() < 3 || nimi.length() > 16) {
            return false;
        }

        if (pelaajat.contains(new Pelaaja(nimi, pelaajallaKirjaimia))) {
            return false;
        }

        pelaajat.add(new Pelaaja(nimi, pelaajallaKirjaimia));
        return true;
    }

    /**
     * Metodi luo Kirjainvaraston pelin käyttöön.
     *
     */
    public void luoKirjainVarasto() {
        this.vapaatKirjaimet = new Kirjainvarasto(pelinPituus);
    }

    /**
     * Metodi arpoo kullekin Pelaajalle kirjaimet Kirjainvarastosta. Ensin
     * kullekin pelajaalle 1 kirjain, sitten toinen, jne.
     *
     */
    public void arvoPelaajienAloitusKirjaimet() {
        for (int i = 0; i < pelaajallaKirjaimia; i++) {
            for (int j = 0; j < pelaajat.size(); j++) {
                pelaajat.get(j).lisaaKirjain(vapaatKirjaimet.arvoKirjain());
            }
        }
    }

    /**
     * Metodi lisää tämänhetkiselle pelaajan vuorokertymään vuoron ja vaihtaa
     * pelivuoroa. Metodissa määritelty "indeksi" viittaa sen pelaajan
     * indeksiin, jolle vuoro asetetaan metodin lopussa.
     *
     */
    public void seuraavaPelaaja() {
        if (indeksi == -1 || indeksi == pelaajat.size() - 1) {
            indeksi = 0;
        } else {
            indeksi++;
        }

        if (aktiivinenPelaaja != null) {
            aktiivinenPelaaja.lisaaVuoro();
        }

        aktiivinenPelaaja = pelaajat.get(indeksi);
    }

    /**
     * Metodi tarkistaa, onko kullakin Pelaajalla vähintään yksi vuoro.
     *
     * @return kaikilla vähintään yksi vuoro (true) tai ei (false)
     */
    public boolean kaikillaVahintaanYksiVuoro() {
        for (Pelaaja pelaaja : pelaajat) {
            if (pelaaja.getVuorot() <= 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * Metodi tarkistaa, onko tällä hetkellä vuorossa olevalla Pelaajalla
     * kirjaimia.
     *
     * @return pelaajalla ei kirjaimia (true) tai on (false)
     */
    public boolean pelaajallaEiKirjaimia() {
        if (!aktiivinenPelaaja.getOmatKirjaimet().isEmpty()) {
            return false;
        }

        return true;
    }

    /**
     * Metodi arpoo pelaajalle Kirjainvarastosta kirjaimia siten, että
     * Pelaajalla on jälleen käytössään maksimimäärä kirjaimia, tai niin monta
     * kun kirjaimia on Kirjainvarastossa jäljellä.
     *
     */
    public void arvoPelaajalleKirjaimia() {
        int montako = aktiivinenPelaaja.getEnintaanKirjaimia() - aktiivinenPelaaja.getOmatKirjaimet().size();
        if (montako > vapaatKirjaimet.getKirjainSailio().size()) {
            montako = vapaatKirjaimet.getKirjainSailio().size();
        }

        for (int i = 0; i < montako; i++) {
            aktiivinenPelaaja.lisaaKirjain(vapaatKirjaimet.arvoKirjain());
        }
    }

    /**
     * Metodi vaihtaa pelaajalla käytössä olevat kirjaimet. Lopussa kutsutaan
     * metodia arvoPelaajalleKirjaimia(), jotta Pelaaja saisi käyttöönsä
     * maksimimäärän kirjaimia vaikka Kirjainavarastossa olisi vähemmän
     * kirjaimia.
     *
     */
    public void vaihdaPelaajanKirjaimet() {
        if (vapaatKirjaimet.getKirjainSailio().isEmpty()) {
            return;
        }

        List<Character> uudet = new ArrayList<>();
        Iterator<Character> iteraattori = vapaatKirjaimet.getKirjainSailio().iterator();

        while (iteraattori.hasNext() && uudet.size() < pelaajallaKirjaimia) {
            uudet.add(vapaatKirjaimet.arvoKirjain());
        }

        for (Character c : aktiivinenPelaaja.getOmatKirjaimet()) {
            vapaatKirjaimet.getKirjainSailio().add(c);
        }

        aktiivinenPelaaja.setOmatKirjaimet(uudet);
        arvoPelaajalleKirjaimia();
    }

    /**
     * Metodi tarkistaa, löytyvätkö parametrina annetun merkkijonon kaikki
     * kirjaimet vuorossa olevan pelaajan hallusta.
     *
     * @param sana
     * @return metodikutsun onnistuminen
     */
    public boolean tarkistaKirjaimet(String sana) {
        List<Character> kirjaimet = new ArrayList<>(aktiivinenPelaaja.getOmatKirjaimet());

        for (int i = 0; i < sana.length(); i++) {
            Character kirjain = sana.charAt(i);
            if (kirjaimet.contains(kirjain)) {
                kirjaimet.remove(kirjain);
            } else {
                return false;
            }
        }

        return true;
    }

    /**
     * Metodi lisää Pelaajalle parametrina saaduista muuttujista määriteltävän
     * Sanan, ja poistaa pelaajalta kirjaimet, joista sana koostuu.
     *
     * @param sana
     * @param merkitys
     * @param pisteet
     */
    public void lisaaSanaPelaajalle(String sana, String merkitys, int pisteet) {
        Sana uusiSana = new Sana(sana, merkitys, pisteet);
        aktiivinenPelaaja.lisaaSana(uusiSana);
        this.viimeisinLuotu = uusiSana;

        if (sana.length() > 1) {
            for (int i = 0; i < sana.length(); i++) {
                aktiivinenPelaaja.poistaKirjain(sana.charAt(i));
            }
        }
    }

    /**
     * Metodi lisää Pelaajalle tyhjän sanan.
     *
     */
    public void lisaaTyhjaSanaPelaajalle() {
        lisaaSanaPelaajalle("", "", 0);
    }

    public int getPelaajallaKirjaimia() {
        return pelaajallaKirjaimia;
    }

    public List<Pelaaja> getPelaajat() {
        return pelaajat;
    }

    public Pelaaja getAktiivinenPelaaja() {
        return aktiivinenPelaaja;
    }

    public Kirjainvarasto getVapaatKirjaimet() {
        return vapaatKirjaimet;
    }

    public int getPelinPituus() {
        return pelinPituus;
    }

    /**
     * Metodi palauttaa merkkijonon, jossa ilmaistaan minkä Sanan vuorossa oleva
     * Pelaaja juuri loi.
     *
     * @return sananluontioperaation merkkijonoesitys
     */
    public String uusiSanaLuotu() {
        return "Vuoro " + aktiivinenPelaaja.getVuorot()
                + " - " + aktiivinenPelaaja.getNimi()
                + " keksi sanan " + viimeisinLuotu.toString() + "\n\n";
    }

    @Override
    public String toString() {
        String loppu = "";
        int vuoroonSaakka = vahitenVuoroja();
        for (int i = 0; i < pelaajat.size(); i++) {
            int pisteet = pelaajat.get(i).laskePisteet(vuoroonSaakka);
            loppu += pelaajat.get(i).getNimi() + ": "
                    + pisteet + " pistettä\n\n";
        }

        return loppu;
    }

    /**
     * Metodi palauttaa vuorojen lukumäärän siltä pelaajalta, jolla niitä on
     * vähiten.
     *
     * @return vuorojen lukumäärä
     */
    public int vahitenVuoroja() {
        int vahitenVuoroja = pelaajat.get(0).getVuorot();
        for (int i = 1; i < pelaajat.size(); i++) {
            int vuoroja = pelaajat.get(i).getVuorot();
            if (vuoroja < vahitenVuoroja) {
                vahitenVuoroja = vuoroja;
            }
        }

        return vahitenVuoroja;
    }
}
