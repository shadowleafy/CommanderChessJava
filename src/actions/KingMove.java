package actions;

import core.*;
import pieces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class KingMove extends Action {
    
    public KingMove(Piece owner){
        super(owner);
        this.setType("move");
        this.setActionId("king_move");
        setDisplayName(Translation.getStatic("king_move_display"));
        setDescription(Translation.getStatic("king_move_description"));
        // set display name once translation is done better.
    }

    public boolean condition(int[] start, int[] end, int[] delta){
        if (Math.abs(delta[0]) <= 1 && Math.abs(delta[1]) <= 1 && !Utility.compareVectors(Utility.formVector("0,0"), delta)){
            if (ownerPiece.getBoard().getPiecesOn(end).size() == 0){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }


}