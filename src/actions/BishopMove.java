package actions;

import core.*;
import pieces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BishopMove extends Action {

    public BishopMove(Piece owner){
        super(owner);
        this.setType("move");
        this.setActionId("bishop_move");
        // set display name once translation is done better.
    }   

    public boolean onUse(){
        int[] loc = UI.requestLocation("Select a place to move to.", this.getOwnerPiece().getControllerObj());
        Board gameboard = this.getOwnerPiece().getBoard();
        int[] myLoc = this.getOwnerPiece().getLocation();
        if (Utility.checkMoveValidity(gameboard, myLoc, loc, Utility.formVector("1,1"), false, true,  true)){
            getOwnerPiece().movePiece(loc);
            return true;
        }
        return false;
    }

}
