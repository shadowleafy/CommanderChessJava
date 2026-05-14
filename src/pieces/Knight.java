package pieces;

import core.*;
import actions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Knight extends Piece{
    
    public Knight(int ctrl, int[] loc, Board board){
        super(ctrl, loc, board);
        pieceId = "knight";
        displayName = Translation.getStatic("knight_display");
        Action capture = new KnightCapture(this);
        Action move = new KnightMove(this);
        Action[] arr = {capture, move};
        actions = Utility.arrayActionToArrayList(arr);
    }  

}
