package ahaakkoset.domain;

public class Ruutu {
    private int vaakaSijainti;
    private char pystySijainti;
    private Character kirjain = null;

    public Ruutu(int numero, char kirjain) {
        this.vaakaSijainti = numero;
        this.pystySijainti = kirjain;
    }

    public void setKirjain(Character kirjain) {
        this.kirjain = kirjain;
    }

    @Override
    public String toString() {
        return pystySijainti + "" + vaakaSijainti;
    }
}
