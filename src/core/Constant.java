package core;

import actions.*;
import pieces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Constant {
  
  public static final int[][] DEFAULT_BOARDSTATE = {
    {4, 3, 2, 1, 0, 2, 3, 4},
    {5, 5, 5, 5, 5, 5, 5, 5},
    {-1, -1, -1, -1, -1, -1, -1, -1},
    {-1, -1, -1, -1, -1, -1, -1, -1}}; // boardstates can't exceed size 4.
  public static final String SYSTEM_NAME = "SYSTEM";
  public static final String[] LETTERS = {"a", "b", "c", "d", "e", "f", "g", "h"};
  public static final String[] COMMANDER_IDS = {};
  public static final Map<String, String[]> COMMANDER_PIECES = Map.ofEntries(
    
  );
  public static final Map<String, int[][]> SETUPS = Map.ofEntries(
          Map.entry("king", DEFAULT_BOARDSTATE)
  );

  // Translation stuffs :3

  public static final String[] LANGUAGES = {"English", "Chinese", "Japanese"};

  public static final Map<String, String> ENGLISH_DICT = Map.ofEntries(
    // Display Names of Pieces
    Map.entry("king_display", "Basic King"),
    Map.entry("queen_display", "Basic Queen"),
    Map.entry("bishop_display", "Basic Bishop"),
    Map.entry("knight_display", "Basic Knight"),
    Map.entry("rook_display", "Basic Rook"),
    Map.entry("pawn_display", "Basic Pawn"),

    Map.entry("ackle_display", "Ackle, Crafted in Code"),
    Map.entry("arch_display", "Arch, Programmed Guardian"),
    Map.entry("bit_display", "Bit"),
    Map.entry("source_code_display", "Source Code"),
    Map.entry("parallel_thread_display", "Parallel Thread"),
    Map.entry("turing_machine_display", "Turing Machine")
    
  );
  public static final Map<String, String> CHINESE_DICT = Map.ofEntries(
    // Display Names of Pieces
    Map.entry("ackle_display", "阿克爾，程式碼人"),
    Map.entry("arch_display", "阿奇，程式監護人"),
    Map.entry("bit_display", "位元"),
    Map.entry("source_code_display", "原始程式碼"),
    Map.entry("parallel_thread_display", "平行過程"),
    Map.entry("turing_machine_display", "圖靈機器")
  );
  public static final Map<String, String> JAPANESE_DICT = Map.ofEntries(
    Map.entry("ackle_display", "アコル、コードで作り上げる"),
    Map.entry("arch_display", "アーチ、プログラムな保護者"),
    Map.entry("bit_display", "ビット"),
    Map.entry("source_code_display", "ソースコード"),
    Map.entry("parallel_thread_display", "平行なプロセス"),
    Map.entry("turing_machine_display", "チューリングな機械")
  );

  public static final Map<String, Map<String, String>> DICT_MAP = Map.ofEntries(

    Map.entry("English", ENGLISH_DICT),
    Map.entry("Chinese", CHINESE_DICT),
    Map.entry("Japanese", JAPANESE_DICT)

  );

  public static final String SELECTCOLOR = "0xE0B8FF";
}
