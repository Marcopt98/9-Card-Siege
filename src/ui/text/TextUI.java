/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.text;
import gameLogic.CardSiege;
import gameLogic.states.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author marcoduarte
 */
public class TextUI {
    
    private CardSiege game;
    private boolean quit = false;
    
    public TextUI(CardSiege game){
        this.game = game;
    }
    
    public void uiAwaitBeginning() throws IOException, ClassNotFoundException{
        
        Scanner read = new Scanner(System.in);
        String option, fileName;
        StringBuffer playerName = new StringBuffer();
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        char op;
        
        System.out.println("\t######################################");
        System.out.println("\t\tWelcome to 9 Card Siege");
        System.out.println("\t######################################");
        
        while(true){
            
            do{
                System.out.println("\n\t\t***MENU***\n");
                System.out.println("\t1 - Play");
                System.out.println("\t2 - Set Player Name");
                System.out.println("\t3 - Continue From a Previously Saved Game ");
                System.out.println("\t4 - Credits");
                System.out.println("\t5 - Quit");
                System.out.print("\t>> ");
            
                option = read.next();
                op = option.charAt(0);
                
            }while(op < '0' || op > '5');
            
            switch(op){
                
                case '1':
                    if(playerName.length() == 0)
                        playerName.append("Player");
                    
                    game.startGame();
                
                    return;
                    
                case '2':
                    System.out.print("Introduce player name: ");
                    playerName.append(read.next());
                    game.setPlayerName(playerName.toString());
                    return;
                    
                case '3':
                    System.out.print("Introduce the File Name: ");
                    
                    fileName = buffer.readLine().trim();
                    
                    if(fileName == null || fileName.length() < 1)
                        return;
                    
                    try{
                        game = resumeGameFromFile(fileName);
                    }catch(IOException e){
                        System.out.println(e);
                    }
                    return;

                case '4':
                    System.out.println("\t\t---CREDITS---\n");
                    System.out.println("\tMarco Duarte & Marco Lopes");
                    System.out.println("\tDEIS-ISEC 2018\n");
                    return;
                    
                case '5':
                    quit = true;
                    return;
                default:
                    return;

            }
        }   
    }
    
    public void uiGameInfo(){
        System.out.println("\nCard: " + game.turnInfo());
    }
    
    public void uiAwaitEndGame(){
        Integer op;
        Scanner read = new Scanner(System.in);
        
        if(game.getGameState().matches("GAMEOVER")){
            System.out.println("\n\t\t    ### GAME OVER ###");
            System.out.println("\tDon't be sad! You'll have more luck next time!\n");
        }
        
        if(game.getGameState().matches("WIN")){
            System.out.println("\n\t\t### YOU WON ###");
            System.out.println("\tWow, you are an excelent tactician!\n");
        }
        System.out.println("\tPRESS 1 TO CONTINUE!\n");
        System.out.print("\t>> ");
        
        op = read.nextInt();
        
        if(op == 1){
            game.endGame();
        }
        
    }
    public void uiAwaitDrawnCard(){
        Scanner read = new Scanner(System.in);
        Integer op;
        
        System.out.println("\t\t---Draw Top Card---");
        System.out.println("\n\tPRESS 1 TO DRAW!");
        System.out.print("\t>> ");
        
        op = read.nextInt();
        
        if(op == 1){
            game.setActionSelection();
        }
    }
    
    public void uiAwaitAction() throws IOException{
        
        Scanner read = new Scanner(System.in);
        String option,fileName;
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        int op;
        
        while(true){
            
            uiGameInfo();
            
            do{
                System.out.println("\t\t---Choose Action---\n");
                System.out.println("\t1 - Archers Attack");
                System.out.println("\t2 - Boiling Water Attack");
                System.out.println("\t3 - Close Combat Attack");
                System.out.println("\t4 - Coupure");
                System.out.println("\t5 - Rally Troops");
                System.out.println("\t6 - Tunnel Movement");
                System.out.println("\t7 - Supply Raid");
                System.out.println("\t8 - Sabotage");
                System.out.println("\t9 - Receive Extra Action Point");
                System.out.println("\t10 - End Turn");
                System.out.println("\t11 - Save and Quit");
                System.out.print("\t>> ");

                op = read.nextInt(); 
        
            }while(op < 1 || op > 11);
            
            
            switch(op){
                case 1:
                    game.setActionSelection(op);
                    return;
                case 2:
                    game.setActionSelection(op);
                    return;
                case 3:
                    game.setActionSelection(op);
                    return;
                case 4:
                    game.setActionSelection(op);
                    return;
                case 5:
                    game.setActionSelection(op);
                    return;
                case 6:
                    game.setActionSelection(op);
                    return;
                case 7:
                    game.setActionSelection(op);
                    return;
                case 8:
                    game.setActionSelection(op);
                    return;
                case 9:
                    game.setActionSelection(op);
                    return;
                case 10:
                    game.endTurn();
                    return;
                case 11:
                    System.out.print("Save As: ");
                    
                    fileName = buffer.readLine().trim();
                    
                    if(fileName == null || fileName.length() < 1)
                        return;
                    
                    SaveAndQuit(fileName);
                    quit = true;
                    return;
                    
                   
            }
            
        }
    }
    
