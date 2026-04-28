import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Action {
  
  private String type;
  private String actionId;
  private String displayName;
  private Piece ownerPiece;
  private ArrayList<String> tags;
  
  public Action(Piece owner){
    tags = new ArrayList<String>();
    ownerPiece = owner;
  }
  
  public String getType(){
    return type;
  }
  
  public String getActionId(){
    return actionId;
  }
  
  public String getDisplayName(){
    return displayName;
  }

  public Piece getOwnerPiece(){
    return ownerPiece;
  }

  public void setOwnerPiece(Piece piece){
    ownerPiece = piece;
  }

  public void addTag(String tag){
    Utility.noRepeatAdd(tags, tag);
  }

  public void removeTag(String tag){
    tags.remove(tag);
  }
  
  public boolean canUse(){
    return true;
  }
  
  public boolean onUse(){} // return true if the move resolved, otherwise return false.
  
}

// This specific action ends your turn. Please make it available when no piece is selected.
class Done extends Action {
    
    public Done(Piece owner){
      super(owner);
      type = "menu";
      actionId = "end_turn";
      displayName = null;
    }
}

// Now for the real actions! :3

class PawnMove extends Action {
  public PawnMove(Piece owner){
    super(owner);
    type = "move";
    actionId = "pawn_move";
    displayName = "Pawn Move";
  }

  public boolean canUse(){
    if (ownerPiece.getControllerObj().getActions() > 0){
      return true;
    }
    else{
      return false;
    }
  }

  public boolean onUse(){
    int[] loc = UI.requestLocation();
    Board gameboard = ownerPiece.getBoard();
    int[] myLoc = ownerPiece.getLocation();
    if (Utility.collisionPoint(gameboard, myLoc, Utility.diffVector(loc, myLoc)) == null){
      if (compareVectors(Utility.diffVector(loc, myLoc), Utility.formVector("1,0"))){
        gameboard.movePiece(myLoc, loc, ownerPiece.getIndex())
        return true;
      }
      if (compareVectors(Utility.diffVector(loc, myLoc), Utility.formVector("2,0"))){
        if ((myLoc[1] <= 1 && ownerPiece.getController() == 0) || (myLoc[1] >= 6 && ownerPiece.getController() == 0)){
          gameboard.movePiece(myLoc, loc, ownerPiece.getIndex())
          return true;
        }
      }
      return false;
    }
    return false;
  }
}

