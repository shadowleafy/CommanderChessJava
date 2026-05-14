package pieces;

import core.*;
import actions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Pawn extends Piece{
    
    public Pawn(int ctrl, int[] loc, Board board){
        super(ctrl, loc, board);
        setPieceId("pawn");
        setDisplayName(Translation.getStatic("pawn_display"));
        Action pawnCapture = new PawnCapture(this);
        Action pawnMove = new BishopMove(this);
        Action[] arr = {pawnCapture, pawnMove};
        setActions(Utility.arrayActionToArrayList(arr));
    }  

}
