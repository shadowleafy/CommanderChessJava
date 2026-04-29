package core;

import actions.*;
import pieces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Game {

  private Board board;
  private String nextMenu;
  private boolean winnerDeclared;
  private int winner;
  
  public static Map<String, String> languageMap;

  public Game(){
    winnerDeclared = false;
    winner = 0;
  }

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
      	Action requestedAction = UI.requestAction(activePlayer);
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
    String whiteName = "";
    String blackName = "";
    String whiteCommanderId = "";
    String blackCommanderId = "";
    Piece[] whitePieces = null;
    Piece[] blackPieces = null;
    boolean done = false;
    while(!done){
      whiteCommanderId = UI.awaitPieceSelection("WHITE COMMANDER", 0, Constant.COMMANDER_IDS);
      whitePieces = new Piece[6];
      whitePieces[0] = Utility.idToPiece(whiteCommanderId);
      for (int i = 1; i < 6; i++){
        String[] possiblePieces = Constant.COMMANDER_PIECES.get(whiteCommanderId);
        if (possiblePieces.length == 1){
          whitePieces[i] = Utility.idToPiece(possiblePieces[0]);
        }
        else{
          // Specify the piece with Translation.java later.
          whitePieces[i] = Utility.idToPiece(UI.awaitPieceSelection("SELECT PIECE (WHITE)", 0, possiblePieces));
        }
      }  
      blackCommanderId = UI.awaitPieceSelection("BLACK COMMANDER", 0, Constant.COMMANDER_IDS);
      blackPieces = new Piece[6];
      blackPieces[0] = Utility.idToPiece(blackCommanderId);
      for (int i = 1; i < 6; i++){
        String[] possiblePieces = Constant.COMMANDER_PIECES.get(blackCommanderId);
        if (possiblePieces.length == 1){
          blackPieces[i] = Utility.idToPiece(possiblePieces[0]);
        }
        else{
          // Specify the piece with Translation.java later.
          blackPieces[i] = Utility.idToPiece(UI.awaitPieceSelection("SELECT PIECE (WHITE)", 0, possiblePieces));
        }
      }  

      whiteName = UI.awaitName(0);
      blackName = UI.awaitName(1);

      done = UI.awaitConfirmation("Ready to begin?");
    }
    // Okay, we have the players!

    Player whitePlayer = new Player(0, whiteName, whitePieces, Constant.SETUPS.get(whiteCommanderId), Utility.idToPiece(whiteCommanderId));
    Player blackPlayer = new Player(0, blackName, blackPieces, Constant.SETUPS.get(blackCommanderId), Utility.idToPiece(blackCommanderId));

    // Let's make the board!

    this.board = new Board(8, 8, whitePlayer, blackPlayer, this);
    beginGame();
  }
  
  public void beginGame(){
    UI.updateBoard(board);
    UI.generateGameUI();
    while (!winnerDeclared){
      gameLoop();
    }
  }

  public void declareWinner(int winner){
    winnerDeclared = true;
    this.winner = winner;
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
