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

  public void setType(String t){
    type = t;
  }

  public void setActionId(String a){
    actionId = a;
  }

  public void setDisplayName(String n){
    displayName = n;
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
  
  public boolean onUse(){
    return true;
  } // return true if the move resolved, otherwise return false.
  
}

// This specific action ends your turn. Please make it available when no piece is selected.
class Done extends Action {
    
    public Done(Piece owner){
      super(owner);
      this.setType("menu");
      this.setActionId("end_turn");
      this.setDisplayName("End Turn");
    }
}

// Now for the real actions! :3

class PawnMove extends Action {
  public PawnMove(Piece owner){
    super(owner);
    this.setType("move");
    this.setActionId("pawn_move");
    this.setDisplayName("Pawn Move");
  }

  public boolean canUse(){
    if (this.getOwnerPiece().getControllerObj().getActions() > 0){
      return true;
    }
    else{
      return false;
    }
  }

  public boolean onUse(){
    int[] loc = UI.requestLocation(this.getOwnerPiece().getBoard(), this.getOwnerPiece().getControllerObj());
    Board gameboard = this.getOwnerPiece().getBoard();
    int[] myLoc = this.getOwnerPiece().getLocation();
    if (Utility.collisionPoint(gameboard, myLoc, Utility.diffVectors(loc, myLoc)) == null){
      if (Utility.compareVectors(Utility.diffVectors(loc, myLoc), Utility.formVector("1,0"))){
        gameboard.movePiece(myLoc, loc, this.getOwnerPiece().getIndex());
        return true;
      }
      if (Utility.compareVectors(Utility.diffVectors(loc, myLoc), Utility.formVector("2,0"))){
        if ((myLoc[1] <= 1 && this.getOwnerPiece().getController() == 0) || (myLoc[1] >= 6 && this.getOwnerPiece().getController() == 0)){
          gameboard.movePiece(myLoc, loc, this.getOwnerPiece().getIndex());
          return true;
        }
      }
      return false;
    }
    return false;
  }
}

