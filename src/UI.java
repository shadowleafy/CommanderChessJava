class UI{

    // UI code here please :3

    // These are the general idea of methods we'll need

    private static Board board;

    public static void updateUI(){
        
    }

    public static void clearUI(){} // Completely clear the UI

    public static void generateGameUI(){} // Generate the basic UI for playing the game.

    public static void generateCharacterSelectUI(){} // Generate the basic UI for the character selection screen.

    public static void generateMainMenu(){} // Generate the basic UI for the main menu.

    public static String awaitMenuSelection(){
        return null;
    } // Await a selection in the main menu. Check Game.java for the desired outputs.

    public static Piece awaitCommanderSelection(){
        return null;
    } // Await a selection of commander from a player. Use the constant Constant.COMMANDER_IDS (and Utility.idToPiece) to get the list.

    public static void updateBoard(Board b){
        board = b;
    } // Visually update the board to match the current boardstate.

    public static void log(String s){} // Visually show a message to players in a scrolling chat menu.

    public static void popupMessage(String s){} // Visually show a message that must be manually closed.

    public static int[] requestLocation(Board board, Player requestedPlayer){
        return null;
    } // Request a square from a player. Note that this isn't called natively when requesting an action.

    public static Action requestAction(Board board, Player requestedPlayer){
        return null;
    } // Request an action from a player (usually the active player)
}