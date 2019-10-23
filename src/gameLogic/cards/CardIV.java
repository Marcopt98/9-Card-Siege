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
public class CardIV extends Card{
    
    public CardIV(GameData game){
        super(game);
    }
        
    @Override
    public String toString(){
        return "Card #4";
    }
    
    @Override
    public void processRules(){
        switch(getGame().getDay()){
            
            case 1:
                getGame().setPlayerPoints(getGame().getPlayerPoints() + 2);
                getGame().reduceMoral(1);
                getGame().moveEnemy(-1, "ladders");
                getGame().moveEnemy(-1, "siegeTower");
                return;
            case 2:
                getGame().setPlayerPoints(getGame().getPlayerPoints() + 2);
                getGame().setAttackBatteringRamPoints(1);
                getGame().moveEnemy(-1, "ladders");
                getGame().moveEnemy(-1, "batteringRam");
                return;
            case 3:
                getGame().setPlayerPoints(getGame().getPlayerPoints() + 3);
                getGame().setAttackSiegeTowerPoints(1);
                getGame().moveEnemy(-1, "siegeTower");
                return;
            default:
                return;
        }
    }
}
