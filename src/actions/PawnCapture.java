package actions;

import core.*;
import pieces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class PawnCapture extends Action {

    public PawnCapture(Piece owner) {
        super(owner);
        this.setType("capture");
        this.setActionId("pawn_capture");
        setDisplayName(Translation.getStatic("pawn_capture_display"));
        setDescription(Translation.getStatic("pawn_capture_description"));
        // set display name once translation is done better.
    }



}