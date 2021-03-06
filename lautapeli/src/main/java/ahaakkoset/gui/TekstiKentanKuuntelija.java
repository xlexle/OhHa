/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ahaakkoset.gui;

import ahaakkoset.sovelluslogiikka.Pelisessio;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * TekstiKentanKuuntelijan tehtävä on havaita, milloin pelaaja painaa
 * Enter-nappulaa ja aiheuttaa tapahtumaketju joka tällä halutaan saada aikaan.
 *
 * @author Ville
 */
public class TekstiKentanKuuntelija implements KeyListener {

    private JFrame frame;
    private Pelisessio sessio;
    private JTextField sananLuontiKentta;
    private JTextArea sanojenListausAlue;
    private PelaajaTextArea pelaaja1TextArea;
    private PelaajaTextArea pelaaja2TextArea;
    private KirjaimetTextArea kirjaimetTextArea;

    /**
     * Konstruktorissa määritellään Pelisessio ja JFrame -oliomuuttujat.
     *
     * @param sessio
     * @param frame
     */
    public TekstiKentanKuuntelija(Pelisessio sessio, JFrame frame) {
        this.sessio = sessio;
        this.frame = frame;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Metodi ottaa ENTER-nappulaa painettaessa tekstikentästä tekstin, muokkaa
     * sitä, aiheuttaa sanan lisäämisen peliin sekä lopuksi tyhjentää kentän.
     *
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            String sana = sananLuontiKentta.getText();
            sana = sana.toUpperCase();
            sana = sana.trim();

            if (sana.length() > 1) {
                if (!sessio.tarkistaKirjaimet(sana)) {
                    JOptionPane.showMessageDialog(frame, "Käytä vain kirjaimia joita omistat!");
                } else {
                    suoritaUudenSananLisays(sana);
                    sanojenListausAlue.append(sessio.uusiSanaLuotu());
                    sessio.arvoPelaajalleKirjaimia();
                    alustaSeuraavaVuoro();
                }

                sananLuontiKentta.setText("");
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    /**
     * Metodi kutsuu while-loopeissa ponnahdusikkuna-metodeja ja kutsuu sessiota
     * lisäämään pelaajalle uuden Sana-olion, kun loopit on suoritettu.
     *
     * @param sana
     */
    private void suoritaUudenSananLisays(String sana) {
        String merkitys = "";
        while (merkitys.length() < 6 || merkitys.length() > 60) {
            merkitys = sananMerkitysDialog(frame);
        }

        int arvosana = 0;
        while (arvosana == 0) {
            try {
                arvosana = Integer.parseInt(sananPisteetDialog(frame));
                break;
            } catch (NumberFormatException e) {
            }
        }

        sessio.lisaaSanaPelaajalle(sana, merkitys, arvosana);
    }

    /**
     * Metodi luo ponnahdusikkunan, jossa kysytään sanan merkitys.
     *
     * @param frame
     * @return  ponnahdusikkunan arvo merkkijonoesityksenä
     */
    private String sananMerkitysDialog(JFrame frame) {
        return (String) JOptionPane.showInputDialog(
                frame, "Mitä sana tarkoittaa?"
                + "\n(6-60 merkkiä)", "Sanan merkitys",
                JOptionPane.QUESTION_MESSAGE, null, null, "");
    }

    /**
     * Metodi luo ponnahdusikkunan, jossa kysytään sanalle pisteytys.
     *
     * @param frame
     * @return  ponnahdusikkunan arvo merkkijonoesityksenä
     */
    private String sananPisteetDialog(JFrame frame) {
        String[] vaihtoehdot = new String[6];
        vaihtoehdot[0] = "0";
        vaihtoehdot[1] = "1";
        vaihtoehdot[2] = "2";
        vaihtoehdot[3] = "3";
        vaihtoehdot[4] = "4";
        vaihtoehdot[5] = "5";
        return (String) JOptionPane.showInputDialog(
                frame, "Vastapelaaja arvioi sanan:"
                + "\n* Kuinka suomenkieliseltä sana kuulostaa?"
                + "\n* Kuinka hauska sanan merkitys on?", "Sanan arvosana",
                JOptionPane.QUESTION_MESSAGE, null, vaihtoehdot, "");
    }

    /**
     * Metodi lisää tyhjän vuoron peliin.
     *
     */
    public void tyhjaVuoro() {
        sessio.lisaaTyhjaSanaPelaajalle();
        sanojenListausAlue.append(sessio.getAktiivinenPelaaja().getNimi()
                + " jätti vuoron väliin\n\n");
    }

    /**
     * Metodi alustaa seuraavan vuoron päivittämällä käyttöliittymäkomponentteja
     * ja kutsumalla sessiota vaihtamaan pelivuoroa.
     *
     */
    public void alustaSeuraavaVuoro() {
        kirjaimetTextArea.paivitaLukumaara();
        pelaaja1TextArea.paivitaKirjaimet();
        pelaaja2TextArea.paivitaKirjaimet();
        sessio.seuraavaPelaaja();
        pelaaja1TextArea.maaritaReunus(sessio.getAktiivinenPelaaja());
        pelaaja2TextArea.maaritaReunus(sessio.getAktiivinenPelaaja());
    }

    public void setSananLuontiKentta(JTextField field) {
        this.sananLuontiKentta = field;
    }

    public void setSanojenListausAlue(JTextArea area) {
        this.sanojenListausAlue = area;
    }

    public void setPelaaja1TextArea(PelaajaTextArea area) {
        this.pelaaja1TextArea = area;
    }

    public void setPelaaja2TextArea(PelaajaTextArea area) {
        this.pelaaja2TextArea = area;
    }

    public void setKirjaimetTextArea(KirjaimetTextArea area) {
        this.kirjaimetTextArea = area;
    }
}
