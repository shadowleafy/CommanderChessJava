package actions;

import core.*;
import pieces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class PawnMove extends Action {
    
    public PawnMove(Piece owner){
        super(owner);
        this.setType("move");
        this.setActionId("pawn_move");
        setDisplayName(Translation.getStatic("pawn_move_display"));
        setDescription(Translation.getStatic("pawn_move_description"));
        // set display name once translation is done better.
    }   



}