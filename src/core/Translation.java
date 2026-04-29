package core;

import actions.*;
import pieces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Translation{

    public static final Map<String, String> ENGLISH = Map.ofEntries(

        // System Messages
        Map.entry("turn_start", "Turn Start!"),

        // Menu Buttons

        Map.entry("end_turn_button", "End Turn"),

        // Action display names

        Map.entry("pawn_move_display_name", "Pawn Move")

        // Piece display names
    );

    public static final Map<String, String> CHINESE = Map.of(

    );

    public static final Map<String, String> JAPANESE = Map.of(

    );

}