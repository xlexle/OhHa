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
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Ville
 */
public class TekstiPanel extends JPanel {
    
    private TekstiKentanKuuntelija tkk;

    public TekstiPanel(LayoutManager lm, TekstiKentanKuuntelija tkk) {
        super(lm);
        this.tkk = tkk;
        luoKomponentit();
    }

    private void luoKomponentit() { // ei testattu
        JTextField sananLuontiKentta = new JTextField();
        sananLuontiKentta.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        sananLuontiKentta.getDocument().addDocumentListener(tkk);
        
        JTextArea sanojenListausAlue = new JTextArea();
        sanojenListausAlue.setEditable(false);
        sanojenListausAlue.setBackground(Color.LIGHT_GRAY);
        add(sananLuontiKentta, BorderLayout.NORTH);
        add(sanojenListausAlue, BorderLayout.CENTER);
    }
}
