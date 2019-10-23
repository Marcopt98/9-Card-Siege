/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameLogic.states;

import gameLogic.GameData;
import java.io.Serializable;

/**
 *
 * @author Marco
 */
public class AwaitMoreActionPoints extends StateAdapter implements Serializable{
    
    public AwaitMoreActionPoints(GameData gameData) {
        super(gameData);
    }
    
    @Override
    public IStates chooseExtraActionPoint(){

        if(getGame().getStatusTracksCard().getMoral() > 1){
            getGame().reduceMoral(1);
            getGame().raisePlayerPoints(1);
        }
        getGame().getPlayer().setCanAdditionalActionPoint(false);

        return new AwaitAction(getGame());
    }
    @Override
    public IStates chooseAction(){

        if(getGame().getStatusTracksCard().getSupplies() > 1){
            getGame().reduceSupplies(1);
            getGame().raisePlayerPoints(1);
        }
        getGame().getPlayer().setCanAdditionalActionPoint(false);

        if(getGame().checkLoseGame(2))
            return new AwaitEndGame(getGame());
                
        return new AwaitAction(getGame());
    }
    
}
