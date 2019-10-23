/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameLogic;

import java.io.Serializable;
import java.util.*;
import gameLogic.cards.*;

/**
 *
 * @author marcoduarte
 */
public class GameData implements Constants, Serializable{
    private List <Card> cards = new ArrayList <>();
    private Player player;
    private int day, lane, dice;
    private int position = MAX_LIST;
    private Card  drawnCard;
    private EnemyTracksCard enemyTracksCard;
    private StatusTracksCard statusTracksCard;
    private boolean badWeather;
    private String stateGame;
    private String loseGameSituation;
    
    
    public GameData(){
        player = new Player("Player");
        day = 1;
        lane = 0;
        dice = 0;
        badWeather = false;
        stateGame = "";
        loseGameSituation = "";
        cards.addAll(Arrays.asList(new CardI(this), new CardII(this), new CardIII(this), new CardIV(this),
                new CardV(this), new CardVI(this), new CardVII(this)));
        enemyTracksCard = new EnemyTracksCard(this);
        statusTracksCard = new StatusTracksCard(this);
    }
    
    public void shuffleCards(){
        Collections.shuffle(cards);
    }
    
    public boolean setPlayerName(String name) 
    {
        try{
            player.setPlayerName(name);
            return true;
        }catch(IndexOutOfBoundsException e){
            return false;
        }
    }
    
    public Card getCard(int pos){
        return cards.get(pos);
    }
    
    public void setBadWeather(boolean value){
        this.badWeather = value;
    }
    
    public boolean getBadWeather(){
        return badWeather;
    }
    
    public String getPlayerName(){
        return player.getName();
    }

    public int getPosition(){
        return position;
    }
    
    public void resetPosition(){
        position = MAX_LIST;
    }
    
    public String getStateGame(){
        return stateGame;
    }
    
    public void setStateGame(String stateGame){
        this.stateGame = stateGame;
    }
    
    public void setLoseGameSituation(String situation){
        loseGameSituation = situation;
    }
    
    public String getLoseGameSituation(){
        return loseGameSituation;
    }
        
    public boolean checkLoseGame(int flag){ // flag = 1 end of turn , flag = 2 lose Immediately
        int count = 0;
        
        if(flag == 1){
            if(statusTracksCard.getMoral() == 0 || statusTracksCard.getSupplies() == 0 || statusTracksCard.getWallStrength() == 0){
                setStateGame("GAMEOVER");
                setLoseGameSituation("ONESURRENDER");
                return true;
            }
            if(enemyTracksCard.getCloseCombat() > 1){
                setStateGame("GAMEOVER");
                setLoseGameSituation("TWOCLOSECOMBAT");
                return true;
            }
        }

        
        if(flag == 2){
            
            if(statusTracksCard.getMoral() == 0)
                count++;
            
            if(statusTracksCard.getSupplies() == 0)
                count++;
            
            if(statusTracksCard.getWallStrength() == 0)
                count++;
            
            if(enemyTracksCard.getCloseCombat() > 2 || count > 1){
                setStateGame("GAMEOVER");
                if(enemyTracksCard.getCloseCombat() > 2)
                    setLoseGameSituation("THREECLOSECOMBAT");
                else if(count > 1)
                    setLoseGameSituation("TWOSURRENDER");
                return true;
            }
        }
            
        return false;
    }
    
    public boolean checkWinGame(){
        setStateGame("WIN");
        return day == 4;
    }
    
    public void changeDay(){
        statusTracksCard.setSupplies(-1);
        day++;
        shuffleCards();
        resetPosition();
        player.resetAllPoints();
        if(statusTracksCard.getTunnelPosition() == 1 || statusTracksCard.getTunnelPosition() == 2){
            statusTracksCard.setTunnelPosition(0);
            statusTracksCard.setSoldierOrientation("home");
            statusTracksCard.setSupplies(statusTracksCard.getStealedSupplies());
            statusTracksCard.setStealedSupplies(0);
        }
        if(statusTracksCard.getTunnelPosition() == 3)
            capture();
    }
    
    public int getDay(){
        return day;
    }
    
    public void setLane(int lane){
        this.lane = lane;
    }
    
    public int getLane(){
        return lane;
    }
    
