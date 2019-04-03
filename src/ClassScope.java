package symboltable;


import java.util.HashMap;
import java.util.Vector;

public class ClassScope extends symboltable.Scope {
    // FuncName,FunctionSignature Map. if user wants to search fast for a func signature inside a class.
    private HashMap<String, symboltable.FuncSignature> funcbindings;
    //we first add values to vectors and after increment sizes;
    private Vector<PairStringInteger> funcoffsets;
    private Vector<PairStringInteger> varoffsets;
    private int varsize;
    private int funcsize;

    public ClassScope(int initvarsz,int initfuncsize) {
        super();
        funcbindings = new HashMap<>();
        funcoffsets = new Vector<>();
        varoffsets = new Vector<>();
        varsize = initvarsz;
        funcsize = initfuncsize;
    }
    public boolean putFuncBind(String key, symboltable.FuncSignature value) {
        if (funcbindings.containsKey(key)) {
            //caller or this func should throw parse error in this case...
            return false;
        }
        funcbindings.put(key,value);
        //TODO: go check if we override a function from parent class ( if we have a parent) and set offset accordingly...
        return true;
    }

    //this can return null...
    public symboltable.FuncSignature getFuncBind(String key) {
        return funcbindings.get(key);
    }

    public void addFuncOffsets(String s, Integer i){
        PairStringInteger p = new PairStringInteger(s,i);
        funcoffsets.add(p);
    }
    public void addVarOffsets(String s,Integer i){
        PairStringInteger p = new PairStringInteger(s,i);
        varoffsets.add(p);
    }

    public Vector<PairStringInteger> getFuncoffsets() {
        return funcoffsets;
    }

    public Vector<PairStringInteger> getVaroffsets() {
        return varoffsets;
    }

    public void setVarSize(int varsize) {
        this.varsize = varsize;
    }
    public int getVarSize() {
        return varsize;
    }
    public void addVarSize(int off) {
        varsize += off;
    }
    public void setFuncSize(int funcsize) {
        this.funcsize = funcsize;
    }
    public int getFuncSize() {
        return funcsize;
    }
    public void addFuncSize(int off) {
        funcsize += off;
    }
}

class PairStringInteger {
    private String funcname;
    private Integer offset;
    public PairStringInteger(String s,Integer i ){
        funcname = s;
        offset = i;
    }
}
