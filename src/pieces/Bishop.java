package pieces;

import core.*;
import actions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Bishop extends Piece{
    
    public Bishop(int ctrl, int[] loc, Board board){
        super(ctrl, loc, board);
        setPieceId("Bishop");
        Action bishopCapture = new BishopCapture(this);
        Action bishopMove = new BishopMove(this);
        Action[] arr = {bishopCapture, bishopMove};
        setActions(Utility.arrayActionToArrayList(arr));
    }  

}