    public EnemyTracksCard getEnemyTracksCard(){
        return enemyTracksCard;
    }
    
    public StatusTracksCard getStatusTracksCard(){
        return statusTracksCard;
    }
    
    public Player getPlayer(){
        return player;
    }
    
    public void decrementPosition(){
        if(position > MIN_LIST)
            position--;
    }
    
    public void drawnCard(int index){
        
        drawnCard = cards.get(index);
        cards.get(index).processRules();
        closeCombat(2);
    }
    
    public int rollDice(){
        dice = (int)(Math.random() * MAX_ROLL + MIN_ROLL);
        return dice;
    }
    
    public int getDiceValue(){
        return dice;
    }
    
    public void setPlayerPoints(int points){
        player.setActionPoints(points);
    }
    
    public int getPlayerPoints(){
        return player.getActionPoints();
    }
    
    public void setTrebuchetCount(int count){
        enemyTracksCard.setTrebuchetCount(count);
    }
    
    public void repairTrebuchet(){
        if(enemyTracksCard.getTrebuchetCount() < 3)
            setTrebuchetCount(1);
    }
    
    public void moveSoldierAutomatically(){
        if(statusTracksCard.getSoldierOrientation().matches("leaving"))
            if(statusTracksCard.getTunnelPosition() < 3)
                statusTracksCard.setTunnelPosition(statusTracksCard.getTunnelPosition() + 1);
        
        if(statusTracksCard.getSoldierOrientation().matches("returning"))
            if(statusTracksCard.getTunnelPosition() > 0)
                statusTracksCard.setTunnelPosition(statusTracksCard.getTunnelPosition() - 1);
        if(statusTracksCard.getTunnelPosition() == 0){
            if(statusTracksCard.getSoldierOrientation().matches("returning")){
                statusTracksCard.setSoldierOrientation("home");
                statusTracksCard.setSupplies(statusTracksCard.getStealedSupplies());
                statusTracksCard.setStealedSupplies(0);
            }
        }
        if(statusTracksCard.getTunnelPosition() == 3)
            statusTracksCard.setSoldierOrientation("enemy line");
        
    }
    
