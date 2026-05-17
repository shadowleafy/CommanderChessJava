package core;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Map;

public abstract class UI implements ActionListener {

    // UI code here please :3

    // These are the general idea of methods we'll need

    private static Board board;

    // THESE STATIC VARIABLES ACT LIKE GLOBAL VARIABLES!!! THEY ARE INTENTIONALLY PUBLIC!
    public static ArrayList<int[]> selectedSquares = new ArrayList<int[]>();
    public static ArrayList<Piece> selectedPieces = new ArrayList<Piece>();
    public static Action selectedAction;
    public static int stepsDone = 0;

    private static JFrame frame;
    private static CardLayout layout;
    private static JPanel container;
    private static String selectedType;

    private static JLabel title;
    private static JLabel pName;
    private static JLabel pDesc;
    private static JLabel pTags;
    private static JLabel pCounters;
    private static JButton playButton;
    private static JButton instructionsButton;
    private static JButton languagesButton;
    private static JButton settingsButton; //potentially remove
    private static JButton done;

    private static JPanel main;
    private static JPanel game;
    private static JPanel charSelect;
    private static JPanel instructions;
    private static String currCard;

    private static CardLayout charSelectLayout;
    private static JPanel charSelectPanel;
    private static JSplitPane charSelectSplit;
    private static JPanel charSelectSwitch;

    private static JButton select;
    private static JButton cancel;
    private static boolean selectShown = false;
    private static int[] currSelectedSquare; //check type
    private static Piece currSelectedPiece;
    private static int[] currInitSquare = new int[2];

    private static JPanel pieceArea;
    private static JPanel logArea;
    private static JTextArea mLog;
    private static CardLayout pieceAreaLayout;
    private static JSplitPane bottomVertSplit;

    private static JPanel pBlank;
    private static JPanel pPickChar;
    private static JPanel pCharInfo;
    private static JPanel pAction;
    private static JPanel pSelect;
    private static JButton passTurn;

    private static int currWidth = 900;
    private static int currHeight = 700;

    private static JButton[][] chessBoard;

