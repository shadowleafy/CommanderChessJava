package core;

import actions.*;
import pieces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;
import java.awt.*;


public class UI{

    // UI code here please :3

    // These are the general idea of methods we'll need

    private static Board board;
    private static JFrame frame;
    private static JButton playButton;
    private static JButton instructionsButton;

    public static void start(){
        frame = new JFrame();
        frame.setTitle("Commander Chess");

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(80,30,10,30)); //probably edit so resizing works later

        playButton = new JButton();
        JLabel playLabel = new JLabel("Play");
        //instructionsButton = new JButton();
        //JLabel instructionsLabel = new JLabel("Instructions");

        playButton.add(playLabel);
        panel.add(playButton);
        frame.add(panel, BorderLayout.CENTER);
        instructionsButton.add(instructionsLabel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(720, 720);
        frame.setVisible(true);
    } // generate screen when first opened

    public static void updateUI(){
        
    }

    public static void clearUI(){

    }// Completely clear the UI (for changing between screens)

    public static void generateGameUI(){
        
    } // Generate the basic UI for playing the game.


    public static void generateCharacterSelectUI(){} // Generate the basic UI for the character selection screen.
    //call before generating game
    
    public static void generateMainMenu(){
        
    } // Generate the basic UI for the main menu (play, menu, etc). (called when game ends, play instructions language settings)

    public static void generateCharacterSelectUI(){} // Generate the basic UI for the character selection screen.

    public static void updateBoard(Board board){
        this.board = board;
    }

    public static void generateMainMenu(){} // Generate the basic UI for the main menu.

    public static String awaitMenuSelection(){
        return null;
    } // Await a selection in the main menu. Check Game.java for the desired outputs.

    public static String awaitPieceSelection(String title, int color, String[] pieceChoices){
        return null;
    } // Await a selection of commander from a player. Just return the ID, I can do the rest.

    public static void updateBoard(Board b){
        board = b;
    } // Visually update the board to match the current boardstate.

    public static void log(String s){} // Visually show a message to players in a scrolling chat menu.

    public static boolean awaitConfirmation(String message){
        return false;
    }

    public static ArrayList<Integer[]> awaitSquares(int expectedSquares){
        return null;
    }

    public static ArrayList<Integer[]> awaitSquares(){
        return null;
    } // Just awaitSquares(int expectedSquares) but there isn't a specific number of expected squares, so the user must terminate on their end.

    public static void popupMessage(String s){} // Visually show a message that must be manually closed.

    public static String awaitName(int color){
        return null;
    } // Ask for the name of the player of that color. Use in a popup in the character selection menu.

    public static int[] requestLocation(Player requestedPlayer){
        return null;
    } // Request a square from a player. Note that this isn't called natively when requesting an action.

    public static Action requestAction(Player requestedPlayer){
        return null;
    } // Request an action from a player (usually the active player)
}