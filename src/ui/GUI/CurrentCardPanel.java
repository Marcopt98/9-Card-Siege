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
public class CurrentCardPanel extends JPanel implements Observer{
    ObservableGame game;
    
    CurrentCardPanel(ObservableGame game){
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
        
        if(game.getGameData().getPosition() == 7)
            g.drawImage(CardSiegePanel.getNoCurrentCard(), 0 , 0, getWidth(), getHeight() - 1, this);
        else if(game.getGameData().getCard(game.getGameData().getPosition()).toString().equals("Card #1"))
            g.drawImage(CardSiegePanel.getCardIImage(), 0 , 0, getWidth(), getHeight() - 1, this);
        else if(game.getGameData().getCard(game.getGameData().getPosition()).toString().equals("Card #2"))
            g.drawImage(CardSiegePanel.getCardIIImage(), 0 , 0, getWidth(), getHeight() - 1, this);
        else if(game.getGameData().getCard(game.getGameData().getPosition()).toString().equals("Card #3"))
            g.drawImage(CardSiegePanel.getCardIIIImage(), 0 , 0, getWidth(), getHeight() - 1, this);
        else if(game.getGameData().getCard(game.getGameData().getPosition()).toString().equals("Card #4"))
            g.drawImage(CardSiegePanel.getCardIVImage(), 0 , 0, getWidth(), getHeight() - 1, this);
        else if(game.getGameData().getCard(game.getGameData().getPosition()).toString().equals("Card #5"))
            g.drawImage(CardSiegePanel.getCardVImage(), 0 , 0, getWidth(), getHeight() - 1, this);
        else if(game.getGameData().getCard(game.getGameData().getPosition()).toString().equals("Card #6"))
            g.drawImage(CardSiegePanel.getCardVIImage(), 0 , 0, getWidth(), getHeight() - 1, this);
        else if(game.getGameData().getCard(game.getGameData().getPosition()).toString().equals("Card #7"))
            g.drawImage(CardSiegePanel.getCardVIIImage(), 0 , 0, getWidth(), getHeight() - 1, this);
        
    }
}
