/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.GUI;

import static gameLogic.Constants.DIM_X_FRAME;
import static gameLogic.Constants.DIM_Y_FRAME;
import gameLogic.ObservableGame;
import gameLogic.states.AwaitDrawnCard;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author marcoduarte
 */
public class DrawCardPanel extends JPanel implements Observer{
    
    ObservableGame game;
    JButton drawCard;
    
    DrawCardPanel(ObservableGame g){
            
        this.game = g;
        this.game.addObserver(this);
        
        drawCard = new JButton ("Draw Card");
       
        setPreferredSize(new Dimension(DIM_X_FRAME - 310, 300));
        setupComponents();
        setupLayout();

        update(g,null);
        
    }
        
    private void setupComponents(){

        drawCard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.chooseAction();
            }
        });
        validate();
    }

    private void setupLayout(){
        
        drawCard.setFont(new Font("Dialog",Font.BOLD,20));
        
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        
        add(Box.createVerticalStrut(60));

        drawCard.setAlignmentX(Component.CENTER_ALIGNMENT);

        //add(Box.createVerticalStrut(10));

        add(drawCard);
        
        validate();
    }

    @Override
    public void update(Observable o, Object arg){
        setVisible(game.getState() instanceof AwaitDrawnCard);
        revalidate();
        repaint();
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        
        g.drawImage(CardSiegePanel.getWoodBackground(), 0 , 0, getWidth() - 1, getHeight(), this);
        //g.drawImage(enemyTrackCardImage, 0 , y, getWidth(), getHeight()/2, this);
    }
}
