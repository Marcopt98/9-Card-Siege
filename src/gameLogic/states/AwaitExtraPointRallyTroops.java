/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameLogic.states;

import gameLogic.*;

/**
 *
 * @author marcoduarte
 */
public class AwaitExtraPointRallyTroops extends StateAdapter{
    
    public AwaitExtraPointRallyTroops(GameData gameData){
        super(gameData);
    }
    
    
    @Override
    public IStates chooseExtraPointMoral(){

        if(getGame().getStatusTracksCard().getSupplies() > 1){
            getGame().reducePlayerPoints(1);
            getGame().reduceSupplies(1);
            getGame().applyDRM("Moral", 1);
            if(getGame().rollDice() + getGame().getMoralPoints() >= 5)
                getGame().raiseMoral(1);


            getGame().applyDRM("Moral", -1);
        }

        if(getGame().checkLoseGame(2))
            return new AwaitEndGame(getGame());
        
        return new AwaitAction(getGame());
    }
    
    @Override
    public IStates chooseAction(){

        getGame().reducePlayerPoints(1);
        if(getGame().rollDice() + getGame().getMoralPoints() >= 5)
            getGame().raiseMoral(1);

        
        if(getGame().checkLoseGame(2))
            return new AwaitEndGame(getGame());
        
        return new AwaitAction(getGame());
    }
    
}
