/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameLogic.states;

/**
 *
 * @author marcoduarte
 */
public interface IStates {
    IStates startGame();
    IStates setPlayerName(String name);
    IStates chooseAction(int index);
    IStates chooseAction();
    IStates chooseLaneArchers();
    IStates chooseLaneBoilingWater();
    IStates chooseExtraPointMoral();
    IStates chooseExtraActionPoint();
    IStates chooseTunnelMovement();
    IStates endTurn();
    IStates endGame();
    IStates quit();
}
