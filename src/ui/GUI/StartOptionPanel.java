/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import gameLogic.ObservableGame;
import gameLogic.states.AwaitBeginning;
import gameLogic.states.AwaitDrawnCard;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Marco
 */
public class StartOptionPanel extends JPanel implements Observer{
    ObservableGame game;
    
    JButton start = new JButton ("Start");
    PlayerNameBox playerName;
    
    
        
    StartOptionPanel (ObservableGame game){
        this.game = game;
        this.game.addObserver(this);
        
        setPreferredSize(new Dimension(1280, 768));
        setBackground(Color.LIGHT_GRAY);
        setupComponents();
        setupLayout();
        
        update(game, null);
        
        //setVisible(game.getState() instanceof AwaitBeginning);
    }
    
    
    private void setupLayout(){
        
        
        
        start.setFont(new Font("Dialog",Font.BOLD,15));
        
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        
        
        playerName.setMinimumSize(new Dimension(120,20));
        
        playerName.setAlignmentX(Component.CENTER_ALIGNMENT);
        playerName.setOpaque(false);
        
        add(Box.createVerticalStrut(250));
        add(playerName);
        
        start.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        add(Box.createVerticalStrut(10));
        add(start);
        
        
        validate();
    }
    
    
    private void setupComponents(){
        playerName = new PlayerNameBox(game);
        
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               game.setPlayerName(playerName.getText());
               game.startGame();
            }
        });
        validate();
    }
            
            
            
            
    @Override
    public void update(Observable o, Object arg){
        //start.setEnabled(game.getState() instanceof AwaitBeginning);
        setVisible(game.getState() instanceof AwaitBeginning);
        revalidate();
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        int dimMin = Math.min(getWidth(), getHeight());
        int x = getWidth() / 2;
        int y = getHeight() / 2;
        
        g.drawImage(CardSiegePanel.getStartBackgroundImage(), 0 , 0, getWidth(), getHeight(), this);
        //g.drawImage(enemyTrackCardImage, 0 , y, getWidth(), getHeight()/2, this);
    }
}
