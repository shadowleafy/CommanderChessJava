package core;

import actions.*;
import pieces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Game {

  private static Board board;
  private static String nextMenu;
  private static boolean winnerDeclared;
  private static int winner;
  
  public static String language;

  public static void setBoard(Board b){
    board = b;
  }

  public static Board getBoard(Board board){
    return board;
  }

  public static String getNextMenu(){
    return nextMenu;
  }

  public static void setNextMenu(String value){
    nextMenu = value;
  }
  
  public static void beginGame(String[] whitePieces, String[] blackPieces, String whiteName, String blackName){
    Player whitePlayer = new Player(0, whiteName, whitePieces[0]);
    Player blackPlayer = new Player(1, blackName, blackPieces[0]);
    board = new Board(whitePlayer, blackPlayer);
  }

  public static void passTurn(){
    for (ArrayList<Piece>[] arr : board.getBoardstate()){
      for (ArrayList<Piece> arrp : arr){
        for (Piece p : arrp){
          p.onTurnEnd();
          UI.updateBoard(board);
        }
      }
    }
    board.switchActivePlayer();
    UI.updateBoard(board);
    for (ArrayList<Piece>[] arr : board.getBoardstate()){
      for (ArrayList<Piece> arrp : arr){
        for (Piece p : arrp){
          p.onTurnStart();
          UI.updateBoard(board);
        }
      }
    }
  }

  public static void declareWinner(int winner){
    UI.generateMainMenu();
  }

  public static void finishAction(Action a){
        a.costUse();
        UI.stepsDone = 0;
        UI.selectedSquares.clear();
        UI.selectedPieces.clear();
        UI.selectedAction = null;
        for (ArrayList<Piece>[] arr : board.getBoardstate()){
          for (ArrayList<Piece> arrp : arr){
            for (Piece p : arrp){
              p.onActionUse(a);
              UI.updateBoard(board);
            }
          }
        }
    }

  public static void main(String[] args) {
    UI.start();
  }
}
