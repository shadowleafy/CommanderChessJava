package pieces;

import core.*;
import actions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Rook extends Piece{
    
    public Rook(int ctrl, int[] loc, Board board){
        super(ctrl, loc, board);
        setPieceId("rook");
        setDisplayName(Translation.getStatic("rook_display"));
        Action rookCapture = new RookCapture(this);
        Action rookMove = new RookMove(this);
        Action[] arr = {rookCapture, rookMove};
        setActions(Utility.arrayActionToArrayList(arr));
    }  

}
