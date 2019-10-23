/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameLogic;

import gameLogic.states.*;
import java.io.*;
import java.util.*;
/**
 *
 * @author marcoduarte
 */
public class CardSiege implements Serializable{

    /**
     * @param args the command line arguments
     */
    private GameData gameData;
    private IStates state;
    
    
    
    public CardSiege(){
        gameData = new GameData(); 
        setState(new AwaitBeginning(gameData));
    }
    
    public void startGame(){
        gameData.shuffleCards();
        setState(getState().startGame()); 
    }
    
    public void setLane(int lane){
        gameData.setLane(lane);
    }
    
    public GameData getGameData(){
        return gameData;
    }
    
    public Player getPlayer(){
        return gameData.getPlayer();
    }
        
    public IStates getState(){
        return state;
    }
    
    public void setState(IStates state){
        this.state = state;
    }
    
    public void setPlayerName(String nome){
        setState(getState().setPlayerName(nome));
    }
    
    public void setActionSelection(){
        setState(getState().chooseAction());
    }
    
    public void setActionSelection(int index){
        setState(getState().chooseAction(index));
    }
    
    public void setLaneSelection(int flag){
        if(flag == 1)
            setState(getState().chooseLaneArchers());
        if(flag == 2)
            setState(getState().chooseLaneBoilingWater());
    }
    
    public void setExtraPointMoral(){
        setState(getState().chooseExtraPointMoral());
    }
    
    public void setExtraActionPoint(){
        setState(getState().chooseExtraActionPoint());
    }
    
    public void setTunnelmovement(){
        setState(getState().chooseTunnelMovement());
    }
    
    public void endTurn(){
        setState(getState().endTurn());
    }
    
    public void quit(){
        setState(getState().quit());
    }
    
    public void endGame(){
        setState(getState().endGame());
    }
    
    public String getGameState(){
        return gameData.getStateGame();
    }
    
    public String turnInfo(){
        return gameData.returnCardName() + gameData.getPlayerInfo() + "Day: " + gameData.getDay() + gameData.getEnemyAndStatusCardInfo() + "Dice: " + gameData.getDiceValue();
    }
}
