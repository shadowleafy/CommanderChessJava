import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Action {
  
  private String type;
  private String actionId;
  private String displayName;
  private ArrayList<String> tags;
  
  public Action(){
    tags = new ArrayList<String>();
  }
  
  public String getType(){
    return type;
  }
  
  public String getActionId(){
    return actionId;
  }
  
  public String getDisplayName(){
    return displayName;
  }
  
  public boolean canUse(){
    return true;
  }
  
  public void activate(){
    if (canUse()){
      onUse();
    }
  }
  
  public void onUse(){}
  
}