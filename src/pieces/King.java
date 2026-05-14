package pieces;

import core.*;
import actions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class King extends CommanderPiece {
    
    public King(int ctrl, int[] loc, Board board){
        super(ctrl, loc, board);
        pieceId = "king";
        displayName = Translation.getStatic("king_display");
        Action capture = new KingCapture(this);
        Action move = new KingMove(this);
        Action[] arr = {capture, move};
        actions = Utility.arrayActionToArrayList(arr);

    }  

}
