package pieces;

import core.*;
import actions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Queen extends Piece{
    
    public Queen(int ctrl, int[] loc, Board board){
        super(ctrl, loc, board);
        pieceId = "queen";
        displayName = Translation.getStatic("queen_display");
        Action capture = new QueenCapture(this);
        Action move = new QueenMove(this);
        Action[] arr = {capture, move};
        actions = Utility.arrayActionToArrayList(arr);
    }  

}
