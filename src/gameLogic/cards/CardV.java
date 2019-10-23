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
public class CardV extends Card{
    
    public CardV(GameData game){
        super(game);
    }
        
    @Override
    public String toString(){
        return "Card #5";
    }
    
   @Override
    public void processRules(){
        switch(getGame().getDay()){
            
            case 1:
                getGame().setPlayerPoints(getGame().getPlayerPoints() + 3);
                getGame().setAllAttacksPoints(1);
                getGame().moveEnemy(-1, "batteringRam");
                return;
            case 2:
                getGame().setPlayerPoints(getGame().getPlayerPoints() + 2);
                getGame().moveEnemy(-1, "ladders");
                getGame().moveEnemy(-1, "batteringRam");
                if(getGame().getEnemyTracksCard().getEnemyLane("siegeTower") == 4)
                    getGame().moveEnemy(500, "siegeTower");
                return;
                
            case 3:
                getGame().setPlayerPoints(getGame().getPlayerPoints() + 2);
                getGame().repairTrebuchet();
                getGame().setCoupurePoints(1);
                getGame().moveEnemy(-1, "ladders");
                return;
                
            default:
                return;
        }
    }
}
