import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Piece {
  
  private int controller; // 0 means white, 1 means black
  private Player controllerObj;
  private int health;
  private String pieceId;
  private String displayName;
  private String abbreviation;
  private ArrayList<Action> actions;
  private ArrayList<String> tags;
  private Map<String, Integer> counters;
  private int[] location;
  private int index;
  
  private Board gameboard;
 
  public Piece(int ctrl, int[] loc, Board board){
    controller = ctrl;
    controllerObj = board.getPlayerObject(controller);
    location = Utility.copyArray(loc);
    tags = new ArrayList<String>();
    counters = new HashMap<String, Integer>();
    gameboard = board;
    onEntry();
  }
  
  public int getController(){
    return controller;
  }

  public Player getControllerObj(){
    return controllerObj;
  }
  
  public String getPieceId(){
    return pieceId;
  }
  
  public String getDisplayName(){
    return displayName;
  }
  
  public String getAbbreviation(){
    return abbreviation;
  }
  
  public ArrayList<Action> getActions(){
    return actions;
  }
  
  public int getHealth(){
    return health;
  }
  
  public int getCounters(String id){
    for (String k : counters.keySet()){
      if (k.equals(id)){
        return counters.get(k);
      }
    }
    return 0;
  }
  
  public ArrayList<String> getTags(){
    return tags;
  }
  
  public int[] getLocation(){
    return location;
  }

  public Board getBoard(){
    return gameboard;
  }

  public int getIndex(){
    return index;
  }
  
  public void setController(int ctrl){
    controller = ctrl;
  }
  
  public void setPieceId(String id){
    pieceId = id;
  }
  
  public void setDisplayName(String name){
    displayName = name;
  }
  
  public void setActions(ArrayList<Action> a){
    actions = a;
  }
  
  public void setHealth(int h){
    health = h;
    if (health <= 0){
      onDeath();
    }
  }
  
  public void setLocation(int[] loc){
    location = loc;
  }

  public void setIndex(int input){
    index = input;
  }
  
  public void addHealth(int h){
    health += h;
    if (Utility.inArrayList(tags, "isCommander")){
      if (h > 0){
      	UI.log(displayName + " (on " + Utility.convertChessNotation(location) + ")'s health has increased by " + h + " to " + health + " !");
      }
      else if (h < 0){
        UI.log(displayName + " (on " + Utility.convertChessNotation(location) + ")'s health has decreased by " + (-h) + " to " + health + " !");
      }
      else{
        UI.log(displayName + " (on " + Utility.convertChessNotation(location) + ")'s health remained unchanged!");
      }
    }
    if (health <= 0){
      UI.log(displayName + " has died!");
      Utility.noRepeatAdd(tags, "dead");
    }
  }
  
  public void addTag(String tag){
    Utility.noRepeatAdd(tags, tag);
  }
  
  public void removeTag(String tag){
    tags.remove(tag);
  }
  
  // Generally, you'll want to check that the turn isn't 0. This is because turn 0 is reserved for original setup.
  public void onEntry(){}
  
  public void onTurnStart(){}
  
  public void onTurnEnd(){}

  public void onActionUse(Action action){}
  
  public void onMoveTo(int[] location){}
  
  public void onMoveFrom(int[] location){}
  
  public void onDeath(){}
  
  public void onCapturePiece(Piece p){}
  
  public void onCapturedBy(Piece p){}
  
  public void processCapturePiece(Piece p){
    UI.log(displayName + " has captured " + p.getDisplayName() + " on square " + Utility.convertChessNotation(location) + "!");
    Utility.noRepeatAdd(tags, "hasCapturedThisTurn");
  }
  
  public void processCapturedBy(Piece p){
    if (!Utility.inArrayList(tags, "shield")){
      addHealth(-1);
    }
    Utility.noRepeatAdd(tags, "beenCapturedThisTurn");
  }
}


