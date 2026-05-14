package actions;

import core.*;
import pieces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class RookCapture extends Action {
    
    public RookCapture(Piece owner){
        super(owner);
        this.setType("capture");
        this.setActionId("rook_capture");
        setDisplayName(Translation.getStatic("rook_capture_display"));
        setDescription(Translation.getStatic("rook_capture_description"));
        // set display name once translation is done better.
    }   



}