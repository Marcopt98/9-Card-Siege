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
public class AwaitLaneForArchers extends StateAdapter{
    
    public AwaitLaneForArchers(GameData gameData){
        super(gameData);
    }
    
    @Override
    public IStates chooseAction(){
        
        
        
        switch(getGame().getLane()){
            case 1:
                if((Integer)getGame().getEnemyTracksCard().getLadders().get(0) < 4){
                    getGame().reducePlayerPoints(1);
                    if((Integer)getGame().getEnemyTracksCard().getLadders().get(1) < getGame().rollDice() + getGame().getPlayer().getAttackLadderPoints())
                        getGame().moveEnemy(1, "ladders");
                }
                break;
            case 2:
                if((Integer)getGame().getEnemyTracksCard().getBatteringRam().get(0) < 4){
                    getGame().reducePlayerPoints(1);
                    if((Integer)getGame().getEnemyTracksCard().getBatteringRam().get(1) < getGame().rollDice() + getGame().getPlayer().getAttackBatteringRamPoints())
                        getGame().moveEnemy(1, "batteringRam");
                }
                break;
            case 3:
                if((Integer)getGame().getEnemyTracksCard().getSiegeTower().get(0) < 4){
                    getGame().reducePlayerPoints(1);
                    if((Integer)getGame().getEnemyTracksCard().getSiegeTower().get(1) < getGame().rollDice() + getGame().getPlayer().getAttackSiegeTowerPoints())
                        getGame().moveEnemy(1, "siegeTower");
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
