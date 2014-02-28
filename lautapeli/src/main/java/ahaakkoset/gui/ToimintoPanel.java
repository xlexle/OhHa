package ahaakkoset.gui;

import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * ToimintoPanel on yksi käyttöliittymän pääkomponenteista. Se sisältää
 * komponentit jotka mahdollistavat pelin lopettamisen kesken, vuoron jättämisen
 * väliin, uuden Sanan luomisen, sekä jäljellä olevien vapaiden kirjainten
 * lukumäärän tarkastelun.
 *
 * @author Ville Lehtinen
 */
public class ToimintoPanel extends JPanel {

    private TapahtumienKuuntelija tk;
    private TekstiKentanKuuntelija tkk;
    private List<Character> kirjainSailio;
    private JButton lopetaPeliPainike;
    private JButton uusiSanaPainike;
    private JButton lopetaVuoroPainike;

    /**
     * Konstruktori määrittää käytettävän LayoutManagerin ja asettaa
     * oliomuuttujien arvot.
     *
     * @param lm
     * @param tk
     * @param tkk
     */
    public ToimintoPanel(LayoutManager lm, TapahtumienKuuntelija tk, TekstiKentanKuuntelija tkk) {
        super(lm);
        this.tk = tk;
        this.tkk = tkk;
    }

    /*
     * Metodi asettaa ToimintoPanelille komponentit: päivittyvä 
     * tekstikenttä sekä painikkeet pelin lopettamiselle ja vuoron 
     * väliin jättämiselle.
     */
    public void luoKomponentit() { // ei testattu
        this.lopetaPeliPainike = new JButton("Lopeta peli");
        lopetaPeliPainike.setToolTipText("Oletkos ihan varma?");
        lopetaPeliPainike.addActionListener(tk);

        KirjaimetTextArea kirjaimet = new KirjaimetTextArea(kirjainSailio);
        kirjaimet.setToolTipText("Pelissä vapaana olevien kirjainten lukumäärä.");
        tkk.setKirjaimetTextArea(kirjaimet);

        this.lopetaVuoroPainike = new JButton("Jätä vuoro väliin");
        lopetaVuoroPainike.addActionListener(tk);
        lopetaVuoroPainike.setToolTipText("Vaihda kirjaimet uusiin.");

        add(lopetaPeliPainike, BorderLayout.NORTH);
        add(kirjaimet, BorderLayout.CENTER);
        add(lopetaVuoroPainike, BorderLayout.SOUTH);
    }

    public JButton getLopetaPeliPainike() {
        return lopetaPeliPainike;
    }

    public JButton getUusiSanaPainike() {
        return uusiSanaPainike;
    }

    public JButton getLopetaVuoroPainike() {
        return lopetaVuoroPainike;
    }

    public void setKirjainSailio(List<Character> kirjainSailio) {
        this.kirjainSailio = kirjainSailio;
    }
}
