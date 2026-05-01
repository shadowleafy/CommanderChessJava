package core;

import actions.*;
import pieces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Board {
  
  private ArrayList<Piece>[][] boardstate;
  private int activePlayer;
  private int turnNumber;
  private Player white;
  private Player black;
  private Map<String, Integer> globalVariables;
  private Game game;
  
  public Board(int x, int y, Player whitePlayer, Player blackPlayer, Game game){
    boardstate = new ArrayList[y][x];
    globalVariables = new HashMap<String, Integer>();
    for (int i=0; i < y; i++){
      for (int j=0; j < x; j++){
        boardstate[y][x] = new ArrayList<Piece>();
      }
    }
    activePlayer = 0; // White goes first
    white = whitePlayer;
    black = blackPlayer;
    turnNumber = 0;
    // Put the pieces onto the board.
    for (int i = 0; i < y; i++){
      for (int j = 0; j < x; j++){
        boardstate[y][x].add(white.getPieces()[white.getStartingSetup()[y][x]]);
        boardstate[7-y][x].add(black.getPieces()[black.getStartingSetup()[y][x]]);
      }
    }
    this.game = game;
  }

  // This one is for temporary Board objects!

  public Board(int x, int y, Player whitePlayer, Player blackPlayer, Game game, ArrayList<Piece>[][] boardstate){
    boardstate = new ArrayList[y][x];
    globalVariables = new HashMap<String, Integer>();
    for (int i=0; i < y; i++){
      for (int j=0; j < x; j++){
        boardstate[y][x] = new ArrayList<Piece>();
      }
    }
    activePlayer = 0; // White goes first
    white = whitePlayer;
    black = blackPlayer;
    turnNumber = 0;
    // Put the pieces onto the board.
    this.boardstate = boardstate;
    this.game = game;
  }
  
  public int getTurnNumber(){
    return turnNumber;
  }
  
  public Player getPlayerObject(int color){
    if (color == 0){
      return white;
    }
    return black;
  }
  
  public Player getActivePlayer(){
    return getPlayerObject(activePlayer);
  }
  
  public Player getPassivePlayer(){
    return getPlayerObject(1-activePlayer);
  }

  public Player getWhitePlayer(){
    return white;
  }

  public Player getBlackPlayer(){
    return black;
  }
  
  public ArrayList<Piece>[][] getBoardstate(){
    return boardstate;
  }

  public Game getGame(){
    return game;
  }

  public int getGlobalVariable(String key){
    if (globalVariables.containsKey(key)){
      return globalVariables.get(key);
    }
    return 0;
  }

  public ArrayList<Piece> getSquareState(int x, int y){
    return boardstate[y][x];
  }
  
  public void printBoardState(){
    String[][] output = new String[boardstate.length+4][boardstate[0].length+4];
    for (int i = 0; i < output[output.length-1].length; i++){
      output[0][i] = Constant.LETTERS[i];
      output[output.length-1][i] = Constant.LETTERS[i];
      output[1][i] = "-";
      output[output.length-2][i] = "-";
    }
    for (int i = 0; i < output.length; i++){
      output[i][0] = "" + (i+1);
      output[i][output[0].length-1] = "" + (i+1);
      output[i][1] = "|";
      output[i][output[0].length-2] = "|";
    }
    for (int i = 0; i < boardstate.length; i++){
      for (int j = 0; j < boardstate[0].length; j++){
        if (boardstate[i][j].size() == 0){
          output[i+2][j+2] = ".";
        }
        else if (boardstate[i][j].size() == 1){
          output[i+2][j+2] = boardstate[i][j].get(0).getAbbreviation();
        }
        else{
          output[i+2][j+2] = "" + boardstate[i][j].size();
        }
      }
    }
    System.out.println(Utility.toString2DArray(output));
  }
  
  public void checkStates(){
    for (int i = 0; i < boardstate.length; i++){
      for (int j = 0; j < boardstate[i].length; j++){
        for (int k = 0; k < boardstate[i][j].size(); k++){
          Piece targetPiece = boardstate[i][j].get(k);
          // Am I dead?
          if (Utility.inArrayList(targetPiece.getTags(), "dead") && !Utility.inArrayList(targetPiece.getTags(), "indestructible")){
            boardstate[i][j].remove(k);
            targetPiece.onDeath();
          }
        }
      }
    }
  }

  public void setGlobalVariable(String key, int val){
    globalVariables.put(key, val);
  }

  public void updateDataOnSquare(int[] loc){
    for (int i = 0; i < boardstate[loc[1]][loc[0]].size(); i++){
      Piece targetPiece = boardstate[loc[1]][loc[0]].get(i);
      targetPiece.setIndex(i);
      targetPiece.setLocation(loc);
    }
  }

  public void updateAllData(){

  }
  
}
