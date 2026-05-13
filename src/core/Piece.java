package core;

import actions.*;
import pieces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Piece {

  private int controller; // 0 means white, 1 means black
  private Player controllerObj;
  private String type;
  private String pieceId;
  private String displayName;
  private String abbreviation;
  private ArrayList<Action> actions;
  private ArrayList<String> tags;
  private Map<String, Integer> counters;
  private int[] location;
  private int index;
  private String[] iconReference; // [white, black]
  private boolean selected;

  // for commanders only but java gets mad at me if i dont do it like this
  private String[] pieceIds;
  
  private Board gameboard;
 
  public Piece(int ctrl, int[] loc, Board board){
    selected = false;
    controller = ctrl;
    if (board != null) {
      controllerObj = board.getPlayerObject(controller);
    }
    location = Utility.copyArray(loc);
    tags = new ArrayList<String>();
    counters = new HashMap<String, Integer>();
    gameboard = board;
    onEntry();
  }

  public String getIconLocation(){
    // return iconReference[controller];
    return "/pixelarts/kaimana-azuretidalforce.png";
  }
  
  // i have changed the code. will this update?
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

  public String getType(){
    return type;
  }

  public String[] getIconReference(){
    return iconReference;
  }

  public boolean getSelected() {
    return selected;
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
  
  public void setLocation(int[] loc){
    location = loc;
  }

  public void setIndex(int input){
    index = input;
  }

  public void setType(String s){
    type = s;
  }

  public void addCounters(String type, int count){
    counters.put(type, counters.get(type)+count);
  }

  public void setCounters(String type, int count){
    counters.put(type, count);
  }

  public void setIcon(String[] i){
    iconReference = i;
  }

  public void setSelected(boolean v){
    selected = v;
  }

  public void updateControllerObj(){
    if (gameboard != null){
      controllerObj = gameboard.getPlayerObject(controller);
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
    addTag("hasCapturedThisTurn");
  }
  
  public void processCapturedBy(Piece p){
    addTag("beenCapturedThisTurn");
    die();
  }

  public void movePiece(int[] end){
    ArrayList<Piece>[][] boardstate = getBoard().getBoardstate();
    int[] start = getLocation();
    boardstate[end[1]][end[0]].add(this);
    boardstate[start[1]][start[0]].remove(index);
    getBoard().updateDataOnSquare(start);
    getBoard().updateDataOnSquare(end);
    onMoveFrom(start);
    onMoveTo(end);
  }

  public void capturePiece(Piece defendingPiece){
    processCapturePiece(defendingPiece);
    defendingPiece.processCapturedBy(this);
    onCapturePiece(defendingPiece);
    defendingPiece.onCapturedBy(this);
  }

  public void die(){
    addTag("dead");
  }

  public String[] getPieceIds(){
    return pieceIds;
  }

}


