package ahaakkoset.gui;

import ahaakkoset.domain.Ruutu;
import java.util.Map;
import javax.swing.JButton;

/**
 * Ruudunaktivoija on PelilautaPanel-luokan apuluokka joka vastaa Ruutuihin
 * liittyvien JButton-nappuloiden aktivointi- ja deaktivointilogiikasta. Se
 * tarjoaa metodit pelin seuraavaksi vuorossa olevalle askeleelle sopivien
 * nappuloiden aktivoinnille.
 *
 * @author Ville Lehtinen
 */
public class Ruudunaktivoija {

    private Map<Ruutu, JButton> ruudukkoNappeja;
    private Map<JButton, Ruutu> ruudukkoRuutuja;
    private JButton edellinenValittu = null;

    /**
     *
     * @param ruudukkoRuutuja
     * @param ruudukkoNappeja
     */
    public Ruudunaktivoija(Map ruudukkoRuutuja, Map ruudukkoNappeja) {
        this.ruudukkoRuutuja = ruudukkoRuutuja;
        this.ruudukkoNappeja = ruudukkoNappeja;
    }

    /**
     *
     */
    public void tyhjennaEdellinenValittu() {
        edellinenValittu = null;
    }

    /**
     *
     * @param aktiivisuus
     */
    public void asetaKaikkienRuutujenAktiivisuus(boolean aktiivisuus) { // ei testattu
        for (JButton nappi : ruudukkoRuutuja.keySet()) {
            if (ruudukkoRuutuja.get(nappi).isVoiSetEnabled()) {
                nappi.setEnabled(aktiivisuus);
            }
        }
    }

    /**
     *
     * @param eka
     */
    public void maaritaEnsimmaisenRuudunJalkeisetAktiiviset(JButton eka) { // ei testattu
        this.asetaKaikkienRuutujenAktiivisuus(false);
        if (ruudukkoRuutuja.get(eka).getKirjain() == null) {
            oikeaAktiiviseksi(eka);
            alempiAktiiviseksi(eka);
        } else if (ylempiJaAlempiSisaltaaKirjaimen(eka)) {
            oikeaAktiiviseksi(eka);
        } else if (vasenJaOikeaSisaltaaKirjaimen(eka)) {
            alempiAktiiviseksi(eka);
        }

        edellinenValittu = eka;
    }

    /**
     *
     * @param nappi
     */
    public void maaritaSeuraavatAktiiviset(JButton nappi) { // ei testattu
        this.asetaKaikkienRuutujenAktiivisuus(false);

        Ruutu edellinen = ruudukkoRuutuja.get(edellinenValittu);
        if (ruudukkoRuutuja.get(nappi).getVasen() == edellinen) {
            oikeaAktiiviseksi(nappi);
        } else if (ruudukkoRuutuja.get(nappi).getYla() == edellinen) {
            alempiAktiiviseksi(nappi);
        }

        edellinenValittu = nappi;
    }

    private void oikeaAktiiviseksi(JButton nappi) { // ei testattu
        if (ruudukkoRuutuja.get(nappi).getOikea() != null) {
            Ruutu oikea = ruudukkoRuutuja.get(nappi).getOikea();
            ruudukkoNappeja.get(oikea).setEnabled(true);
        }
    }

    private void alempiAktiiviseksi(JButton nappi) { // ei testattu
        if (ruudukkoRuutuja.get(nappi).getAla() != null) {
            Ruutu ala = ruudukkoRuutuja.get(nappi).getAla();
            ruudukkoNappeja.get(ala).setEnabled(true);
        }
    }

    private boolean ylempiJaAlempiSisaltaaKirjaimen(JButton nappi) { // ei testattu
        Ruutu yla = ruudukkoRuutuja.get(nappi).getYla();
        Ruutu ala = ruudukkoRuutuja.get(nappi).getAla();
        return yla.getKirjain() != null && ala.getKirjain() != null;
    }

    private boolean vasenJaOikeaSisaltaaKirjaimen(JButton nappi) { // ei testattu
        Ruutu vasen = ruudukkoRuutuja.get(nappi).getVasen();
        Ruutu oikea = ruudukkoRuutuja.get(nappi).getOikea();
        return vasen.getKirjain() != null && oikea.getKirjain() != null;
    }

    /**
     *
     */
    public void asetaLopullisestiInaktiiviset() {  // ei testattu
        this.asetaKaikkienRuutujenAktiivisuus(false);
        for (Ruutu ruutu : ruudukkoRuutuja.values()) {
            if (kahdenKirjainRuudunKulmassa(ruutu) || sananPaadyssa(ruutu)) {
                ruutu.eiVoiSetEnabled();
            }
        }
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
