package actions;

import core.*;
import pieces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class KnightMove extends Action {

    public KnightMove(Piece owner){
        super(owner);
        this.setType("move");
        this.setActionId("knight_move");
        setDisplayName(Translation.getStatic("knight_move_display"));
        setDescription(Translation.getStatic("knight_move_description"));
        // set display name once translation is done better.
    }



}