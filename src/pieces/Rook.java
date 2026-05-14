package pieces;

import core.*;
import actions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Rook extends Piece{
    
    public Rook(int ctrl, int[] loc, Board board){
        super(ctrl, loc, board);
        pieceId = "rook";
        displayName = Translation.getStatic("rook_display");
        Action capture = new RookCapture(this);
        Action move = new RookMove(this);
        Action[] arr = {capture, move};
        actions = Utility.arrayActionToArrayList(arr);
    }  

}
