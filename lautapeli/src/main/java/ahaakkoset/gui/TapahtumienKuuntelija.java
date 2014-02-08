package ahaakkoset.gui;

import ahaakkoset.domain.Ruutu;
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
    private PelilautaPanel pelilautaPanel;
    private KirjaimetTextArea kirjaimetTextArea;
    private PelaajaTextArea pelaaja1TextArea;
    private PelaajaTextArea pelaaja2TextArea;
    private Pelisessio sessio;
    private Ruudunaktivoija ruudunaktivoija;

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
        if (tapahtuma.getSource() == (JButton) toimintoPanel.getLopetaPeliPainike()) {
            suoritaPelinLopetus();
        }

        if (tapahtuma.getSource() == (JButton) toimintoPanel.getUusiSanaPainike()) {
            suoritaUudenSananLisays();
            alustaSeuraavaVuoro();
        }

        if (tapahtuma.getSource() == (JButton) toimintoPanel.getLopetaVuoroPainike()) {
            suoritaTyhjanSananLisays();
            alustaSeuraavaVuoro();
        }

        if (pelilautaPanel.getRuudukkoRuutuja().containsKey((JButton) tapahtuma.getSource())) {
            JButton ruudunNappi = (JButton) tapahtuma.getSource();
            suoritaKirjaimenLisays(ruudunNappi);
        }
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
    }

    private void suoritaTyhjanSananLisays() {
        sessio.lisaaSanaPelaajalle("Jätit vuoron väliin", 0);
    }

    /**
     *
     */
    public void alustaSeuraavaVuoro() { // ei testattu
//          - asettaa kaikki ruudut aktiivisiksi
        ruudunaktivoija.asetaKaikkienRuutujenAktiivisuus(true);
//          - palauttaa painikkeiden tilan
        toimintoPanel.getUusiSanaPainike().setEnabled(true);
        toimintoPanel.getLopetaVuoroPainike().setEnabled(true);
//          - vaihtaa aktiivista pelaajaa, samalla lisää edelliselle yhden vuoron
        sessio.seuraavaPelaaja();
    }

    private void suoritaKirjaimenLisays(JButton ruudunNappi) { // ei testattu
        String kirjain;

        //1. kysy kirjain pelaajalta
        //  - dialogin otsake on pelaajan nimi

        try {
            kirjain = (String) JOptionPane.showInputDialog(frame, "X"); //placeholder
        } catch (NullPointerException e) {
            return;
        }

        toimintoPanel.getLopetaVuoroPainike().setEnabled(false);

        //  -> ota kirjain pelaajalta

        //2. aktivoi seuraavat ruudut
        //  -> deaktivoi ensin kaikki, aktivoi sitten oikea&ala tarpeen mukaan
        ruudunaktivoija.asetaKaikkienRuutujenAktiivisuus(false);

        if (sessio.sanassaEiVielaKirjaimia()) {
            ruudunaktivoija.maaritaEnsimmaisenRuudunJalkeisetAktiiviset(ruudunNappi);
        } else {
            ruudunaktivoija.maaritaSeuraavatAktiiviset(ruudunNappi);
        }
        //3. lisää kirjain JButtoniin ja Ruutuun (ruutuNappi.setText ja ruutu.setKirjain)
        //  -> JButton disabloidaan pysyvästi
        Character c = (Character) kirjain.charAt(0);
        ruudunNappi.setText(kirjain);
        ruudunNappi.setEnabled(false);
        Ruutu r = pelilautaPanel.haeRuutu(ruudunNappi);
        r.setKirjain(c);
        r.eiVoiSetEnabled();
        //4. lisää kirjain kesken olevaan sanaan
        sessio.lisaaKirjainSanaan(c);
        //jos keskenolevassa sanassa vähintään kaksi kirjainta, asettaa toimintoPanel.getUusiSanaPainike().setEnabled(true);
        if (sessio.getKeskenOlevaSana().length() == 2) {
            toimintoPanel.getUusiSanaPainike().setEnabled(true);
        }

        paivitaTiedot();
        //jos kaikki kirjaimet käytetty tai pelilaudan laita saavutettu, suoritaUudenSananLisays();
        if (sessio.pelaajallaEiKirjaimia()) {
            suoritaUudenSananLisays();
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

    /**
     *
     * @param lautaPanel
     */
    public void setPelilautaPanel(PelilautaPanel lautaPanel) { // ei testattu;
        this.pelilautaPanel = lautaPanel;
        this.ruudunaktivoija = new Ruudunaktivoija(pelilautaPanel.getRuudukkoRuutuja(), pelilautaPanel.getRuudukkoNappeja());
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
