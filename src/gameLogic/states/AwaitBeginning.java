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
public class AwaitBeginning extends StateAdapter{
    
    public AwaitBeginning(GameData gameData){
        super(gameData);
        
    }
    
    @Override
    public IStates setPlayerName(String name){
        getGame().setPlayerName(name);
        return this;
    }
    
    @Override
    public IStates startGame(){
        return new AwaitDrawnCard(getGame());
    }
}
