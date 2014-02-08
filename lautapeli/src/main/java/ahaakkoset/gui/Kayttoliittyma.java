package ahaakkoset.gui;

import ahaakkoset.sovelluslogiikka.Pelisessio;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

/**
 * Käyttöliittymä on ohjelman käyttäjän ja Pelisession graafinen välikappale.
 *
 * @author Ville Lehtinen
 */
public class Kayttoliittyma implements Runnable {

    Pelisessio sessio;
    private JFrame frame;

    /**
     * Konstruktori asettaa Käyttöliittymän käyttöön Main-luokan määräämän
     * Pelisession.
     *
     * @param sessio
     */
    public Kayttoliittyma(Pelisessio sessio) {
        this.sessio = sessio;
    }

    @Override
    public void run() { // ei testattu
        frame = new JFrame("Ahaakkoset");
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        luoKomponentit(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Metodi luo Käyttöliittymän visuaaliset pääkomponentit ja kiinnittää
     * niihin TapahtumiennKuuntelijan sekä tarvittavat pelin domain-luokat. Sama
     * TapahtumiennKuuntelija annetaan kaikkien komponenttien käyttöön.
     * TapahtumiennKuuntelijan käyttöön määritellään tarvittavat
     * käyttöliittymäkomponentit.
     *
     * @param container
     */
    public void luoKomponentit(Container container) { // ei testattu
        container.setLayout(new BorderLayout());

        TapahtumienKuuntelija kuuntelija = new TapahtumienKuuntelija(sessio, frame);

        int pituus = sessio.getPelilauta().getSivunPituus();
        PelilautaPanel pelilautaPanel = new PelilautaPanel(new GridLayout(pituus, pituus), sessio.getPelilauta(), kuuntelija);
        ToimintoPanel toimintoPanel = new ToimintoPanel(new GridLayout(11, 1), sessio.getVapaatKirjaimet(), kuuntelija);
        PelaajaPanel pelaajaPanel = new PelaajaPanel(new GridLayout(), kuuntelija);
        kuuntelija.setToimintoPanel(toimintoPanel);
        kuuntelija.setPelilautaPanel(pelilautaPanel);

        container.add(pelilautaPanel, BorderLayout.CENTER);
        container.add(toimintoPanel, BorderLayout.EAST);
        container.add(pelaajaPanel, BorderLayout.SOUTH);

        luoAlkuDialogit(frame, pelaajaPanel);
        alustaPeli(kuuntelija);
    }

    private void luoAlkuDialogit(JFrame frame, PelaajaPanel pelaajaPanel) { // ei testattu
        while (true) {
            if (sessio.lisaaPelaaja(pelaaja1Dialog(frame))) {
                break;
            }
        }

        while (true) {
            if (sessio.lisaaPelaaja(pelaaja2Dialog(frame))) {
                break;
            }
        }

        pelaajaPanel.luoKomponentit(sessio.getPelaajat());
    }

    private String pelaaja1Dialog(JFrame frame) { // ei testattu
        return (String) JOptionPane.showInputDialog(
                frame, "Anna ensimmäisen pelaajan nimi"
                + "\n(3-16 merkkiä):", "Pelaaja 1",
                JOptionPane.QUESTION_MESSAGE, null, null, "");
    }

    private String pelaaja2Dialog(JFrame frame) { // ei testattu
        return (String) JOptionPane.showInputDialog(
                frame, "Anna toisen pelaajan nimi:"
                + "\n(3-16 merkkiä)", "Pelaaja 2",
                JOptionPane.QUESTION_MESSAGE, null, null, "");
    }

    private void alustaPeli(TapahtumienKuuntelija kuuntelija) { // ei testattu
        sessio.arvoPelaajienAloitusKirjaimet();
        kuuntelija.paivitaTiedot();
        kuuntelija.alustaSeuraavaVuoro();
    }
}
