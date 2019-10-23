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
public class AwaitLaneForBoilingWater extends StateAdapter{
    
    public AwaitLaneForBoilingWater(GameData gameData){
        super(gameData);
    }
    
    @Override
    public IStates chooseAction(){
        
        int dice = getGame().rollDice();
        getGame().setAttackCirclePoints(1);
        switch(getGame().getLane()){
            case 1:
                if(getGame().getPlayer().getCanBoilingWater() == true){
                    if((Integer)getGame().getEnemyTracksCard().getLadders().get(0) == 1){
                        getGame().reducePlayerPoints(1);
                        if((Integer)getGame().getEnemyTracksCard().getLadders().get(1) < dice + getGame().getPlayer().getAttackCirclePoints())
                            getGame().moveEnemy(1, "ladders");
                        else if(dice == 1)
                            getGame().reduceMoral(1);
                        getGame().getPlayer().setCanBoilingWater(false);
                    }
                }
                break;
            case 2:
                if(getGame().getPlayer().getCanBoilingWater() == true){
                    if((Integer)getGame().getEnemyTracksCard().getBatteringRam().get(0) == 1){
                        getGame().reducePlayerPoints(1);
                        if((Integer)getGame().getEnemyTracksCard().getBatteringRam().get(1) < dice + getGame().getPlayer().getAttackCirclePoints())
                            getGame().moveEnemy(1, "batteringRam");
                        else if(dice == 1)
                            getGame().reduceMoral(1);
                        getGame().getPlayer().setCanBoilingWater(false);
                    }
                }
                break;
            case 3:
                if(getGame().getPlayer().getCanBoilingWater() == true){
                    if((Integer)getGame().getEnemyTracksCard().getSiegeTower().get(0) == 1){
                        getGame().reducePlayerPoints(1);
                        if((Integer)getGame().getEnemyTracksCard().getSiegeTower().get(1) < dice + getGame().getPlayer().getAttackCirclePoints())
                            getGame().moveEnemy(1, "siegeTower");
                        else if(dice == 1)
                            getGame().reduceMoral(1);
                        getGame().getPlayer().setCanBoilingWater(false);
                    }
                }
                break;
            default:
                break;
        }
        
        if(getGame().checkLoseGame(2))
            return new AwaitEndGame(getGame());
                
        return new AwaitAction(getGame());
    }
}
