package actions;

import core.*;
import pieces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PawnMove extends Action {
  public PawnMove(Piece owner){
    super(owner);
    this.setType("move");
    this.setActionId("pawn_move");
    this.setDisplayName(Game.languageMap.get("pawn_move_display_name"));
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
    int[] loc = UI.requestLocation(this.getOwnerPiece().getControllerObj());
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