package ahaakkoset.gui;

import ahaakkoset.sovelluslogiikka.Kirjainvarasto;
import java.awt.LayoutManager;
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

    private TapahtumienKuuntelija kuuntelija;
    private Kirjainvarasto varasto;
    private JButton lopetaPeliPainike;
    private JButton uusiSanaPainike;
    private JButton lopetaVuoroPainike;

    /**
     *
     * @param lm
     * @param varasto
     * @param kuuntelija
     */
    public ToimintoPanel(LayoutManager lm, Kirjainvarasto varasto, TapahtumienKuuntelija kuuntelija) { // ei testattu
        super(lm);
        this.varasto = varasto;
        this.kuuntelija = kuuntelija;
        luoKomponentit();
    }

    private void luoKomponentit() { // ei testattu
        this.lopetaPeliPainike = new JButton("Lopeta peli");
        lopetaPeliPainike.addActionListener(kuuntelija);
        add(lopetaPeliPainike);

        add(new JLabel(""));
        add(new JLabel(""));
        add(new JLabel(""));
        add(new JLabel(""));
        add(new JLabel(""));
        add(new JLabel(""));
        add(new JLabel(""));

        KirjaimetTextArea kirjaimet = new KirjaimetTextArea(varasto.getKirjainSailio());
        add(kirjaimet);
        kuuntelija.setKirjaimetTextArea(kirjaimet);

        this.uusiSanaPainike = new JButton("Luo uusi sana");
        uusiSanaPainike.addActionListener(kuuntelija);
        add(uusiSanaPainike);

        this.lopetaVuoroPainike = new JButton("Jätä vuoro väliin");
        lopetaVuoroPainike.addActionListener(kuuntelija);
        add(lopetaVuoroPainike);
    }

    /**
     *
     * @return
     */
    public JButton getLopetaPeliPainike() {
        return lopetaPeliPainike;
    }

    /**
     *
     * @return
     */
    public JButton getUusiSanaPainike() {
        return uusiSanaPainike;
    }

    /**
     *
     * @return
     */
    public JButton getLopetaVuoroPainike() {
        return lopetaVuoroPainike;
    }
}
