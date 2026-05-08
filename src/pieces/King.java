package pieces;

import core.*;
import actions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class King extends CommanderPiece {
    
    public King(int ctrl, int[] loc, Board board){
        super(ctrl, loc, board);
        setPieceId("king");
        Action kingCapture = new KingCapture(this);
        Action kingMove = new KingMove(this);
        Action[] arr = {kingCapture, kingMove};
        setActions(Utility.arrayActionToArrayList(arr));


    }  

}