    public static void start() {
        frame = new JFrame();
        frame.setTitle("Commander Chess");
        frame.setSize(900, 700);

        currInitSquare[0] = -1;
        currInitSquare[1] = -1;

        frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                currWidth = frame.getWidth();
                currHeight = frame.getHeight();

                if (currCard.equals("Menu")) {
                    resizeMain();
                } else if (currCard.equals("Game")) {
                    resizeGame();
                } else if (currCard.equals("Character Selection")) {
                    resizeCharSelect();
                } else {
                    resizeInstructions();
                }
            }
        });

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

        currCard = "Menu";
        layout.show(container, "Menu"); //probably move

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(900, 650));
        frame.setVisible(true);

    } // generate screen when first opened

    public static void createMenuPanel() {
        //title.setBounds(300, 50, 350, 100);
        //playButton.setBounds(270,200, 250, 80);
        //instructionsButton.setBounds(270, 300, 250, 80);
        //languagesButton.setBounds(270,400, 250, 80);
        //settingsButton.setBounds(270,500, 250, 80);

        title = new JLabel("Commander Chess");
        title.setFont(new Font("Calibri", Font.BOLD, 30));

        main.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(20, 20, 20, 20);
        c.weightx = 0.5;
        main.add(title, c);

        playButton = new JButton("Play");
        c.gridx = 0;
        c.gridy = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 30;
        c.insets = new Insets(10, 50, 10, 50);
        playButton.addActionListener(e -> {
            layout.show(container, "Game");
            currCard = "Game";

            // For testing purposes only
            String[] whiteArray = {"king", "queen", "bishop", "knight", "rook", "pawn"};
            String[] blackArray = {"king", "queen", "bishop", "knight", "rook", "pawn"};
            Game.beginGame(whiteArray, blackArray, "Hatsune Miku", "Kasane Teto");
        });
        main.add(playButton, c);

        instructionsButton = new JButton("Instructions");
        c.gridx = 0;
        c.gridy = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 30;
        c.insets = new Insets(10, 50, 10, 50);
        instructionsButton.addActionListener(e -> {
            layout.show(container, "Instructions");
            currCard = "Instructions";
        });
        main.add(instructionsButton, c);


        languagesButton = new JButton("Language");
        c.gridx = 0;
        c.gridy = 3;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 30;
        c.insets = new Insets(10, 50, 10, 50);
        languagesButton.addActionListener(e -> {
            //add functionality
        });
        main.add(languagesButton, c);

        settingsButton = new JButton("Settings"); //potentially remove
        c.gridx = 0;
        c.gridy = 4;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 30;
        c.insets = new Insets(10, 50, 10, 50);
        main.add(settingsButton, c);
    } //intializes components for main menu

    public static void createGamePanel() {
        //background colors: #854D24 (dark), #EBD2B2 (light)
        game.setLayout(new BorderLayout());
        chessBoard = new JButton[8][8];

        pieceAreaLayout = new CardLayout();
        pieceArea = new JPanel(pieceAreaLayout);
        logArea = new JPanel(new BorderLayout());
        pSelect = new JPanel();

        JPanel boardArea = new JPanel(new GridLayout(8, 8));
        boardArea.setPreferredSize(new Dimension(500, 500));

        createPieceArea();
        createLog();


        select = new JButton("Select");
        select.addActionListener(e -> {
            selectButtonFunction();
        });

        cancel = new JButton("Cancel");
        cancel.addActionListener(e -> {
           cancel("");
        });
        pSelect.add(cancel);
        pSelect.add(select);
        passTurn = new JButton("Pass Turn");
        passTurn.addActionListener(e -> {
            Game.passTurn();
            log("The turn has been passed.");
        });
        pSelect.add(passTurn);
        done = new JButton("Done");
        done.addActionListener(e -> {
            stepsDone++;
            if (selectedAction != null) {
                selectedAction.onUse();
            }
            selectedSquares.clear();
            selectedPieces.clear();
            pieceAreaLayout.show(pieceArea, "Blank");
            pPickChar.removeAll();
            currSelectedSquare = null;
            currSelectedPiece = null;
            updateBoard(board);
            select.setText("Select");
            select.setVisible(false);
            done.setVisible(false);
            cancel.setVisible(false);
            passTurn.setVisible(true);
            currInitSquare[0] = -1;
            currInitSquare[1] = -1;

            pSelect.repaint();
            pSelect.revalidate();
        });
        pSelect.add(done);
        done.setVisible(false);
        select.setVisible(false);
        cancel.setVisible(false);


        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                chessBoard[7 - row][col] = new JButton();
                chessBoard[7-row][col].setFocusPainted(false);
                int r = row;
                int c = col;
                int[] loc = {col, 7 - row};
                chessBoard[7 - row][col].addActionListener(e -> {
                    selectedType = "square";
                    if (stepsDone != 0) {
                        currSelectedSquare = loc;
                        if (Utility.squareInArrayList(selectedSquares, loc)) {
                            select.setText("Unselect");
                        } else {
                            select.setText("Select");
                        }
                    }
                    onInitSquareClicked(7 - r, c);

                });
                if ((row+col) % 2 == 0) {
                    chessBoard[7 - row][col].setBackground(new Color(0x854D24));
                } else {
                    chessBoard[7 - row][col].setBackground(new Color(0xEBD2B2));
                }
                chessBoard[7 - row][col].setOpaque(true);
                chessBoard[7 - row][col].setBorderPainted(false);
                chessBoard[7-row][col].setFocusPainted(false);


                boardArea.add(chessBoard[7 - row][col]);
            }
        }

        bottomVertSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, pSelect, logArea);
        JSplitPane vertSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, pieceArea, bottomVertSplit);
        JSplitPane horizSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, boardArea, vertSplit);
        bottomVertSplit.setDividerLocation(0.12);
        bottomVertSplit.setResizeWeight(0.11);
        vertSplit.setDividerLocation(0.66);
        vertSplit.setResizeWeight(0.66);
        horizSplit.setDividerLocation(0.66);
        horizSplit.setResizeWeight(0.66);

        //hideSelectButton();

        game.add(horizSplit, BorderLayout.CENTER);

    } //initializes components for game page

    public static void createInstructionsPanel() {
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
            currCard = "Menu";
        });

        instructions.add(scrollpane, BorderLayout.CENTER);
        instructions.add(closeInstructions, BorderLayout.SOUTH);
    } //initialize components for instructions page


    public static void createCharSelectPanel() {
        charSelect.setLayout(new BorderLayout());
        charSelectLayout = new CardLayout();
        charSelectPanel = new JPanel(charSelectLayout);
        charSelectSwitch = new JPanel();
        charSelectSwitch.setLayout(new GridBagLayout());

        //create next and prev buttons
        JButton csNext = new JButton("Next");
        JButton csPrevious = new JButton("Previous");
        csNext.addActionListener(e -> {
            charSelectLayout.next(charSelectPanel);
        });
        csPrevious.addActionListener( e->{
            charSelectLayout.previous(charSelectPanel);
        });

        GridBagConstraints g = new GridBagConstraints();
        g.gridx = 0;
        g.weightx = 0.25;
        g.weighty = 1.0;
        charSelectSwitch.add(csPrevious, g);
        JPanel p = new JPanel();
        g.gridx = 1;
        g.weightx = 0.5;
        charSelectSwitch.add(p, g);
        g.gridx = 2;
        g.weightx = 0.25;
        g.weighty = 1.0;
        charSelectSwitch.add(csNext, g);

        //create card for each commander
        JLabel[] commanderName = new JLabel[Constant.COMMANDER_IDS.length];
        ImageIcon[] commanderImage = new ImageIcon[Constant.COMMANDER_IDS.length];
        JLabel charSelectTitle = new JLabel("Character Selection");
        for (int i = 0; i < Constant.COMMANDER_IDS.length; i++){
            JPanel character = new JPanel();
            commanderName[i] = new JLabel();
            commanderImage[i] = new ImageIcon();
            JLabel characterImage = new JLabel(commanderImage[i]);

            character.add(commanderName[i]);
            character.add(characterImage);
            charSelectPanel.add(character);
        }

        charSelectSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, charSelectPanel, charSelectSwitch);
        charSelectSplit.setDividerLocation(0.9);
        charSelectSplit.setResizeWeight(0.9);

        charSelectLayout.show(charSelectPanel, "   "); //add first commander later
        charSelect.add(charSelectSplit, BorderLayout.CENTER);
    } //initialize components for character selection page, show commanders, when clicked have drop down w descriptions

    /*public void actionPerformed(ActionEvent e){
    
    } //need for override*/

    public static void resizeMain() {
        GridBagConstraints g = new GridBagConstraints();
        g.gridx = 0;
        g.gridy = 1;
        g.ipady = 30;
        g.fill = GridBagConstraints.HORIZONTAL;

        g.insets = new Insets(currHeight / 50, currWidth / 20, currHeight / 50, currWidth / 20);
        GridBagLayout l = (GridBagLayout) main.getLayout();

        title.setFont(new Font("Calibri", Font.BOLD, currWidth / 30));
        l.setConstraints(playButton, g);
        playButton.setFont(new Font("Calibri", Font.PLAIN, currWidth / 50));
        g.gridy = 2;
        l.setConstraints(instructionsButton, g);
        instructionsButton.setFont(new Font("Calibri", Font.PLAIN, currWidth / 50));
        g.gridy = 3;
        l.setConstraints(languagesButton, g);
        languagesButton.setFont(new Font("Calibri", Font.PLAIN, currWidth / 50));
        g.gridy = 4;
        l.setConstraints(settingsButton, g);
        settingsButton.setFont(new Font("Calibri", Font.PLAIN, currWidth / 50));

        main.revalidate();
    }

    public static void resizeGame() {
        /*for (int r = 0; r < 8; r++){
            for (int c = 0; c < 8; c++){
                if (!(board.getBoardstate()[r][c].isEmpty())){
                    int squareWidth = chessBoard[r][c].getWidth();
                    int squareHeight = chessBoard[r][c].getHeight();
                    if (squareWidth <= 0 || squareHeight <= 0){
                        continue;
                    }
                    ImageIcon image = (ImageIcon) chessBoard[r][c].getIcon();
                    int imgWidth = image.getIconWidth();
                    int imgHeight = image.getIconHeight();
                    int tarWidth = (int) (squareWidth * 0.9);
                    int tarHeight = (int) (squareHeight * 0.9);
                    double scaleW = (double) (tarWidth / imgWidth);
                    double scaleH = (double) (tarHeight / imgHeight);

                    double scale = Math.min(scaleW, scaleH);

                    int w = (int) (imgWidth * scale);
                    int h = (int) (imgHeight * scale);
                    if (w <= 0 || h <= 0){
                        continue;
                    }
                    Image img = image.getImage();
                    Image newImg = img.getScaledInstance(w, h, Image.SCALE_SMOOTH);
                    chessBoard[r][c].setIcon(new ImageIcon(newImg));
                }
            }
        }*/
 
    }

    public static void resizeCharSelect() {

    }

    public static void resizeInstructions() {

    }

    public static void generateGameUI() {
        layout.show(container, "Game");
        currCard = "Game";
    } // Generate the basic UI for playing the game.


    public static void generateCharacterSelectUI() {
        layout.show(container, "Character Selection");
        currCard = "Character Selection";
    } // Generate the basic UI for the character selection screen.
    //call before generating game

    public static void generateMainMenu() {
        layout.show(container, "Menu");
        currCard = "Menu";
    } // Generate the basic UI for the main menu (play, menu, etc). (called when game ends, play instructions language settings)

    public static void createLog() {
        mLog = new JTextArea();
        mLog.setRows(12);
        mLog.setFont(new Font("Arial", Font.PLAIN, 14)); //change text size once have instructions
        mLog.setText("Messages to you will appear here"); // add initial message
        mLog.append("\n-------------");

        mLog.setEditable(false);
        mLog.setLineWrap(true);
        mLog.setWrapStyleWord(true);
        JScrollPane mLogPane = new JScrollPane(mLog);

        logArea.add(mLogPane, BorderLayout.CENTER); //figure out layout (should be at bottom right under selected piece info)

    } //creates initial log area

    public static void createPieceArea() {
        pBlank = new JPanel();
        pPickChar = new JPanel();
        pCharInfo = new JPanel();
        pAction = new JPanel();

        JLabel initPieceMessage = new JLabel("Piece Information + Action Decisions will be here");
        pBlank.add(initPieceMessage);

        pPickChar.setLayout(new GridLayout(0, 1, 5, 5));

        pCharInfo.setLayout(new GridLayout(0, 1, 5, 5));

        pAction.setLayout(new GridLayout(0, 1, 5, 5));

        pieceArea.add(pBlank, "Blank");
        pieceArea.add(pPickChar, "Pick Character");
        pieceArea.add(pCharInfo, "Character Info");
        pieceArea.add(pAction, "Action");

        pieceAreaLayout.show(pieceArea, "Blank");
    }

    public static void onInitSquareClicked(int row, int col) { //change color of selected square to SELECTCOLOR

        currInitSquare[0] = row;
        currInitSquare[1] = col;

        pieceAreaLayout.show(pieceArea, "Blank");
        pPickChar.removeAll();
        currSelectedSquare = new int[]{col, row};
        updateBoard(board);
        ArrayList<Piece> currPieceArray = board.getBoardstate()[row][col]; //row goes bottom to top
        //chessBoard[row][col].setBackground(Constant.SELECTCOLOR); //highlights square

        if (!currPieceArray.isEmpty()) {
            //get the rest of the info needed for info panel
            for (Piece value : currPieceArray) {
                JButton piece = new JButton(value.getDisplayName());
                Piece p = value;
                //add image icon for button in side panel
                piece.addActionListener(e -> {
                    currSelectedPiece = p;
                    onInitPieceSelected(p);
                    log(""+stepsDone);
                });
                pPickChar.add(piece);
            }
            // show the drop down menu. dont show info for piece until they pick the piece (show pieces on square)
            pieceAreaLayout.show(pieceArea, "Pick Character");
        }

    } //when square on board is clicked

    public static void onInitPieceSelected(Piece p) {
        cancel.setVisible(true);
        pieceAreaLayout.show(pieceArea, "Blank");
        pPickChar.removeAll();
        pCharInfo.removeAll();
        currSelectedPiece = p;
        selectedType = "piece";
        pName = new JLabel(p.getDisplayName() + " (Controlled by " + p.getControllerObj().getDisplayName() + ")");
        if (p.getSelected()) {
            pName.setForeground(new Color(Constant.SELECTCOLOR));
            pName.setText("[Selected] " + p.getDisplayName() + " (Controlled by " + p.getControllerObj().getDisplayName() + ")");
        } else {
            pName.setForeground(null);
            pName.setText(p.getDisplayName() + " (Controlled by " + p.getControllerObj().getDisplayName() + ")");
        }
        //get remaining piece info (movements, abilities, description)
        pCharInfo.add(pName);
        pDesc = new JLabel();
        String desc = p.getDescription();
        pDesc.setText(desc);
        desc = "Tags: ";
        for (String tag : p.getTags()){
            desc += tag + ", ";
        }

        if (p.getTags().isEmpty()){
            desc += "None";
        }
        else{
            desc = desc.substring(0, desc.length()-2);
        }
        pTags = new JLabel(desc);
        desc = "Counters: ";
        for (String counter : p.getCounters().keySet()){
            desc += counter;
            desc += " (" + p.getCounters(counter) + "), ";
        }

        if (p.getCounters().keySet().isEmpty()){
            desc += "None";
        }
        else{
            desc = desc.substring(0, desc.length()-2);
        }
        pCounters = new JLabel(desc);
        pCharInfo.add(pDesc);
        pCharInfo.add(pTags);
        pCharInfo.add(pCounters);




        if (stepsDone == 0) {
            for (int i = 0; i < p.getActions().size(); i++) { //creates list of action buttons
                Action currAction = p.getActions().get(i);
                JButton a = new JButton(currAction.getDisplayName());
                a.addActionListener(e -> {
                    if (stepsDone == 0) {
                        selectedAction = currAction;
                    }
                    stepsDone++;
                    selectActionStuff();
                    selectedAction.onUse(); //check for method name
                    selectedSquares.clear();
                    selectedPieces.clear();

                });
                pCharInfo.add(a);
            }
        }
        pieceAreaLayout.show(pieceArea, "Character Info");
    }

    public static void selectActionStuff() { //fix layout
        //JTextArea actionDesc = new JTextArea(selectedAction.getDescription()); //double check later
        pAction.removeAll();

        showSelectButton();

        //pAction.add(actionDesc);

        select.setVisible(true);
        done.setVisible(true);
        passTurn.setVisible(false);


        pieceAreaLayout.show(pieceArea, "Blank");
    }

    public static void selectButtonFunction() {

        if (selectedType.equals("square") && currSelectedSquare != null) {
            if (Utility.squareInArrayList(selectedSquares, currSelectedSquare)) {
                selectedSquares.remove(Utility.findSquareInArrayList(selectedSquares, currSelectedSquare));
                select.setText("Select");
            } else {
                selectedSquares.add(currSelectedSquare);
                select.setText("Unselect");
            }
        } else if (selectedType.equals("piece") && currSelectedPiece != null) {
            if (Utility.pieceInArrayList(selectedPieces, currSelectedPiece)) {
                selectedPieces.remove(currSelectedPiece);
                select.setText("Select");
                if (pName != null) {
                    pName.setForeground(null);
                    pName.setText(currSelectedPiece.getDisplayName() + " (Controlled by " + currSelectedPiece.getControllerObj().getDisplayName() + ")");
                } else {
                    log("Error SN300: pName is currently null.");
                }
            } else {
                selectedPieces.add(currSelectedPiece);
                select.setText("Unselect");
                if (pName != null) {
                    pName.setForeground(new Color(Constant.SELECTCOLOR));
                    pName.setText("[Selected] " + currSelectedPiece.getDisplayName() + " (Controlled by " + currSelectedPiece.getControllerObj().getDisplayName() + ")");
                } else {
                    log("Error SN300: pName is currently null.");
                }
            }
        }
        if (currSelectedSquare != null) {
            updateSquare(board, currSelectedSquare);
        } else if (currSelectedPiece != null) {
            updateSquare(board, currSelectedPiece.getLocation());
        } else {
            log("Error SN202: After selection, the variable currSelectedSquare and currSelectedPiece are both still null.");
        }
    }

    public static void showSelectButton() {
        if (!selectShown) {
            bottomVertSplit.setTopComponent(pSelect);
            bottomVertSplit.setBottomComponent(logArea);
            bottomVertSplit.setDividerLocation(0.12);
            bottomVertSplit.revalidate();
            selectShown = true;
        }
    }

    public static void hideSelectButton() {
        if (selectShown) {
            bottomVertSplit.setTopComponent(logArea);
            bottomVertSplit.setBottomComponent(null);
            bottomVertSplit.revalidate();
            selectShown = false;
        }
    }

    public static void updateSquare(Board b, int[] loc) {
        board = b;
        int i = loc[1];
        int j = loc[0];
        ArrayList<Piece> currSquareArray = board.getBoardstate()[i][j];
        if (currSquareArray.size() > 1) {
            //set to multiple pieces image
            try {
                BufferedImage img = ImageIO.read(UI.class.getResource("/pixelarts/multiplepieces.png"));
                if (currSquareArray.get(0).getController() == 1){
                    Utility.invertImage(img);
                }
                Image im = img.getScaledInstance(currWidth / 15, currHeight / 14, Image.SCALE_REPLICATE); //check later
                chessBoard[i][j].setIcon(new ImageIcon(im));
            } catch (IOException e) {
                log("Error SN200: The image at multiplepieces.png is null.");
                log(e.getMessage());
            }

        } else if (currSquareArray.size() == 1) {
            if (currSquareArray.get(0).getIconLocation() != null) {
                // set icon to image
                try {
                    BufferedImage img = ImageIO.read(UI.class.getResource(currSquareArray.get(0).getIconLocation()));
                    if (currSquareArray.get(0).getController() == 1){
                        Utility.invertImage(img);
                    }
                    Image im = img.getScaledInstance(currWidth / 15, currHeight / 14, Image.SCALE_REPLICATE); //check later
                    chessBoard[i][j].setIcon(new ImageIcon(im));
                    chessBoard[i][j].setText(null);

                } catch (IOException e) {
                    log("Error SN201: The image at " + currSquareArray.get(0).getIconLocation() + " is null.");
                    log(e.getMessage());
                }
            } else {
                // set to null
                chessBoard[i][j].setIcon(null);
                chessBoard[i][j].setText(currSquareArray.get(0).getDisplayName());

            }
        } else {
            // set to null
            chessBoard[i][j].setIcon(null);
            chessBoard[i][j].setText(null);
        }

        // Check and highlight / dehighlight square and pieces.
        if (Utility.compareVectors(loc, currSelectedSquare)){
            chessBoard[i][j].setBackground(new Color(Constant.PICKCOLOR));
        }
        else if (Utility.squareInArrayList(selectedSquares, loc)) {
            chessBoard[i][j].setBackground(new Color(Constant.SELECTCOLOR));
        }

        else {
            if ((i + j) % 2 == 0) {
                chessBoard[i][j].setBackground(new Color(0xEBD2B2));
            } else {
                chessBoard[i][j].setBackground(new Color(0x854D24));
            }
        }

        for (Piece p : b.getBoardstate()[i][j]) {
            if (Utility.pieceInArrayList(selectedPieces, p)) {
                p.setSelected(true);
            } else {
                p.setSelected(false);
            }
        }


    }

    public static void updateBoard(Board b) {
        for (int i = 0; i < b.getBoardstate().length; i++) {
            for (int j = 0; j < b.getBoardstate()[i].length; j++) {
                int[] k = {i, j};
                updateSquare(b, k);
            }
        }
    }

//update where pieces appear too

    public static void log(String s) {
        mLog.append("\n" + s);
        mLog.append("\n-------------");
    } // Visually show a message to players in a scrolling chat menu.

    public static void cancel(String message) {
        selectedSquares.clear();
        selectedPieces.clear();
        selectedAction = null;
        select.setVisible(false);
        done.setVisible(false);
        cancel.setVisible(false);
        passTurn.setVisible(true);
        pieceAreaLayout.show(pieceArea, "Blank");
        stepsDone = 0;
        if (message != null) {
            log(message);
        }
        updateBoard(board);
    }

    public static void newSquare() {
        stepsDone = 0;
        selectedSquares.clear();
        selectedPieces.clear();
    }

}
