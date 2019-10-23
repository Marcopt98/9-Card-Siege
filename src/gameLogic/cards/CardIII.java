/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameLogic.cards;

import gameLogic.*;

/**
 *
 * @author Marco
 */
public class CardIII extends Card{
    
    public CardIII(GameData game){
            super(game);
    }
        
    @Override
    public String toString(){
        return "Card #3";
    }
    
    @Override
    public void processRules(){
        
        switch(getGame().getDay()){
            case 1:
                getGame().setPlayerPoints(getGame().getPlayerPoints() + 2);
                getGame().reduceSupplies(1);
                getGame().moveEnemy(-1, "ladders");
                return;
            case 2:
                getGame().setPlayerPoints(getGame().getPlayerPoints() + 2);
                getGame().setBadWeather(true);
                return;
            case 3:
                getGame().setPlayerPoints(getGame().getPlayerPoints() + 2);
                getGame().setAttackCirclePoints(2);
                getGame().moveEnemy(-1, "ladders");
                getGame().moveEnemy(-1, "batteringRam");
                return;
            default:
                return;
        }
    }
}
