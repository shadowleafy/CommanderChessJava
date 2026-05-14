package actions;

import core.*;
import pieces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class BishopCapture extends Action {
    
    public BishopCapture(Piece owner){
        super(owner);
        this.setType("capture");
        this.setActionId("bishop_capture");
        setDisplayName(Translation.getStatic("bishop_capture_display"));
        setDescription(Translation.getStatic("bishop_capture_description"));
        // set display name once translation is done better.
    }

}