package ahaakkoset.domain;

public class Sana {
    private String sana;
    private String merkitys;
    private int annetutPisteet;
    private Pelaaja luoja;

    public Sana(String sana, String merkitys, Pelaaja pelaaja) {
        this.sana = sana;
        this.merkitys = merkitys;
        this.annetutPisteet = annaPisteet();
        this.luoja = pelaaja;
    }
    
    private int annaPisteet() {
        return 0;
    }
}
