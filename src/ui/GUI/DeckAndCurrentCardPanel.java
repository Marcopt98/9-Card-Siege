/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.GUI;

import gameLogic.ObservableGame;
import gameLogic.states.AwaitAction;
import gameLogic.states.AwaitBeginning;
import gameLogic.states.AwaitDrawnCard;
import gameLogic.states.AwaitEndGame;
import gameLogic.states.AwaitExtraPointRallyTroops;
import gameLogic.states.AwaitLaneForArchers;
import gameLogic.states.AwaitLaneForBoilingWater;
import gameLogic.states.AwaitMoreActionPoints;
import gameLogic.states.AwaitTypeMovement;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 *
 * @author marcoduarte
 */
public class DeckAndCurrentCardPanel extends JPanel implements Observer{
    ObservableGame game;
    
    ActionMenuPanel actionMenuPanel;
    DeckPanel deckPanel;
    CurrentCardPanel currentCard;
    
    DeckAndCurrentCardPanel (ObservableGame game){
        this.game = game;
        this.game.addObserver(this);
        
        setMinimumSize(new Dimension(400, 100));
        setPreferredSize(new Dimension(980, 555));
        setBackground(Color.BLACK);
        setupComponents();
        setupLayout();
        
        update(game, null);

    }
    
    private void setupComponents(){
        deckPanel = new DeckPanel(game);
        currentCard = new CurrentCardPanel(game);
    }
        
    private void setupLayout(){
        
        JPanel deckP = new JPanel();
        deckP.setPreferredSize(new Dimension(275, 255));
        deckP.add(deckPanel, BorderLayout.CENTER);

        
        JPanel currentC = new JPanel();
        currentC.setPreferredSize(new Dimension(275, 255));
        currentC.add(currentCard, BorderLayout.CENTER);
        
        
        Box deck = Box.createVerticalBox();
        
        //deck.setPreferredSize(new Dimension(275, 100));
        //deck.setBorder(new LineBorder(Color.DARK_GRAY));
        deck.add(Box.createVerticalGlue());
        deck.add(deckP);
        deck.add(Box.createVerticalGlue());

        
        Box card = Box.createVerticalBox();
        //card.setPreferredSize(new Dimension(275, 100));
        //card.setBorder(new LineBorder(Color.DARK_GRAY));

        
        card.add(Box.createVerticalGlue());
        card.add(currentC);
        card.add(Box.createVerticalGlue());
        setLayout(new BorderLayout());
        
        add(deck, BorderLayout.WEST);
        add(card, BorderLayout.EAST);
        validate();
    }
          
            
    @Override
    public void update(Observable o, Object arg){

        setVisible(! (game.getState() instanceof AwaitBeginning));
        repaint();
        revalidate();
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        g.drawImage(CardSiegePanel.getBackgroundImage(), 0 , 0, getWidth() - 1 , getHeight(), this);
        
    }
}
