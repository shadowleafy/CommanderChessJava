package actions;

import core.*;


public class basicCapture extends Action {
    
    public basicCapture(Piece owner){
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
                    int[] start = ownerPiece.getLocation();
                    int[] end = UI.selectedPieces.get(0).getLocation();
                    int[] delta = Utility.diffVectors(end, start);
                    if (condition(start, end, delta)){
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