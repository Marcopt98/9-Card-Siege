/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameLogic;

import gameLogic.*;
import java.io.Serializable;

/**
 *
 * @author Marco
 */
public class StatusTracksCard implements Serializable{
    
    
    private Integer wallStrength, moral, supplies, stealedSupplies, tunnelPosition;
    private GameData game;
    private String SoldierOrientation = "";
                            
    public StatusTracksCard(GameData game){
        this.game = game;
        wallStrength = 4;
        moral = 4;
        supplies = 4;
        stealedSupplies = 0;
        tunnelPosition = 0;
    }
    
    public int getWallStrength(){
        return wallStrength;
    }
    
    public void setWallStrength(int wallStrength){
        if((this.wallStrength + wallStrength) > 4)
            this.wallStrength = 4;
    
        if((this.wallStrength + wallStrength) < 0)
            this.wallStrength = 0;
        else
            this.wallStrength += wallStrength;
    }
    
    public int getMoral(){
        return moral;
    }
    
    public void setMoral(int moral){
        if((this.moral + moral) > 4)
            this.moral = 4;
    
        if((this.moral + moral) < 0)
            this.moral = 0;
        else
            this.moral += moral;
    }
    
    public int getSupplies(){
        return supplies;
    }
    
    public void setSupplies(int supplies){
        if((this.supplies + supplies) > 4)
            this.supplies = 4;
    
        if((this.supplies + supplies) < 0)
            this.supplies = 0;
        else
            this.supplies += supplies;
    }
    
    public int getStealedSupplies(){
        return stealedSupplies;
    }
    
    public void setStealedSupplies (int stealedSupplies){
        this.stealedSupplies = stealedSupplies;
    }
    
    public int getTunnelPosition(){
        return tunnelPosition;
    }
    
    public void setTunnelPosition(int tunnelPosition){
        this.tunnelPosition = tunnelPosition;
    }
    
    public String getTunnelPositionName(){
        switch(tunnelPosition){
            case 0:
                return "Castle";
            case 1:
                return "1st Position in the Tunnel";
            case 2:
                return "2nd Position in the Tunnel";
            case 3:
                return "Enemy Line";
        }
        return null;
    }
    
    public String getSoldierOrientation(){
        return SoldierOrientation;
    }
    
    public void setSoldierOrientation(String orientation){
            SoldierOrientation = orientation;
    }
    
    
    @Override
    public String toString(){
        return "\n\n---- Status Track Card Information ----\nSupplies: " + supplies 
                + "\nMoral: " + moral + "\nWall Strength: " + wallStrength 
                + "\nSoldier is in the " + getTunnelPositionName() + "\nStealed Supplies: " + stealedSupplies + "\n\n";
    }
}
