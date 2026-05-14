package actions;

import core.*;


public class basicMovement extends Action {
    
    public basicMovement(Piece owner){
        super(owner);
    }

    public boolean condition(int[] start, int[] end, int[] delta){
        return true;
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
                    int[] start = ownerPiece.getLocation();
                    int[] end = UI.selectedSquares.get(0);
                    int[] delta = Utility.diffVectors(end, start);
                    if (condition(start, end, delta)){
                        getOwnerPiece().movePiece(loc);
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