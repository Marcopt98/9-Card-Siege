/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.GUI;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import gameLogic.ObservableGame;
import java.awt.BorderLayout;

/**
 *
 * @author Marco
 */
public class PlayerNameBox extends JPanel{
    
    ObservableGame game;
    JTextField playerName;
    
    PlayerNameBox(ObservableGame g){
        String name;
        this.game = g;
        
        name = g.getPlayer().getName();
        
        playerName = new JTextField(15);
        playerName.setText(name);
        
        playerName.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                game.setPlayerName(playerName.getName());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                game.setPlayerName(playerName.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                game.setPlayerName(playerName.getText());
            }
        });
        
        playerName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.setPlayerName(playerName.getText());
                game.startGame();
            }
        });
        
        setMaximumSize(new Dimension(200,40)); 
        setupLayout();
    }
    
    
    
    private void setupLayout(){
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        add(new JLabel("          Enter your name"));
        add(playerName);
    }    
    
    
    public String getText(){
        return playerName.getText();
    }
}
