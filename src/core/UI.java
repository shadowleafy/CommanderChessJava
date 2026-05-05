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

    // THESE STATIC VARIABLES ACT LIKE GLOBAL VARIABLES!!! THEY ARE INTENTIONALLY PUBLIC!
    public static ArrayList<int[]> selectedSquares;
    public static ArrayList<Piece> selectedPieces;
    public static Action selectedAction;
    public static int stepsDone = 0;

    private static JFrame frame;
    private static CardLayout layout;
    private static JPanel container;
    
    private static JButton playButton;
    private static JButton instructionsButton;
    private static JButton languagesButton;
    private static JButton settingsButton; //potentially remove
    
    private static JPanel main;
    private static JPanel game;
    private static JPanel charSelect;
    private static JPanel instructions;

    private static JButton[][] chessBoard;

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
        createGamePanel();
        createCharSelectPanel();

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
        playButton.addActionListener(e ->{
            layout.show(container, "Game");
        });
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
        
        settingsButton = new JButton("Settings"); //potentially remove
        c.gridx = 0;
        c.gridy = 4;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 30;
        c.insets = new Insets(10, 10, 10, 10);
        main.add(settingsButton, c);
    } //intializes components for main menu

    public static void createGamePanel(){
        //background colors: #854D24 (dark), #EBD2B2 (light)
        game.setLayout(new BorderLayout());
    
        chessBoard = new JButton[8][8];
        JPanel boardArea = new JPanel(new GridLayout(8, 8));
        boardArea.setPreferredSize(new Dimension(600, 600));

        for (int row = 0; row < 8; row++){
            for (int col = 0; col < 8; col++){
                chessBoard[row][col] = new JButton();
                if (row % 2 == 0){
                    if (col % 2 == 0){
                        chessBoard[row][col].setBackground(new Color(0xEBD2B2));
                    }
                    else {
                        chessBoard[row][col].setBackground(new Color(0x854D24));
                    }
                }
                else{
                    if (col % 2 == 0){
                        chessBoard[row][col].setBackground(new Color(0x854D24));
                    }
                    else {
                        chessBoard[row][col].setBackground(new Color(0xEBD2B2));
                    }
                }
                chessBoard[row][col].setOpaque(true);
                chessBoard[row][col].setBorderPainted(false);
                boardArea.add(chessBoard[row][col]);
            }
        }
        game.add(new JPanel(), BorderLayout.WEST);
        game.add(new JPanel(), BorderLayout.EAST);
        game.add(new JPanel(), BorderLayout.NORTH);
        game.add(new JPanel(), BorderLayout.SOUTH);
        game.add(boardArea, BorderLayout.CENTER);
        //somewhere in here call createLog() and figure out layout

    } //initializes components for game page

    public static void createInstructionsPanel(){
        instructions.setLayout(new BorderLayout());
        JTextArea textarea = new JTextArea();
        textarea.setFont(new Font("Arial", Font.PLAIN, 20)); //change text size once have instructions
        textarea.setText("hi i need to make this text really long so ignore me while i ramble there is gonna be a fake skating" +
                         " movie and im really excited but im afraid theyre not gonna cast it well and its gonna end up bad because"
                         + " that happens so often. they need to cast the right people for dani and alec who capture their essence well" 
                         + "and have that dynamic from the book so hopefully they can find the right people and the movie will be good."); //ADD INSTRUCTIONS HERE
        textarea.setEditable(false);
        textarea.setLineWrap(true);
        textarea.setWrapStyleWord(true);
        JScrollPane scrollpane = new JScrollPane(textarea);

        JButton closeInstructions = new JButton("Close");
        closeInstructions.addActionListener(e -> {
            layout.show(container, "Menu");
        });
        
        instructions.add(scrollpane, BorderLayout.CENTER);
        instructions.add(closeInstructions, BorderLayout.SOUTH);
    } //initialize components for instructions page

    public static void createCharSelectPanel(){
        charSelect.setLayout(new GridLayout());

        JLabel[] commanders = new JLabel[Constant.COMMANDER_IDS.length];
        JLabel charSelectTitle = new JLabel("Character Selection");
        for (int i = 0; i < Constant.COMMANDER_IDS.length; i++){
            commanders[i] = new JLabel();
            
        }
    } //initialize components for character selection page, show commanders, when clicked have drop down w descriptions

    public void actionPerformed(ActionEvent e){
    
    } //need for override

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

    public static void createLog(){
        JTextArea mLog = new JTextArea();
        mLog.setFont(new Font("Arial", Font.PLAIN, 20)); //change text size once have instructions
        mLog.setText("Messages to you will appear here"); // add initial message

        mLog.setEditable(false);
        mLog.setLineWrap(true);
        mLog.setWrapStyleWord(true);
        JScrollPane mLogPane = new JScrollPane(mLog);

        game.add(mLogPane); //figure out layout (should be at bottom right under selected piece info)

    } //creates initial log area

    public static void updateBoard(Board b){
        board = b;
    }

    public static void log(String s){

    } // Visually show a message to players in a scrolling chat menu.

    public static void await(String message){

    }

    public static void cancel(String message){
        stepsDone = 0;
        selectedSquares.clear();
        selectedPieces.clear();
        selectedAction = null;
        log(message);
    }
    
}
