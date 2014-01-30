package ahaakkoset.domain;

import java.util.HashMap;
import java.util.Map;

public class Pelilauta {

    private int sivunPituus;
    private Map<Integer, Ruutu> ruudut = new HashMap<>();

    public Pelilauta(int sivunPituus) {
        this.sivunPituus = sivunPituus;
        luoRuudukko();
    }
    
    // haeRuudut tarpeellisia..? kayttoliittyma
    public Ruutu haeRuutu(int sijainti) {
        return ruudut.get(sijainti);
    }
    
    public Ruutu haeVasenRuutu(int sijainti) {
        return ruudut.get(sijainti).getVasen();
    }
    
    public Ruutu haeYlaRuutu(int sijainti) {
        return ruudut.get(sijainti).getYla();
    }
    
    public Ruutu haeOikeaRuutu(int sijainti) {
        return ruudut.get(sijainti).getOikea();
    }
    
    public Ruutu haeAlaRuutu(int sijainti) {
        return ruudut.get(sijainti).getAla();
    }

    private void luoRuudukko() {
        for (int i = 1; i <= sivunPituus * sivunPituus; i++) {
            Ruutu ruutu = new Ruutu(maaritaVasenRuutu(i), maaritaYlaRuutu(i));
            ruudut.put(i, ruutu);
            maaritaOikeaksiRuuduksi(i, ruutu);
            maaritaAlaRuuduksi(i, ruutu);
        }
    }

    private Ruutu maaritaVasenRuutu(int i) {
        Ruutu vasen;
        boolean vasemmallaEiRuutua = i == 1 || (i - 1) % sivunPituus == 0;
        if (vasemmallaEiRuutua) {
            vasen = null;
        } else {
            vasen = ruudut.get(i - 1);
        }

        return vasen;
    }

    private Ruutu maaritaYlaRuutu(int i) {
        Ruutu yla;
        boolean yllaEiRuutua = i <= sivunPituus;
        if (yllaEiRuutua) {
            yla = null;
        } else {
            yla = ruudut.get(i - sivunPituus);
        }
        return yla;
    }

    private void maaritaOikeaksiRuuduksi(int i, Ruutu r) {
        boolean vasemmallaOnRuutu = i != 1 || (i - 1) % sivunPituus != 0;
        if (vasemmallaOnRuutu) {
            ruudut.get(i - 1).setOikea(ruudut.get(i));
        }
    }

    private void maaritaAlaRuuduksi(int i, Ruutu r) {
        boolean yllaOnRuutu = i > sivunPituus;
        if (yllaOnRuutu) {
            ruudut.get(i - sivunPituus).setAla(r);
        }
    }

//     luotu testejä varten
    public Map<Integer, Ruutu> getRuudut() {
        return ruudut;
    }
}
