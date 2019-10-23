/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.GUI;

import gameLogic.ObservableGame;
import gameLogic.states.AwaitExtraPointRallyTroops;
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

/**
 *
 * @author Marco
 */
public class AwaitExtraPointRallyTroopsPanel extends JPanel implements Observer{
    private ObservableGame game;
    
    private JRadioButton loseOneSupply;
    private JRadioButton sameSupplies;
    
    private ButtonGroup radioGroup;
    
    private JLabel information;
    
    public AwaitExtraPointRallyTroopsPanel(ObservableGame g){
        this.game = g;
        this.game.addObserver(this);
        
        loseOneSupply = new JRadioButton("1 - I want to sacrifice 1 supply point! (Lose 1 supply)",false);
        sameSupplies = new JRadioButton("2 - Hell no! I'm to hungry to make the trade! (Same supplies)",false);
        
        information = new JLabel("--- Choose Extra Point ---\nWARNING: In return for the extra point you lose 1 supply point!\n");
        
        radioGroup = new ButtonGroup();
        radioGroup.clearSelection();
        radioGroup.add(loseOneSupply);
        radioGroup.add(sameSupplies);
        
        add(loseOneSupply);
        add(sameSupplies);
        
        setupComponents();
        setupLayout();
        
        update(g,null);
    }
    
    public void setupComponents(){
        
        loseOneSupply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.rallyTroops();
            }
        }); 
        
        sameSupplies.addActionListener(new ActionListener() {
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
        mainBox.add(loseOneSupply);
        mainBox.add(Box.createVerticalStrut(10));
        mainBox.add(sameSupplies);
        mainBox.add(Box.createVerticalGlue());
        
        setLayout(new BorderLayout());
        
        add(mainBox, BorderLayout.SOUTH);
        validate();
    }
    
    
    
    @Override
    public void update(Observable t, Object o) { 
        
        radioGroup.clearSelection();
        setVisible(game.getState() instanceof AwaitExtraPointRallyTroops);
    }
}
