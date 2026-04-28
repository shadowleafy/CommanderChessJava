import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Game {
  
  public void gameLoop(Board board){
    // Determine active and passive players
    Player activePlayer = board.getActivePlayer();
    Player passivePlayer = board.getPassivePlayer();
    
    // Display status of current board
    UI.log("Turn start!");
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
    
  }
  
  public void beginGame(Board board){
    
  }
  public static void main(String[] args) {
    System.out.println("Hello Codiva");
    
  }
}