    public void moveSoldier(int tunnelPosition, int flag){
        
        if(flag == 1){
            getStatusTracksCard().setSoldierOrientation("leaving");
            getStatusTracksCard().setTunnelPosition(tunnelPosition);
        }
        
        
        if(flag == 2){
            getStatusTracksCard().setSoldierOrientation("returning");
            getStatusTracksCard().setTunnelPosition(tunnelPosition);
        }
        
    }
    
    
    public void moveSlowerEnemy(int movement){
        if(enemyTracksCard.getEnemyLane("ladders") == enemyTracksCard.getEnemyLane("batteringRam")){
            if(enemyTracksCard.getEnemyLane("batteringRam") == enemyTracksCard.getEnemyLane("siegeTower")){
                enemyTracksCard.setLanePosition("ladders", enemyTracksCard.getEnemyLane("ladders") + movement, 1);
                enemyTracksCard.setLanePosition("batteringRam", enemyTracksCard.getEnemyLane("batteringRam") + movement, 1);
                enemyTracksCard.setLanePosition("siegeTower", enemyTracksCard.getEnemyLane("siegeTower") + movement, 1);
                return;
            }
            
            if(enemyTracksCard.getEnemyLane("ladders") > enemyTracksCard.getEnemyLane("siegeTower")){
                enemyTracksCard.setLanePosition("ladders", enemyTracksCard.getEnemyLane("ladders") + movement, 1);
                enemyTracksCard.setLanePosition("batteringRam", enemyTracksCard.getEnemyLane("batteringRam") + movement, 1);
                return;
            }else{
                enemyTracksCard.setLanePosition("siegeTower", enemyTracksCard.getEnemyLane("siegeTower") + movement, 1);
                return;
            }
        }
        
        if(enemyTracksCard.getEnemyLane("siegeTower") == enemyTracksCard.getEnemyLane("batteringRam")){
            if(enemyTracksCard.getEnemyLane("siegeTower") > enemyTracksCard.getEnemyLane("ladders")){
                enemyTracksCard.setLanePosition("batteringRam", enemyTracksCard.getEnemyLane("batteringRam") + movement, 1);
                enemyTracksCard.setLanePosition("siegeTower", enemyTracksCard.getEnemyLane("siegeTower") + movement, 1);
                return;
            }else{
                enemyTracksCard.setLanePosition("ladders", enemyTracksCard.getEnemyLane("ladders") + movement, 1);
                return;
            }
        }
        
        if(enemyTracksCard.getEnemyLane("ladders") == enemyTracksCard.getEnemyLane("siegeTower")){
            if(enemyTracksCard.getEnemyLane("siegeTower") > enemyTracksCard.getEnemyLane("batteringRam")){
                enemyTracksCard.setLanePosition("ladders", enemyTracksCard.getEnemyLane("ladders") + movement, 1);
                enemyTracksCard.setLanePosition("siegeTower", enemyTracksCard.getEnemyLane("siegeTower") + movement, 1);
                return;
            }else{
                enemyTracksCard.setLanePosition("batteringRam", enemyTracksCard.getEnemyLane("batteringRam") + movement, 1);
                return;
            }
        }
        
        if(enemyTracksCard.getEnemyLane("ladders") > enemyTracksCard.getEnemyLane("siegeTower") && enemyTracksCard.getEnemyLane("ladders") > enemyTracksCard.getEnemyLane("batteringRam")){
            enemyTracksCard.setLanePosition("ladders", enemyTracksCard.getEnemyLane("ladders") + movement, 1);
            return;
        }
        
        if(enemyTracksCard.getEnemyLane("batteringRam") > enemyTracksCard.getEnemyLane("siegeTower") && enemyTracksCard.getEnemyLane("batteringRam") > enemyTracksCard.getEnemyLane("ladders")){
            enemyTracksCard.setLanePosition("batteringRam", enemyTracksCard.getEnemyLane("batteringRam") + movement, 1);
            return;
        }
        if(enemyTracksCard.getEnemyLane("siegeTower") > enemyTracksCard.getEnemyLane("ladders") && enemyTracksCard.getEnemyLane("siegeTower") > enemyTracksCard.getEnemyLane("batteringRam")){
            enemyTracksCard.setLanePosition("siegeTower", enemyTracksCard.getEnemyLane("siegeTower") + movement, 1);
            return;
        }
    }
    
    public void moveEnemy(int movement, String enemy){
        
        int count = 0;
        
        if(movement < 0){
            if(enemy.matches("ladders"))
                enemyTracksCard.setLanePosition("ladders", enemyTracksCard.getEnemyLane("ladders") + movement, 1);
            if(enemy.matches("batteringRam"))
                enemyTracksCard.setLanePosition("batteringRam", enemyTracksCard.getEnemyLane("batteringRam") + movement, 1);
            if(enemy.matches("siegeTower")){
                if(enemyTracksCard.getEnemyLane("siegeTower") < 5)
                    enemyTracksCard.setLanePosition("siegeTower", enemyTracksCard.getEnemyLane("siegeTower") + movement, 1);
            }
            if(enemy.matches("slower"))
                moveSlowerEnemy(movement);
        
        }
        
        if(movement > 0){
            
            if(enemy.matches("ladders"))
                enemyTracksCard.setLanePosition("ladders", enemyTracksCard.getEnemyLane("ladders") + movement, 2);
            if(enemy.matches("batteringRam"))
                    enemyTracksCard.setLanePosition("batteringRam", enemyTracksCard.getEnemyLane("batteringRam") + movement, 2);
            if(enemy.matches("siegeTower"))
                if(enemyTracksCard.getEnemyLane("siegeTower") < 5)
                    enemyTracksCard.setLanePosition("siegeTower", enemyTracksCard.getEnemyLane("siegeTower") + movement, 2);
        }
        
        if(enemyTracksCard.getEnemyLane(enemy) == 0){
            enemyTracksCard.setStrength(enemy, 4);
            enemyTracksCard.setCloseCombat(enemyTracksCard.getCloseCombat() + 1);
        }
        
        if(enemyTracksCard.getEnemyLane("ladders") == 0)
            count++;
        if(enemyTracksCard.getEnemyLane("batteringRam") == 0)
            count++;
        if(enemyTracksCard.getEnemyLane("siegeTower") == 0)
            count++;

        if(count == 0)
            enemyTracksCard.setCloseCombat(0);
        if(count == 1)
            enemyTracksCard.setCloseCombat(1);
        if(count == 2)
            enemyTracksCard.setCloseCombat(2);
        if(count == 3)
            enemyTracksCard.setCloseCombat(3);

    }
    
