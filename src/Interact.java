import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Interact {
  
  public static void log(String message){
    String output = "[" + Constants.SYSTEM_NAME + "]: " + message;
    System.out.println(output);
  }
  
  public static void messageFrom(String speaker, String message){
    String output = "[" + speaker + "]: " + message;
    System.out.println(output);
  }
  
  public static String selectOption(ArrayList<String> options, String playerName){
    String output = playerName + ", please select an option. Your options are:\n";
    for (String option : options){
      output += option + "\n";
    }
    log(output);
    Scanner read = new Scanner(System.in);
    String selection = "";
    while (!Utility.inArrayList(options, selection)){
      selection = read.nextLine();
      if (!Utility.inArrayList(options, selection)){
        log("Please select a valid option.");
      }
    }
    return selection;
  }
  
}