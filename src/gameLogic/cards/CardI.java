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
public class CardI extends Card{
    
    public CardI(GameData game){
        super(game);    
    }
    
    @Override
    public String toString(){
        return "Card #1";
    }
    
    @Override
    public void processRules(){
        switch(getGame().getDay()){
            case 1:
                getGame().setPlayerPoints(getGame().getPlayerPoints() + 3);
                getGame().trebuchetAttack();
                return;
            case 2:
                getGame().setPlayerPoints(getGame().getPlayerPoints() + 2);
                getGame().trebuchetAttack();
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
