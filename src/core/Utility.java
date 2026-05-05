package core;

import actions.*;
import pieces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Utility {

  public static int gcd(int a, int b){
    int q = a / b;
    int r = a % b;
    int lastR = b;
    while (r != 0){
      lastR = r;
      int temp = q / r;
      r = q % r;
      q = temp;
    }
    return lastR;
  }
  
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

  public static ArrayList<Action> arrayActionToArrayList(Action[] arr){
    ArrayList<Action> output = new ArrayList<Action>();
    for (Action obj : arr){
      output.add(obj);
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

  public static ArrayList<Piece>[][] copyBoardstate(ArrayList<Piece>[][] boardstate){
    ArrayList<Piece>[][] output = new ArrayList[8][8];
    for (int i = 0; i < boardstate.length; i++){
      for (int j = 0; j < boardstate[i].length; j++){
        for (int k = 0; k < boardstate[i][j].size(); k++){
          output[i][j].add(boardstate[i][j].get(k));
        }
      }
    }
    return output;
  }

  public static void swapValues(int[] arr, int indexOne, int indexTwo){
    int temp = arr[indexOne];
    arr[indexOne] = arr[indexTwo];
    arr[indexTwo] = temp;
  }

  public static int[] simplifyVector(int[] input){
    int[] output = copyArray(input);
    if (output[0] == 0){
      return formVector("0,1");
    }
    else if (output[1] == 0){
      return formVector("1,0");
    }
    int gcd = gcd(output[0], output[1]);
    output[0] /= gcd;
    output[1] /= gcd;
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

  public static int[] colorshiftVector(int[] a, int color){
    int[] output = copyArray(a);
    if (color == 0){
      return output;
    }
    else{
      output[1] *= -1;
      return output;
    }
  }

  public static int[] colorshiftLocation(int[] a, int color){
    int[] output = copyArray(a);
    if (color == 0){
      return output;
    }
    else{
      output[1] = 7 - output[1];
      return output;
    }
  }

  public static boolean compareVectors(int[] a, int[] b){
    if (a.length == b.length){
      for (int i = 0; i < a.length; i++){
        if (a[i] != b[i]){
          return false;
        }
      }
      return true;
    }
    return false;
  }

  public static int[] sortedAbs(int[] input){
    int[] output = copyArray(input);
    for (int i = 0; i < input.length; i++){
      int minimum = Math.abs(output[i]);
      int mindex = 0;
      for (int j = i+1; j < input.length; j++){
        if (Math.abs(output[j]) < minimum){
          minimum = Math.abs(output[j]);
          mindex = j;
        }
        swapValues(output, i, mindex);
      }
    }
    return output;
  }

  public static boolean compareVectorsSymmetric(int[] a, int[] b){

    if (a.length == b.length){
      int[] aSorted = sortedAbs(a);
      int[] bSorted = sortedAbs(b);
      for (int i = 0; i < a.length; i++){
        if (!(aSorted[i] == bSorted[i])){
          return false;
        }
      }
      return true;
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
    return Constant.LETTERS[loc[0]] + (loc[1] + 1);
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
// make sure that movement is not simplifyable if you are doing ray
  public static boolean checkMoveValidity(Board board, int[] start, int[] end, int[] movement, boolean passthrough, boolean symmetric, boolean continuous){
    if (continuous){
      if (passthrough){
        if (symmetric){
          return (compareVectorsSymmetric(movement, simplifyVector(diffVectors(end, start))) && board.getBoardstate()[end[1]][end[0]].size() == 0);
        }
        else{
          return (compareVectors(movement, simplifyVector(diffVectors(end, start))) && board.getBoardstate()[end[1]][end[0]].size() == 0);
        }
      }
      else{
        if (symmetric){
          return (compareVectorsSymmetric(movement, simplifyVector(diffVectors(end, start))) && collisionPoint(board, start, diffVectors(end, start)) == null);
        }
        else{
          return (compareVectors(movement, simplifyVector(diffVectors(end, start))) && collisionPoint(board, start, diffVectors(end, start)) == null);
        }
      }
    }
    else{
      if (passthrough){
        if (symmetric){
          return (compareVectorsSymmetric(movement, diffVectors(end, start)) && board.getBoardstate()[end[1]][end[0]].size() == 0);
        }
        else{
          return (compareVectors(movement, diffVectors(end, start)) && board.getBoardstate()[end[1]][end[0]].size() == 0);
        }
      }
      else{
        if (symmetric){
          return (compareVectorsSymmetric(movement, diffVectors(end, start)) && collisionPoint(board, start, diffVectors(end, start)) == null);
        }
        else{
          return (compareVectors(movement, diffVectors(end, start)) && collisionPoint(board, start, diffVectors(end, start)) == null);
        }
      }
    }
  }

  public static boolean checkCaptureValidity(Board board, Piece startPiece, Piece endPiece, int[] movement, boolean passthrough, boolean symmetric, boolean continuous){
    if (endPiece.getController() == startPiece.getController()){
      return false;
    }
    else{
      ArrayList<Piece>[][] alterBoardstate = copyBoardstate(board.getBoardstate());
      int[] start = startPiece.getLocation();
      int[] end = endPiece.getLocation();
      alterBoardstate[end[1]][end[0]] = new ArrayList<Piece>();
      Board alterBoard = new Board(8, 8, board.getWhitePlayer(), board.getBlackPlayer(), board.getGame(), alterBoardstate);
      return checkMoveValidity(alterBoard, start, end, movement, passthrough, symmetric, continuous);
    }
  }

}