/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.GUI;

import gameLogic.Constants;
import gameLogic.ObservableGame;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import files.FileUtility;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import gameLogic.CardSiege;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPopupMenu;

/**
 *
 * @author marcoduarte
 */
public class CardSiegeFrame extends JFrame implements Observer, Constants{
    
    private ObservableGame observableGame;
    private CardSiegePanel cardSiegePanel;
    JPopupMenu drmL;
    JButton drmClose;
    JLabel drmTitle;
    JLabel drmAllAttacks;
    JLabel drmAttacksBatteringRam;
    JLabel drmAttacksLadders;
    JLabel drmAttacksSiegeTower;
    JLabel drmCircleSpaces;
    JLabel drmCloseCombat;
    JLabel drmSabotage;
    JLabel drmSupplyRaid;
    JLabel drmRaiseMorale;
    JLabel drmCoupure;
    
    public CardSiegeFrame(ObservableGame ob){
        this(ob, 100, 100, DIM_X_FRAME, DIM_Y_FRAME);
    }
    
    public CardSiegeFrame(ObservableGame ob, int x, int y){
        this(ob, x, y, DIM_X_FRAME, DIM_Y_FRAME);
    }
    
    public CardSiegeFrame(ObservableGame ob, int x, int y, int width, int height){
        super("9 Card Siege");
        
        observableGame = ob;
        ob.addObserver(this);
        
        Container cont = getContentPane();
        
        cardSiegePanel = new CardSiegePanel(observableGame);
        cont.add(cardSiegePanel, BorderLayout.CENTER);
        drmL = new JPopupMenu("DRM List");
        drmClose = new JButton("Close");
        drmTitle = new JLabel("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t----- DRM List -----");
        drmAllAttacks = new JLabel("All Attacks: ");
        drmAttacksBatteringRam = new JLabel("Attacks on Battering Ram: ");
        drmAttacksLadders = new JLabel("Attacks on Ladders: ");
        drmAttacksSiegeTower = new JLabel("Attacks on Siege Tower: ");
        drmCircleSpaces = new JLabel("Circle Spaces(POS1): ");
        drmCloseCombat = new JLabel("Close Combat: ");
        drmSabotage = new JLabel("Sabotage: ");
        drmSupplyRaid = new JLabel("Supply Raid: ");
        drmRaiseMorale = new JLabel("Raise Morale: ");
        drmCoupure = new JLabel("Coupure: ");
        
        menus();
        
        setLocation(x, y);
        setSize(width, height);
        setMinimumSize(new Dimension(width, height));
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        validate();
        setResizable(false);
        
    }
    
    private void menus() {        
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        // game menu
        JMenu gameMenu = new JMenu("Game");
        gameMenu.setMnemonic(KeyEvent.VK_G);
        

        
        JMenuItem newObjJMI = new JMenuItem("Stop");
        newObjJMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
          
        JMenuItem readObjJMI = new JMenuItem("Load");
        readObjJMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
        
        JMenuItem saveObjJMI = new JMenuItem("Save");
        saveObjJMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        
        JMenuItem exitJMI = new JMenuItem("Exit");
        exitJMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));

        gameMenu.add(newObjJMI);
        gameMenu.add(readObjJMI);
        gameMenu.add(saveObjJMI);
        gameMenu.addSeparator();    
        gameMenu.add(exitJMI);
        menuBar.add(gameMenu);
        
        newObjJMI.addActionListener(new NewObjMenuBarListener());
        readObjJMI.addActionListener(new LoadObjMenuBarListener());
        saveObjJMI.addActionListener(new SaveObjMenuBarListener());
        exitJMI.addActionListener(new ExitListener());
        
        //Menu DRM
        JMenu drmMenu = new JMenu("DRM");
        drmMenu.setMnemonic(KeyEvent.VK_D);
        
        JMenuItem drmList = new JMenuItem("DRM List");
        drmList.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        
        drmMenu.add(drmList);
        menuBar.add(drmMenu);
        
        drmL.setPreferredSize(new Dimension(450, 550));
        drmL.setLocation(450, 150);
        drmL.setBackground(Color.LIGHT_GRAY);
        drmTitle.setForeground(Color.WHITE);
        drmTitle.setFont(new Font("Dialog", Font.BOLD, 15));
        drmL.add(drmTitle);
        drmL.add(Box.createVerticalGlue());
        drmL.add(drmAllAttacks);
        drmL.add(Box.createVerticalGlue());
        drmL.add(drmAttacksBatteringRam);
        drmL.add(Box.createVerticalGlue());
        drmL.add(drmAttacksLadders);
        drmL.add(Box.createVerticalGlue());
        drmL.add(drmAttacksSiegeTower);
        drmL.add(Box.createVerticalGlue());
        drmL.add(drmCircleSpaces);
        drmL.add(Box.createVerticalGlue());
        drmL.add(drmCloseCombat);
        drmL.add(Box.createVerticalGlue());
        drmL.add(drmSabotage);
        drmL.add(Box.createVerticalGlue());
        drmL.add(drmSupplyRaid);
        drmL.add(Box.createVerticalGlue());
        drmL.add(drmRaiseMorale);
        drmL.add(Box.createVerticalGlue());
        drmL.add(drmCoupure);
        drmL.add(Box.createVerticalGlue());
        drmL.add(Box.createHorizontalStrut(25));
        drmL.add(Box.createVerticalStrut(45));
        
