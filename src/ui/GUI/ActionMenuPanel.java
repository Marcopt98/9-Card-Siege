/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.GUI;

/**
 *
 * @author marcoduarte
 */    
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
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
import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import gameLogic.Constants;
import static gameLogic.Constants.ARCHERS_ATTACK;
import static gameLogic.Constants.BOILING_WATER_ATTACK;
import static gameLogic.Constants.CLOSE_COMBAT;
import static gameLogic.Constants.COUPURE;
import static gameLogic.Constants.EXTRA_ACTION_POINT;
import static gameLogic.Constants.RAISE_MORAL;
import static gameLogic.Constants.SABOTAGE;
import static gameLogic.Constants.SUPPLYRAID;
import static gameLogic.Constants.TUNNEL_MOVEMENT;

    
public class ActionMenuPanel extends JPanel implements Observer{
    
    ObservableGame game;
    JButton ArchersAttack;
    JButton BoilingWaterAttack;
    JButton CloseCombatAttack;
    JButton Coupure;
    JButton RallyTroops;
    JButton TunnelMovement;
    JButton SupplyRaid;
    JButton Sabotage;
    JButton ReceiveExtraActionPoint;
    JButton EndTurn;
    
    JLabel playerName;
    JLabel actionPoints;
    JLabel dayLabel;
    JLabel turnLabel;
    JLabel diceLabel;
    
    ActionMenuPanel(ObservableGame g){
        this.game = g;
        this.game.addObserver(this);
        
        ArchersAttack = new JButton ("     Archers Attack     ");
        BoilingWaterAttack = new JButton (" Boiling Water Attack");
        CloseCombatAttack = new JButton ("Close Combat Attack");
        Coupure = new JButton ("          Coupure          ");
        RallyTroops = new JButton ("        Rally Troops      ");
        TunnelMovement = new JButton ("      Tunnel Movement       ");
        SupplyRaid = new JButton ("            Supply Raid           ");
        Sabotage = new JButton ("              Sabotage             ");
        ReceiveExtraActionPoint = new JButton ("Receive Extra Action Point");
        EndTurn = new JButton ("    End Turn   ");
        
        
        playerName = new JLabel("Player Name: ");
        actionPoints = new JLabel("Action Points: ");
        dayLabel = new JLabel("Day: ");
        turnLabel = new JLabel("Cards in the deck: ");
        diceLabel = new JLabel("Dice Value: ");
       
        
        setupComponents();
        setupLayout();

        update(g,null);
        
    }
    
   
    private void setupLayout(){
        //ArchersAttack.setPreferredSize(new Dimension(60,30));
        
        Box LeftBox = Box.createVerticalBox();
        
        LeftBox.add(Box.createVerticalGlue());
        LeftBox.add(playerName);
        LeftBox.add(Box.createVerticalStrut(15));
        LeftBox.add(dayLabel);
        LeftBox.add(Box.createVerticalStrut(15));
        LeftBox.add(turnLabel);
        LeftBox.add(Box.createVerticalStrut(15));
        LeftBox.add(actionPoints);
        LeftBox.add(Box.createVerticalStrut(15));
        LeftBox.add(diceLabel);
        LeftBox.add(Box.createVerticalGlue());
        
        Box CenterBox = Box.createVerticalBox();
        
        CenterBox.add(Box.createVerticalGlue());
        CenterBox.add(ArchersAttack);
        CenterBox.add(BoilingWaterAttack);
        CenterBox.add(CloseCombatAttack);
        CenterBox.add(Coupure);
        CenterBox.add(RallyTroops);
        CenterBox.add(Box.createVerticalGlue());
        
        
        Box RightBox = Box.createVerticalBox();
        
        RightBox.add(Box.createVerticalGlue());
        RightBox.add(Box.createVerticalStrut(30));
        RightBox.add(TunnelMovement);
        RightBox.add(SupplyRaid);
        RightBox.add(Sabotage);
        RightBox.add(ReceiveExtraActionPoint);
        RightBox.add(Box.createVerticalGlue());
        
        
        Box EndBox = Box.createVerticalBox();
        
        EndBox.add(Box.createVerticalGlue());
        EndBox.add(Box.createVerticalStrut(120));
        EndBox.add(EndTurn);
        EndBox.add(Box.createVerticalGlue());
        
        //add(Box.createHorizontalStrut(100));
        
        Box MainBox = Box.createHorizontalBox();
        
        
        MainBox.add(Box.createHorizontalGlue());
        
        //MainBox.add(Box.createVerticalStrut(180));
        MainBox.add(Box.createHorizontalStrut(10));
        MainBox.add(LeftBox);
        MainBox.add(Box.createHorizontalStrut(30));
        MainBox.add(CenterBox);
        MainBox.add(Box.createHorizontalStrut(20));
        MainBox.add(RightBox);
        MainBox.add(Box.createHorizontalStrut(20));
        MainBox.add(EndBox);
        MainBox.add(Box.createHorizontalStrut(10));
        //MainBox.add(Box.createVerticalStrut(50));
        
        MainBox.setBorder(new LineBorder(Color.DARK_GRAY));
        
        setLayout(new BorderLayout());
        
        add(MainBox, BorderLayout.SOUTH);
        
        validate();
    }
    
    private void setupComponents(){
        
        ArchersAttack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.chooseAction(ARCHERS_ATTACK);
            }
        });
        
        BoilingWaterAttack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.chooseAction(BOILING_WATER_ATTACK);
            }
        });
        
        CloseCombatAttack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.chooseAction(CLOSE_COMBAT);
            }
        });
        
        Coupure.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.chooseAction(COUPURE);
                
            }
        });
        
        RallyTroops.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.chooseAction(RAISE_MORAL);
            }
        });
        
        TunnelMovement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.chooseAction(TUNNEL_MOVEMENT);
            }
        });
        
        SupplyRaid.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.chooseAction(SUPPLYRAID);
            }
        });
        
        Sabotage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.chooseAction(SABOTAGE);
            }
        });
        
        ReceiveExtraActionPoint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.chooseAction(EXTRA_ACTION_POINT);
            }
        });
        
        EndTurn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.endTurn();
            }
        });
        
        
    }
    
    @Override
    public void update(Observable t, Object o) {
    
        
        //String playerNameString = game.getPlayerName();

        setVisible(game.getState() instanceof AwaitAction);
        

        playerName.setText("Player Name: " + game.getGameData().getPlayerName());
        dayLabel.setText("Day: " + game.getGameData().getDay());
        turnLabel.setText("Cards in the deck: " + game.getGameData().getPosition());
        actionPoints.setText("Action Points: " + game.getPlayer().getActionPoints());
        diceLabel.setText("Dice Value: " + game.getGameData().getDiceValue());
        revalidate();
    } 
}

