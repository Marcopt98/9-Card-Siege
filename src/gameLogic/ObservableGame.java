/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameLogic;

import gameLogic.states.IStates;
import java.util.Observable;
import gameLogic.Constants;
import static gameLogic.Constants.ARCHERS_ATTACK;

/**
 *
 * @author marcoduarte
 */
public class ObservableGame extends Observable{
    
    CardSiege cardSiege;
    
    public ObservableGame(){
        cardSiege = new CardSiege();
    }
    
    public GameData getGameData(){
        return cardSiege.getGameData();
    }
    
    public void setCardSiege(CardSiege cardSiege){
        this.cardSiege = cardSiege;
        
        setChanged();
        notifyObservers();
    }
    
    public CardSiege getCardSiege(){
        return cardSiege;
    }
    
    public void quit(){
        cardSiege.quit();
        
        setChanged();
        notifyObservers();
    }
    
    public void setPlayerName(String name){
        cardSiege.setPlayerName(name);
        
        setChanged();
        notifyObservers();
    }
    
    public void startGame(){
        cardSiege.startGame();
        
        setChanged();
        notifyObservers();
    }
    
    public void chooseAction(){
        cardSiege.setActionSelection();
        
        setChanged();
        notifyObservers();
    }
    
    public void chooseAction(int num){
        cardSiege.setActionSelection(num);
        
        setChanged();
        notifyObservers();
    }
    
    public void extraActionPoint(){
        cardSiege.setExtraActionPoint();
        
        setChanged();
        notifyObservers();
    }
    
    public void tunnelmovement(){
        cardSiege.setTunnelmovement();
        
        setChanged();
        notifyObservers();
    }
    
    public void rallyTroops(){
        cardSiege.setExtraPointMoral();
        
        setChanged();
        notifyObservers();
    }
    
    public void endTurn(){
        cardSiege.endTurn();
        
        setChanged();
        notifyObservers();
    }
    
    public void playAgain(){
        cardSiege.endGame();
        
        setChanged();
        notifyObservers();
    }
    
    public Player getPlayer(){
        return cardSiege.getPlayer();
    }
    
    public IStates getState(){
        return cardSiege.getState();
    }

   
}
