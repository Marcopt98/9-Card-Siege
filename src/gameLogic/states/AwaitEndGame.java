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
 * @author marcoduarte
 */
public class AwaitEndGame extends StateAdapter implements Serializable{
    
    public AwaitEndGame(GameData gameData){
        super(gameData);
    }
    
    @Override
    public IStates endGame(){
        return new AwaitBeginning(getGame());
    }
}
