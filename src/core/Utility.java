package core;

import actions.*;
import pieces.*;

import java.awt.*;
import java.awt.image.BufferedImage;
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

   public static boolean squareInArrayList(ArrayList<int[]> object, int[] target){
    for (int[] k : object){
      if (compareVectors(target, k)){
        return true;
      }
    }
    return false;
  }

  public static boolean pieceInArrayList(ArrayList<Piece> object, Piece target){
    for (Piece k : object){
      if (target == k) {
        return true;
      }
    }
    return false;
  }

  public static int findSquareInArrayList(ArrayList<int[]> object, int[] target){
    for (int i = 0; i < object.size(); i++){
      if (compareVectors(target, object.get(i))){
        return i;
      }
    }
    return -1;
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
    while (storage.contains("|")){
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

  public static String printArray(int[] i){
    String output = "";
    for (int k : i){
      output += k;
    }
    return output;
  }

  public static boolean compareVectors(int[] a, int[] b){
    if (a == null || b == null){
      return false;
    }
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

  public static int magnitudeSq(int[] a){
    int output = 0;
    for (int k : a){
      output += k*k;
    }
    return output;
  }

  public static int[] formVector(String input){
    int[] output = new int[countInString(input, ",")];
    for (int i = 0; i < output.length; i++){
      output[i] = Integer.parseInt(input.substring(0, input.indexOf(",")));
      input = input.substring(input.indexOf(",")+1);
    }
    return output;
  }

  public static int[] colorshift(int[] input, int color){
    int[] output = copyArray(input);
    if (color == 1){
      output[1] = 7-output[1];
    }
    return output;
  }

  public static int[] colorshiftDelta(int[] input, int color){
    int[] output = copyArray(input);
    if (color == 1){
      output[1] *= -1;
    }
    return output;
  }

  
  public static String convertChessNotation(int[] loc){
    return Constant.LETTERS[loc[0]] + (loc[1] + 1);
  }

  public static int countInString(String input, String target){
    int count = 0;
    while (input.contains(target)){
      count++;
      input = input.substring(input.indexOf(target) + 1);
    }
    return count;
  }

  public static Piece idToPiece(String id, int ctrl, int[] loc, Board board){
      return switch (id) {
          case "king" -> new King(ctrl, loc, board);
          case "queen" -> new Queen(ctrl, loc, board);
          case "bishop" -> new Bishop(ctrl, loc, board);
          case "knight" -> new Knight(ctrl, loc, board);
          case "rook" -> new Rook(ctrl, loc, board);
          case "pawn" -> new Pawn(ctrl, loc, board);
          default -> null;
      };
  }

  public static Action idToActionObj(String id, Piece owner){
    Action output = null;
    switch(id){
      
    }
    return output;
  }

  public static void invertImage(BufferedImage image){
    for (int i = 0; i < image.getWidth(); i++){
      for (int j = 0; j < image.getHeight(); j++){
        int colorCode = image.getRGB(i, j);
        Color color = new Color(colorCode, true);
        color = new Color(255-color.getRed(), 255-color.getGreen(), 255-color.getBlue());
        if ((colorCode>>24) != 0x00) {
          image.setRGB(i, j, color.getRGB());
        }
      }
    }
  }

}