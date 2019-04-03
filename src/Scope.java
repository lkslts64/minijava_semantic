package symboltable;

import java.util.HashMap;

public class Scope {
    private HashMap <String,String> bindings;

    public Scope(){
        bindings = new HashMap<>();
    }
    public int put(String key,String value){
        if bindings.containsKey(key) {
            //caller or this func should throw parse error in this case...
            return -1;
        }
        bindings.put(key,value);
        return 0;
    }
    //caller should check whether this return null...
    public String get(String key){
        return bindings.get(key);
    }
}
