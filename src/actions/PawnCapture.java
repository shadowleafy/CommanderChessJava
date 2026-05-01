package actions;

import core.*;
import pieces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


// Note: En Passant is not coded. If you want en passant, you'll have to code it yourself.

public class PawnCapture extends Action {

    public PawnCapture(Piece owner){
        super(owner);
        this.setType("capture");
        this.setActionId("pawn_capture");
        // set display name once translation is done better.
    }   

    public boolean onUse(){
        int[] loc = UI.requestLocation(this.getOwnerPiece().getControllerObj());
        int defendingIndex = UI.requestNumber(this.getOwnerPiece().getControllerObj(), 0, this.getOwnerPiece().getBoard().getBoardstate()[loc[1]][loc[0]].size()-1);
        Board gameboard = this.getOwnerPiece().getBoard();
        int[] myLoc = this.getOwnerPiece().getLocation();
        if (Utility.checkCaptureValidity(gameboard, myLoc, loc, getOwnerPiece().getIndex(), defendingIndex, Utility.colorshiftVector(Utility.formVector("1,1"), getOwnerPiece().getController()), false, false,  false)){
            getOwnerPiece().capturePieceOn(loc, defendingIndex);
            return true;
        }
        if (Utility.checkCaptureValidity(gameboard, myLoc, loc, getOwnerPiece().getIndex(), defendingIndex, Utility.colorshiftVector(Utility.formVector("-1,1"), getOwnerPiece().getController()), false, false,  false)){
            getOwnerPiece().capturePieceOn(loc, defendingIndex);
            return true;
        }
        return false;
    }
}