//        Box closeButtonBox =  Box.createHorizontalBox();
//        closeButtonBox.add(Box.createVerticalGlue());
//        //closeButtonBox.add(Box.createHorizontalStrut(100));
//        closeButtonBox.add(drmClose, BorderLayout.CENTER);
//        closeButtonBox.add(Box.createVerticalGlue());
        
        drmClose.setFont(new Font("Dialog",Font.BOLD, 15));
        
        drmL.add(drmClose);
        drmL.add(Box.createVerticalGlue());
        drmClose.addActionListener(new DrmCloseListener());
        drmList.addActionListener(new DrmListListener());
        
        // help menu
        JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);
        
        JMenuItem helpContentJMI = new JMenuItem("Help Contents");
        helpContentJMI.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));

        JMenuItem aboutJMI = new JMenuItem("About");
        aboutJMI.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
       
        helpMenu.add(helpContentJMI);
        helpMenu.add(aboutJMI);
        menuBar.add(helpMenu);
      
        helpContentJMI.addActionListener(new HelpContentListener());
        aboutJMI.addActionListener(new AboutListener());
    } 
    
    
    class NewObjMenuBarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
             observableGame.quit();//setGameModel(new GameModel());
        }
    }
    
    class LoadObjMenuBarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fc = new JFileChooser("./data");
            int returnVal = fc.showOpenDialog(CardSiegeFrame.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();             
                try{
                    observableGame.setCardSiege((CardSiege)FileUtility.retrieveGameFromFile(file));
                }catch(IOException | ClassNotFoundException ex){
                    JOptionPane.showMessageDialog(CardSiegeFrame.this, "Operation failed: \r\n\r\n" + e);
                }
          
            } else {
                System.out.println("Operation canceled ");
            }
        }
    }

    class SaveObjMenuBarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fc = new JFileChooser("./data");
            int returnVal = fc.showSaveDialog(CardSiegeFrame.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                try{
                    FileUtility.saveGameToFile(file, observableGame.getCardSiege());
                }catch(IOException ex){
                    JOptionPane.showMessageDialog(CardSiegeFrame.this, "Operation failed: \r\n\r\n" + e);
                }
            } else {
                System.out.println("Operation canceled ");
            }
        }
    }

    class ExitListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    class DrmListListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

             drmL.setVisible(true);
        }
    }
    
    class DrmCloseListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

             drmL.setVisible(false);
        }
    }
        
    class HelpContentListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(CardSiegeFrame.this,
                    "Don't let the enemy reach your castle, keep your soldiers motivated and well fed. Survive for 3 days.",
                    "Help Contents", JOptionPane.PLAIN_MESSAGE);
        }
    }

    class AboutListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(CardSiegeFrame.this,
                    "   All rights reserved @2018\n\n             Programmers:\n  Marco Duarte & Marco Lopes",
                    "About", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    @Override
    public void update(Observable o, Object arg){
        drmAllAttacks.setText("All Attacks: " + observableGame.getGameData().getPlayer().getAllAttacksPoints());
        drmAttacksBatteringRam.setText("Attacks on Battering Ram: " + observableGame.getGameData().getPlayer().getAttackBatteringRamPoints());
        drmAttacksLadders.setText("Attacks on Ladders: " + observableGame.getGameData().getPlayer().getAttackLadderPoints());
        drmAttacksSiegeTower.setText("Attacks on Siege Tower: " + observableGame.getGameData().getPlayer().getAttackSiegeTowerPoints());
        drmCircleSpaces.setText("Circle Spaces(POS1): " + observableGame.getGameData().getPlayer().getAttackCirclePoints());
        drmCloseCombat.setText("Close Combat: " + observableGame.getGameData().getPlayer().getAttackCloseCombatPoints());
        drmSabotage.setText("Sabotage: " + observableGame.getGameData().getPlayer().getSabotagePoints());
        drmSupplyRaid.setText("Supply Raid: " + observableGame.getGameData().getPlayer().getRaidPoints());
        drmRaiseMorale.setText("Raise Morale: " + observableGame.getGameData().getPlayer().getMoralPoints());
        drmCoupure.setText("Coupure: " + observableGame.getGameData().getPlayer().getCouputePoints());
        repaint();
    }
    
    
}
