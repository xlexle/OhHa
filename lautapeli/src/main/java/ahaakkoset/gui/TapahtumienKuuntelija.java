package ahaakkoset.gui;

import ahaakkoset.sovelluslogiikka.Pelisessio;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
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
    private KirjaimetTextArea kirjaimetTextArea;
    private PelaajaTextArea pelaaja1TextArea;
    private PelaajaTextArea pelaaja2TextArea;
    private Pelisessio sessio;
    private JButton seuraavaPainike;

    /**
     * Konstruktori asettaa kuuntelijalle Käyttöliittymän määrittämän
     * Pelisession ja Käyttöliittymän graafisen pohjaelementin JFrame. Loput
     * oliomuuttujat asetetaan sitä mukaa kun ne luodaan Käyttöliittymässä.
     *
     * @param sessio
     * @param frame
     */
    public TapahtumienKuuntelija(Pelisessio sessio, JFrame frame) { // ei testattu
        this.sessio = sessio;
        this.frame = frame;
    }

    /**
     * Metodin tehtävä on havaita muutos, tunnistaa komponentti jossa muutos
     * tapahtui, ja suorittaa asianmukainen pyyntö Pelisessiolle tai toiselle
     * käyttöliittymäkomponentille.
     *
     * @param tapahtuma
     */
    @Override
    public void actionPerformed(ActionEvent tapahtuma) { // ei testattu
//        if (pelilautaPanel.getRuudukkoRuutuja().containsKey((JButton) tapahtuma.getSource())) {
//            JButton ruudunNappi = (JButton) tapahtuma.getSource();
//            suoritaKirjaimenLisays(ruudunNappi);
//            
//        }
//
//        if (tapahtuma.getSource() == (JButton) toimintoPanel.getLopetaVuoroPainike()) {
//            suoritaTyhjanSananLisays();
//        }
//
//        if (tapahtuma.getSource() == (JButton) toimintoPanel.getUusiSanaPainike()) {
//            suoritaUudenSananLisays();
//        }
//
//        if (tapahtuma.getSource() == (JButton) toimintoPanel.getLopetaPeliPainike()) {
//            suoritaPelinLopetus();
//        }
    }

    private void suoritaPelinLopetus() { // ei testattu
        if (sessio.kaikillaVahintaanYksiVuoro()) {
//          - tulostaa loppunäkymän, joku popup-JTextArea (ei tulosta mitään jos ei vähintään yhtä vuoroa per pelaaja)
            JOptionPane.showMessageDialog(frame, sessio.toString()); //placeholder
        }

        frame.dispose();
        System.exit(0);
    }

    private void suoritaUudenSananLisays() { // ei testattu
//          - kysyy dialogissa sanan merkityksen; vain kirjaimia
//              - dialogin otsake on pelaajan nimi
        String merkitys = ""; //placeholder
//          - kysyy dialogissa sanan pisteet; vain numeroita
//              - dialogin otsake on pelaajan nimi
        int pisteet = 0; //placeholder
//          - lisää aktiiviselle pelaajalle sanan ja asettaa sanan pisteet
        sessio.lisaaSanaPelaajalle(merkitys, pisteet);
//          - arpoo aktiiviselle pelaajalle uudet kirjaimet
        sessio.arvoPelaajalleKirjaimia();
        paivitaTiedot();
        alustaSeuraavaVuoro();
    }

    private void suoritaTyhjanSananLisays() {
        sessio.lisaaSanaPelaajalle("Jätit vuoron väliin", 0);
        alustaSeuraavaVuoro();
    }

    /**
     *
     */
    public void alustaSeuraavaVuoro() {
//        ruudunaktivoija.ennenEnsimmaistaKirjainta();
        toimintoPanel.getUusiSanaPainike().setEnabled(false);
        toimintoPanel.getLopetaVuoroPainike().setEnabled(true);
        sessio.seuraavaPelaaja();
        pelaaja1TextArea.maaritaReunus(sessio.getAktiivinenPelaaja());
        pelaaja2TextArea.maaritaReunus(sessio.getAktiivinenPelaaja());
    }

    /**
     *
     */
