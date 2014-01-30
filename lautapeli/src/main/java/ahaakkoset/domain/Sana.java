package ahaakkoset.domain;

public class Sana {
    private String sana;
    private String merkitys;
    private int pisteet;
    private Pelaaja luoja;

    public Sana(String sana, String merkitys, Pelaaja pelaaja) {
        this.sana = sana;
        this.merkitys = merkitys; // pitää varmistaa että sisältää vain kirjaimia
        this.pisteet = asetaPisteet();
        this.luoja = pelaaja;
    }
    
    private int asetaPisteet() {
        return 0;
    }

    @Override
    public String toString() {
        return sana + ": " + merkitys + " (pelaajalle " + luoja.getNimi() + " " + pisteet + " pistettä)";
    }   
}