    public void uiAwaitLaneForArchers(){
        
        Scanner read = new Scanner(System.in);
        Integer op;
        
        do{
            System.out.println("\t\t---Choose Lane For Archers Attack---\n");
            System.out.println("\t1 - Ladders Lane");
            System.out.println("\t2 - Battering Rams Lane");
            System.out.println("\t3 - Siege Towers Lane");
            System.out.print("\t>> ");

            op = read.nextInt();

        }while(op < 1 || op > 3);

        game.setLane(op);
        game.setActionSelection();

    }
    
    public void uiAwaitLaneForBoilingWater(){
        
        Scanner read = new Scanner(System.in);
        Integer op;
        
        do{
            System.out.println("\t\t---Choose Lane For Boiling Water Attack---\n");
            System.out.println("\t1 - Ladders Lane");
            System.out.println("\t2 - Battering Rams Lane");
            System.out.println("\t3 - Siege Towers Lane");
            System.out.print("\t>> ");

            op = read.nextInt();

        }while(op < 1 || op > 3);

        game.setLane(op);
        game.setActionSelection();

    }
        
    public void uiAwaitExtraPointRallyTroops(){
        
        Scanner read = new Scanner(System.in);
        Integer op;
        
        do{
            System.out.println("\t\t---Choose Extra Point---\n");
            System.out.println("\tWARNING: In return for the extra point you lose 1 supply point!\n");
            System.out.println("\t1 - I want to sacrifice 1 supply point! (Lose 1 supply)");
            System.out.println("\t2 - Hell no! I'm to hungry to make the trade! (Same supplies)");
            System.out.print("\t>> ");
            
            op = read.nextInt();
            
        }while(op < 1 || op > 2);
        
        switch(op){
            case 1:
                game.setExtraPointMoral();
                break;
            case 2:
                game.setActionSelection();
                break;
            default:
                break;
        }
        
    }
    
    public void uiAwaitMoreActionPoints(){
        
        Scanner read = new Scanner(System.in);
        Integer op;
        
        do{
            System.out.println("\t\t---Choose Extra Point---\n");
            System.out.println("\tWARNING: In return for the extra action point you lose 1 supply point or 1 morale point!\n");
            System.out.println("\t1 - I prefer to be depressed than to be hungry! (Lose 1 morale)");
            System.out.println("\t2 - I just want to be happy no matter the cost! (Lose 1 supply)");
            System.out.print("\t>> ");
            
            op = read.nextInt();
            
        }while(op < 1 || op > 2);
        
        switch(op){
            case 1:
                game.setExtraActionPoint();
                break;
            case 2:
                game.setActionSelection();
                break;
            default:
                break;
        }
    }
    
    public void uiAwaitTypeMovement(){
        
        Scanner read = new Scanner(System.in);
        Integer op;
        
        do{
            System.out.println("\t\t---Choose Tunnel Type Movement---\n");
            //System.out.println("\tWARNING: In return for the extra action point you lose 1 supply point or 1 morale point!\n");
            System.out.println("\t1 - I'm poor! If it's free I'll always take it! (Free Movement)");
            System.out.println("\t2 - I'm scared of tunnels! It's better if I run! (Fast Movement)");
            System.out.print("\t>> ");
            
            op = read.nextInt();
            
        }while(op < 1 || op > 2);
        
        switch(op){
            case 1:
                game.setTunnelmovement();
                break;
            case 2:
                game.setActionSelection();
                break;
            default:
                break;
        }
    }
    public void run() throws IOException, ClassNotFoundException{
        
        while(!quit){
            
           IStates state = game.getState();
            
           if(state instanceof AwaitBeginning) 
               uiAwaitBeginning();
           else if(state instanceof AwaitDrawnCard)
               uiAwaitDrawnCard();
           else if(state instanceof AwaitAction)
               uiAwaitAction();
           else if(state instanceof AwaitLaneForArchers)
               uiAwaitLaneForArchers();
           else if(state instanceof AwaitLaneForBoilingWater)
               uiAwaitLaneForBoilingWater();
           else if(state instanceof AwaitExtraPointRallyTroops)
               uiAwaitExtraPointRallyTroops();
           else if(state instanceof AwaitMoreActionPoints)
               uiAwaitMoreActionPoints();
           else if(state instanceof AwaitTypeMovement)
               uiAwaitTypeMovement();
           else if(state instanceof AwaitEndGame)
               uiAwaitEndGame();
           
        }
        
    }
    
    private void SaveAndQuit(String fileName) throws IOException, NotSerializableException{
        ObjectOutputStream out = null;
        
        try{
            out = new ObjectOutputStream(new FileOutputStream(fileName));
            out.writeUnshared(game);
        }finally{
            if(out != null)
                out.close();
            
        }
    }
    
    
    private CardSiege resumeGameFromFile(String fileName) throws IOException, ClassNotFoundException{
        ObjectInputStream in = null;
        CardSiege newCardSiegeGame = null;
        
        try{
            in = new ObjectInputStream (new FileInputStream(fileName));
            //Creates the new game object
            newCardSiegeGame = (CardSiege)in.readObject();
            return newCardSiegeGame;
        }finally{
            if(in != null)
                in.close();
        }
    }
}
