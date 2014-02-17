package ahaakkoset.domain;

/**
 * Sana edustaa käyttöliittymässä luotua merkkijonoa jolle käyttäjä on antanut
 * merkityksen ja pisteytyksen.
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
     * Metodia käytetään Sanan listaamisessa pelin luodut sanat sisältävään
     * tekstikenttään sekä lopetusruudulle.
     *
     * @return
     */
    @Override
    public String toString() {
        return sisalto + "\nMerkitys: " + merkitys + "\nPisteet: " + pisteet;
    }

    public int getPisteet() {
        return pisteet;
    }
}
