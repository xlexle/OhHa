package ahaakkoset.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * Pelilauta edustaa käyttöliittymän kaksiulotteista ruudukkoa ja on Pelisession
 * käytössä. Pelilauta luo itselleen sijainnin mukaan linkitetyn säiliön
 * Ruutu-olioita. Pelisessio määrää Pelilaudan koon.
 *
 * @author Ville Lehtinen
 */
public class Pelilauta {

    private int sivunPituus;
    private Map<Integer, Ruutu> ruudut = new HashMap<>();

    /**
     * Konstruktori tarkistaa, että Pelisession parametrina saatu sivun pituus
     * mahdollistaa ruudukon, jossa on olemassa yksi keskimmäinen ruutu. Muussa
     * tapauksessa ohjelman suorittajalle ilmoitetaan ohjeet ja ohjelma
     * lopetetaan. Konstruktori rakennuttaa Pelilaudan ruudukon.
     *
     * @param sivunPituus
     */
    public Pelilauta(int sivunPituus) { // ei testattu
        if (sivunPituus % 2 != 1) {
            System.out.println("Anna pariton luku.");
            System.exit(0);
        } else {
            this.sivunPituus = sivunPituus;
        }
        luoRuudukko();
    }

    /**
     * Metodi palauttaa sijaintia vastaavan Ruutu-olion.
     *
     * @param sijainti
     * @return Ruutu
     */
    public Ruutu haeRuutu(int sijainti) {
        return ruudut.get(sijainti);
    }
    
    /**
     * Metodi luo ruudukon sivun pituuden perusteella.
     * 
     */
    private void luoRuudukko() {
        for (int i = 1; i <= sivunPituus * sivunPituus; i++) {
            Ruutu ruutu = new Ruutu(maaritaVasenRuutu(i), maaritaYlaRuutu(i));
            ruudut.put(i, ruutu);
            maaritaOikeaksiRuuduksi(i);
            maaritaAlaRuuduksi(i);
        }
    }
    
    /**
     * 
     * @param sijainti
     * @return 
     */
    private Ruutu maaritaVasenRuutu(int sijainti) {
        Ruutu vasen;
        boolean vasemmallaEiRuutua = sijainti == 1 || (sijainti - 1) % sivunPituus == 0;
        if (vasemmallaEiRuutua) {
            vasen = null;
        } else {
            vasen = ruudut.get(sijainti - 1);
        }

        return vasen;
    }

    /**
     * 
     * @param sijainti
     * @return 
     */
    private Ruutu maaritaYlaRuutu(int sijainti) {
        Ruutu yla;
        boolean yllaEiRuutua = sijainti <= sivunPituus;
        if (yllaEiRuutua) {
            yla = null;
        } else {
            yla = ruudut.get(sijainti - sivunPituus);
        }
        return yla;
    }

    /**
     * 
     * @param ruudunSijainti 
     */
    private void maaritaOikeaksiRuuduksi(int ruudunSijainti) {
        boolean vasemmallaOnRuutu = (ruudunSijainti - 1) % sivunPituus != 0;
        if (vasemmallaOnRuutu) {
            ruudut.get(ruudunSijainti - 1).setOikea(ruudut.get(ruudunSijainti));
        }
    }

    /**
     * 
     * @param ruudunSijainti 
     */
    private void maaritaAlaRuuduksi(int ruudunSijainti) {
        boolean yllaOnRuutu = ruudunSijainti > sivunPituus;
        if (yllaOnRuutu) {
            ruudut.get(ruudunSijainti - sivunPituus).setAla(ruudut.get(ruudunSijainti));
        }
    }

    public int getSivunPituus() {
        return sivunPituus;
    }

    public Map<Integer, Ruutu> getRuudut() {
        return ruudut;
    }
}
