/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameLogic.cards;

/**
 *
 * @author Marco
 */

import gameLogic.*;

public class CardVII extends Card{
    
    public CardVII(GameData game){
        super(game);
    }
        
    @Override
    public String toString(){
        return "Card #7";
    }
    
    @Override
    public void processRules(){
        switch(getGame().getDay()){
            
            case 1:
                getGame().setPlayerPoints(getGame().getPlayerPoints() + 2);
                getGame().setAttackBatteringRamPoints(-1);
                getGame().moveEnemy(-1, "batteringRam");
                return;
            case 2:
                getGame().setPlayerPoints(getGame().getPlayerPoints() + 2);
                getGame().setAttackSiegeTowerPoints(-1);
                getGame().moveEnemy(-1, "siegeTower");
                return;
                
            case 3:
                getGame().setPlayerPoints(getGame().getPlayerPoints() + 3);
                getGame().setAttackBatteringRamPoints(1);
                getGame().setAttackLadderPoints(1);
                getGame().setMoralPoints(1);
                getGame().moveEnemy(-1, "ladders");
                getGame().moveEnemy(-1, "batteringRam");
                getGame().moveEnemy(-1, "siegeTower");
                return;
                
            default:
                return;
        }
    }
}
