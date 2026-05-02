package actions;

import core.*;
import pieces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class KingMove extends Action{
    public KingMove(Piece owner){
        super(owner);
        this.setType("move");
        this.setActionId("king_move");
        // set display name once translation is done better.
    }   

    public boolean onUse(){
        int[] loc = UI.requestLocation("Select a place to move to.", this.getOwnerPiece().getControllerObj());
        Board gameboard = this.getOwnerPiece().getBoard();
        int[] myLoc = this.getOwnerPiece().getLocation();
        if (Utility.checkMoveValidity(gameboard, myLoc, loc, Utility.formVector("1,1"), true, true,  false)){
            getOwnerPiece().movePiece(loc);
            return true;
        }
        else if (Utility.checkMoveValidity(gameboard, myLoc, loc, Utility.formVector("0,1"), true, true,  false)){
            getOwnerPiece().movePiece(loc);
            return true;
        }
        return false;
    }

}
