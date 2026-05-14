package actions;

import core.*;
import pieces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class QueenMove extends Action {
    
    public QueenMove(Piece owner){
        super(owner);
        this.setType("move");
        this.setActionId("queen_move");
        setDisplayName(Translation.getStatic("queen_move_display"));
        setDescription(Translation.getStatic("queen_move_description"));
        // set display name once translation is done better.
    }   



}