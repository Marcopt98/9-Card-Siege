/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.GUI;


import static gameLogic.Constants.*;
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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 *
 * @author marcoduarte
 */
public class EnemyTracksCardPanel extends JPanel implements Observer {
    
    ObservableGame game;
    

    
    EnemyTracksCardPanel (ObservableGame game){
        this.game = game;
        this.game.addObserver(this);
        
        setupComponents();
        setupLayout();
        
        update(game, null);
        
    }
    
    
    private void setupComponents(){
        
    }
    
    private void setupLayout(){
        setMinimumSize(new Dimension(DIM_X_TRACKS_CARD, DIM_Y_TRACKS_CARD));
        setPreferredSize(new Dimension(DIM_X_TRACKS_CARD, DIM_Y_TRACKS_CARD));
        
        setBorder(new LineBorder(Color.DARK_GRAY));
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
        
        int closeCombat = 0;
        
        g.drawImage(CardSiegePanel.getEnemyTracksCardImage(), 0 , 0, getWidth() - 1, getHeight() - 1 , this);
        
        switch (game.getGameData().getEnemyTracksCard().getEnemyLane(LADDERS)) {
            case 4:
                g.drawImage(CardSiegePanel.getPawnImage(), POS_X_LADDERS , POS4_LADDERS, PAWN_SIZE_X_RESOURCES, PAWN_SIZE_Y_RESOURCES, this);
                break;
            case 3:
                g.drawImage(CardSiegePanel.getPawnImage(), POS_X_LADDERS , POS3_LADDERS, PAWN_SIZE_X_RESOURCES, PAWN_SIZE_Y_RESOURCES, this);
                break;
            case 2:
                g.drawImage(CardSiegePanel.getPawnImage(), POS_X_LADDERS , POS2_LADDERS, PAWN_SIZE_X_RESOURCES, PAWN_SIZE_Y_RESOURCES, this);
                break;
            case 1:
                g.drawImage(CardSiegePanel.getPawnImage(), POS_X_LADDERS , POS1_LADDERS, PAWN_SIZE_X_RESOURCES, PAWN_SIZE_Y_RESOURCES, this);
                break;
            case 0:
                closeCombat++;
                break;
            default:
                break;
        }
        
        switch (game.getGameData().getEnemyTracksCard().getEnemyLane(BATTERING_RAM)) {
            case 4:
                g.drawImage(CardSiegePanel.getPawnImage(), POS_X_BATTERING_RAM , POS4_BATTERING_RAM, PAWN_SIZE_X_RESOURCES, PAWN_SIZE_Y_RESOURCES, this);
                break;
            case 3:
                g.drawImage(CardSiegePanel.getPawnImage(), POS_X_BATTERING_RAM , POS3_BATTERING_RAM, PAWN_SIZE_X_RESOURCES, PAWN_SIZE_Y_RESOURCES, this);
                break;
            case 2:
                g.drawImage(CardSiegePanel.getPawnImage(), POS_X_BATTERING_RAM , POS2_BATTERING_RAM, PAWN_SIZE_X_RESOURCES, PAWN_SIZE_Y_RESOURCES, this);
                break;
            case 1:
                g.drawImage(CardSiegePanel.getPawnImage(), POS_X_BATTERING_RAM , POS1_BATTERING_RAM, PAWN_SIZE_X_RESOURCES, PAWN_SIZE_Y_RESOURCES, this);
                break;
            case 0:
                closeCombat++;
                break;
            default:
                break;
        }
        
        switch (game.getGameData().getEnemyTracksCard().getEnemyLane(SIEGE_TOWER)) {
            case 4:
                g.drawImage(CardSiegePanel.getPawnImage(), POS_X_SIEGE_TOWER , POS4_SIEGE_TOWER, PAWN_SIZE_X_RESOURCES, PAWN_SIZE_Y_RESOURCES, this);
                break;
            case 3:
                g.drawImage(CardSiegePanel.getPawnImage(), POS_X_SIEGE_TOWER , POS3_SIEGE_TOWER, PAWN_SIZE_X_RESOURCES, PAWN_SIZE_Y_RESOURCES, this);
                break;
            case 2:
                g.drawImage(CardSiegePanel.getPawnImage(), POS_X_SIEGE_TOWER , POS2_SIEGE_TOWER, PAWN_SIZE_X_RESOURCES, PAWN_SIZE_Y_RESOURCES, this);
                break;
            case 1:
                g.drawImage(CardSiegePanel.getPawnImage(), POS_X_SIEGE_TOWER , POS1_SIEGE_TOWER, PAWN_SIZE_X_RESOURCES, PAWN_SIZE_Y_RESOURCES, this);
                break;
            case 0:
                closeCombat++;
                break;
            default:
                break;
        }
        
        switch (game.getGameData().getEnemyTracksCard().getTrebuchetCount()) {
            case 3:
                g.drawImage(CardSiegePanel.getPawnImage(), THREE_TREBUCHET , POS_Y_TREBUCHET, PAWN_SIZE_X_RESOURCES, PAWN_SIZE_Y_RESOURCES, this);
                break;
            case 2:
                g.drawImage(CardSiegePanel.getPawnImage(), TWO_TREBUCHET , POS_Y_TREBUCHET, PAWN_SIZE_X_RESOURCES, PAWN_SIZE_Y_RESOURCES, this);
                break;
            case 1:
                g.drawImage(CardSiegePanel.getPawnImage(), ONE_TREBUCHET , POS_Y_TREBUCHET, PAWN_SIZE_X_RESOURCES, PAWN_SIZE_Y_RESOURCES, this);
                break;
            case 0:
                break;
            default:
                break;
        }

        if(closeCombat == 1)
            g.drawImage(CardSiegePanel.getPawnImage(), POS1_CLOSE_COMBAT , POS_Y_CLOSE_COMBAT, PAWN_SIZE_X_RESOURCES, PAWN_SIZE_Y_RESOURCES, this);
        if(closeCombat == 2){
            g.drawImage(CardSiegePanel.getPawnImage(), POS1_CLOSE_COMBAT , POS_Y_CLOSE_COMBAT, PAWN_SIZE_X_RESOURCES, PAWN_SIZE_Y_RESOURCES, this);
            g.drawImage(CardSiegePanel.getPawnImage(), POS2_CLOSE_COMBAT , POS_Y_CLOSE_COMBAT, PAWN_SIZE_X_RESOURCES, PAWN_SIZE_Y_RESOURCES, this);
        }
        //g.drawImage(enemyTrackCardImage, 0 , y, getWidth(), getHeight()/2, this);
    }
}
