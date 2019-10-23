/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.GUI;

/**
 *
 * @author Marco
 */
import static gameLogic.Constants.DIM_X_FRAME;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import gameLogic.states.AwaitEndGame;
import gameLogic.ObservableGame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.Box;

public class EndPanel extends JPanel implements Observer{

    private ObservableGame observableGame;
    private JLabel finalLabel;
    private JLabel finalLabel2;
    private JLabel loseReasonLabel;
    private JButton playAgainButton;
    private JButton quitButton;

    public EndPanel(ObservableGame g) {
        
        this.observableGame = g;
        this.observableGame.addObserver(this);

        setPreferredSize(new Dimension(DIM_X_FRAME - 310, 175));
        finalLabel = new JLabel("");
        finalLabel2 = new JLabel("");
        loseReasonLabel = new JLabel("");
        playAgainButton = new JButton("Play again");
        quitButton = new JButton("Quit");
        
        add(finalLabel);        
        add(finalLabel2);
        add(playAgainButton);
        add(quitButton);
        
        setupComponents();
        setupLayout();
        
        update(observableGame, null);
    
    }
    
    public void setupLayout(){
        
        finalLabel.setFont(new Font("Dialog",Font.BOLD,15));
        finalLabel2.setFont(new Font("Dialog",Font.BOLD,15));
        loseReasonLabel.setFont(new Font("Dialog",Font.BOLD,15));
        playAgainButton.setFont(new Font("Dialog",Font.BOLD,15));
        quitButton.setFont(new Font("Dialog",Font.BOLD,15));
        
        
        Box textBox = Box.createVerticalBox();

        textBox.add(Box.createHorizontalStrut(200));
        textBox.add(Box.createHorizontalGlue());
        textBox.add(finalLabel);
        textBox.add(Box.createVerticalStrut(20));
        textBox.add(loseReasonLabel);
        textBox.add(Box.createVerticalStrut(10));
        textBox.add(finalLabel2);
        textBox.add(Box.createVerticalStrut(5));
        
        
        Box buttonBox = Box.createHorizontalBox();

        buttonBox.add(Box.createVerticalGlue());
        buttonBox.add(Box.createHorizontalStrut(385));
        buttonBox.add(quitButton);
        buttonBox.add(Box.createHorizontalStrut(5));
        buttonBox.add(playAgainButton);
        buttonBox.add(Box.createVerticalGlue());
        
        
        setLayout(new BorderLayout());
        
        add(textBox, BorderLayout.NORTH);
        add(buttonBox, BorderLayout.CENTER);
        validate();
    }
    
    public void setupComponents(){
        playAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                observableGame.playAgain();
            }
        });
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
            
    }
    
    
    @Override
    public void update(Observable t, Object o) {
        if(observableGame.getCardSiege().getGameState().matches("GAMEOVER")){
            finalLabel.setForeground(Color.RED);
            finalLabel2.setForeground(Color.red);
            loseReasonLabel.setForeground(Color.red);
            finalLabel.setText("                     \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t### GAME OVER ###");   
            finalLabel2.setText("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tDon't be sad! You'll have more luck next time!");
            
            if(observableGame.getGameData().getLoseGameSituation().equals("ONESURRENDER"))
                loseReasonLabel.setText("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tYou lost because one of the resources was depleted!");
            else if(observableGame.getGameData().getLoseGameSituation().equals("TWOSURRENDER"))
                loseReasonLabel.setText("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tYou lost because two of the resources were depleted!");
            else if(observableGame.getGameData().getLoseGameSituation().equals("TWOCLOSECOMBAT"))
                loseReasonLabel.setText("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tYou lost because two enemy units breached into the castle!");
            else if(observableGame.getGameData().getLoseGameSituation().equals("THREECLOSECOMBAT"))
                loseReasonLabel.setText("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tYou lost because all enemy units breached into the castle!");
        }
        if(observableGame.getCardSiege().getGameState().matches("WIN")){
            finalLabel.setForeground(Color.GREEN);
            finalLabel2.setForeground(Color.GREEN);
            finalLabel.setText("                 \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t### YOU WON ###");   
            finalLabel2.setText("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tWow, you are an excelent tactician!");
        }
        setVisible(observableGame.getState() instanceof AwaitEndGame);
        repaint();
        revalidate();
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        if(observableGame.getCardSiege().getGameState().matches("GAMEOVER"))
            g.drawImage(CardSiegePanel.getGameOverBackgroundImage(), 0 , 0, getWidth(), 250, this);
        else if(observableGame.getCardSiege().getGameState().matches("WIN"))
            g.drawImage(CardSiegePanel.getVictoryBackgroundImage(), 0 , 0, getWidth(), 250, this);
        
        
        //g.drawImage(enemyTrackCardImage, 0 , y, getWidth(), getHeight()/2, this);
    }
    
    
    
}
