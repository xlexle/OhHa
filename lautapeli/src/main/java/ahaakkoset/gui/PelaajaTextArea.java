package ahaakkoset.gui;

import ahaakkoset.domain.Pelaaja;
import javax.swing.JTextArea;

/**
 * PelaajaTextArea näyttää Pelaajan tietoja ja päivittyy pelin edetessä. Luokka
 * ei ole ohjelman käyttäjän kanssa suorassa interaktiossa.
 *
 *
 * @author Ville Lehtinen
 */
public class PelaajaTextArea extends JTextArea {

    private Pelaaja pelaaja;

    /**
     *
     * @param pelaaja
     */
    public PelaajaTextArea(Pelaaja pelaaja) { // ei testattu
        this.pelaaja = pelaaja;
        this.setEditable(false);
    }

    /**
     *
     */
    public void paivitaKirjaimet() {
        this.setText(pelaaja.toString());
    }

    /**
     *
     * @return
     */
    public Pelaaja getPelaaja() {
        return pelaaja;
    }
}
