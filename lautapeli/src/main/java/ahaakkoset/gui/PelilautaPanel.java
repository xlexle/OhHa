package ahaakkoset.gui;

import ahaakkoset.domain.Pelilauta;
import ahaakkoset.domain.Ruutu;
import java.awt.LayoutManager;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * PelilautaPanel on yksi käyttöliittymän pääkomponenteista. Se luo Pelilautaa
 * vastaavan JButton-kokoelman ja mahdollistaa JButtonin ja sitä vastaavan
 * Ruutu-olion tunnistamisen kumpaankin suuntaan.
 *
 * @author Ville Lehtinen
 */
public class PelilautaPanel extends JPanel {

    private TapahtumienKuuntelija kuuntelija;
    private Pelilauta pelilauta;
    private Map<Ruutu, JButton> ruudukkoNappeja = new HashMap<>();
    private Map<JButton, Ruutu> ruudukkoRuutuja = new HashMap<>();
    private Ruudunaktivoija ruudunaktivoija;

    /**
     *
     * @param lm
     * @param pelilauta
     * @param kuuntelija
     */
    public PelilautaPanel(LayoutManager lm, Pelilauta pelilauta, TapahtumienKuuntelija kuuntelija) { // ei testattu
        super(lm);
        this.kuuntelija = kuuntelija;
        this.pelilauta = pelilauta;
        luoKomponentit();

    }

    private void luoKomponentit() { // ei testattu
        int ruutuja = pelilauta.getSivunPituus() * pelilauta.getSivunPituus();
        for (int i = 1; i <= ruutuja; i++) {
            Ruutu ruutu = pelilauta.haeRuutu(i);
            JButton ruudunNappi = new JButton();
            ruudunNappi.addActionListener(kuuntelija);
            ruudukkoNappeja.put(ruutu, ruudunNappi);
            ruudukkoRuutuja.put(ruudunNappi, ruutu);
            add(ruudunNappi);
        }
    }

    /**
     *
     * @param ruutu
     * @return
     */
    public JButton haeRuudunNappi(Ruutu ruutu) {
        return ruudukkoNappeja.get(ruutu);
    }

    /**
     *
     * @param nappi
     * @return
     */
    public Ruutu haeRuutu(JButton nappi) {
        return ruudukkoRuutuja.get(nappi);
    }

    public Map<JButton, Ruutu> getRuudukkoRuutuja() {
        return ruudukkoRuutuja;
    }

    public Map<Ruutu, JButton> getRuudukkoNappeja() {
        return ruudukkoNappeja;
    }
}
