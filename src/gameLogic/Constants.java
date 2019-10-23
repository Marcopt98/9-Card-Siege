/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameLogic;

/**
 *
 * @author marcoduarte
 */
public interface Constants {
    public static final int MIN_LIST = 0;
    public static final int MAX_LIST = 7;
    public static final int MAX_ROLL = 6;
    public static final int MIN_ROLL = 1;
    public static final int MAX_LANE = 4;
    public static final int MIN_LANE = 0;
    
    public static final String LADDERS = "ladders";
    public static final String BATTERING_RAM = "batteringRam";
    public static final String SIEGE_TOWER = "siegeTower";
    public static final String TREBUCHET = "trebuchet";
    
    public static final int ARCHERS_ATTACK = 1;
    public static final int BOILING_WATER_ATTACK = 2;
    public static final int CLOSE_COMBAT = 3;
    public static final int COUPURE = 4;
    public static final int RAISE_MORAL = 5;
    public static final int TUNNEL_MOVEMENT = 6;
    public static final int SUPPLYRAID = 7;
    public static final int SABOTAGE = 8;
    public static final int EXTRA_ACTION_POINT = 9;
    
    public static final int DIM_X_FRAME = 1280;
    public static final int DIM_Y_FRAME = 768;
    
    public static final int DIM_X_TRACKS_CARD = 300;
    public static final int DIM_Y_TRACKS_CARD = (DIM_Y_FRAME/2) - 30;
    
    public static final int DIM_X_EAST_PANEL = 300;
    public static final int DIM_Y_EAST_PANEL = DIM_Y_FRAME;
    
    
    public static final int PAWN_SIZE_X_RESOURCES = 50;
    public static final int PAWN_SIZE_Y_RESOURCES = 45;
    
    public static final int PAWN_SIZE_X_TUNNEL = 45;
    public static final int PAWN_SIZE_Y_TUNNEL = 40;
    
    //Constants for Status Track Card Panel
    public static final int POS_X_WALL_STRENGTH = 15;
    public static final int POS4_WALL_STRENGTH = 8;
    public static final int POS3_WALL_STRENGTH = 68;
    public static final int POS2_WALL_STRENGTH = 126;
    public static final int POS1_WALL_STRENGTH = 184;
    
    public static final int POS_X_MORALE = 111;
    public static final int POS4_MORALE = 8;
    public static final int POS3_MORALE = 68;
    public static final int POS2_MORALE = 126;
    public static final int POS1_MORALE = 184;
    
    public static final int POS_X_SUPPLIES = 208;
    public static final int POS4_SUPPLIES = 8;
    public static final int POS3_SUPPLIES = 68;
    public static final int POS2_SUPPLIES = 126;
    public static final int POS1_SUPPLIES = 184;
    
    public static final int POS_Y_TUNNEL = 306;
    public static final int CASTLE_TUNNEL = 7;
    public static final int POS1_TUNNEL = 53;
    public static final int POS2_TUNNEL = 102;
    public static final int ENEMY_LINE_TUNNEL = 152;
    
    public static final int POS_X_RAID_SUPPLIES = 216;
    public static final int POS1_RAID_SUPPLIES = 304;
    public static final int POS2_RAID_SUPPLIES = 261;
    
    public static final int POS_X_SURRENDER = 111;
    public static final int POS_Y_SURRENDER = 245;
    
    //Contants for Enemy Tracks Card Panel
    public static final int POS_X_LADDERS = 19;
    public static final int POS1_LADDERS = 59;
    public static final int POS2_LADDERS = 116;
    public static final int POS3_LADDERS = 175;
    public static final int POS4_LADDERS = 234;
    
    public static final int POS_X_BATTERING_RAM = 121;
    public static final int POS1_BATTERING_RAM = 59;
    public static final int POS2_BATTERING_RAM = 116;
    public static final int POS3_BATTERING_RAM = 175;
    public static final int POS4_BATTERING_RAM = 234;
    
    public static final int POS_X_SIEGE_TOWER = 223;
    public static final int POS1_SIEGE_TOWER = 59;
    public static final int POS2_SIEGE_TOWER = 116;
    public static final int POS3_SIEGE_TOWER = 175;
    public static final int POS4_SIEGE_TOWER = 234;
    
    public static final int POS_Y_TREBUCHET = 303;
    public static final int THREE_TREBUCHET = 223;
    public static final int TWO_TREBUCHET = 121;
    public static final int ONE_TREBUCHET = 19;
    
    public static final int POS_Y_CLOSE_COMBAT = 5;
    public static final int POS1_CLOSE_COMBAT = 95;
    public static final int POS2_CLOSE_COMBAT = 147;

}
