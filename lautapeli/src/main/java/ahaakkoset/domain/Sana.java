package ahaakkoset.domain;

public class Sana {
    private String sana;
    private String merkitys;
    private int pisteet;

    public Sana(String sana, String merkitys) {
        this.sana = sana;
        this.merkitys = merkitys; // pitää varmistaa että sisältää vain kirjaimia
    }
    
    public void asetaPisteet(int pisteet) {
        this.pisteet = pisteet;
    }
}
