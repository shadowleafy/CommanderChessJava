package actions;

import core.*;
import pieces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class BishopCapture extends Action {

    public BishopCapture(Piece owner){
        super(owner);
        this.setType("capture");
        this.setActionId("bishop_capture");
        // set display name once translation is done better.
    }   

    public boolean onUse(){
        int[] loc = UI.requestLocation("Select a piece to capture.", this.getOwnerPiece().getControllerObj());
        int defendingIndex = 0;
        if (this.getOwnerPiece().getBoard().getBoardstate()[loc[1]][loc[0]].size() > 1){
            defendingIndex = UI.requestNumber("Pick a piece to capture.", this.getOwnerPiece().getControllerObj(), 0, this.getOwnerPiece().getBoard().getBoardstate()[loc[1]][loc[0]].size()-1);
        }
        else if (this.getOwnerPiece().getBoard().getBoardstate()[loc[1]][loc[0]].size() == 0){
            return false;
        }
        Board gameboard = this.getOwnerPiece().getBoard();
       int[] myLoc = this.getOwnerPiece().getLocation();
        if (Utility.checkCaptureValidity(gameboard, myLoc, loc, getOwnerPiece().getIndex(), defendingIndex, Utility.formVector("1,1"), false, true,  true)){
            getOwnerPiece().capturePieceOn(loc, defendingIndex);
            return true;
        }
        return false;
    }
}
