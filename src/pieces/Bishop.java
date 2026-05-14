package pieces;

import core.*;
import actions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Bishop extends Piece{
    
    public Bishop(int ctrl, int[] loc, Board board){
        super(ctrl, loc, board);
        pieceId = "bishop";
        displayName = Translation.getStatic("bishop_display");
        Action capture = new BishopCapture(this);
        Action move = new BishopMove(this);
        Action[] arr = {capture, move};
        actions = Utility.arrayActionToArrayList(arr);

        // testing


    }  

}
