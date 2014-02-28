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
        frame.setPreferredSize(new Dimension(640, 480));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        luoKomponentit(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Metodi luo Käyttöliittymän visuaaliset pääkomponentit ja kiinnittää
     * niihin TapahtumiennKuuntelijan sekä tarvittavat pelin domain-luokat. Sama
     * TapahtumienKuuntelija annetaan kaikkien komponenttien käyttöön.
     * TapahtumienKuuntelijan käyttöön määritellään tarvittavat
     * käyttöliittymäkomponentit.
     *
     * @param container
     */
    public void luoKomponentit(Container container) {
        container.setLayout(new BorderLayout());

        TekstiKentanKuuntelija tkk = new TekstiKentanKuuntelija(sessio, frame);
        TapahtumienKuuntelija tk = new TapahtumienKuuntelija(sessio, frame, tkk);

        ToimintoPanel toimintoPanel = new ToimintoPanel(new BorderLayout(), tk, tkk);
        TekstiPanel tekstiPanel = new TekstiPanel(new BorderLayout(), tkk);
        PelaajaPanel pelaajaPanel = new PelaajaPanel(new GridLayout(), sessio.getPelaajat(), tk, tkk);
        tk.setToimintoPanel(toimintoPanel);

        container.add(toimintoPanel, BorderLayout.EAST);
        container.add(tekstiPanel, BorderLayout.CENTER);
        container.add(pelaajaPanel, BorderLayout.SOUTH);

        luoAlkuDialogit(toimintoPanel, pelaajaPanel);
        sessio.arvoPelaajienAloitusKirjaimet();
        tkk.alustaSeuraavaVuoro();
    }

    /**
     * Metodi kutsuu metodeita, jotka kutsuvat metodeita, jotka luovat peliä
     * käynnistettäessä näytettävät ponnahdusikkunat, ja lopuksi alustaa
     * käyttöliittymän pelin aloitusta varten.
     *
     * @param toimintoPanel
     * @param pelaajaPanel
     */
    private void luoAlkuDialogit(ToimintoPanel toimintoPanel, PelaajaPanel pelaajaPanel) {
        kysyPelinPituutta();
        kysyPelinVaikeustasoa();
        kysyEnsimmaisenPelaajanNimea();
        kysyToisenPelaajanNimea();

        pelaajaPanel.luoKomponentit();
        sessio.luoKirjainVarasto();
        toimintoPanel.setKirjainSailio(sessio.getVapaatKirjaimet().getKirjainSailio());
        toimintoPanel.luoKomponentit();
    }

    /**
     * Mikäli käyttäjältä ei saada ponnahdusikkunassa oikeaa syötettä, kutsutaan
     * ponnahdusikkunaa uudelleen.
     */
    private void kysyPelinPituutta() {
        while (true) {
            if (sessio.asetaPelinPituus(pelinPituusDialog())) {
                break;
            }
        }
    }

    /**
     * Mikäli käyttäjältä ei saada ponnahdusikkunassa oikeaa syötettä, kutsutaan
     * ponnahdusikkunaa uudelleen.
     */
    private void kysyPelinVaikeustasoa() {
        while (true) {
            if (sessio.asetaPelaajallaKirjaimia(pelaajallaKirjaimiaDialog())) {
                break;
            }
        }
    }
    
    /**
     * Mikäli käyttäjältä ei saada ponnahdusikkunassa oikeaa syötettä, kutsutaan
     * ponnahdusikkunaa uudelleen.
     */
    private void kysyEnsimmaisenPelaajanNimea() {
        while (true) {
            if (sessio.lisaaPelaaja(pelaaja1Dialog(frame))) {
                break;
            }
        }
    }

    /**
     * Mikäli käyttäjältä ei saada ponnahdusikkunassa oikeaa syötettä, kutsutaan
     * ponnahdusikkunaa uudelleen.
     */
    private void kysyToisenPelaajanNimea() {
        while (true) {
            if (sessio.lisaaPelaaja(pelaaja2Dialog(frame))) {
                break;
            }
        }
    }

    /**
     * Metodi luo ponnahdusikkunan jossa kysytään pelin pituus.
     *
     * @return ponnahdusikkunan arvo merkkijonoesityksenä
     */
    private String pelinPituusDialog() {
        String[] vaihtoehdot = new String[2];
        vaihtoehdot[0] = "Normaali";
        vaihtoehdot[1] = "Marathon";
        return (String) JOptionPane.showInputDialog(
                frame, "Määritä pelin pituus.", "Pituus",
                JOptionPane.QUESTION_MESSAGE, null, vaihtoehdot, "");
    }

    /**
     * Metodi luo ponnahdusikkunan jossa kysytään pelin vaikeustaso.
     *
     * @return ponnahdusikkunan arvo merkkijonoesityksenä
     */
    private String pelaajallaKirjaimiaDialog() {
        String[] vaihtoehdot = new String[4];
        vaihtoehdot[0] = "Pala kakkua";
        vaihtoehdot[1] = "Rokataan";
        vaihtoehdot[2] = "Täältä pesee";
        vaihtoehdot[3] = "Däämn oon hyvä";
        return (String) JOptionPane.showInputDialog(
                frame, "Määritä pelin vaikeustaso."
                + "\nVaikeustaso määrää pelaajalla kerralla"
                + "\nolevien kirjainten lukumäärän (6, 7, 8 tai 9)", "Vaikeustaso",
                JOptionPane.QUESTION_MESSAGE, null, vaihtoehdot, "");
    }

    /**
     * Metodi luo ponnahdusikkunan jossa kysytään ensimmäisen pelaajan nimi.
     *
     * @param frame
     * @return ponnahdusikkunan arvo merkkijonoesityksenä
     */
    private String pelaaja1Dialog(JFrame frame) {
        return (String) JOptionPane.showInputDialog(
                frame, "Anna ensimmäisen pelaajan nimi"
                + "\n(3-16 merkkiä):", "Pelaaja 1",
                JOptionPane.QUESTION_MESSAGE, null, null, "");
    }

    /**
     * Metodi luo ponnahdusikkunan jossa kysytään toisen pelaajan nimi.
     *
     * @param frame
     * @return ponnahdusikkunan arvo merkkijonoesityksenä
     */
    private String pelaaja2Dialog(JFrame frame) {
        return (String) JOptionPane.showInputDialog(
                frame, "Anna toisen pelaajan nimi:"
                + "\n(3-16 merkkiä)", "Pelaaja 2",
                JOptionPane.QUESTION_MESSAGE, null, null, "");
    }
}
