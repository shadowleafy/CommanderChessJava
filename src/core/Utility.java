package core;

import actions.*;
import pieces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Utility {
  
  public static boolean inArrayList(ArrayList<String> object, String target){
    for (String k : object){
      if (k.equals(target)){
        return true;
      }
    }
    return false;
  }
  
  public static int arrayListIndexOf(ArrayList<String> object, String target){
    for (int i = 0; i < object.size(); i++){
      if (object.get(i).equals(target)){
        return i;
      }
    }
    return -1;
  }
  
  // Precondition: The last character can't be |.
  public static ArrayList<String> formArrayList(String input){
    String storage = input;
    ArrayList<String> output = new ArrayList<String>();
    while (storage.indexOf("|") != -1){
      output.add(storage.substring(0, storage.indexOf("|")));
      storage = storage.substring(storage.indexOf("|")+1);
    }
    return output;
  }
  
  public static String toString2DArray(String[][] input){
    String output = "";
    for (String[] arr : input){
      for (String s : arr){
        output += s;
      }
      output += "\n";
    }
    return output;
  }
  
  public static void noRepeatAdd(ArrayList<String> object, String target){
    if (!inArrayList(object, target)){
      object.add(target);
    }
  }
  
  public static int[] copyArray(int[] input){
    int[] output = new int[input.length];
    for (int i = 0; i < input.length; i++){
      output[i] = input[i];
    }
    return output;
  }
  
  public static String[] copyArray(String[] input){
    String[] output = new String[input.length];
    for (int i = 0; i < input.length; i++){
      output[i] = input[i];
    }
    return output;
  }
  
  public static int[] sumVectors(int[] a, int[] b){
    if (a.length == b.length){
      int[] c = new int[a.length];
      for (int i = 0; i < a.length; i++){
        c[i] = a[i] + b[i];
      }
      return c;
    }
    else{
      return null;
    }
  }

  public static int[] diffVectors(int[] a, int[] b){
    if (a.length == b.length){
      int[] c = new int[a.length];
      for (int i = 0; i < a.length; i++){
        c[i] = a[i] - b[i];
      }
      return c;
    }
    else{
      return null;
    }
  }

  public static boolean compareVectors(int[] a, int[] b){
    if (a.length == b.length){
      for (int i = 0; i < a.length; i++){
        if (a[i] != b[i]){
          return false;
        }
        return true;
      }
    }
    return false;
  }

  public static int[] formVector(String input){
    int[] output = new int[countInString(input, ",")];
    for (int i = 0; i < output.length; i++){
      output[i] = Integer.parseInt(input.substring(0, input.indexOf(",")));
      input = input.substring(input.indexOf(",")+1);
    }
    return output;
  }
  
  public static int[] collisionPoint(Board board, int[] start, int[] rayValue){
    int[] newStart = copyArray(start);
    sumVectors(newStart, rayValue);
    ArrayList[][] gameboard = board.getBoardstate();
    while (gameboard[newStart[1]][newStart[0]].size() == 0){
      sumVectors(newStart, rayValue);
      if (newStart[0] > 7 || newStart[0] < 0 || newStart[1] > 7 || newStart[1] < 0){
        return null;
      }
    }
    return newStart;
  }
  
  public static String convertChessNotation(int[] loc){
    return Constants.LETTERS[loc[0]] + (loc[1] + 1);
  }

  public static int countInString(String input, String target){
    int count = 0;
    while (input.indexOf(target) != -1){
      count++;
      input = input.substring(input.indexOf(target) + 1);
    }
    return count;
  }

  public static Piece idToPiece(String id){
    Piece output = null;
    return output;
  }

  public static Action idToActionObj(String id, Piece owner){
    Action output = null;
    switch(id){
      case "pawn_move":
        output = new PawnMove(owner);
      break;
    }
    return output;
  }
}