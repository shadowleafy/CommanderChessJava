package actions;

import core.*;

public class Snipe extends Action {

    private Piece sacrificedPiece;

    public Snipe(Piece owner){
        super(owner);
        type = "action";
        actionId = "snipe";
        displayName = Translation.getStatic("snipe_display");
        description = Translation.getStatic("snipe_description");
    }

    public void onUse(){
        switch (UI.stepsDone) {
            case 1:
                if (ownerPiece.getControllerObj().getActions() >= 1) {
                    UI.log("Select a piece within a king's move to sacrifice.");
                } else {
                    UI.cancel("You don't have enough actions to use this!");
                }
                break;
            case 2:
                if (UI.selectedSquares.size() == 0) {
                    if (UI.selectedPieces.size() == 1) {
                        int[] start = ownerPiece.getLocation();
                        int[] end = UI.selectedPieces.get(0).getLocation();
                        int[] delta = Utility.diffVectors(end, start);
                        if (!(delta[0] == 0 && delta[1] == 0) && (Math.abs(delta[0]) <= 1 && Math.abs(delta[1]) <= 1)) {
                            sacrificedPiece = UI.selectedPieces.get(0);
                            UI.log("Select a piece within a queen's move to capture.");
                        } else {
                            UI.cancel("That piece isn't within a king's move!");
                        }
                    } else {
                        UI.cancel("You must sacrifice exactly one piece!");
                    }
                } else {
                    UI.cancel("Please select pieces (and not squares) for this action.");
                }
                break;
            case 3:
                if (UI.selectedSquares.size() == 0) {
                    if (UI.selectedPieces.size() == 1) {
                        int[] start = ownerPiece.getLocation();
                        int[] end = UI.selectedPieces.get(0).getLocation();
                        int[] delta = Utility.diffVectors(end, start);
                        if (delta[0] == 0 && delta[1] == 0) {
                            UI.cancel("You can't hit a piece on your own square!");
                        }
                        if (Math.abs(delta[0]) == Math.abs(delta[1])) {
                            int[] unitDelta = {delta[0] / Math.abs(delta[0]), delta[1] / Math.abs(delta[1])};
                            int[] checkPoint = Utility.copyArray(start);
                            while (!Utility.compareVectors(checkPoint, end)) {
                                checkPoint = Utility.sumVectors(checkPoint, unitDelta);
                                if (!ownerPiece.getBoard().getPiecesOn(checkPoint).isEmpty() && !Utility.compareVectors(checkPoint, end)) {
                                    UI.cancel("That piece isn't within a queen's move!");
                                }
                            }
                            sacrificedPiece.die();
                            ownerPiece.capturePiece(UI.selectedPieces.get(0));
                        } else if (delta[0] == 0 || delta[1] == 0) {
                            int[] unitDelta;
                            if (delta[0] == 0) {
                                unitDelta = new int[]{0, delta[1] / Math.abs(delta[1])};
                            } else {
                                unitDelta = new int[]{delta[0] / Math.abs(delta[0]), 0};
                            }
                            int[] checkPoint = Utility.copyArray(start);
                            while (!Utility.compareVectors(checkPoint, end)) {
                                checkPoint = Utility.sumVectors(checkPoint, unitDelta);
                                if (!ownerPiece.getBoard().getPiecesOn(checkPoint).isEmpty() && !Utility.compareVectors(checkPoint, end)) {
                                    UI.cancel("That piece isn't within a queen's move!");
                                }
                            }
                            sacrificedPiece.die();
                            ownerPiece.capturePiece(UI.selectedPieces.get(0));
                        } else {
                            UI.cancel("That piece isn't within a queen's move!");
                        }
                    } else {
                        UI.cancel("You must sacrifice exactly one piece!");
                    }
                } else {
                    UI.cancel("Please select pieces (and not squares) for this action.");
                }
                break;

        }
    }

}
