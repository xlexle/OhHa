package ahaakkoset.domain;

/**
 * Ruutu edustaa käyttöliittymän kaksiulotteisen ruudukon yksittäistä
 * painiketta. Ruutu tarjoaa metodit sisältönsä sekä ympärillä olevien ruutujen
 * määrittämiseen ja tunnistamiseen. Ruutu voi epäsuorasti määrittää siihen
 * liittyvän käyttöliittymäpainikkeen tilan.
 *
 * @author Ville Lehtinen
 */
public class Ruutu {

    private boolean voiSetEnabled = true;
    private Character kirjain = null;
    private Ruutu vasen;
    private Ruutu yla;
    private Ruutu oikea = null;
    private Ruutu ala = null;

    /**
     * Konstruktori asettaa vasemmaksi ja ylemmäksi Ruuduksi jotkin aiemmin
     * luodut, Pelilaudan määräämät ruudut. Oikea ja alempi Ruutu määrätään
     * kyseisten Ruutujen luonnin yhteydessä.
     *
     * @param vasen
     * @param yla
     */
    public Ruutu(Ruutu vasen, Ruutu yla) {
        this.vasen = vasen;
        this.yla = yla;
    }

    public void eiVoiSetEnabled() {
        this.voiSetEnabled = false;
    }

    public boolean isVoiSetEnabled() {
        return voiSetEnabled;
    }

    public void setKirjain(Character kirjain) {
        this.kirjain = kirjain;
    }

    public void setOikea(Ruutu oikea) {
        this.oikea = oikea;
    }

    public void setAla(Ruutu ala) {
        this.ala = ala;
    }

    public Ruutu getVasen() {
        return vasen;
    }

    public Ruutu getYla() {
        return yla;
    }

    public Ruutu getOikea() {
        return oikea;
    }

    public Ruutu getAla() {
        return ala;
    }

    public Character getKirjain() {
        return kirjain;
    }
}
