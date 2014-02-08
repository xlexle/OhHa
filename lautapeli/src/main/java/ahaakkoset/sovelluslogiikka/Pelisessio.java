package ahaakkoset.sovelluslogiikka;

import ahaakkoset.domain.Pelaaja;
import ahaakkoset.domain.Pelilauta;
import ahaakkoset.domain.Sana;
import java.util.List;
import java.util.ArrayList;

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

    private final int kirjaimia = 8;
    private int pelaajanIndeksi = -1;
    private Pelaaja aktiivinenPelaaja;
    private String keskenOlevaSana = "";
    private Pelilauta pelilauta = new Pelilauta(11);
    private List<Pelaaja> pelaajat = new ArrayList<>();
    private Kirjainvarasto vapaatKirjaimet = new Kirjainvarasto();

    /**
     *
     */
    public Pelisessio() {
    }

    /**
     * Metodi lisää pelaajat-listaan uuden Pelaajan jonka nimi saadaan
     * parametrina käyttöliittymästä. Pelaajalle asetetaan maksimikirjainmäärä
     * ennaltamäärätyn oliomuuttujan 'kirjaimia' perusteella. Uutta Pelaajaa ei lisätä
     * mikäli nimi on väärän pituinen tai sessiosta löytyy jo samanniminen
     * Pelaaja.
     *
     * @param nimi
     * @return metodikutsun onnistuminen
     */
    public boolean lisaaPelaaja(String nimi) {
        if (nimi == null || nimi.length() < 3 || nimi.length() > 16) {
            return false;
        }

        if (pelaajat.contains(new Pelaaja(nimi, kirjaimia))) {
            return false;
        }

        pelaajat.add(new Pelaaja(nimi, kirjaimia));
        return true;
    }

    /**
     *
     */
    public void arvoPelaajienAloitusKirjaimet() {
        for (int i = 0; i < kirjaimia; i++) {
            for (int j = 0; j < pelaajat.size(); j++) {
                pelaajat.get(j).lisaaKirjain(vapaatKirjaimet.arvoKirjain());
            }
        }
    }

    /**
     *
     */
    public void seuraavaPelaaja() {
        if (pelaajanIndeksi == -1 || pelaajanIndeksi == pelaajat.size() - 1) {
            pelaajanIndeksi = 0;
        } else {
            pelaajanIndeksi++;
        }

        if (aktiivinenPelaaja != null) {
            aktiivinenPelaaja.lisaaVuoro();
        }

        tyhjennaKeskenOlevaSana();
        aktiivinenPelaaja = pelaajat.get(pelaajanIndeksi);
    }

    /**
     *
     * @return
     */
    public boolean kaikillaVahintaanYksiVuoro() {
        for (Pelaaja pelaaja : pelaajat) {
            if (pelaaja.getVuorot() == 0) {
                return false;
            }
        }

        return true;
    }

    /**
     *
     * @param c
     * @return
     */
    public Character otaPelaajaltaKirjain(Character c) {
        return aktiivinenPelaaja.otaKirjain(c);
    }

    /**
     *
     * @return
     */
    public boolean pelaajallaEiKirjaimia() {
        if (!aktiivinenPelaaja.getOmatKirjaimet().isEmpty()) {
            return false;
        }

        return true;
    }

    /**
     *
     */
    public void arvoPelaajalleKirjaimia() {
        int montako = aktiivinenPelaaja.getEnintaanKirjaimia() - aktiivinenPelaaja.getOmatKirjaimet().size();
        for (int i = 0; i < montako; i++) {
            aktiivinenPelaaja.lisaaKirjain(vapaatKirjaimet.arvoKirjain());
        }
    }

    /**
     *
     * @param c
     */
    public void lisaaKirjainSanaan(Character c) {
        keskenOlevaSana += c;
    }

    /**
     *
     * @return
     */
    public boolean sanassaEiVielaKirjaimia() {
        return keskenOlevaSana.isEmpty();
    }

    /**
     *
     */
    public void tyhjennaKeskenOlevaSana() {
        keskenOlevaSana = "";
    }

    /**
     *
     * @param merkitys
     * @param pisteet
     */
    public void lisaaSanaPelaajalle(String merkitys, int pisteet) {
        aktiivinenPelaaja.lisaaSana(new Sana(keskenOlevaSana, merkitys, pisteet));
    }

    /**
     *
     * @return
     */
    public int getKirjaimia() {
        return kirjaimia;
    }

    /**
     *
     * @return
     */
    public Pelilauta getPelilauta() {
        return pelilauta;
    }

    /**
     *
     * @return
     */
    public List<Pelaaja> getPelaajat() {
        return pelaajat;
    }

    /**
     *
     * @return
     */
    public Pelaaja getAktiivinenPelaaja() {
        return aktiivinenPelaaja;
    }

    /**
     *
     * @return
     */
    public Kirjainvarasto getVapaatKirjaimet() {
        return vapaatKirjaimet;
    }

    /**
     *
     * @return
     */
    public String getKeskenOlevaSana() {
        return keskenOlevaSana;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "loppuyhteenveto"; //placeholder
        // i = suoritettujen vuorojen määrä pelaajalla jolla vähemmän vuoroja
        // hakee sanat indeksillä 0->i ja kysyy pelaajalta pisteet i:hin asti
        // listaa pelaajan sanat i:hin asti (Sana.toString)
    }
}
