package actions;

import core.*;
import pieces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class KingMove extends Action {
    
    public KingMove(Piece owner){
        super(owner);
        this.setType("move");
        this.setActionId("king_move");
        setDisplayName(Translation.getStatic("king_move_display"));
        setDescription(Translation.getStatic("king_move_description"));
        // set display name once translation is done better.
    }   



}