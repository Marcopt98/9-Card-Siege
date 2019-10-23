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
public class StatusTracksCardPanel extends JPanel implements Observer {
    ObservableGame game;
    
    StatusTracksCardPanel (ObservableGame game){
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
        
        int dimMin = Math.min(getWidth(), getHeight());
        int x = getWidth() / 2;
        int y = getHeight() / 2;
        
        g.drawImage(CardSiegePanel.getStatusTracksCardImage(), 0 , 0, getWidth() - 1, getHeight() - 1, this);
        
        switch (game.getGameData().getStatusTracksCard().getWallStrength()) {
            case 4:
                g.drawImage(CardSiegePanel.getPawnImage(), POS_X_WALL_STRENGTH , POS4_WALL_STRENGTH, PAWN_SIZE_X_RESOURCES, PAWN_SIZE_Y_RESOURCES, this);
                break;
            case 3:
                g.drawImage(CardSiegePanel.getPawnImage(), POS_X_WALL_STRENGTH , POS3_WALL_STRENGTH, PAWN_SIZE_X_RESOURCES, PAWN_SIZE_Y_RESOURCES, this);
                break;
            case 2:
                g.drawImage(CardSiegePanel.getPawnImage(), POS_X_WALL_STRENGTH , POS2_WALL_STRENGTH, PAWN_SIZE_X_RESOURCES, PAWN_SIZE_Y_RESOURCES, this);
                break;
            case 1:
                g.drawImage(CardSiegePanel.getPawnImage(), POS_X_WALL_STRENGTH , POS1_WALL_STRENGTH, PAWN_SIZE_X_RESOURCES, PAWN_SIZE_Y_RESOURCES, this);
                break;
            case 0:
                g.drawImage(CardSiegePanel.getPawnImage(), POS_X_SURRENDER , POS_Y_SURRENDER, PAWN_SIZE_X_RESOURCES, PAWN_SIZE_Y_RESOURCES, this);
                break;
            default:
                break;
        }
        
        switch (game.getGameData().getStatusTracksCard().getMoral()) {
            case 4:
                g.drawImage(CardSiegePanel.getPawnImage(), POS_X_MORALE , POS4_MORALE, PAWN_SIZE_X_RESOURCES, PAWN_SIZE_Y_RESOURCES, this);
                break;
            case 3:
                g.drawImage(CardSiegePanel.getPawnImage(), POS_X_MORALE , POS3_MORALE, PAWN_SIZE_X_RESOURCES, PAWN_SIZE_Y_RESOURCES, this);
                break;
            case 2:
                g.drawImage(CardSiegePanel.getPawnImage(), POS_X_MORALE , POS2_MORALE, PAWN_SIZE_X_RESOURCES, PAWN_SIZE_Y_RESOURCES, this);
                break;
            case 1:
                g.drawImage(CardSiegePanel.getPawnImage(), POS_X_MORALE , POS1_MORALE, PAWN_SIZE_X_RESOURCES, PAWN_SIZE_Y_RESOURCES, this);
                break;
            case 0:
                g.drawImage(CardSiegePanel.getPawnImage(), POS_X_SURRENDER , POS_Y_SURRENDER, PAWN_SIZE_X_RESOURCES, PAWN_SIZE_Y_RESOURCES, this);
                break;
            default:
                break;
        }
                
        switch (game.getGameData().getStatusTracksCard().getSupplies()) {
            case 4:
                g.drawImage(CardSiegePanel.getPawnImage(), POS_X_SUPPLIES , POS4_SUPPLIES, PAWN_SIZE_X_RESOURCES, PAWN_SIZE_Y_RESOURCES, this);
                break;
            case 3:
                g.drawImage(CardSiegePanel.getPawnImage(), POS_X_SUPPLIES , POS3_SUPPLIES, PAWN_SIZE_X_RESOURCES, PAWN_SIZE_Y_RESOURCES, this);
                break;
            case 2:
                g.drawImage(CardSiegePanel.getPawnImage(), POS_X_SUPPLIES , POS2_SUPPLIES, PAWN_SIZE_X_RESOURCES, PAWN_SIZE_Y_RESOURCES, this);
                break;
            case 1:
                g.drawImage(CardSiegePanel.getPawnImage(), POS_X_SUPPLIES , POS1_SUPPLIES, PAWN_SIZE_X_RESOURCES, PAWN_SIZE_Y_RESOURCES, this);
                break;
            case 0:
                g.drawImage(CardSiegePanel.getPawnImage(), POS_X_SURRENDER , POS_Y_SURRENDER, PAWN_SIZE_X_RESOURCES, PAWN_SIZE_Y_RESOURCES, this);
                break;
            default:
                break;
        }

        switch (game.getGameData().getStatusTracksCard().getTunnelPosition()) {
            case 0:
                g.drawImage(CardSiegePanel.getPawnImage(), CASTLE_TUNNEL, POS_Y_TUNNEL, PAWN_SIZE_X_TUNNEL, PAWN_SIZE_Y_TUNNEL, this);
                break;
            case 1:
                g.drawImage(CardSiegePanel.getPawnImage(), POS1_TUNNEL, POS_Y_TUNNEL, PAWN_SIZE_X_TUNNEL, PAWN_SIZE_Y_TUNNEL, this);
                break;
            case 2:
                g.drawImage(CardSiegePanel.getPawnImage(), POS2_TUNNEL, POS_Y_TUNNEL, PAWN_SIZE_X_TUNNEL, PAWN_SIZE_Y_TUNNEL, this);
                break;
            case 3:
                g.drawImage(CardSiegePanel.getPawnImage(), ENEMY_LINE_TUNNEL, POS_Y_TUNNEL, PAWN_SIZE_X_TUNNEL, PAWN_SIZE_Y_TUNNEL, this);
                break;
            default:
                break;
        }
        
        switch (game.getGameData().getStatusTracksCard().getStealedSupplies()) {
            case 0:
                break;
            case 1:
                g.drawImage(CardSiegePanel.getPawnImage(), POS_X_RAID_SUPPLIES, POS1_RAID_SUPPLIES, PAWN_SIZE_X_RESOURCES, PAWN_SIZE_Y_RESOURCES, this);
                break;
            case 2:
                g.drawImage(CardSiegePanel.getPawnImage(), POS_X_RAID_SUPPLIES, POS2_RAID_SUPPLIES, PAWN_SIZE_X_RESOURCES, PAWN_SIZE_Y_RESOURCES, this);
                break;
            default:
                break;
        }
        
    }
    
}
