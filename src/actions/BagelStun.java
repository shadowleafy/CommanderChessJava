package actions;

import core.*;
import pieces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class BagelStun extends Action {
    
    public BagelStun(Piece owner){
        super(owner);
        this.setType("action");
        this.setActionId("bagel_stun");
        // set display name once translation is done better.
    }

    public boolean onUse(){
        Integer[] loc = UI.awaitSquares("Please select a square to target.", 1, getOwnerPiece().getControllerObj()).get(0);
        int defendingIndex = 0;
        if (this.getOwnerPiece().getBoard().getBoardstate()[loc[1]][loc[0]].size() > 1){
            defendingIndex = UI.requestNumber("Pick a piece to capture.", this.getOwnerPiece().getControllerObj(), 0, this.getOwnerPiece().getBoard().getBoardstate()[loc[1]][loc[0]].size()-1);
        }
        else if (this.getOwnerPiece().getBoard().getBoardstate()[loc[1]][loc[0]].size() == 0){
            return false;
        }
        Piece defendingPiece = getOwnerPiece().getBoard().getBoardstate()[loc[1]][loc[0]].get(defendingIndex);
        ArrayList<Integer[]> squares = UI.awaitSquares("Select two Soldiers to sacrifice.",2, getOwnerPiece().getControllerObj());
        Piece[] marked = new Piece[2];
        int count = 0;
        for (Integer[] arr : squares){
            for (Piece p : getOwnerPiece().getBoard().getBoardstate()[arr[1]][arr[0]]){
                if (p.getType() == "soldier"){
                    marked[count] = p;
                    count++;
                    break;
                }
            }
        }
        if (count == 2 && defendingPiece.getController() != getOwnerPiece().getController()){
            for (Piece p : marked){
                p.die();
            }
            defendingPiece.addCounters("stun", 2);
            return true;
        }
        return false;
    }

}
