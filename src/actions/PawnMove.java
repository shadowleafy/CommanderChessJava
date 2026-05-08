package actions;

import core.*;
import pieces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class PawnMove extends Action {
    
    public PawnMove(Piece owner){
        super(owner);
        this.setType("move");
        this.setActionId("pawn_move");
        setDisplayName(Translation.getStatic("pawn_move_display"));
        setDescription(Translation.getStatic("pawn_move_description"));
        // set display name once translation is done better.
    }   

    public void onUse(){
        Piece owner = getOwnerPiece();
        switch(UI.stepsDone){
            case 1:
                if (owner.getControllerObj().getActions() >= 1){
                    UI.log("Select a square in range to move to.");
                }
                else{
                    UI.cancel("You don't have enough actions to use this!");
                }
            break;
            case 2:
                if (UI.selectedSquares.size() >= 2) {
                    UI.cancel("You can only move to one square!");
                }
                else if (UI.selectedPieces.size() != 0){
                    UI.cancel("You must move to squares, not pieces. If you want to capture, use your capture action!");
                }
                else if (UI.selectedSquares.size() == 0){
                    UI.cancel("You must select a square to move to!");
                }
                else{
                    int[] loc = UI.selectedSquares.get(0);
                    Board gameboard = this.getOwnerPiece().getBoard();
                    int[] myLoc = this.getOwnerPiece().getLocation();
                    int color = getOwnerPiece().getController();
                    if (Utility.checkMoveValidity(gameboard, color, myLoc, loc, Utility.formVector("0,1"), false, false,  false) || 
                    (Utility.checkMoveValidity(gameboard, color, myLoc, loc, Utility.formVector("0,2"), false, false,  false)) &&
                    Utility.colorshiftLocation(myLoc, color)[1] == 1){
                    if (gameboard.getBoardstate()[loc[1]][loc[0]].size() == 0){
                        getOwnerPiece().movePiece(loc);
                    }
                        Game.finishAction(this);
                    }
                    else{
                        UI.cancel("That square isn't within movement range!");
                    }
                }
            break;
        }
        
    }

}