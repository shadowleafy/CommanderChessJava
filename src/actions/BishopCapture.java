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

    public boolean condition(){
        int[] start = ownerPiece.getLocation();
        int[] end = UI.selectedPieces.get(0).getLocation();
        int[] delta = Utility.diffVectors(end, start);
        if (delta[0] == 0 || delta[1] == 0){
            return false;
        }
        int[] unitDelta = {(delta[0] / Math.abs(delta[0])), (delta[1] / Math.abs(delta[1]))};
        if (Math.abs(delta[0]) == Math.abs(delta[1])){
            int[] checkPoint = Utility.copyArray(start);
            while (!Utility.compareVectors(checkPoint, end)){
                checkPoint = Utility.sumVectors(checkPoint, unitDelta);
                if (ownerPiece.getBoard().getPiecesOn(checkPoint).size() != 0 && !Utility.compareVectors(checkPoint, end)){
                    return false;
                }
            }
            return true;
        }
        else{
            return false;
        }
    }

    public void onUse(){
        Piece owner = getOwnerPiece();
        switch(UI.stepsDone){
            case 1:
                if (owner.getControllerObj().getActions() >= 1){
                    UI.log("Select a piece in range to capture.");
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
                    if (condition()){
                        getOwnerPiece().capturePiece(UI.selectedPieces.get(0));
                        if (gameboard.getBoardstate()[loc[1]][loc[0]].size() == 0){
                            getOwnerPiece().movePiece(loc);
                        }
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