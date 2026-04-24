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
}