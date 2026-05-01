package pieces;

import core.*;
import actions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Queen extends Piece{
    
    public Queen(int ctrl, int[] loc, Board board){
        super(ctrl, loc, board);
        setPieceId("queen");
        Action queenCapture = new QueenCapture(this);
        Action queenMove = new QueenMove(this);
        Action[] arr = {queenCapture, queenMove};
        setActions(Utility.arrayActionToArrayList(arr));
    }  

}
