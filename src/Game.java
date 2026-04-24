import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Game {
  
  public void gameLoop(Board board){
    
    Scanner scan = new Scanner(System.in);
    
    // Determine active and passive players
    Player activePlayer = board.getActivePlayer();
    Player passivePlayer = board.getPassivePlayer();
    
    // Display status of current board
    Interact.log("It is currently turn " + board.getTurnNumber() + ", and the active player is player " + activePlayer.getDisplayName() + ".");
    Interact.log("The boardstate is as follows:");
    board.printBoardState();
    
    // Check for on turn start triggers
    for (ArrayList<Piece>[] arr : board.getBoardstate()){
      for (ArrayList<Piece> arrl : arr){
        for (Piece piece : arrl){
          piece.onTurnStart();
        }
      }
    }
    // Begin giving the activePlayer agency.
    activePlayer.setActions(1);
    boolean done = false;
    while (!done){
      	
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
