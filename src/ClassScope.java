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

    public ClassScope(int initvarsz,int initfuncsize,String cn) {
        super(cn);
        funcbindings = new HashMap<>();
        funcoffsets = new Vector<>();
        varoffsets = new Vector<>();
        varsize = initvarsz;
        funcsize = initfuncsize;
    }

    @Override
    public boolean put(String key, String value) {
        if ( super.put(key, value) == false ) {
            System.out.println("KEY EXISTS");
            return false;           //we dont want to provoke to further actions if this fails...

        }
        else {
            System.out.println("inserted to hashmap");
        }
        if (value.equals("int")) {
            PairStringInteger pairStringInteger = new PairStringInteger(key,varsize);
            varoffsets.add(pairStringInteger);
            varsize += 4;
        }
        else if ( value.equals("boolean")) {
            PairStringInteger pairStringInteger = new PairStringInteger(key,varsize);
            varoffsets.add(pairStringInteger);
            varsize += 1;
        }
        else {
            PairStringInteger pairStringInteger = new PairStringInteger(key,varsize);
            varoffsets.add(pairStringInteger);
            varsize += 8;

        }
        //return super.put(key, value);
        return true;
    }

    public String getClassName() {
        return name;
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

    public boolean removeFuncBind(String key) {
        if (funcbindings.remove(key) == null)
            return false;
        return true;
    }
    //this can return null...
    public symboltable.FuncSignature getFuncBind(String key) {
        return funcbindings.get(key);
    }

    public HashMap<String, symboltable.FuncSignature> getFuncbindings() {
        return funcbindings;
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
    public void addFuncSize() {
        funcsize += 8;
    }
}

