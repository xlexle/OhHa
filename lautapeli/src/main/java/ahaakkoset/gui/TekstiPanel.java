/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ahaakkoset.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.LayoutManager;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * TekstiPanel on yksi käyttöliittymän pääkomponenteista. Se sisältää kentän
 * johon käyttäjältä saadaan tekstisyöte, ja tapahtumalokina toimivan kentän.
 *
 * @author Ville
 */
public class TekstiPanel extends JPanel {

    private TekstiKentanKuuntelija tkk;

    /**
     * Konstruktori määrittää käytettävän LayoutManagerin ja asettaa
     * oliomuuttujan arvon.
     *
     * @param lm
     * @param tkk
     */
    public TekstiPanel(LayoutManager lm, TekstiKentanKuuntelija tkk) {
        super(lm);
        this.tkk = tkk;
        luoKomponentit();
    }

    /**
     * Metodi lisää TekstiPanel-oliolle komponentit.
     *
     */
    private void luoKomponentit() {
        add(sananLuontiKentta(), BorderLayout.NORTH);
        add(sanojenListausAlue(), BorderLayout.CENTER);
    }

    /**
     * Metodi luo komponentin, johon käyttäjä voi antaa tekstisyötteen.
     *
     * @return JTextField
     */
    private JTextField sananLuontiKentta() {
        JTextField field = new JTextField();
        field.setToolTipText("Paina ENTER lisätäksesi sanan.");
        field.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        field.addKeyListener(tkk);
        tkk.setSananLuontiKentta(field);
        return field;
    }

    /**
     * Metodi luo komponentin, joka toimii pelin tapahtumalokina.
     *
     * @return JTextArea
     */
    private JScrollPane sanojenListausAlue() {
        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(area);
        tkk.setSanojenListausAlue(area);
        return scrollPane;
    }
}
