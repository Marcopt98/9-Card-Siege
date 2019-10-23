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
public class AwaitTypeMovement extends StateAdapter{
    
    public AwaitTypeMovement(GameData gameData){
        super(gameData);
    }
    
    //Slow Movement
    @Override
    public IStates chooseTunnelMovement(){ 
        
        if(!getGame().getBadWeather()){
            if(getGame().getStatusTracksCard().getTunnelPosition() == 0){
                getGame().moveSoldier(1, 1);
            }
            if(getGame().getStatusTracksCard().getTunnelPosition() == 3){
                getGame().moveSoldier(2, 2);
            }
        }
        
        if(getGame().checkLoseGame(2))
            return new AwaitEndGame(getGame());
                
        return new AwaitAction(getGame());
    }
    
    //Fast Movement
    @Override
    public IStates chooseAction(){
        
        if(!getGame().getBadWeather()){
        // leaving
        
            if(getGame().getStatusTracksCard().getTunnelPosition() > 0 && getGame().getStatusTracksCard().getTunnelPosition() < 3){
                if(getGame().getPlayer().getActionPoints() > 0){
                    getGame().reducePlayerPoints(1);
                    getGame().moveSoldier(3, 1);
                }
            }else 
            if(getGame().getStatusTracksCard().getTunnelPosition() == 0){
                if(getGame().getPlayer().getActionPoints() > 1){
                    getGame().reducePlayerPoints(2);
                    getGame().moveSoldier(3, 1);
                }
            }else 
            if(getGame().getStatusTracksCard().getTunnelPosition() > 0 && getGame().getStatusTracksCard().getTunnelPosition() < 3){
                if(getGame().getPlayer().getActionPoints() > 0){
                    getGame().reducePlayerPoints(1);
                    getGame().moveSoldier(0, 2);
                    getGame().transferSupplies();
                }
            }else
            if(getGame().getStatusTracksCard().getTunnelPosition() == 3){
                if(getGame().getPlayer().getActionPoints() > 1){
                    getGame().reducePlayerPoints(2);
                    getGame().moveSoldier(0, 2);
                    getGame().transferSupplies();
                }
            }
        }
      
        if(getGame().checkLoseGame(2))
            return new AwaitEndGame(getGame());
              
        return new AwaitAction(getGame());
    }
    
}
