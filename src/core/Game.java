package core;

import actions.*;
import pieces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Game {

  private Board board;
  private String nextMenu;
  
  public static Map<String, String> languageMap;

  public void setBoard(Board board){
    this.board = board;
    this.nextMenu = "main";
  }

  public Board getBoard(Board board){
    return board;
  }

  public String getNextMenu(){
    return nextMenu;
  }

  public void setNextMenu(String value){
    nextMenu = value;
  }
  
  public void gameLoop(){
    // Determine active and passive players
    Player activePlayer = board.getActivePlayer();
    Player passivePlayer = board.getPassivePlayer();
    
    // Display status of current board
    UI.log(languageMap.get("turn_start"));
    // for dynamic messages like this, ill have to do something more clever
    UI.log("It is currently turn " + board.getTurnNumber() + ", and the active player is player " + activePlayer.getDisplayName() + ".");
    board.printBoardState();
    UI.updateBoard(board);
    
    // Check for on turn start triggers
    for (ArrayList<Piece>[] arr : board.getBoardstate()){
      for (ArrayList<Piece> arrl : arr){
        for (Piece piece : arrl){
          piece.onTurnStart();
          UI.updateBoard(board);
        }
      }
    }
    // Begin giving the activePlayer agency.
    activePlayer.setActions(1);
    boolean done = false;
    while (!done){
      	Action requestedAction = UI.requestAction(board, activePlayer);
        if (requestedAction.getActionId().equals("end_turn")){
            done = true;
        }
        else if (requestedAction.canUse()){
            // Check for on action use triggers
            boolean success = requestedAction.onUse();
            if (success){
              for (ArrayList<Piece>[] arr : board.getBoardstate()){
                for (ArrayList<Piece> arrl : arr){
                  for (Piece piece : arrl){
                    piece.onActionUse(requestedAction);
                    UI.updateBoard(board);
                  }
                }
              }
            }
            
        }
        else{
            UI.log("Sorry, " + activePlayer + ", this move is illegal. Please select a different move.");
        }
    }

    // Check for on turn end triggers
    for (ArrayList<Piece>[] arr : board.getBoardstate()){
      for (ArrayList<Piece> arrl : arr){
        for (Piece piece : arrl){
          piece.onTurnEnd();
          UI.updateBoard(board);
        }
      }
    }
  }
  
  public void tutorial(){
    
  }
  
  public void characterSelection(){
    UI.generateCharacterSelectUI();
    UI.awaitPieceSelection("WHITE COMMANDER", 0, Constants.COMMANDER_IDS);
    UI.awaitPieceSelection("BLACK COMMANDER", 0, Constants.COMMANDER_IDS);
  }
  
  public void beginGame(){
    
  }

  public void declareWinner(int winner){
    // somehow stop all thre threads, implement later.
    String winnerName;
    winnerName = board.getPlayerObject(winner).getDisplayName();
    UI.popupMessage("The game has ended. " + winnerName + " has won! Congratulations!");
    nextMenu = "main";
  }
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
