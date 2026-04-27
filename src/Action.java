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
class Done extends Action{
    
    public Done(Piece owner){
      super(owner);
      type = "menu";
      actionId = "end_turn";
      displayName = null;
    }
}

// Now for the real actions! :3

class PawnMove extends Action{
  public PawnMove(Piece owner){
    super(owner);
    type = "move";
    actionId = "pawn_move";
    displayName = "Pawn Move";
  }

  public boolean canUse(){
    
  }
}

// miku miku oo ee oo