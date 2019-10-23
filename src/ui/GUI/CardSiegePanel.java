/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.GUI;

import gameLogic.Constants;
import gameLogic.ObservableGame;
import gameLogic.states.AwaitDrawnCard;
import java.awt.BorderLayout;
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
public class CardSiegePanel extends JPanel implements Observer, Constants {
    
    static private BufferedImage backgroundImage = null; 
    static private BufferedImage startBackgroundImage = null;
    static private BufferedImage enemyTracksCardImage = null;
    static private BufferedImage statusTracksCardImage = null;
    static private BufferedImage pawnImage = null;
    static private BufferedImage cardsBackImage = null;
    static private BufferedImage cardIImage = null;
    static private BufferedImage cardIIImage = null;
    static private BufferedImage cardIIIImage = null;
    static private BufferedImage cardIVImage = null;
    static private BufferedImage cardVImage = null;
    static private BufferedImage cardVIImage = null;
    static private BufferedImage cardVIIImage = null;
    static private BufferedImage woodBackground = null;
    static private BufferedImage noCurrentCard = null;
    static private BufferedImage gameOverBackground = null;
    static private BufferedImage victoryBackground = null;
    StartOptionPanel startPanel;
    StatusTracksCardPanel statusTracksCardPanel;
    EnemyTracksCardPanel enemyTracksCardPanel;
    DeckAndCurrentCardPanel deckAndCurrentCardPanel;
    ActionMenuPanel actionMenuPanel;
    DrawCardPanel drawCardPanel;
    AwaitLaneForArchersPanel awaitLaneForArchersAttackPanel;
    AwaitExtraPointRallyTroopsPanel awaitExtraPointRallyTroopsPanel;
    AwaitLaneForBoilingWaterPanel awaitLaneForBoilingWaterPanel;
    AwaitMoreActionsPointsPanel awaitMoreActionsPointsPanel;
    AwaitTypeMovementPanel awaitTypeMovementPanel;
    EndPanel endPanel;
    
    static{
        try{
            backgroundImage = ImageIO.read(Resources.getResourceFile("images/Background.jpg"));
            startBackgroundImage = ImageIO.read(Resources.getResourceFile("images/StartBackground.jpg"));
            enemyTracksCardImage = ImageIO.read(Resources.getResourceFile("images/EnemyTracksCard.png"));
            statusTracksCardImage = ImageIO.read(Resources.getResourceFile("images/StatusTracksCard.png"));
            pawnImage = ImageIO.read(Resources.getResourceFile("images/Pawn.jpg"));
            cardsBackImage = ImageIO.read(Resources.getResourceFile("images/CardsBack.png"));
            cardIImage = ImageIO.read(Resources.getResourceFile("images/CardI.png"));
            cardIIImage = ImageIO.read(Resources.getResourceFile("images/CardII.png"));
            cardIIIImage = ImageIO.read(Resources.getResourceFile("images/CardIII.png"));
            cardIVImage = ImageIO.read(Resources.getResourceFile("images/CardIV.png"));
            cardVImage = ImageIO.read(Resources.getResourceFile("images/CardV.png"));
            cardVIImage = ImageIO.read(Resources.getResourceFile("images/CardVI.png"));
            cardVIIImage = ImageIO.read(Resources.getResourceFile("images/CardVII.png"));
            woodBackground = ImageIO.read(Resources.getResourceFile("images/WoodBackground.jpg"));
            noCurrentCard = ImageIO.read(Resources.getResourceFile("images/NoCurrentCard.jpg"));
            gameOverBackground = ImageIO.read(Resources.getResourceFile("images/GameOverBackground.jpg"));
            victoryBackground = ImageIO.read(Resources.getResourceFile("images/VictoryBackground.jpg"));
            
        }catch(IOException e){
            System.out.println("Error loading images!");
        }
    }

    public static BufferedImage getBackgroundImage(){
        return backgroundImage;
    }
    
    public static BufferedImage getStartBackgroundImage(){
        return startBackgroundImage;
    }
    
    public static BufferedImage getStatusTracksCardImage(){
        return statusTracksCardImage;
    }
    
    public static BufferedImage getEnemyTracksCardImage(){
        return enemyTracksCardImage;
    }
    
    public static BufferedImage getPawnImage(){
        return pawnImage;
    }
    
