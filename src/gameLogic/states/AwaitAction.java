/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameLogic.states;

import gameLogic.*;
import static gameLogic.Constants.*;
import java.io.Serializable;
/**
 *
 * @author marcoduarte
 */
public class AwaitAction extends StateAdapter implements Serializable{
    
    public AwaitAction(GameData gameData){
        super(gameData);
    }
    
    
    @Override
    public IStates chooseAction(int index){
        
        switch(index){
            case ARCHERS_ATTACK:
                if(!getGame().getBadWeather())
                    if(getGame().getPlayer().getActionPoints() > 0)
                        return new AwaitLaneForArchers(getGame());
                return this; 
            case BOILING_WATER_ATTACK:
                if(!getGame().getBadWeather())
                    if(getGame().getPlayer().getActionPoints() > 0)
                        return new AwaitLaneForBoilingWater(getGame());
                return this; 
            case CLOSE_COMBAT:
                if(!getGame().getBadWeather())
                    getGame().closeCombat(1);
                
                if(getGame().checkLoseGame(2))
                    return new AwaitEndGame(getGame());
                
                return this; 
            case COUPURE:
                if(!getGame().getBadWeather())
                    if(getGame().getPlayer().getActionPoints() > 0)
                        getGame().coupure();
                
                if(getGame().checkLoseGame(2))
                    return new AwaitEndGame(getGame());
                
                return this;   
            case RAISE_MORAL:
                if(!getGame().getBadWeather())
                    if(getGame().getPlayer().getActionPoints() > 0)
                        return new AwaitExtraPointRallyTroops(getGame());
                return this; 
            case TUNNEL_MOVEMENT:
                if(!getGame().getBadWeather())
                    return new AwaitTypeMovement(getGame());
                return this; 
            case SUPPLYRAID:
                if(getGame().getPlayer().getActionPoints() > 0)      
                    getGame().supplyRaid();
                
                if(getGame().checkLoseGame(2))
                    return new AwaitEndGame(getGame());
                        
                return this;
            case SABOTAGE:
                if(getGame().getPlayer().getActionPoints() > 0)
                    getGame().sabotage();
                
                if(getGame().checkLoseGame(2))
                    return new AwaitEndGame(getGame());
                
                return this; 
            case EXTRA_ACTION_POINT:
                if(!getGame().getBadWeather())
                    if(getGame().getPlayer().getCanAdditionalActionPoint() == true)
                        return new AwaitMoreActionPoints(getGame());
                return this; 
        }
        
        return quit();

    }
    
//    @Override
//    public IStates chooseLaneArchers(){
//        return new AwaitLaneForArchers(getGame());
//    }
//    
//    @Override
//    public IStates chooseLaneBoilingWater(){
//        return new AwaitLaneForBoilingWater(getGame());
//    }
//    
//    @Override
//    public IStates chooseExtraPointMoral(){
//        return new AwaitExtraPointRallyTroops(getGame());
//    }
//    
//    @Override
//    public IStates chooseExtraActionPoint(){
//        return new AwaitMoreActionPoints(getGame());
//    }
//    
//    @Override
//    public IStates chooseTunnelMovement(){
//        return new AwaitTypeMovement(getGame());
//    }
    
    @Override
    public IStates endTurn(){
        if(getGame().getPosition() == 0){
            getGame().changeDay();
        }
        
        getGame().getPlayer().resetAllPoints();
        getGame().setPlayerPoints(0);
        getGame().setBadWeather(false);
        getGame().moveSoldierAutomatically();
        
        if(getGame().checkLoseGame(1))
            return new AwaitEndGame(getGame());
        if(getGame().checkWinGame())
            return new AwaitEndGame(getGame());
        
        return new AwaitDrawnCard(getGame());
    }
    
    @Override
    public IStates quit(){

        return new AwaitBeginning(getGame());
    }
   
    
}
