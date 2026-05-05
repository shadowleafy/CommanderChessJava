package pieces;

import core.*;
import actions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Knight extends Piece{
    
    public Knight(int ctrl, int[] loc, Board board){
        super(ctrl, loc, board);
        setPieceId("knight");
        Action knightCapture = new KnightCapture(this);
        Action knightMove = new KnightCapture(this);
        Action[] arr = {knightCapture, knightMove};
        setActions(Utility.arrayActionToArrayList(arr));
    }  

}
