/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameLogic.states;

import gameLogic.*;
import java.io.Serializable;
/**
 *
 * @author marcoduarte
 */
public class StateAdapter implements IStates, Serializable{
    
    private GameData gameData;
    
    StateAdapter(GameData gameData){
        this.gameData = gameData;
    }
    
    public GameData getGame() 
    {
        return gameData;
    }
    
    public void setGame(GameData gameData) 
    {
        this.gameData = gameData;
    }
    
    
    @Override
    public IStates startGame(){
        return this;
    }
    @Override
    public IStates setPlayerName(String name){
        return this;
    }
    
    @Override
    public IStates chooseAction(){
        return this;
    }
    
    @Override
    public IStates chooseAction(int index){
        return this;
    }
    
    @Override
    public IStates chooseLaneArchers(){
        return this;
    }
    
    @Override
    public IStates chooseLaneBoilingWater(){
        return this;
    }
    
    @Override
    public IStates chooseExtraPointMoral(){
        return this;
    }
    
    @Override
    public IStates chooseExtraActionPoint(){
        return this;
    }
    
    @Override
    public IStates chooseTunnelMovement(){
        return this;
    }
    
    @Override
    public IStates quit(){
        return this;
    }
    
    @Override
    public IStates endGame(){
        return this;
    }
    
    @Override
    public IStates endTurn(){
        return this;
    }
}
