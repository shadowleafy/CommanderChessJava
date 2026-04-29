package actions;

import core.*;
import pieces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class QueenMove extends Action{

    public QueenMove(Piece owner){
        super(owner);
        this.setType("move");
        this.setActionId("queen_move");
        // set display name once translation is done better.
    }   

    public boolean onUse(){
        int[] loc = UI.requestLocation(this.getOwnerPiece().getControllerObj());
        Board gameboard = this.getOwnerPiece().getBoard();
        int[] myLoc = this.getOwnerPiece().getLocation();
        if (Utility.checkMoveValidity(gameboard, myLoc, loc, Utility.formVector("1,1"), false, true,  true)){
            getOwnerPiece().movePiece(loc);
            return true;
        }
        else if (Utility.checkMoveValidity(gameboard, myLoc, loc, Utility.formVector("1,0"), false, true,  true)){
            getOwnerPiece().movePiece(loc);
            return true;
        }
        return false;
    }

}
