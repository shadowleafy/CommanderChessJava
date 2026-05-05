package core;

import actions.*;
import pieces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Constant {
  
  public static final int[][] DEFAULT_BOARDSTATE = {
    {3, 2, 1, 0, -1, 1, 2, 3},
    {4, 4, 4, 4, 4, 4, 4, 4},
    {-1, -1, -1, -1, -1, -1, -1, -1},
    {-1, -1, -1, -1, -1, -1, -1, -1}}; // boardstates can't exceed size 4.
  public static final String SYSTEM_NAME = "SYSTEM";
  public static final String[] LETTERS = {"a", "b", "c", "d", "e", "f", "g", "h"};
  public static final String[] COMMANDER_IDS = {};
  public static final Map<String, String[]> COMMANDER_PIECES = Map.ofEntries(
    
  );
  public static final Map<String, int[][]> SETUPS = Map.ofEntries(

  );

  // Translation stuffs :3

  public static final Map<String, String> ENGLISH_DICT = Map.ofEntries();
  public static final Map<String, String> CHINESE_DICT = Map.ofEntries();
  public static final Map<String, String> JAPANESE_DICT = Map.ofEntries();

  public static final Map<String, Map<String, String>> DICT_MAP = Map.ofEntries(

    Map.entry("english", ENGLISH_DICT),
    Map.entry("chinese", CHINESE_DICT),
    Map.entry("japanese", JAPANESE_DICT)

  );

}