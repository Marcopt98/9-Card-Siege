/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameLogic.states;

import gameLogic.*;
import gameLogic.cards.*;
/**
 *
 * @author marcoduarte
 */
public class AwaitDrawnCard extends StateAdapter{
    int pos;
    public AwaitDrawnCard(GameData gameData){
        super(gameData);
    }
    
    
    @Override
    public IStates chooseAction(){

        getGame().decrementPosition();
        pos = getGame().getPosition();
        
        if(pos != -1)
            getGame().drawnCard(pos);
        
        if(getGame().checkLoseGame(2))
            return new AwaitEndGame(getGame());
        
        return new AwaitAction(getGame());
    }
    
    @Override
    public IStates quit(){

        return new AwaitBeginning(getGame());
    }
}
