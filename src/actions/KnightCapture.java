package actions;

import core.*;
import pieces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class KnightCapture extends basicCapture {
    
    public KnightCapture(Piece owner){
        super(owner);
        this.setType("capture");
        this.setActionId("knight_capture");
        setDisplayName(Translation.getStatic("knight_capture_display"));
        setDescription(Translation.getStatic("knight_capture_description"));
        // set display name once translation is done better.
    }

    public boolean condition(int[] start, int[] end, int[] delta) {
        return (Utility.magnitudeSq(delta) == 5);
    }
}