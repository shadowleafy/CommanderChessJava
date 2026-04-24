import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Player {
  
  private int color;
  private int actions;
  private String displayName;
  private Piece[] pieceArray;
  private int[][] startingSetup;
  
  // Precondition: pieces.length == 6 (can >6 but additional indicies will be ignored)
  public Player(int c, String n, Piece[] pieces){
    color = c;
    actions = 0;
    displayName = n;
    pieceArray = pieces; // [Commander, Champion, Captain, Calvary, Support, Soldier] order.
    startingSetup = Constants.DEFAULT_BOARDSTATE;
  }
  
  public Player(int c, String n, Piece[] pieces, int[][] customStartingSetup){
    color = c;
    actions = 0;
    displayName = n;
    pieceArray = pieces;
    // For custom starting setups, start from the bottom row and go up. Never go past four rows because otherwise my program won't like you.
    startingSetup = customStartingSetup;
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