package ahaakkoset.sovelluslogiikka;

import ahaakkoset.domain.Pelaaja;
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

    private int pelaajallaKirjaimia;
    private int pelinPituus;
    private int pelaajanIndeksi = -1;
    private Pelaaja aktiivinenPelaaja;
    private String keskenOlevaSana = "";
    private List<Pelaaja> pelaajat = new ArrayList<>();
    private Kirjainvarasto vapaatKirjaimet;

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

        if (pelaajat.contains(new Pelaaja(nimi, pelaajallaKirjaimia))) {
            return false;
        }

        pelaajat.add(new Pelaaja(nimi, pelaajallaKirjaimia));
        return true;
    }
    
    public void luoKirjainVarasto() {
        // luodaan kirjainvarasto varastossaKirjaimia avulla
        this.vapaatKirjaimet = new Kirjainvarasto(pelinPituus);
    }

    /**
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
    public void lisaaKirjainSanaan(Character c) { // ei testattu
        keskenOlevaSana += c;
        aktiivinenPelaaja.poistaKirjain(c);
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

    public int getKirjaimia() {
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

    public String getKeskenOlevaSana() {
        return keskenOlevaSana;
    }

    public void setPelaajallaKirjaimia(int kirjaimia) {
        this.pelaajallaKirjaimia = kirjaimia;
    }

    public void setPelinPituus(int pituus) {
        this.pelinPituus = pituus;
    }

    @Override
    public String toString() {
        return "loppuyhteenveto"; //placeholder
        // i = suoritettujen vuorojen määrä pelaajalla jolla vähemmän vuoroja
        // hakee sanat indeksillä 0->i ja kysyy pelaajalta pisteet i:hin asti
        // listaa pelaajan sanat i:hin asti (Sana.toString)
    }
}
