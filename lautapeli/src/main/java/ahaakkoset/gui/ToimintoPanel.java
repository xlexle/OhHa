package ahaakkoset.gui;

import ahaakkoset.sovelluslogiikka.Kirjainvarasto;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.LayoutManager;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
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
     *
     * @param lm
     * @param varasto
     * @param tk
     */
    public ToimintoPanel(LayoutManager lm, Kirjainvarasto varasto, TapahtumienKuuntelija tk, TekstiKentanKuuntelija tkk) {
        super(lm);
        this.tk = tk;
        this.tkk = tkk;
    }

    public void luoKomponentit() { // ei testattu
        this.lopetaPeliPainike = new JButton("Lopeta peli");
        lopetaPeliPainike.setToolTipText("Oletkos ihan varma?");
        lopetaPeliPainike.addActionListener(tk);

        add(new JLabel(""));
        add(new JLabel(""));
        add(new JLabel(""));
        add(new JLabel(""));
        add(new JLabel(""));
        add(new JLabel(""));
        add(new JLabel(""));

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
