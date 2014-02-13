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
    private JButton edellinenValittu;
    private JButton seuraavaPainike;

    /**
     *
     * @param ruudukkoRuutuja
     * @param ruudukkoNappeja
     */
    public Ruudunaktivoija(Map ruudukkoRuutuja, Map ruudukkoNappeja, JButton seuraavaPainike) {
        this.ruudukkoRuutuja = ruudukkoRuutuja;
        this.ruudukkoNappeja = ruudukkoNappeja;
        this.seuraavaPainike = seuraavaPainike;
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
     * Metodi asettaa aktiivisiksi pelilaudan napit lukuunottamatta niitä,
     * joista sanan aloittaminen tapahtuisi jo olemassaolevan sanan päädystä tai
     * aiheuttaisi tilanteen jossa (1) missään suunnassa ei ole ruutua jonka voi
     * valita, tai (2) valittavissa on vain sana jonka päätyruutuun ei voi
     * lisätä mitään.
     *
     */
    public void ennenEnsimmaistaKirjainta() { // ei testattu
        asetaKaikkienRuutujenAktiivisuus(true);

        for (Ruutu ruutu : ruudukkoRuutuja.values()) {
            JButton nappi = ruudukkoNappeja.get(ruutu);
            Ruutu vasen = ruutu.getVasen();
            Ruutu yla = ruutu.getYla();
            Ruutu oikea = ruutu.getOikea();
            Ruutu ala = ruutu.getAla();
            
            if (oikea == null && ala == null) {
                nappi.setEnabled(false);
            }

            if (ruutu.sisaltaaKirjaimen()) {
                if (vasen != null && yla != null) {
                    if (vasen.sisaltaaKirjaimen() && yla.sisaltaaKirjaimen()) {
                        nappi.setEnabled(false);
                    }
                }

                if (vasen != null && vasen.getAla() != null) {
                    if (vasen.sisaltaaKirjaimen()
                            && vasen.getAla().sisaltaaKirjaimen()) {
                        nappi.setEnabled(false);
                    }
                }

                if (yla != null && yla.getOikea() != null) {
                    if (yla.sisaltaaKirjaimen()
                            && yla.getOikea().sisaltaaKirjaimen()) {
                        nappi.setEnabled(false);
                    }
                }

                if (oikea != null && oikea.getAla() != null && vasen != null) {
                    if (oikea.sisaltaaKirjaimen()
                            && oikea.getAla().sisaltaaKirjaimen()
                            && vasen.sisaltaaKirjaimen()) {
                        nappi.setEnabled(false);
                    }
                }

                if (ala != null && ala.getOikea() != null && yla != null) {
                    if (ala.sisaltaaKirjaimen()
                            && ala.getOikea().sisaltaaKirjaimen()
                            && yla.sisaltaaKirjaimen()) {
                        nappi.setEnabled(false);
                    }
                }
            } else {
                if (vasen != null) {
                    if (vasen.sisaltaaKirjaimen()) {
                        nappi.setEnabled(false);
                    }
                }
                if (yla != null) {
                    if (yla.sisaltaaKirjaimen()) {
                        nappi.setEnabled(false);
                    }
                }

                if (kahdenKirjainRuudunKulmassa(ruutu)) {
                    ruutu.eiVoiSetEnabled();
                }
            }
        }
    }

//    private boolean ensimmainenTyhjaOikeallaKaytettavissa(Ruutu ruutu) {
//        Ruutu seuraava = ruutu.getOikea();
//    }
//
//    private boolean ensimmainenTyhjaAllaKaytettavissa(Ruutu ruutu) {
//        Ruutu seuraava = ruutu.getAla();
//    }

    /**
     *
     * @param eka
     */
    public void ensimmaisenKirjaimenJalkeen(JButton nappi) {
        this.asetaKaikkienRuutujenAktiivisuus(false);
        Ruutu ruutu = ruudukkoRuutuja.get(nappi);
        Ruutu vasen = ruutu.getVasen();
        Ruutu yla = ruutu.getYla();
        Ruutu oikea = ruutu.getOikea();
        Ruutu ala = ruutu.getAla();

        if (ruutu.sisaltaaKirjaimen()) {
        } else {
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

    /**
     *
     * @param nappi
     */
    private void oikeaAktiiviseksi(JButton nappi) { // ei testattu
        if (ruudukkoRuutuja.get(nappi).getOikea() != null) {
            Ruutu oikea = ruudukkoRuutuja.get(nappi).getOikea();
            if (oikea.isVoiSetEnabled()) {
                JButton oikeaNappi = ruudukkoNappeja.get(oikea);
                oikeaNappi.setEnabled(true);
                seuraavaPainike = oikeaNappi;
            }
        } else {
            seuraavaPainike = null;
        }
    }

    /**
     *
     * @param nappi
     */
    private void alempiAktiiviseksi(JButton nappi) { // ei testattu
        if (ruudukkoRuutuja.get(nappi).getAla() != null) {
            Ruutu ala = ruudukkoRuutuja.get(nappi).getAla();
            if (ala.isVoiSetEnabled()) {
                JButton alaNappi = ruudukkoNappeja.get(ala);
                alaNappi.setEnabled(true);
                seuraavaPainike = alaNappi;
            }
        } else {
            seuraavaPainike = null;
        }
    }

    /**
     *
     * @param nappi
     */
    private boolean ylempiJaAlempiSisaltaaKirjaimen(JButton nappi) { // ei testattu
        Ruutu r = ruudukkoRuutuja.get(nappi);

        if (r.getYla() == null) {
            return r.getAla().getKirjain() != null;
        }

        return r.getYla().getKirjain() != null && r.getAla().getKirjain() != null;
    }

    /**
     *
     * @param nappi
     */
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
            if (ruutu.getKirjain() == null && kahdenKirjainRuudunKulmassa(ruutu)) {
                ruutu.eiVoiSetEnabled();
            }
        }
    }

    /**
     *
     * @param r
     */
    private boolean kahdenKirjainRuudunKulmassa(Ruutu r) { // ei testattu
        boolean allaJaOikealla = r.getAla().getKirjain() != null && r.getOikea().getKirjain() != null;
        boolean allaJaVasemmalla = r.getAla().getKirjain() != null && r.getVasen().getKirjain() != null;
        boolean yllaJaOikealla = r.getYla().getKirjain() != null && r.getOikea().getKirjain() != null;
        boolean yllaJaVasemmalla = r.getYla().getKirjain() != null && r.getVasen().getKirjain() != null;

        if (r.getYla() == null) {
            if (r.getVasen() == null) {
                return allaJaOikealla;
            } else if (r.getOikea() == null) {
                return allaJaVasemmalla;
            } else {
                return allaJaOikealla || allaJaVasemmalla;
            }
        } else if (r.getOikea() == null) {
            if (r.getAla() == null) {
                return yllaJaVasemmalla;
            } else {
                return yllaJaVasemmalla || allaJaVasemmalla;
            }
        } else if (r.getAla() == null) {
            if (r.getVasen() == null) {
                return yllaJaOikealla;
            } else {
                return yllaJaOikealla || yllaJaVasemmalla;
            }
        } else if (r.getVasen() == null) {
            return yllaJaOikealla || allaJaOikealla;
        } else {
            return allaJaOikealla || allaJaVasemmalla || yllaJaOikealla || yllaJaVasemmalla;
        }
    }
//
//    private boolean sanaVasemmalla(Ruutu r) { // ei testattu
//        if (r.getVasen() != null) {
//            if (r.getVasen().getKirjain() != null) {
//                if (r.getVasen().getVasen() != null) {
//                    if (r.getVasen().getVasen().getKirjain() != null) {
//                        return true;
//                    }
//                }
//            }
//        }
//
//        return false;
//    }
//
//    private boolean sanaOikealla(Ruutu r) { // ei testattu
//        if (r.getOikea() != null) {
//            if (r.getOikea().getKirjain() != null) {
//                if (r.getOikea().getOikea() != null) {
//                    if (r.getOikea().getOikea().getKirjain() != null) {
//                        return true;
//                    }
//                }
//            }
//        }
//
//        return false;
//    }
//
//    private boolean sanaYlla(Ruutu r) { // ei testattu
//        if (r.getYla() != null) {
//            if (r.getYla().getKirjain() != null) {
//                if (r.getYla().getYla() != null) {
//                    if (r.getYla().getYla().getKirjain() != null) {
//                        return true;
//                    }
//                }
//            }
//        }
//
//        return false;
//    }
//
//    private boolean sanaAlla(Ruutu r) { // ei testattu
//        if (r.getAla() != null) {
//            if (r.getAla().getKirjain() != null) {
//                if (r.getAla().getAla() != null) {
//                    if (r.getAla().getAla().getKirjain() != null) {
//                        return true;
//                    }
//                }
//            }
//        }
//
//        return false;
//    }
}
