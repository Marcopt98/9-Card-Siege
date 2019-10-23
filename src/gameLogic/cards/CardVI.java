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
public class CardVI extends Card{
    
    public CardVI(GameData game){
        super(game);
    }
        
    @Override
    public String toString(){
        return "Card #6";
    }
    
    @Override
    public void processRules(){
        switch(getGame().getDay()){
            
            case 1:
                getGame().setPlayerPoints(getGame().getPlayerPoints() + 3);
                getGame().setRaidPoints(1);
                getGame().setSabotagePoints(1);
                getGame().moveEnemy(-1, "slower");
                return;
            case 2:
                getGame().setPlayerPoints(getGame().getPlayerPoints() + 3);
                getGame().setCoupurePoints(1);
                getGame().setRaidPoints(1);
                getGame().setSabotagePoints(1);
                getGame().moveEnemy(-1, "ladders");
                return;
                
            case 3:
                getGame().setPlayerPoints(getGame().getPlayerPoints() + 3);
                getGame().setAttackCloseCombatPoints(1);
                getGame().setAttackCirclePoints(1);
                getGame().moveEnemy(-1, "batteringRam");
                getGame().moveEnemy(-1, "siegeTower");
                return;
                
            default:
                return;
                
        }
    }
}
