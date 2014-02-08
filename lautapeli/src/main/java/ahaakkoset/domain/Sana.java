package ahaakkoset.domain;

/**
 * Sana edustaa käyttöliittymän ruudukkoon asetettua kirjainsarjaa.
 *
 * @author Ville Lehtinen
 */
public class Sana {

    private String sisalto;
    private String merkitys;
    private int pisteet;

    /**
     * Konstruktori asettaa Sanalle Pelisession määrittämän sisällön,
     * merkityksen ja pisteet.
     *
     * @param sisalto
     * @param merkitys
     * @param pisteet
     */
    public Sana(String sisalto, String merkitys, int pisteet) {
        this.sisalto = sisalto;
        this.merkitys = merkitys;
        this.pisteet = pisteet;
    }

    /**
     * Metodia käytetään Sanan listaamisessa pelin lopetusruudulle.
     *
     * @return
     */
    @Override
    public String toString() {
        return sisalto + " - " + pisteet + " pistettä\n"
                + "= " + merkitys + "\n"
                + "-----";
    }

    public int getPisteet() {
        return pisteet;
    }
}
