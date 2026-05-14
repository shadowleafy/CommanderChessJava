package actions;

import core.*;
import pieces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class RookMove extends Action {
    
    public RookMove(Piece owner){
        super(owner);
        this.setType("move");
        this.setActionId("rook_move");
        setDisplayName(Translation.getStatic("rook_move_display"));
        setDescription(Translation.getStatic("rook_move_description"));
        // set display name once translation is done better.
    }   



}