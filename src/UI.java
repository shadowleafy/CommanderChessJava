class UI{

    // UI code here please :3

    // These are the general idea of methods we'll need

    public void updateBoard(Board board){} // Visually update the board to match the current boardstate.

    public void log(String s){} // Visually show a message to players.

    public int[] requestLocation(Board board, Player requestedPlayer){} // Request a square from a player. Note that this isn't called natively when requesting an action.

    public Action requestAction(Board board, Player requestedPlayer){} // Request an action from a player (usually the active player)
}