    public static BufferedImage getCardsBackImage(){
        return cardsBackImage;
    }
        
    public static BufferedImage getCardIImage(){
        return cardIImage;
    }
            
    public static BufferedImage getCardIIImage(){
        return cardIIImage;
    }
    
    public static BufferedImage getCardIIIImage(){
        return cardIIIImage;
    }
        
    public static BufferedImage getCardIVImage(){
        return cardIVImage;
    }
            
    public static BufferedImage getCardVImage(){
        return cardVImage;
    }
    
    public static BufferedImage getCardVIImage(){
        return cardVIImage;
    }
        
    public static BufferedImage getCardVIIImage(){
        return cardVIIImage;
    }
    
    public static BufferedImage getWoodBackground(){
        return woodBackground;
    }
    
    public static BufferedImage getNoCurrentCard(){
        return noCurrentCard;
    }
    
    public static BufferedImage getGameOverBackgroundImage(){
        return gameOverBackground;
    }
    
    public static BufferedImage getVictoryBackgroundImage(){
        return victoryBackground;
    }
    private ObservableGame observableGame;
    
    public CardSiegePanel(ObservableGame ob){
        
        this.observableGame = ob;
        this.observableGame.addObserver(this);
        
        setupComponents();
        setupLayout();
        
        update(ob, null);
        
    }
    
    private void setupComponents(){
        startPanel = new StartOptionPanel(observableGame);
        statusTracksCardPanel = new StatusTracksCardPanel(observableGame);
        enemyTracksCardPanel = new EnemyTracksCardPanel(observableGame);
        deckAndCurrentCardPanel = new DeckAndCurrentCardPanel(observableGame);
        actionMenuPanel = new ActionMenuPanel(observableGame);
        drawCardPanel = new DrawCardPanel(observableGame);
        awaitLaneForArchersAttackPanel = new AwaitLaneForArchersPanel(observableGame);
        awaitExtraPointRallyTroopsPanel = new AwaitExtraPointRallyTroopsPanel(observableGame);
        awaitLaneForBoilingWaterPanel = new AwaitLaneForBoilingWaterPanel(observableGame);
        awaitMoreActionsPointsPanel = new AwaitMoreActionsPointsPanel(observableGame);
        awaitTypeMovementPanel = new AwaitTypeMovementPanel(observableGame);
        endPanel = new EndPanel(observableGame);
    }
    
    private void setupLayout(){
        
        
        //Panel for the EnemyTrackCard and StatusTrackCard
        JPanel east = new JPanel();
        //east.setMinimumSize(new Dimension(DIM_X_TRACKS_CARD, DIM_Y_FRAME));
        //east.setPreferredSize(new Dimension(DIM_X_EAST_PANEL, DIM_Y_EAST_PANEL));
        //east.setBorder(new LineBorder(Color.DARK_GRAY));
        east.setLayout(new BorderLayout());
        east.add(statusTracksCardPanel, BorderLayout.NORTH);
        east.add(enemyTracksCardPanel, BorderLayout.SOUTH);
        
        JPanel mainS = new JPanel();
        mainS.add(actionMenuPanel);
        mainS.add(drawCardPanel);
        mainS.add(awaitLaneForArchersAttackPanel);
        mainS.add(awaitExtraPointRallyTroopsPanel);
        mainS.add(awaitLaneForBoilingWaterPanel);
        mainS.add(awaitMoreActionsPointsPanel);
        mainS.add(awaitTypeMovementPanel);
        mainS.add(endPanel);
        
        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());
        main.add(deckAndCurrentCardPanel, BorderLayout.NORTH);
        main.add(mainS, BorderLayout.SOUTH);
        
        JPanel center = new JPanel();
        center.add(main);
        center.add(startPanel);
        
        setLayout(new BorderLayout());
        
        add(east, BorderLayout.EAST);
        add(center, BorderLayout.CENTER);
        //add(startPanel, BorderLayout.CENTER);
        
        validate();
    }
    
    @Override
    public void update(Observable o, Object arg){
        revalidate();
        repaint();
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        int dimMin = Math.min(getWidth(), getHeight());
        int x = (getWidth() - dimMin) / 2;
        int y = (getHeight() - dimMin) / 2;
        
        g.drawImage(backgroundImage, 0 , 0, getWidth(), getHeight(), this);
    }
    
}
