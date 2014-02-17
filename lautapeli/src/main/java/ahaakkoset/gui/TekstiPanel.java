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

    private void luoKomponentit() {
        add(sananLuontiKentta(), BorderLayout.NORTH);
        add(sanojenListausAlue(), BorderLayout.CENTER);
    }
    
    private JTextField sananLuontiKentta() {
        JTextField field = new JTextField();
        field.setToolTipText("Paina ENTER lisätäksesi sanan.");
        field.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        field.addKeyListener(tkk);
        tkk.setSananLuontiKentta(field);
        return field;
    }
    
    private JScrollPane sanojenListausAlue() {
        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(area); 
        tkk.setSanojenListausAlue(area);
        return scrollPane;
    }
}
