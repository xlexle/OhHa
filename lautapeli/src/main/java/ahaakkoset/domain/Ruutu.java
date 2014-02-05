package ahaakkoset.domain;

public class Ruutu {
    private boolean aktiivinen = false; 
    private boolean voiAsettaaAktiiviseksi = true;
    private Character kirjain = null;
    private Ruutu vasen;
    private Ruutu yla;
    private Ruutu oikea = null;
    private Ruutu ala = null;

    public Ruutu(Ruutu vasen, Ruutu yla) {
        this.vasen = vasen;
        this.yla = yla;
    }

    public boolean isAktiivinen() {
        return aktiivinen;
    }

    public void setAktiivinen(boolean aktiivinen) { // ei testattu
        if (voiAsettaaAktiiviseksi) {
            this.aktiivinen = aktiivinen;
        }
    }

    public void eiVoiAsettaaAktiiviseksi() {
        this.voiAsettaaAktiiviseksi = false;
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

//    luotu testejä varten
    public Character getKirjain() {
        return kirjain;
    }
}
