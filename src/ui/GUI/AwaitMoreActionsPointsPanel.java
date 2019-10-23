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
import gameLogic.states.AwaitMoreActionPoints;

/**
 *
 * @author Marco
 */
public class AwaitMoreActionsPointsPanel extends JPanel implements Observer{
    private ObservableGame game;
    
    private JRadioButton loseOneMoral;
    private JRadioButton loseOneSupply;
    
    private ButtonGroup radioGroup;
    
    private JLabel information;
    
    public AwaitMoreActionsPointsPanel(ObservableGame g){
        this.game = g;
        this.game.addObserver(this);
        
        loseOneMoral = new JRadioButton("1 - I prefer to be depressed than to be hungry! (Lose 1 morale)",false);
        loseOneSupply = new JRadioButton("2 - I just want to be happy no matter the cost! (Lose 1 supply)",false);
        
        information = new JLabel("--- Choose Extra Point ---\n WARNING: In return for the extra action point you lose 1 supply point or 1 morale point!\n");
        
        radioGroup = new ButtonGroup();
        radioGroup.clearSelection();
        radioGroup.add(loseOneMoral);
        radioGroup.add(loseOneSupply);
        
        add(loseOneMoral);
        add(loseOneSupply);
        
        setupComponents();
        setupLayout();
        
        update(g,null);
    }
    
    public void setupComponents(){
        
        loseOneMoral.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.extraActionPoint();
            }
        }); 
        
        loseOneSupply.addActionListener(new ActionListener() {
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
        mainBox.add(loseOneMoral);
        mainBox.add(Box.createVerticalStrut(10));
        mainBox.add(loseOneSupply);
        mainBox.add(Box.createVerticalGlue());
        
        setLayout(new BorderLayout());
        
        add(mainBox, BorderLayout.SOUTH);
        validate();
    }
    
    
    
    @Override
    public void update(Observable t, Object o) { 
        
        radioGroup.clearSelection();
        setVisible(game.getState() instanceof AwaitMoreActionPoints);
    }
}
