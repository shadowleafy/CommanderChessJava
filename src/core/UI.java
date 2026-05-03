package core;

import actions.*;
import pieces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;


public class UI implements ActionListener{

    // UI code here please :3

    // These are the general idea of methods we'll need

    private static Board board;
    private static JFrame frame;
    private static CardLayout layout;
    private static JPanel container;
    
    private static JButton playButton;
    private static JButton instructionsButton;
    private static JButton languagesButton;
    private static JButton settingsButton;
    
    private static JPanel main;
    private static JPanel game;
    private static JPanel charSelect;
    private static JPanel instructions;

    public static void start(){
        frame = new JFrame();
        frame.setTitle("Commander Chess");

        layout = new CardLayout();
        container = new JPanel(layout);

        main = new JPanel();
        game = new JPanel();
        charSelect = new JPanel();
        instructions = new JPanel();
        //prev existence of create menu started here
        createMenuPanel();
        createInstructionsPanel();

        //Adds cards to slideshow kinda and frame
        container.add(main, "Menu");
        container.add(game, "Game");
        container.add(charSelect, "Character Selection");
        container.add(instructions, "Instructions");
        frame.add(container);
        
        layout.show(container, "Menu"); //probably move
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 720);
        frame.setVisible(true);
    } // generate screen when first opened

    public static void createMenuPanel(){
        //title.setBounds(300, 50, 350, 100);
        //playButton.setBounds(270,200, 250, 80);
        //instructionsButton.setBounds(270, 300, 250, 80);
        //languagesButton.setBounds(270,400, 250, 80);
        //settingsButton.setBounds(270,500, 250, 80);
        
        JLabel title = new JLabel("Commander Chess");
        title.setFont(new Font("Calibri", Font.BOLD, 30));

        main.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 20;
        c.insets = new Insets(20, 20, 20, 20);
        main.add(title, c);
        
        playButton = new JButton("Play");
        c.gridx = 0;
        c.gridy = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 30;
        c.insets = new Insets(10, 10, 10, 10);
        main.add(playButton, c);
        
        instructionsButton = new JButton("Instructions");
        c.gridx = 0;
        c.gridy = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 30;
        c.insets = new Insets(10, 10, 10, 10);
        instructionsButton.addActionListener(e -> {
            layout.show(container, "Instructions");
        });
        main.add(instructionsButton,c );


        languagesButton = new JButton("Language");
        c.gridx = 0;
        c.gridy = 3;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 30;
        c.insets = new Insets(10, 10, 10, 10);
        main.add(languagesButton, c);
        
        settingsButton = new JButton("Settings");
        c.gridx = 0;
        c.gridy = 4;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 30;
        c.insets = new Insets(10, 10, 10, 10);
        main.add(settingsButton, c);
    } //intializes components for main menu

    public static void createGamePanel(){

    } //initializes components for game page

    public static void createInstructionsPanel(){
        instructions.setLayout(new BorderLayout());
        JTextArea textarea = new JTextArea();
        textarea.setText(""); //ADD INSTRUCTIONS HERE
        textarea.setEditable(false);
        textarea.setLineWrap(true);
        textarea.setWrapStyleWord(true);
        JScrollPane scrollpane = new JScrollPane(textarea);
        
        instructions.add(scrollpane);
    } //initialize components for instructions page

    public static void createCharSelectPanel(){

    } //initialize components for character selection page

    public void actionPerformed(ActionEvent e){
    
    } //need for override
    
    public static void updateUI(){
        
    }

    public static void clearUI(){
        
    }// Completely clear the UI (for changing between screens) maybe delete bc i dont think i need it

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

    public static ArrayList<Integer[]> awaitSquares(String message, int expectedSquares, Player requestedPlayer){
        return null;
    }

    public static ArrayList<Integer[]> awaitSquares(String message, Player requestedPlayer){
        return null;
    } // Just awaitSquares(int expectedSquares) but there isn't a specific number of expected squares, so the user must terminate on their end.

    public static void popupMessage(String s){} // Visually show a message that must be manually closed.

    public static String awaitName(int color){
        return null;
    } // Ask for the name of the player of that color. Use in a popup in the character selection menu.

    public static int[] requestLocation(String message, Player requestedPlayer){
        return null;
    } // Request a square from a player. Note that this isn't called natively when requesting an action.

    public static Action requestAction(String message, Player requestedPlayer){
        return null;
    } // Request an action from a player (usually the active player)

    public static int requestNumber(String message, Player requestedPlayer, int min, int max){
        return 0;
    }
}
