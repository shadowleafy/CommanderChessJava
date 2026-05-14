package actions;

import core.*;
import pieces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class QueenMove extends basicMovement {
    
    public QueenMove(Piece owner){
        super(owner);
        this.setType("move");
        this.setActionId("queen_move");
        setDisplayName(Translation.getStatic("queen_move_display"));
        setDescription(Translation.getStatic("queen_move_description"));
        // set display name once translation is done better.
    }

    public boolean condition(int[] start, int[] end, int[] delta) {
        if (delta[0] == 0 && delta[1] == 0){
            return false;
        }
        if (Math.abs(delta[0]) == Math.abs(delta[1])){
            int[] unitDelta = {delta[0] / Math.abs(delta[0]), delta[1] / Math.abs(delta[1])};
            int[] checkPoint = Utility.copyArray(start);
            while (!Utility.compareVectors(checkPoint, end)){
                checkPoint = Utility.sumVectors(checkPoint, unitDelta);
                if (!ownerPiece.getBoard().getPiecesOn(checkPoint).isEmpty()){
                    return false;
                }
            }
            return true;
        }
        else if (delta[0] == 0 || delta[1] == 0){
            int[] unitDelta;
            if (delta[0] == 0) {
                unitDelta = new int[]{0, delta[1] / Math.abs(delta[1])};
            }
            else{
                unitDelta = new int[]{delta[0] / Math.abs(delta[0]), 0};
            }
            int[] checkPoint = Utility.copyArray(start);
            while (!Utility.compareVectors(checkPoint, end)){
                checkPoint = Utility.sumVectors(checkPoint, unitDelta);
                if (!ownerPiece.getBoard().getPiecesOn(checkPoint).isEmpty()){
                    return false;
                }
            }
            return true;
        }
        else{
            return false;
        }
    }
}