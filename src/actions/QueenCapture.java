package actions;

import core.*;
import pieces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class QueenCapture extends Action {
    
    public QueenCapture(Piece owner){
        super(owner);
        this.setType("capture");
        this.setActionId("queen_capture");
        setDisplayName(Translation.getStatic("queen_capture_display"));
        setDescription(Translation.getStatic("queen_capture_description"));
        // set display name once translation is done better.
    }   



}