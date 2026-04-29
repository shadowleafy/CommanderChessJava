package core;

import actions.*;
import pieces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Player {
  
  private int color;
  private int actions;
  private String displayName;
  private Piece commander;
  private Piece[] pieceArray;
  private int[][] startingSetup;
  
  // Precondition: pieces.length == 6 (can >6 but additional indicies will be ignored)
  
  public Player(int c, String n, Piece[] pieces, int[][] customStartingSetup, Piece cmdr){
    color = c;
    actions = 0;
    displayName = n;
    pieceArray = pieces;
    // For custom starting setups, start from the bottom row and go up. Never go past four rows because otherwise my program won't like you.
    if (!(customStartingSetup == null)){
      startingSetup = customStartingSetup;
    }
    else{
      startingSetup = Constant.DEFAULT_BOARDSTATE;
    }
    commander = cmdr;
  }
  
  public int getColor(){
    return color;
  }
  
  public int getActions(){
    return actions;
  }
  
  public String getDisplayName(){
    return displayName;
  }
  
  public Piece[] getPieces(){
    return pieceArray;
  }
  
  public int[][] getStartingSetup(){
    return startingSetup;
  }

  public Piece getCommander(){
    return commander;
  }
  
  public void addActions(int num){
    actions += num;
  }
  
  public void setActions(int num){
    actions = num;
  }
  
  public void setName(String n){
    displayName = n;
  }
  
}
