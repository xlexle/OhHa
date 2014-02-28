package ahaakkoset.domain;

/**
 * Sana edustaa käyttöliittymässä luotua merkkijonoa jolle käyttäjä on antanut
 * merkityksen ja pisteytyksen.
 *
 * @author Ville Lehtinen
 */
public class Sana {

    /*
     * Merkkijono josta sana koostuu.
     */
    private String sisalto;
    /*
     * Sanan merkitys eli määritelmä.
     */
    private String merkitys;
    /*
     * Sanalle annettu arvosana.
     */
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
     * @return Sanan merkkijonoesitys
     */
    @Override
    public String toString() {
        return sisalto + "\nMerkitys: " + merkitys + "\nPisteet: " + pisteet;
    }

    public int getPisteet() {
        return pisteet;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }

        Sana verrattava = (Sana) o;

        if (!verrattava.toString().equals(this.toString())) {
            return false;
        }

        return true;
    }
}
