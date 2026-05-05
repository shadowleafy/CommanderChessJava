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
        // set display name once translation is done better.
    }   

    public void onUse(){
        Piece owner = getOwnerPiece();
        switch(UI.stepsDone){
            case 1:
                if (owner.getControllerObj().getActions() >= 1){
                    UI.await("Select a piece in range to capture.");
                }
                else{
                    UI.cancel("You don't have enough actions to use this!");
                }
            break;
            case 2:
                if (UI.selectedSquares.size() != 0) {
                    UI.cancel("Do not select squares when capturing a piece, select a piece.");
                }
                else if (UI.selectedPieces.size() == 0){
                    UI.cancel("You must select a piece to capture!");
                }
                else if (UI.selectedPieces.size() >= 2){
                    UI.cancel("You can only select one piece to capture!");
                }
                else{
                    int[] loc = UI.selectedPieces.get(0).getLocation();
                    Board gameboard = this.getOwnerPiece().getBoard();
                    int[] myLoc = this.getOwnerPiece().getLocation();
                    if (Utility.checkCaptureValidity(gameboard, getOwnerPiece(), UI.selectedPieces.get(0), Utility.formVector("1,1"), false, true,  false) ||
                        Utility.checkCaptureValidity(gameboard, getOwnerPiece(), UI.selectedPieces.get(0), Utility.formVector("1,0"), false, true,  false)){
                        getOwnerPiece().capturePiece(UI.selectedPieces.get(0));
                        getOwnerPiece().movePiece(loc);
                        Game.finishAction(this);
                    }
                    else{
                        UI.cancel("That piece isn't within capture range!");
                    }
                }
            break;
        }
        
    }

}