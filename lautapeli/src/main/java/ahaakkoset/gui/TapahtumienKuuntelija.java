package ahaakkoset.gui;

import ahaakkoset.sovelluslogiikka.Pelisessio;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * TapahtumienKuuntelija osaa käsitellä kaikki Käyttöliittymän kompontteihin
 * kohdistuvat tapahtumat. Kuuntelija tarkkailee muutosta jossakin
 * käyttöliittymäkomponentissa.
 *
 * @author Ville Lehtinen
 */
public class TapahtumienKuuntelija implements ActionListener {

    private JFrame frame;
    private ToimintoPanel toimintoPanel;
    private TekstiKentanKuuntelija tkk;
    private Pelisessio sessio;

    /**
     * Konstruktori asettaa kuuntelijalle Käyttöliittymän määrittämän
     * Pelisession ja Käyttöliittymän graafisen pohjaelementin JFrame. Loput
     * oliomuuttujat asetetaan sitä mukaa kun ne luodaan Käyttöliittymässä.
     *
     * @param sessio
     * @param frame
     */
    public TapahtumienKuuntelija(Pelisessio sessio, JFrame frame, TekstiKentanKuuntelija tkk) { // ei testattu
        this.sessio = sessio;
        this.frame = frame;
        this.tkk = tkk;
    }

    /**
     * Metodin tehtävä on havaita muutos, tunnistaa komponentti jossa muutos
     * tapahtui, ja suorittaa asianmukainen pyyntö Pelisessiolle tai toiselle
     * käyttöliittymäkomponentille.
     *
     * @param tapahtuma
     */
    @Override
    public void actionPerformed(ActionEvent tapahtuma) {
        if (tapahtuma.getSource() == (JButton) toimintoPanel.getLopetaVuoroPainike()) {
            sessio.vaihdaPelaajanKirjaimet();
            tkk.tyhjaVuoro();
            tkk.alustaSeuraavaVuoro();
        }

        if (tapahtuma.getSource() == (JButton) toimintoPanel.getLopetaPeliPainike()) {
            suoritaPelinLopetus();
        }
    }

    private void suoritaPelinLopetus() {
        if (sessio.kaikillaVahintaanYksiVuoro()) {
            JOptionPane.showMessageDialog(frame, sessio.toString(), "Pisteet lopussa", JOptionPane.PLAIN_MESSAGE); //placeholder
        }

        frame.dispose();
        System.exit(0);
    }

    public void setToimintoPanel(ToimintoPanel panel) {
        this.toimintoPanel = panel;
    }
}