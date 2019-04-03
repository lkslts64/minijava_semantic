package symboltable;

import java.util.HashMap;
import java.util.Vector;

public class ClassScope extends symboltable.Scope {
    // FuncName,FunctionSignature Map. if user wants to search fast for a func signature inside a class.
    private HashMap<String, symboltable.FuncSignature> funcbindings;
    private Vector<Integer> funcoffsets;
    private Vector<Integer> varoffsets;

    public ClassScope() {
        super();
        funcbindings = new HashMap<>();
        funcoffsets = new Vector<>();
        varoffsets = new Vector<>();
    }
    public int put(String key, symboltable.FuncSignature value) {
        if (funcbindings.containsKey(key)) {
            //caller or this func should throw parse error in this case...
            return -1;
        }
        funcbindings.put(key,value);
        //TODO: go check if we override a function from parent class ( if we have a parent) and set offset accordingly...
        return 0;
    }

    public addFuncOffsets(Integer i){
        funcoffsets.add(i);
    }
    public addVarOffsets(Integer i){
        varoffsets.add(i);
    }

    public Vector<Integer> getFuncoffsets() {
        return funcoffsets;
    }

    public Vector<Integer> getVaroffsets() {
        return varoffsets;
    }
}
