package ahaakkoset.gui;

import java.util.List;
import javax.swing.JTextArea;

/**
 * KirjaimetTextArea näyttää pelissä jäljellä olevien vapaiden kirjainten
 * lukumäärän ja päivittyy pelin edetessä.
 *
 * @author Ville Lehtinen
 */
public class KirjaimetTextArea extends JTextArea {

    private List kirjaimet;

    /**
     *
     * @param kirjaimet
     */
    public KirjaimetTextArea(List kirjaimet) { // ei testattu
        super();
        this.kirjaimet = kirjaimet;
        this.setEditable(false);
    }

    /**
     *
     */
    public void paivitaLukumaara() { // ei testattu
        this.setText(" Vapaita kirjaimia:" + "\n " + kirjaimet.size());
    }
}
