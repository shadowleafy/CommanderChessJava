package actions;

import core.*;
import pieces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class KingCapture extends basicCapture {
    
    public KingCapture(Piece owner){
        super(owner);
        this.setType("capture");
        this.setActionId("king_capture");
        setDisplayName(Translation.getStatic("king_capture_display"));
        setDescription(Translation.getStatic("king_capture_description"));
        // set display name once translation is done better.
    }

    public boolean condition(int[] start, int[] end, int[] delta){
        if (Math.abs(delta[0]) <= 1 && Math.abs(delta[1]) <= 1 && !Utility.compareVectors(Utility.formVector("0,0"), delta)){
            return true;
        }
        else{
            return false;
        }
    }



}