package ahaakkoset.gui;

import java.awt.Color;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JTextField;

/**
 * KirjaimetTextArea näyttää pelissä jäljellä olevien vapaiden kirjainten
 * lukumäärän ja päivittyy pelin edetessä.
 *
 * @author Ville Lehtinen
 */
public class KirjaimetTextArea extends JTextField {

    private List kirjaimet;

    /**
     * Konstruktori asettaa parametrina saadun listan oliomuuttujaan, ja
     * määrittää muita JTextField-ominaisuuksia.
     *
     * @param kirjaimet
     */
    public KirjaimetTextArea(List kirjaimet) { // ei testattu
        super();
        this.kirjaimet = kirjaimet;
        this.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        this.setHorizontalAlignment(JTextField.CENTER);
        this.setEditable(false);
    }

    /**
     * Metodi päivittää JTextFieldissä näytettävän tekstin.
     *
     */
    public void paivitaLukumaara() { // ei testattu
        this.setText("Kirjaimia: " + kirjaimet.size());
    }
}
