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
import gameLogic.states.AwaitLaneForBoilingWater;

/**
 *
 * @author Marco
 */
public class AwaitLaneForBoilingWaterPanel extends JPanel implements Observer{
    private ObservableGame game;
    
    private JRadioButton laddersLane;
    private JRadioButton batteringRamLane;
    private JRadioButton siegeTowerLane;
    
    private ButtonGroup radioGroup;
    
    private JLabel information;
    
    public AwaitLaneForBoilingWaterPanel(ObservableGame g){
        this.game = g;
        this.game.addObserver(this);
        
        
        laddersLane = new JRadioButton("Ladders Lane",false);
        batteringRamLane = new JRadioButton("Battering Ram Lane",false);
        siegeTowerLane = new JRadioButton("Siege Tower Lane",false);
        
        information = new JLabel("--- Choose Lane For Boiling Water Attack ---\n");
        
        radioGroup = new ButtonGroup();
        radioGroup.clearSelection();
        radioGroup.add(laddersLane);
        radioGroup.add(batteringRamLane);
        radioGroup.add(siegeTowerLane);
        
        add(laddersLane);
        add(batteringRamLane);
        add(siegeTowerLane);
        
        setupComponents();
        setupLayout();
        
        update(g,null);
    }
    
    public void setupComponents(){
        
        laddersLane.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.getCardSiege().setLane(1);
                game.chooseAction();
            }
        }); 
        
        batteringRamLane.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.getCardSiege().setLane(2);
                game.chooseAction();
            }
        });
        
        siegeTowerLane.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.getCardSiege().setLane(3);
                game.chooseAction();
            }
        });
    }
    
    public void setupLayout(){
        Box mainBox = Box.createVerticalBox();
        
        mainBox.add(Box.createHorizontalGlue());
        mainBox.add(information);
        mainBox.add(Box.createVerticalStrut(20));
        mainBox.add(laddersLane);
        mainBox.add(Box.createVerticalStrut(10));
        mainBox.add(batteringRamLane);
        mainBox.add(Box.createVerticalStrut(10));
        mainBox.add(siegeTowerLane);
        mainBox.add(Box.createVerticalGlue());
        
        setLayout(new BorderLayout());
        
        add(mainBox, BorderLayout.SOUTH);
        validate();
    }
    
    
    
    @Override
    public void update(Observable t, Object o) { 
        
        radioGroup.clearSelection();
        setVisible(game.getState() instanceof AwaitLaneForBoilingWater);
    }
}
