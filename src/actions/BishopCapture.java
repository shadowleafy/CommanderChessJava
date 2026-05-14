package actions;

import core.*;
import pieces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class BishopCapture extends basicCapture {
    
    public BishopCapture(Piece owner){
        super(owner);
        this.setType("capture");
        this.setActionId("bishop_capture");
        setDisplayName(Translation.getStatic("bishop_capture_display"));
        setDescription(Translation.getStatic("bishop_capture_description"));
        // set display name once translation is done better.
    }

    public boolean condition(int[] start, int[] end, int[] delta){
        if (delta[0] == 0 || delta[1] == 0){
            return false;
        }
        int[] unitDelta = {(delta[0] / Math.abs(delta[0])), (delta[1] / Math.abs(delta[1]))};
        if (Math.abs(delta[0]) == Math.abs(delta[1])){
            int[] checkPoint = Utility.copyArray(start);
            while (!Utility.compareVectors(checkPoint, end)){
                checkPoint = Utility.sumVectors(checkPoint, unitDelta);
                if (!ownerPiece.getBoard().getPiecesOn(checkPoint).isEmpty() && !Utility.compareVectors(checkPoint, end)){
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