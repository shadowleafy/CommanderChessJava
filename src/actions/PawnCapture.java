package actions;

import core.*;
import pieces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class PawnCapture extends basicCapture {

    public PawnCapture(Piece owner) {
        super(owner);
        this.setType("capture");
        this.setActionId("pawn_capture");
        setDisplayName(Translation.getStatic("pawn_capture_display"));
        setDescription(Translation.getStatic("pawn_capture_description"));
        // set display name once translation is done better.
    }

    public boolean condition(int[] start, int[] end, int[] delta) {
        int color = ownerPiece.getController();
        delta = Utility.colorshiftDelta(delta, color);
        return ((delta[1] == 1) && (delta[0] == 1 || delta[0] == -1));
    }
}