package ahaakkoset.domain;

import java.util.HashMap;
import java.util.Map;

public class Pelilauta {

    private int sivunPituus;
    private Map<Integer, Ruutu> ruudut = new HashMap<>();

    public Pelilauta(int sivunPituus) { // ei testattu
        if (sivunPituus % 2 != 1) {
            System.out.println("Anna pariton luku.");
            System.exit(0);
        } else {
            this.sivunPituus = sivunPituus;
        }
        luoRuudukko();
    }

    public Ruutu haeRuutu(int sijainti) {
        return ruudut.get(sijainti);
    }

    private void luoRuudukko() {
        for (int i = 1; i <= sivunPituus * sivunPituus; i++) {
            Ruutu ruutu = new Ruutu(maaritaVasenRuutu(i), maaritaYlaRuutu(i));
            ruudut.put(i, ruutu);
            maaritaOikeaksiRuuduksi(i);
            maaritaAlaRuuduksi(i);
        }
    }

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

    private void maaritaOikeaksiRuuduksi(int ruudunSijainti) {
        boolean vasemmallaOnRuutu = ruudunSijainti != 1 || (ruudunSijainti - 1) % sivunPituus != 0;
        if (vasemmallaOnRuutu) {
            ruudut.get(ruudunSijainti - 1).setOikea(ruudut.get(ruudunSijainti));
        }
    }

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
