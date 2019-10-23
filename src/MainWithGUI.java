
import gameLogic.ObservableGame;
import ui.GUI.CardSiegeFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author marcoduarte
 */
public class MainWithGUI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ObservableGame ob = new ObservableGame();
        new CardSiegeFrame(ob);
    }
    
}
