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
  }

  public boolean onUse(){
    int[] loc = UI.requestLocation(this.getOwnerPiece().getControllerObj());
    Board gameboard = this.getOwnerPiece().getBoard();
    int[] myLoc = this.getOwnerPiece().getLocation();
    if (Utility.checkMoveValidity(gameboard, myLoc, loc, Utility.colorshiftVector(Utility.formVector("0,1"), getOwnerPiece().getController()), false, false, false)){
      getOwnerPiece().movePiece(loc);
      return true;
    }
    else if (Utility.colorshiftLocation(myLoc, getOwnerPiece().getController())[1] == 1){
      if (Utility.checkMoveValidity(gameboard, myLoc, loc, Utility.colorshiftVector(Utility.formVector("0,2"), getOwnerPiece().getController()), false, false, false)){
        getOwnerPiece().movePiece(loc);
        return true;
      }
    }
    return false;
  }
}