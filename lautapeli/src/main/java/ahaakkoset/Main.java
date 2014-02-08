package ahaakkoset;

import ahaakkoset.gui.Kayttoliittyma;
import ahaakkoset.sovelluslogiikka.Pelisessio;
import javax.swing.SwingUtilities;

/**
 * Main-luokka luo ohjelman sovelluslogiikan ja käyttöliittymän.
 *
 * @author Ville Lehtinen
 */
public class Main {

    /**
     * Luo uuden pelisession ja siirtää ohjelman suorituksen vastuun
     * käyttöliittymälle, joka saa pelisession parametrina käyttöönsä.
     *
     * @param args
     */
    public static void main(String[] args) {
        Pelisessio sessio = new Pelisessio();
        Kayttoliittyma UI = new Kayttoliittyma(sessio);
        SwingUtilities.invokeLater(UI);
    }
}
