/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author marcoduarte
 */

import gameLogic.CardSiege;
import java.io.IOException;
import ui.text.TextUI;

public class MainWithText {
    
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        
        TextUI textUI = new TextUI(new CardSiege());
        textUI.run();
        
    }
}
