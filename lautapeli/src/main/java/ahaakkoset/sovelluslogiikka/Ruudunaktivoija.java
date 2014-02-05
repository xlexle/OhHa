package ahaakkoset.sovelluslogiikka;

import ahaakkoset.domain.Ruutu;
import java.util.Map;

public class Ruudunaktivoija {

    private Map<Integer, Ruutu> ruudut;
    private Ruutu edellinenValittu = null;

    public Ruudunaktivoija(Map ruudut) {
        this.ruudut = ruudut;
    }
    
    public void tyhjennaEdellinenValittu() {
        edellinenValittu = null;
    }

    public void asetaKaikkienRuutujenAktiivisuus(boolean aktiivisuus) { // ei testattu
        for (Ruutu ruutu : ruudut.values()) {
            ruutu.setAktiivinen(aktiivisuus);
        }
    }

    public void asetaLopullisestiInaktiiviset() {  // ei testattu
        this.asetaKaikkienRuutujenAktiivisuus(false);
        for (Ruutu ruutu : ruudut.values()) {
            if (kahdenKirjainRuudunKulmassa(ruutu) || sananPaadyssa(ruutu)) {
                ruutu.eiVoiAsettaaAktiiviseksi();
            }
        }
    }

    public void maaritaEnsimmaisenRuudunJalkeisetAktiiviset(Ruutu eka) { // ei testattu
        this.asetaKaikkienRuutujenAktiivisuus(false);
        if (eka.getKirjain() == null) {
            oikeaAktiiviseksi(eka);
            alempiAktiiviseksi(eka);
        } else if (ylempiJaAlempiSisaltaaKirjaimen(eka)) {
            oikeaAktiiviseksi(eka);
        } else if (vasenJaOikeaSisaltaaKirjaimen(eka)) {
            alempiAktiiviseksi(eka);
        }

        edellinenValittu = eka;
    }

    public void maaritaSeuraavatAktiiviset(Ruutu ruutu) { // ei testattu
        this.asetaKaikkienRuutujenAktiivisuus(false);

        if (ruutu.getVasen() == edellinenValittu) {
            oikeaAktiiviseksi(ruutu);
        } else if (ruutu.getYla() == edellinenValittu) {
            alempiAktiiviseksi(ruutu);
        }

        edellinenValittu = ruutu;
    }

    private void oikeaAktiiviseksi(Ruutu r) { // ei testattu
        if (r.getOikea() != null) {
            r.getOikea().setAktiivinen(true);
        }
    }

    private void alempiAktiiviseksi(Ruutu r) { // ei testattu
        if (r.getAla() != null) {
            r.getAla().setAktiivinen(true);
        }
    }

    private boolean ylempiJaAlempiSisaltaaKirjaimen(Ruutu r) { // ei testattu
        return r.getYla().getKirjain() == null || r.getAla().getKirjain() == null;
    }

    private boolean vasenJaOikeaSisaltaaKirjaimen(Ruutu r) { // ei testattu
        return r.getVasen().getKirjain() == null || r.getOikea().getKirjain() == null;
    }

    private boolean kahdenKirjainRuudunKulmassa(Ruutu r) { // ei testattu
        return (r.getYla().getKirjain() == null || r.getOikea().getKirjain() == null)
                || (r.getYla().getKirjain() == null || r.getVasen().getKirjain() == null)
                || (r.getAla().getKirjain() == null || r.getOikea().getKirjain() == null)
                || (r.getAla().getKirjain() == null || r.getVasen().getKirjain() == null);
    }

    private boolean sananPaadyssa(Ruutu r) { // ei testattu
        return sanaVasemmalla(r) || sanaOikealla(r) || sanaYlla(r) || sanaAlla(r);
    }

    private boolean sanaVasemmalla(Ruutu r) { // ei testattu
        if (r.getVasen() != null) {
            if (r.getVasen().getKirjain() != null) {
                if (r.getVasen().getVasen() != null) {
                    if (r.getVasen().getVasen().getKirjain() != null) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean sanaOikealla(Ruutu r) { // ei testattu
        if (r.getOikea() != null) {
            if (r.getOikea().getKirjain() != null) {
                if (r.getOikea().getOikea() != null) {
                    if (r.getOikea().getOikea().getKirjain() != null) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean sanaYlla(Ruutu r) { // ei testattu
        if (r.getYla() != null) {
            if (r.getYla().getKirjain() != null) {
                if (r.getYla().getYla() != null) {
                    if (r.getYla().getYla().getKirjain() != null) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean sanaAlla(Ruutu r) { // ei testattu
        if (r.getAla() != null) {
            if (r.getAla().getKirjain() != null) {
                if (r.getAla().getAla() != null) {
                    if (r.getAla().getAla().getKirjain() != null) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
