/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.GUI;

import gameLogic.ObservableGame;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import gameLogic.states.AwaitTypeMovement;

/**
 *
 * @author Marco
 */

public class AwaitTypeMovementPanel extends JPanel implements Observer{
    private ObservableGame game;
    
    private JRadioButton freeMovement;
    private JRadioButton fastMovement;
    
    private ButtonGroup radioGroup;
    
    private JLabel information;
    
    public AwaitTypeMovementPanel(ObservableGame g){
        this.game = g;
        this.game.addObserver(this);
        
        freeMovement = new JRadioButton("1 - I'm poor! If it's free I'll always take it! (Free Movement)",false);
        fastMovement = new JRadioButton("2 - I'm scared of tunnels! It's better if I run! (Fast Movement)",false);
        
        information = new JLabel("--- Choose Tunnel Type Movement ---\n");
        
        radioGroup = new ButtonGroup();
        radioGroup.clearSelection();
        radioGroup.add(freeMovement);
        radioGroup.add(fastMovement);
        
        add(freeMovement);
        add(fastMovement);
        
        setupComponents();
        setupLayout();
        
        update(g,null);
    }
    
    public void setupComponents(){
        
        freeMovement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.tunnelmovement();
            }
        }); 
        
        fastMovement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.chooseAction();
                
            }
        });
    }
    
    public void setupLayout(){
        Box mainBox = Box.createVerticalBox();
        
        mainBox.add(Box.createHorizontalGlue());
        mainBox.add(information);
        mainBox.add(Box.createVerticalStrut(20));
        mainBox.add(freeMovement);
        mainBox.add(Box.createVerticalStrut(10));
        mainBox.add(fastMovement);
        mainBox.add(Box.createVerticalGlue());
        
        setLayout(new BorderLayout());
        
        add(mainBox, BorderLayout.SOUTH);
        validate();
    }
    
    
    
    @Override
    public void update(Observable t, Object o) { 
        
        radioGroup.clearSelection();
        setVisible(game.getState() instanceof AwaitTypeMovement);
    }
}
