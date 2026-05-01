package actions;

import core.*;
import pieces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class QueenCapture extends Action {

    public QueenCapture(Piece owner){
        super(owner);
        this.setType("capture");
        this.setActionId("queen_capture");
        // set display name once translation is done better.
    }   

    public boolean onUse(){
        int[] loc = UI.requestLocation(this.getOwnerPiece().getControllerObj());
        int defendingIndex = UI.requestNumber(this.getOwnerPiece().getControllerObj(), 0, this.getOwnerPiece().getBoard().getBoardstate()[loc[1]][loc[0]].size()-1);
        Board gameboard = this.getOwnerPiece().getBoard();
        int[] myLoc = this.getOwnerPiece().getLocation();
        if (Utility.checkCaptureValidity(gameboard, myLoc, loc, getOwnerPiece().getIndex(), defendingIndex, Utility.formVector("1,0"), false, true,  true)){
            getOwnerPiece().capturePieceOn(loc, defendingIndex);
            return true;
        }
        if (Utility.checkCaptureValidity(gameboard, myLoc, loc, getOwnerPiece().getIndex(), defendingIndex, Utility.formVector("1,1"), false, true,  true)){
            getOwnerPiece().capturePieceOn(loc, defendingIndex);
            return true;
        }
        return false;
    }
}
