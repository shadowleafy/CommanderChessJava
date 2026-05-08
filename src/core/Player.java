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
  private String[] pieceIdArray;
  private int[][] startingSetup;
  
  // Precondition: pieces.length == 6 (can >6 but additional indicies will be ignored)
  
  public Player(int c, String n, String[] pieceIds){
    color = c;
    displayName = n;
    int[] vector = new int[2];
    if (c == 0){
      vector = Utility.formVector("0,4");
    }
    else{
      vector = Utility.formVector("7,4");
    } // note, because im hardcoding this we wont be able to move where the commander is :(
    commander = Utility.idToPiece(pieceIds[0], c, vector, null);
    pieceIdArray = Utility.copyArray(pieceIds);
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
  
  public String[] getPieceIds(){
    return pieceIdArray;
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
