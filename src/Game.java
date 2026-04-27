import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Game {
  
  public void gameLoop(Board board, UI ui){
    
    Scanner scan = new Scanner(System.in);
    
    // Determine active and passive players
    Player activePlayer = board.getActivePlayer();
    Player passivePlayer = board.getPassivePlayer();
    
    // Display status of current board
    ui.log("Turn start!")
    ui.log("It is currently turn " + board.getTurnNumber() + ", and the active player is player " + activePlayer.getDisplayName() + ".");
    board.printBoardState();
    ui.updateBoard();
    
    // Check for on turn start triggers
    for (ArrayList<Piece>[] arr : board.getBoardstate()){
      for (ArrayList<Piece> arrl : arr){
        for (Piece piece : arrl){
          piece.onTurnStart();
          ui.updateBoard();
        }
      }
    }
    // Begin giving the activePlayer agency.
    activePlayer.setActions(1);
    boolean done = false;
    while (!done){
      	Action requestedAction = UI.requestAction(board, activePlayer);
        if (requestedAction.getActionId.equals("end_turn")){
            done == true;
        }
        else if (requestedAction.canUse()){
            // Check for on action use triggers
            
            for (ArrayList<Piece>[] arr : board.getBoardstate()){
                for (ArrayList<Piece> arrl : arr){
                    for (Piece piece : arrl){
                        piece.onActionUse(requestedAction);
                        ui.updateBoard();
                    }
                }
            }
            requestedAction.onUse();
        }
        else{
            ui.log("Sorry, " + activePlayer + ", this move is illegal. Please select a different move.")
        }
    }

    // Check for on turn end triggers
    for (ArrayList<Piece>[] arr : board.getBoardstate()){
      for (ArrayList<Piece> arrl : arr){
        for (Piece piece : arrl){
          piece.onTurnEnd();
          ui.updateBoard();
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