    public void trebuchetAttack(){
        
        switch(enemyTracksCard.getTrebuchetCount()){
            case 3:
                statusTracksCard.setWallStrength(-2);
                return;
            case 2:
                statusTracksCard.setWallStrength(-1);
                return;
            case 1:
                rollDice();
                if(getDiceValue() == 4 || getDiceValue() == 5 || getDiceValue() == 6)
                    statusTracksCard.setWallStrength(-1);
        }
    }
        
    public void reducePlayerPoints(int points){
        player.setActionPoints(player.getActionPoints() - points);
    }
    
    public void raisePlayerPoints(int points){
        player.setActionPoints(player.getActionPoints() + points);
    }
    
    public void applyDRM(String type, int points){
        
        if(type.matches("Moral")){
            player.setMoralPoints(player.getMoralPoints() + points);
        }
    }
    
    public void transferSupplies(){
        getStatusTracksCard().setSupplies(getStatusTracksCard().getStealedSupplies());
        getStatusTracksCard().setStealedSupplies(0);
    }
    
    //Functions for actions without states
    public void supplyRaid(){ 
        
        if(statusTracksCard.getTunnelPosition() == 3 && statusTracksCard.getStealedSupplies() < 2){
            rollDice();
            player.setActionPoints(player.getActionPoints() - 1);
            if(getDiceValue() > 2 && getDiceValue() < 6)
                statusTracksCard.setStealedSupplies(statusTracksCard.getStealedSupplies() + 1);
            if(getDiceValue() == 6){
                if(statusTracksCard.getStealedSupplies() < 1)
                    statusTracksCard.setStealedSupplies(statusTracksCard.getStealedSupplies() + 2);
                else
                    statusTracksCard.setStealedSupplies(statusTracksCard.getStealedSupplies() + 1);
            }
            if(getDiceValue() == 1){
                capture();
            }

        }
    }
    
    public void capture(){
        statusTracksCard.setStealedSupplies(0);
        statusTracksCard.setTunnelPosition(0);
        reduceMoral(1);
    }
    
    public void coupure(){

        if(statusTracksCard.getWallStrength() < 4){
            rollDice();
            player.setActionPoints(player.getActionPoints() - 1);
            if(getDiceValue() == 5 || getDiceValue() == 6){
                statusTracksCard.setWallStrength(1); 
            }
        }
    }

    
    public void sabotage(){
        
        if(player.getActionPoints() > 0){
            if(statusTracksCard.getTunnelPosition() == 3){
                rollDice();
                player.setActionPoints(player.getActionPoints() - 1);
                if(getDiceValue() + player.getSabotagePoints() > 4)
                    enemyTracksCard.setTrebuchetCount(-1);
                if(getDiceValue() + player.getSabotagePoints() == 1)
                    capture();
            }
        }
    }

