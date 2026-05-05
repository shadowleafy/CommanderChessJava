package core;

import actions.*;
import pieces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CommanderPiece extends Piece {

  private int health;

  public CommanderPiece(int ctrl, int[] loc, Board board){
    super(ctrl, loc, board);
  }

  public int getHealth(){ 
    return health;
  }

  public void setHealth(int h){
    health = h;
    if (health <= 0){
      onDeath();
    }
  }

  public void addHealth(int h){
    health += h;
    if (h > 0){
      	UI.log(getDisplayName() + " (on " + Utility.convertChessNotation(getLocation()) + ")'s health has increased by " + h + " to " + health + " !");
    }
    else if (h < 0){
        UI.log(getDisplayName() + " (on " + Utility.convertChessNotation(getLocation()) + ")'s health has decreased by " + (-h) + " to " + health + " !");
    }
    else{
        UI.log(getDisplayName() + " (on " + Utility.convertChessNotation(getLocation()) + ")'s health remained unchanged!");
    }
    if (health <= 0){
      UI.log(getDisplayName() + " has died!");
      Utility.noRepeatAdd(getTags(), "dead");
    }
  }

  public void onDeath(){
    Game.declareWinner(1-getController());
  }

  public void processCapturedBy(Piece p){
    addHealth(-1);
    Utility.noRepeatAdd(getTags(), "beenCapturedThisTurn");
  }
}
