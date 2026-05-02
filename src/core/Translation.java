package core;

import actions.*;
import pieces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Translation{

    private static Map<String, String> selectedDictionary;

    public static void setDict(String language){
        selectedDictionary = Constant.DICT_MAP.get(language);
    }

    public static String getStatic(String id){
        return selectedDictionary.get(id);
    }

    // For dynamic messages, send in the format of <Object.property>.
    public static String convertDynamic(String message, Game game, Board board, Piece piece, Action action, Player player){
        String object = message.substring(0, message.indexOf("."));
        String property = message.substring(message.indexOf(".")+1);
        String output = "";
        switch(object){
            case "ActivePlayer" : 
                Player ap = board.getActivePlayer();
                switch(property){};
            break;

            case "PassivePlayer" :
                Player pp = board.getPassivePlayer();
                switch(property){};
            break;

            case "Piece" : 
                switch(property){};
            break;

            case "Action" : 
                switch(property){};
            break;

            case "Board" : 
                switch(property){};
            break;
        }
        return output;

    }

    public static String getDynamic(String id, Game game, Board board, Piece piece, Action action, Player player){
        String preMessage = selectedDictionary.get(id);
        while (preMessage.indexOf("<") != -1){
            String conversion = convertDynamic(preMessage.substring(preMessage.indexOf("<")+1, preMessage.indexOf(">")), game, board, piece, action, player);
            preMessage = preMessage.substring(0, preMessage.indexOf("<")) + conversion + preMessage.substring(preMessage.indexOf(">")+1);
        }
        return preMessage;
    }

}