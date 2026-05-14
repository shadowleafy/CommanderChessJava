package actions;

import core.*;
import pieces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class KingCapture extends Action {
    
    public KingCapture(Piece owner){
        super(owner);
        this.setType("capture");
        this.setActionId("king_capture");
        setDisplayName(Translation.getStatic("king_capture_display"));
        setDescription(Translation.getStatic("king_capture_description"));
        // set display name once translation is done better.
    }   



}