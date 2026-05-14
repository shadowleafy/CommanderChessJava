package pieces;

import core.*;
import actions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Pawn extends Piece{
    
    public Pawn(int ctrl, int[] loc, Board board){
        super(ctrl, loc, board);
        pieceId = "pawn";
        displayName = Translation.getStatic("pawn_display");
        Action capture = new PawnCapture(this);
        Action move = new PawnMove(this);
        Action[] arr = {capture, move};
        actions = Utility.arrayActionToArrayList(arr);
    }  

}