    public void closeCombat(int flag){

        if(flag == 1){
            if(enemyTracksCard.getCloseCombat() == 1){
                rollDice();
                if(enemyTracksCard.getEnemyLane("ladders") == 0)
                    if(getDiceValue() + player.getAttackCloseCombatPoints() > enemyTracksCard.getStrength("ladders")){
                        moveEnemy(1, "ladders");
                        //enemyTracksCard.setStrength("ladders", 2);
                    }

                if(enemyTracksCard.getEnemyLane("batteringRam") == 0)
                    if(getDiceValue() + player.getAttackCloseCombatPoints() > enemyTracksCard.getStrength("batteringRam")){
                        moveEnemy(1, "batteringRam");
                        //enemyTracksCard.setStrength("batteringRam", 3);
                    }

                if(enemyTracksCard.getEnemyLane("siegeTower") == 0)
                    if(getDiceValue() + player.getAttackCloseCombatPoints() > enemyTracksCard.getStrength("siegeTower")){
                        moveEnemy(1, "siegeTower");
                        //enemyTracksCard.setStrength("siegeTower", 4);
                    }
            }
            

            if(flag == 2){
                int op;
                boolean verifyCloseCombatPosition = false;

                if(enemyTracksCard.getCloseCombat() == 2){
                    rollDice();

                    do{
                        op = (int)(Math.random() * 3 + 1);

                        if(op == 1)
                            if(enemyTracksCard.getEnemyLane("ladders") == 0)
                                verifyCloseCombatPosition = true;
                        if(op == 2)
                            if(enemyTracksCard.getEnemyLane("batteringRam") == 0)
                                verifyCloseCombatPosition = true;
                        if(op == 3)
                           if(enemyTracksCard.getEnemyLane("siegeTower") == 0)
                               verifyCloseCombatPosition = true;

                    }while(verifyCloseCombatPosition != true);

                    switch(op){
                        case 1:
                            if(enemyTracksCard.getEnemyLane("ladders") == 0)
                                if(getDiceValue() + player.getAttackCloseCombatPoints() > enemyTracksCard.getStrength("ladders")){
                                    moveEnemy(1, "ladders");
                                if(getDiceValue() + player.getAttackCloseCombatPoints() == 1)
                                    reduceMoral(1);
                                }
                            break;
                        case 2:
                            if(enemyTracksCard.getEnemyLane("batteringRam") == 0)
                                if(getDiceValue() + player.getAttackCloseCombatPoints() > enemyTracksCard.getStrength("batteringRam")){
                                    moveEnemy(1, "ladders");
                                if(getDiceValue() + player.getAttackCloseCombatPoints() == 1)
                                    reduceMoral(1);
                                }
                            break;
                        case 3:
                            if(enemyTracksCard.getEnemyLane("siegeTower") == 0)
                                if(getDiceValue() + player.getAttackCloseCombatPoints() > enemyTracksCard.getStrength("siegeTower")){
                                    moveEnemy(1, "ladders");
                                if(getDiceValue() + player.getAttackCloseCombatPoints() == 1)
                                    reduceMoral(1);
                                }
                            break;
                        default:
                            break;
                }
            }
            }
        }
    }
    
    // Set Functions for the cards events
    
    public void setSabotagePoints(int points){
        player.setSabotagePoints(points);
    }
    
    public void setMoralPoints(int points){
        player.setMoralPoints(points);
    }
    
    public int getMoralPoints(){
        return player.getMoralPoints();
    }
    
    public void setRaidPoints(int points){
        player.setRaidPoints(points);
    }
    
    public void setAttackCirclePoints(int points){
        player.setAttackCirclePoints(points);
    }
    
    public int getAttackCirclePoints(){
        return player.getAttackCirclePoints();
    }
    
    public void setAttackBatteringRamPoints(int points){
        player.setAttackBatteringRamPoints(points);
    }
    
    public void setAttackSiegeTowerPoints(int points){
        player.setAttackSiegeTowerPoints(points);
    }
    
    public void setCoupurePoints(int points){
        player.setCoupurePoints(points);
    }
    
    public void setAllAttacksPoints(int points){
        player.setAllAttacksPoints(points);
    }
    
    public void setAttackCloseCombatPoints(int points){
        player.setAttackCloseCombatPoints(points);
    }
    
    public void setAttackLadderPoints(int points){
        player.setAttackLadderPoints(points);
    }
    
    
    
    // toString Functions
    
    public String getPlayerInfo(){
        return player.toString();
    }
    
    public String returnCardName(){
        return cards.get(position).toString();
    }
    
    public String getEnemyAndStatusCardInfo(){
        return enemyTracksCard.toString() + statusTracksCard.toString();
    }

    // Set Funcitons of Enemy and Status Card
    
    public void reduceMoral(int quantity){
        statusTracksCard.setMoral(-quantity);
    }
    
    public void reduceSupplies(int quantity){
        statusTracksCard.setSupplies(-quantity);
    }
    
    public void raiseMoral(int quantity){
        statusTracksCard.setMoral(quantity);
    }
    
    public void raiseSupplies(int quantity){
        statusTracksCard.setSupplies(quantity);
    } 
}
