package core;

import actions.*;
import pieces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Action {
  
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
      this.setDisplayName(Game.languageMap.get("end_turn_button"));
    }
}


