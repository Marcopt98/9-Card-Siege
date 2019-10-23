/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameLogic;

import java.util.*;
import gameLogic.*;
import java.io.Serializable;

/**
 *
 * @author Marco
 */
public class EnemyTracksCard implements Serializable{
    
    Integer trebuchetCount, closeCombat;
    private ArrayList <Integer> ladders,batteringRam,siegeTower; //0 - Lane, 1 - Strength
    GameData game;
    
    public EnemyTracksCard(GameData game){
        this.game = game;
        trebuchetCount = 3;
        closeCombat = 0;
        ladders = new ArrayList<>();
        batteringRam = new ArrayList<>();
        siegeTower = new ArrayList<>();
        ladders.add(0, 4);
        ladders.add(1, 2);
        batteringRam.add(0, 4);
        batteringRam.add(1, 3);
        siegeTower.add(0, 4);
        siegeTower.add(1, 4);
    }
    
    public int getTrebuchetCount(){
        return trebuchetCount;
    }
    
    public void setTrebuchetCount(int count){
        if((this.trebuchetCount + count) < 0)
            this.trebuchetCount = 0;
        
        if((this.trebuchetCount + count) > 3)
            this.trebuchetCount = 3;
        else
            this.trebuchetCount += count;
    }
    
    public ArrayList getLadders(){
        return ladders;
    }
    
    public ArrayList getBatteringRam(){
        return batteringRam;
    }
        
    public ArrayList getSiegeTower(){
        return siegeTower;
    }
    
    public int getCloseCombat(){
        return closeCombat;
    }
    
    public void setCloseCombat(int inCloseCombat){
        this.closeCombat = inCloseCombat;
    }
    
    public int getEnemyLane(String enemyName){
        switch(enemyName){
            case "ladders":
                return (int)ladders.get(0);
            case "batteringRam":
                return (int)batteringRam.get(0);
            case "siegeTower":
                return (int)siegeTower.get(0);
        } 
        return 6;
    }
    
    public void setStrength(String laneName,int strength){
        switch(laneName){
            case "ladders":
                ladders.set(1, strength);
                return;
            case "batteringRam":
                batteringRam.set(1, strength);
                return;
            case "siegeTower":
                siegeTower.set(1, strength);
                return;
            default:
                return;
        } 
    }

    public int getStrength(String laneName){
        switch(laneName){
            case "ladders":
                return ladders.get(1);
            case "batteringRam":
                return batteringRam.get(1);
            case "siegeTower":
                return siegeTower.get(1);
            default:
                break;
        }
        return 1;
    }
    
    public boolean canMove(String laneName){
        switch(laneName){
            case "ladders":
                return ladders.get(0) > 0;
            case "batteringRam":
                return batteringRam.get(0) > 0;
            case "siegeTower":
                return siegeTower.get(0) > 0;
            default:
                return false;
        }
    }
    
    public void setLanePosition(String laneName,int position, int flag){
        
        if(flag == 1){
            switch(laneName){
                case "ladders":
                    if(canMove("ladders"))
                        ladders.set(0, position);
                    return;
                case "batteringRam":
                    if(canMove("batteringRam"))
                        batteringRam.set(0, position);
                    return;
                case "siegeTower":
                    if(canMove("siegeTower"))
                        siegeTower.set(0, position);
                    return;
                default:
                    return;
            }
        }
        if(flag == 2){
            switch(laneName){
                case "ladders":
                    ladders.set(0, position);
                    return;
                case "batteringRam":
                    batteringRam.set(0, position);
                    return;
                case "siegeTower":
                    siegeTower.set(0, position);
                    return;
                default:
                    return;
            }
        }
    }
   
    
    public String getLaneString(String enemyName){
        switch(enemyName){
            case "ladders":
                if(ladders.get(0) < 5)
                    return "Position: " + ladders.get(0) + " Strength: " + ladders.get(1);
                else
                    return "Enemy Doest Not Exist.";
            case "batteringRam":
                if(batteringRam.get(0) < 5)
                    return "Position: " + batteringRam.get(0) + " Strength: " + batteringRam.get(1);
                else
                    return "Enemy Doest Not Exist.";
            case "siegeTower":
                if(siegeTower.get(0) < 5)
                    return "Position: " + siegeTower.get(0) + " Strength: " + siegeTower.get(1);
                else
                    return "Enemy Doest Not Exist.";
        } 
        return null;
    }
    
    @Override
    public String toString(){
        return "\n\n---- Enemy Track Card Information ----\nLadders > " + getLaneString("ladders") + "\nBattering Ram > " + getLaneString("batteringRam") 
                + "\nSiege Tower > " + getLaneString("siegeTower") + "\nTrebuchet Counter: " 
                + trebuchetCount + "\nEnemies in Close Combat Area: " + closeCombat;            
    }
    
    
}
