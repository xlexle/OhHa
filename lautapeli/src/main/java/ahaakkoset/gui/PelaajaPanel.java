package ahaakkoset.gui;

import ahaakkoset.domain.Pelaaja;
import java.awt.LayoutManager;
import java.util.List;
import javax.swing.JPanel;

/**
 * PelaajaPanel on yksi käyttöliittymän pääkomponenteista. Se sisältää Pelaajiin
 * kytketyt PelaajaTextAreat.
 *
 * @author Ville Lehtinen
 */
public class PelaajaPanel extends JPanel {

    private TapahtumienKuuntelija kuuntelija;

    /**
     *
     * @param lm
     * @param kuuntelija
     */
    public PelaajaPanel(LayoutManager lm, TapahtumienKuuntelija kuuntelija) { // ei testattu
        super(lm);
        this.kuuntelija = kuuntelija;
    }

    /**
     *
     * @param pelaajat
     */
    public void luoKomponentit(List pelaajat) { // ei testattu
        PelaajaTextArea pelaaja1 = new PelaajaTextArea((Pelaaja) pelaajat.get(0));
        add(pelaaja1);
        kuuntelija.setPelaaja1TextArea(pelaaja1);

        PelaajaTextArea pelaaja2 = new PelaajaTextArea((Pelaaja) pelaajat.get(1));
        add(pelaaja2);
        kuuntelija.setPelaaja2TextArea(pelaaja2);
    }
    

}
