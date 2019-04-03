package symboltable;

import java.util.HashMap;

public class Scope {
    private HashMap <String,String> bindings; //basic mapping : IDENTIFIER , TYPE

    public Scope(){
        bindings = new HashMap<>();
    }
    public boolean put(String key,String value){
        if (bindings.containsKey(key)) {
            //caller or this func should throw parse error in this case...
            return false;
        }
        bindings.put(key,value);
        return true;
    }
    //caller should check whether this return null...
    public String get(String key){
        return bindings.get(key);
    }
    public boolean hasValue(String value) {
        return bindings.containsValue(value);
    }

}
