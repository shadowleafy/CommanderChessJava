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
    private JFrame frame;
    private CardLayout layout;
    private JPanel container;
    private JButton playButton;
    private JButton instructionsButton;
    private JButton languagesButton;
    private JButton settingsButton;
    private JPanel main;
    private JPanel game;
    private JPanel charSelect;

    public static void start(){
        this.frame = new JFrame();
        this.frame.setTitle("Commander Chess");

        this.layout = new CardLayout();
        this.container = new JPanel(layout);

        this.main = new JPanel();
        this.game = new JPanel();
        this.charSelect = new JPanel();
        //prev existence of create menu started here
        createMenu();  //add this. if nonstatic

        //Adds cards to slideshow kinda and frame
        this.container.add(main, "Menu");
        this.container.add(game, "Game");
        this.container.add(charSelect, "Character Selection");
        this.frame.add(container);
        
        this.layout.show(container, "Menu"); //probably move
        
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(800, 720);
        this.frame.setVisible(true);
    } // generate screen when first opened

    public static void createMenu(){
        this.main.setLayout(null); //change later so resizeable
        
        Label title = new JLabel("Commander Chess");
        title.setBounds(300, 50, 350, 100);
        title.setFont(new Font("Calibri", Font.BOLD, 20));
        
        this.playButton = new JButton("Play");
        this.playButton.setBounds(270,200, 250, 80);
        this.instructionsButton = new JButton("Instructions");
        this.instructionsButton.setBounds(270, 300, 250, 80);
        this.languagesButton = new JButton("Language");
        this.languagesButton.setBounds(270,400, 250, 80);
        this.settingsButton = new JButton("Settings");
        this.settingsButton.setBounds(270,500, 250, 80);

        this.main.add(title);
        this.main.add(playButton);
        this.main.add(instructionsButton);
        this.main.add(languagesButton);
        this.main.add(settingsButton);
    } //intializes components for main menu

    public static void createGame(){

    } //initializes components for game page
    
    public static void updateUI(){
        
    }

    public static void clearUI(){
        frame.setVisible(false);
        
    }// Completely clear the UI (for changing between screens)

    public static void generateGameUI(){
        layout.show(container, "Game");
    } // Generate the basic UI for playing the game.


    public static void generateCharacterSelectUI(){
        layout.show(container, "Character Selection");
    } // Generate the basic UI for the character selection screen.
    //call before generating game
    
    public static void generateMainMenu(){
        layout.show(container, "Menu");
    } // Generate the basic UI for the main menu (play, menu, etc). (called when game ends, play instructions language settings)


    public static void updateBoard(Board board){
        this.board = board;
    }

    public static String awaitMenuSelection(){
        return null;
    } // Await a selection in the main menu. Check Game.java for the desired outputs.

    public static String awaitPieceSelection(String title, int color, String[] pieceChoices){
        return null;
    } // Await a selection of commander from a player. Just return the ID, I can do the rest.

    public static void log(String s){} // Visually show a message to players in a scrolling chat menu.

    public static boolean awaitConfirmation(String message){
        return false;
    }

    public static ArrayList<Integer[]> awaitSquares(int expectedSquares, Player requestedPlayer){
        return null;
    }

    public static ArrayList<Integer[]> awaitSquares(Player requestedPlayer){
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

    public static int requestNumber(Player requestedPlayer, int min, int max){
        return 0;
    }
}