//    private void suoritaKirjaimenLisays(JButton ruudunNappi) { // ei testattu
//        Ruutu ruutu = pelilautaPanel.haeRuutu(ruudunNappi);
//        String kirjain = "";
//
//        if (ruutu.getKirjain() != null) {
//            kirjain += ruutu.getKirjain();
//        } else {
//            kirjain = kirjainDialog();
//            if (kirjain == null) {
//                return;
//            }
//        }
//
//        maaritaAktiiviset(ruudunNappi);
//
//        Character c = (Character) kirjain.charAt(0);
//        sessio.lisaaKirjainSanaan(c); //lisaa c:n ja poistaa sen pelaajalta
//        tarkistaPainikkeet();
//
//        ruudunNappi.setText(kirjain);
//        ruutu.setKirjain(c);
//        
//        if (seuraavaPainike == null) {
//            suoritaUudenSananLisays();
//        } else if (!seuraavaPainike.isEnabled()) {
//            suoritaUudenSananLisays();
//        }
//        
//        paivitaTiedot();
//    }

    /**
     *
     */
    private String kirjainDialog() { // ei testattu
        List<Character> kirjaimet = sessio.getAktiivinenPelaaja().getOmatKirjaimet();
        String[] vaihtoehdot = new String[kirjaimet.size()];
        for (int i = 0; i < vaihtoehdot.length; i++) {
            vaihtoehdot[i] = "" + kirjaimet.get(i);
        }

        String kirjain = (String) JOptionPane.showInputDialog(
                frame,
                "Valitse kirjain",
                sessio.getAktiivinenPelaaja().getNimi(),
                JOptionPane.PLAIN_MESSAGE,
                null,
                vaihtoehdot,
                "");

        return kirjain;
    }

    /**
     *
     */
    private void tarkistaPainikkeet() { // ei testattu
        int pituus = sessio.getKeskenOlevaSana().length();
        if (pituus == 2) {
            toimintoPanel.getUusiSanaPainike().setEnabled(true);
        }

        if (pituus == 1) {
            toimintoPanel.getLopetaVuoroPainike().setEnabled(false);
        }
    }

    /**
     *
     */
    public void paivitaTiedot() {
        kirjaimetTextArea.paivitaLukumaara();
        pelaaja1TextArea.paivitaKirjaimet();
        pelaaja2TextArea.paivitaKirjaimet();
    }

    public void setToimintoPanel(ToimintoPanel infoPanel) {
        this.toimintoPanel = infoPanel;
    }
    
    public void setKirjaimetTextArea(KirjaimetTextArea kirjaimet) {
        this.kirjaimetTextArea = kirjaimet;
    }

    public void setPelaaja1TextArea(PelaajaTextArea pelaaja1) {
        this.pelaaja1TextArea = pelaaja1;
    }

    public void setPelaaja2TextArea(PelaajaTextArea pelaaja2) {
        this.pelaaja2TextArea = pelaaja2;
    }

    
}
//        luodaan komponentit; mitä tarvitaan?
//        - Framen sisäinen pelilauta-Container: private JPanel luoPelilauta() (extends JPanel)
//          - pelilaudan sisäisiä neliömallisia JButtoneja 121 kpl; GridLayout(sivunPituus,sivunPituus)
//          - kukin JButton tuntee Ruudun, ja painettaessa ponnauttaa kirjain-radiobutton dialogin
//        - Framen sisäinen pelaaja1-Container: private JPanel luoPelaaja() (extends JPanel)
//        - Framen sisäinen pelaaja2-Container: private JPanel luoPelaaja() (extends JPanel)
//          - JText: nimi
//          - JText: käytössä olevat kirjaimet
//          - JText: pisteet
//          - väriä vaihtavat reunukset
//              - raisedEtched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
//              - pane.setBorder(raisedEtched);
//              - blackBorder = BorderFactory.createLineBorder(Color.black)
//              - blackBorder = BorderFactory.createLineBorder(Color.blue)
//              - pane.setBorder(blackBorder);
//        - Framen sisäinen infoboksi: private JPanel peliOheis() (extends JPanel)
//          - JText: vapaita kirjaimia jäljellä
//          - JButton: luo sana
//          - JButton: skippaa vuoro
//          - JButton: lopeta peli
//        - Dialogeja: 
//          - Pelaajalle nimi ->
//              - JOptionPane.showInputDialog
//          - Uusi kirjain! -> näyttää vain pelaajan kirjaimet
//              - ButtonGroup + 7 kpl JRadioButton (kirjain x 7)
//          - Sanalle merkitys -> 
//              - JOptionPane.showInputDialog(frame,"Mitä sana tarkoittaa? (5-50 merkkiä)","Sanan merkitys",JOptionPane.PLAIN_MESSAGE,question,null,"")
//              - showMessageDialog: "Annoit liikaa tai liian vähän merkkejä."
//          - Sanalle pisteet -> 
//              - minkä Dialogin sisälle??
//              - ButtonGroup + 7 kpl JRadioButton (4,5,6,7,8,9,10)
//          - Loppunäkymä: pisteet, luodut sanat, pisteitä per sana, ilmoittaa voittajan
//        Nappaimistonkuuntelija nk = new Nappaimistonkuuntelija();
