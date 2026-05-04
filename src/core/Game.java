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
  
  public static void passTurn(){
    
  }

  public static void confirm(){}

  public static void main(String[] args) {
    Game game = new Game();
    UI.start();
    while(!game.getNextMenu().equals("quit")){
      if (game.getNextMenu().equals("main")){
        UI.generateMainMenu();
      }
      else if (game.getNextMenu().equals("characterSelection")){
        game.characterSelection();
      }
      else if (game.getNextMenu().equals("tutorial")){
        game.tutorial();
      }
      game.setNextMenu(UI.awaitMenuSelection());
    }
    System.exit(0);

  }
}
