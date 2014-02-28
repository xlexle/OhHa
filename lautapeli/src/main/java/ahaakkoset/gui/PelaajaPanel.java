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

    private TapahtumienKuuntelija tk;
    private TekstiKentanKuuntelija tkk;
    private List pelaajat;

    /**
     * Konstruktori asettaa PelaajaPanelin käyttöön kuuntelijat, pelaajalistan
     * ja määrittää käytettävän LayoutManagerin.
     *
     * @param lm
     * @param pelaajat
     * @param tk
     * @param tkk 
     */
    public PelaajaPanel(LayoutManager lm, List pelaajat, TapahtumienKuuntelija tk, TekstiKentanKuuntelija tkk) { // ei testattu
        super(lm);
        this.pelaajat = pelaajat;
        this.tk = tk;
        this.tkk = tkk;
    }

    /**
     * Metodi luo PelaajaTextArean kummallekin pelaajalle.
     */
    public void luoKomponentit() {
        PelaajaTextArea pelaaja1 = new PelaajaTextArea((Pelaaja) pelaajat.get(0));
        add(pelaaja1);
        tkk.setPelaaja1TextArea(pelaaja1);

        PelaajaTextArea pelaaja2 = new PelaajaTextArea((Pelaaja) pelaajat.get(1));
        add(pelaaja2);
        tkk.setPelaaja2TextArea(pelaaja2);
    }
}
