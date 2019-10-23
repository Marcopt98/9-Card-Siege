/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameLogic;

import java.io.Serializable;

/**
 *
 * @author marcoduarte
 */
public class Player implements Serializable{
    private String name;
    private int actionPoints, sabotagePoints, moralPoints, RaidPoints, attackCirclePoints, attackBatteringRamPoints, attackSiegeTowerPoints, coupurePoints, allAttacksPoints, attackCloseCombatPoints, attackLadderPoints;
    private boolean canAdditionalActionPoint, canBoilingWater;
    
    public Player(String name){
        this.name = name;
        actionPoints = 0;
        sabotagePoints= 0;
        moralPoints = 0;
        RaidPoints = 0;
        attackCirclePoints = 0;
        attackBatteringRamPoints = 0;
        attackSiegeTowerPoints = 0;
        coupurePoints = 0;
        allAttacksPoints = 0;
        attackCloseCombatPoints = 0;
        attackLadderPoints = 0;
        canAdditionalActionPoint = true;
        canBoilingWater = true;
    }
    
    public void setPlayerName(String name){
        this.name = name;
    }
    
    public String getName(){
        return name;
    }
    
    public void setActionPoints(int points){
        if((points) < 0)
            this.actionPoints = 0;
        else
            this.actionPoints = points;
    }
    
    public int getActionPoints(){
        return this.actionPoints;
    }
    
    public void setSabotagePoints (int points){
        this.sabotagePoints = points;
    }
    
    public int getSabotagePoints(){
        return sabotagePoints;
    }
    
    public void setMoralPoints (int points){
        this.moralPoints = points;
    }
    
    public int getMoralPoints(){
        return moralPoints;
    }
    
    public void setRaidPoints(int points){
        this.RaidPoints = points;
    }
    
    public int getRaidPoints(){
        return RaidPoints;
    }
    
    public void setAttackCirclePoints(int points){
        this.attackCirclePoints = points;
    }
    
    public int getAttackCirclePoints(){
        return attackCirclePoints;
    }
    
    public void setAttackBatteringRamPoints(int points){
        this.attackBatteringRamPoints = points;
    }
    
    public int getAttackBatteringRamPoints(){
        return attackBatteringRamPoints;
    }
    
    public void setAttackSiegeTowerPoints(int points){
        this.attackSiegeTowerPoints = points;
    }
    
    public int getAttackSiegeTowerPoints(){
        return attackSiegeTowerPoints;
    }
    
    public void setCoupurePoints(int points){
        this.coupurePoints = points;
    }
    
    public int getCouputePoints(){
        return coupurePoints;
    }
    
    public void setAllAttacksPoints(int points){
        this.allAttacksPoints = points;
    }
    
    public int getAllAttacksPoints(){
        return allAttacksPoints;
    }
    
    public void setAttackCloseCombatPoints(int points){
        this.attackCloseCombatPoints = points;
    }
    
    public int getAttackCloseCombatPoints(){
        return attackCloseCombatPoints;
    }
    
    public void setAttackLadderPoints(int points){
        this.attackLadderPoints = points;
    }
    
    public int getAttackLadderPoints(){
        return attackLadderPoints;
    }
    
    public void setCanAdditionalActionPoint(boolean value){
        this.canAdditionalActionPoint = value;
    }
    
    public boolean getCanAdditionalActionPoint(){
        return canAdditionalActionPoint;
    }
    
    public void setCanBoilingWater(boolean value){
        this.canBoilingWater = value;
    }
    
    public boolean getCanBoilingWater(){
        return canBoilingWater;
    }
    
    public void resetAllPoints(){
        sabotagePoints= 0;
        moralPoints = 0;
        RaidPoints = 0;
        attackCirclePoints = 0;
        attackBatteringRamPoints = 0;
        attackSiegeTowerPoints = 0;
        coupurePoints = 0;
        allAttacksPoints = 0;
        attackCloseCombatPoints = 0;
        attackLadderPoints = 0;
        canAdditionalActionPoint = true;
        canBoilingWater = true;
    }
    
    @Override
    public String toString(){
       return " Player Name: " + name + " Action Points: " + actionPoints + "  ";
    }
    
}
