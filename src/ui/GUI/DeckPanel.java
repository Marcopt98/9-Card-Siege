/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.GUI;

import gameLogic.ObservableGame;
import gameLogic.states.AwaitBeginning;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

/**
 *
 * @author marcoduarte
 */
public class DeckPanel extends JPanel implements Observer{
    ObservableGame game;
    
    DeckPanel(ObservableGame game){
        this.game = game;
        this.game.addObserver(this);
        setPreferredSize(new Dimension(275, 350));
        update(game, null);
    }
    
    
    @Override
    public void update(Observable o, Object arg){
        //start.setEnabled(game.getState() instanceof AwaitBeginning);
        //setVisible(game.getState() instanceof AwaitBeginning);
        revalidate();
        repaint();
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        g.drawImage(CardSiegePanel.getCardsBackImage(), 0 , 0, getWidth() , getHeight() - 1, this);
        
    }
}
