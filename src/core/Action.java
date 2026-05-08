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
  private String description;
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

  public String getDescription(){
    return description;
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

  public void setDescription(String d){
    description = d;
  }

  public void addTag(String tag){
    Utility.noRepeatAdd(tags, tag);
  }

  public void removeTag(String tag){
    tags.remove(tag);
  }
  
  public boolean canUse(){
    return (getOwnerPiece().getControllerObj().getActions() > 0 && getOwnerPiece().getCounters("stun") == 0);
  }
  
  public void onUse(){
  } // call me!
  
  public void costUse(){

    getOwnerPiece().getControllerObj().addActions(-1);

  } // Cost to use. Is run after usage.

}




