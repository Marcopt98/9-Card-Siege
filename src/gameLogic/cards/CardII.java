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
public class CardII extends Card{
    
    public CardII(GameData game){
        super(game);
    }
    
    @Override
    public String toString(){
        return "Card #2";
    }
    
    @Override
    public void processRules(){
        
        switch(getGame().getDay()){
            
            case 1:
                getGame().setPlayerPoints(getGame().getPlayerPoints() + 2);
                getGame().reduceMoral(1);
                getGame().reduceSupplies(1);
                getGame().moveEnemy(-1,"siegeTower");
                return;
            case 2:
                getGame().setPlayerPoints(getGame().getPlayerPoints() + 2);
                getGame().setSabotagePoints(1);
                getGame().setMoralPoints(1);
                getGame().moveEnemy(-1, "slower");
                return;
                
            case 3:
                getGame().setPlayerPoints(getGame().getPlayerPoints() + 1);
                getGame().trebuchetAttack();
                return;
                
            default:
                return;
                
        }
    }
    
    
}
