/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameLogic.cards;

import java.io.Serializable;
import gameLogic.GameData;

/**
 *
 * @author marcoduarte
 */
public abstract class Card implements Serializable{
    
    private GameData game;
    
    public Card(GameData game){
        this.game = game;
    }
    
    public GameData getGame(){
        return game;
    }
    
    abstract public void processRules();

}
