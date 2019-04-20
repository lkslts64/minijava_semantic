// This class represents a function scope.
package symboltable;

import java.util.HashMap;

public class Scope {
    private HashMap <String,String> bindings; //basic mapping : IDENTIFIER , TYPE
    protected String name;

    public Scope(String n)
    {
        name = n;
        bindings = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public boolean put(String key, String value){
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

    public HashMap<String, String> getBindings() {
        return bindings;
    }

    public boolean hasValue(String value) {
        return bindings.containsValue(value);
    }

}
