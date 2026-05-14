package actions;

import core.*;
import pieces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class PawnMove extends basicMovement {
    
    public PawnMove(Piece owner){
        super(owner);
        this.setType("move");
        this.setActionId("pawn_move");
        setDisplayName(Translation.getStatic("pawn_move_display"));
        setDescription(Translation.getStatic("pawn_move_description"));
        // set display name once translation is done better.
    }

    public boolean condition(int[] start, int[] end, int[] delta) {
        int color = ownerPiece.getController();
        start = Utility.colorshift(start, color);
        delta = Utility.colorshiftDelta(delta, color);

        // Are we on the back two rows?
        if (start[1] <= 1){
            if (delta[0] != 0){
                return false;
            }
            else{
                UI.log("We got here!");
                if (delta[1] == 1){
                    return (ownerPiece.getBoard().getPiecesOn(end).isEmpty());
                }
                else if (delta[1] == 2){
                    UI.log("delta is {" + delta[0] + ", " + delta[1] + "}");
                    start = Utility.colorshift(start, color);
                    delta = Utility.colorshiftDelta(delta, color);
                    int[] interloper = {start[0], start[1]+delta[1]/2};
                    UI.log("interloper is {" + interloper[0] + ", " + interloper[1] + "}");
                    return (ownerPiece.getBoard().getPiecesOn(end).isEmpty() && ownerPiece.getBoard().getPiecesOn(interloper).isEmpty());
                }
                return false;
            }
        }
        return ((delta[0] == 0) && (delta[1] == 1) && ownerPiece.getBoard().getPiecesOn(end).isEmpty());

    }
}