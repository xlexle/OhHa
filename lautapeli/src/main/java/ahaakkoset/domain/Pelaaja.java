package ahaakkoset.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Pelaaja edustaa ohjelmaan käyttöliittymän kautta yhteydessä olevaa henkilöä.
 * Kullakin Pelaajalla on ainutlaatuinen nimi. Pelaajalla on käytössään
 * kirjaimia enintään Pelisession määräämä määrä. Pelaaja pitää kirjaa
 * suorittamistaan vuoroista ja luomistaan Sanoista.
 *
 * @author Ville Lehtinen
 */
public class Pelaaja {

    private String nimi;
    private int enintaanKirjaimia;
    /*
     * Kertyneiden vuorojen lukumäärä. Mukaan lasketaan niin väliin jätetyt 
     * kuin loppuunkin suoritetut vuorot.
     */
    private int vuoroja = 0;
    private List<Character> omatKirjaimet;
    private List<Sana> luodutSanat;

    /**
     * Konstruktori asettaa Pelaajalle Pelisessiolta saadun ainutlaatuisen nimen
     * ja kirjainten enimmäislukumäärän.
     *
     * @param nimi
     * @param kirjaimia
     */
    public Pelaaja(String nimi, int kirjaimia) {
        this.nimi = nimi;
        this.enintaanKirjaimia = kirjaimia;
        this.omatKirjaimet = new ArrayList<>();
        this.luodutSanat = new ArrayList<>();
    }

    /**
     * Metodi lisää Pelisessiolta saadun kirjaimen pelaajan käyttöön.
     *
     * @param kirjain
     * @return metodikutsun onnistuminen
     */
    public boolean lisaaKirjain(Character kirjain) {
        if (omatKirjaimet.size() >= enintaanKirjaimia) {
            return false;
        }

        omatKirjaimet.add(kirjain);
        return true;
    }

    public void lisaaVuoro() {
        vuoroja++;
    }

    /**
     * Metodi kirjaa Pelaajalle Pelisessiolta saadun uuden Sana-olion.
     *
     * @param sana
     */
    public void lisaaSana(Sana sana) {
        luodutSanat.add(sana);
    }

    /**
     * Tätä käytetään Pelaajan tietojen esittämiseen graafisessa
     * käyttöliittymässä.
     *
     * @return Pelaajan merkkijonoesitys
     */
    @Override
    public String toString() { // ei testattu
        return "Nimi: " + nimi + "\n" + "Pisteet: " + laskePisteet() + "\n" + "Kirjaimet: " + omatKirjaimet;
    }

    /**
     * Poistaa parametrina saadun kirjaimen pelaajalta.
     *
     * @param kirjain
     * @return kirjain
     */
    public void poistaKirjain(Character kirjain) { // ei testattu
        omatKirjaimet.remove(kirjain);
    }

    public String getNimi() {
        return nimi;
    }

    public int getVuorot() {
        return vuoroja;
    }

    public List<Sana> getLuodutSanat() {
        return luodutSanat;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }

        Pelaaja verrattava = (Pelaaja) o;
        String[] nimet = new String[2];
        nimet[0] = verrattava.nimi;
        nimet[1] = this.nimi;

        for (int i = 0; i < nimet.length; i++) {
            nimet[i] = nimet[i].toUpperCase();
            nimet[i] = nimet[i].trim();
        }

        if (!nimet[0].equals(nimet[1])) {
            return false;
        }

        return true;
    }

    /**
     * Metodi laskee pelaajalle kirjattujen Sanojen pisteiden summan parametrina
     * annettuun indeksiin asti. Koska Sanat kirjataan Pelaajalle siinä
     * järjestykessä kun vuoroja kertyy, metodia voidaan käyttää pisteiden
     * summan laskemiseen tiettyyn vuoron järjestysnumeroon saakka.
     *
     * @param viimIndeksi
     * @return pisteiden summa
     */
    public int laskePisteet(int viimIndeksi) {
        int pisteet = 0;
        for (int i = 0; i < viimIndeksi; i++) {
            pisteet += luodutSanat.get(i).getPisteet();
        }

        return pisteet;
    }

    public int laskePisteet() {
        return laskePisteet(luodutSanat.size());
    }

    public List<Character> getOmatKirjaimet() {
        return omatKirjaimet;
    }

    public int getEnintaanKirjaimia() {
        return enintaanKirjaimia;
    }
}
