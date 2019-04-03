package symboltable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;

public class SymbolTable {

    private HashMap<symboltable.Scope, symboltable.ClassScope> ScopeInheritanceChain;
    private HashMap<String, symboltable.ClassScope> classHash;   // Classname,classScope .easy access to class
    private HashMap<PairStrings, symboltable.Scope> funcHash; // key_funcname,classScope
    //second field of undeclared should be null if vardecl was at function scope.
    private HashSet<String> undeclared;              // Type,Class_that_type_found .Types that have not been declared yet
    private HashSet<String> knownTypes;                     //all known types .


    public SymbolTable() {
        ScopeInheritanceChain = new HashMap<>();
        classHash = new HashMap<>();
        funcHash = new HashMap<>();
        undeclared = new HashSet<>();
        knownTypes = new HashSet<>();
    }

    public boolean putScopeInheritanceChain(symboltable.Scope scope, symboltable.ClassScope classScope) {
        if (ScopeInheritanceChain.containsKey(scope)) {
            //caller or this func should throw parse error in this case...
            return false;
        }
        ScopeInheritanceChain.put(scope, classScope);
        return true;
    }

    //caller should check whether this return null...
    public symboltable.ClassScope getScopeInheritanceChain(symboltable.Scope scope) {
        return ScopeInheritanceChain.get(scope);
    }

    public putClassHash(String type, symboltable.ClassScope cs) {
        if (classHash.containsKey(type)) {
            //caller or this func should throw parse error in this case...
            return false;
        }
        classHash.put(type, cs);
        return true;
    }

    //caller should check whether this return null...
    public symboltable.ClassScope getClassHash(String key) {
        return classHash.get(key);
    }

    public putFuncHash(String s1, String s2, symboltable.Scope sc) {
        PairStrings p = new PairStrings(s1, s2);
        if (funcHash.containsKey(p)) {
            return false;
        }
        funcHash.put(p, sc);
        return true;
    }

    public symboltable.Scope getFuncHash(String s1, String s2) {
        PairStrings p = new PairStrings(s1, s2);
        return funcHash.get(p);
    }

    public boolean isDeclared(String type) {
        return knownTypes.contains(type);
    }

    public addKnownTypes(String type) {
        knownTypes.add(type);
    }

    public addUndeclared(String type) {
        undeclared.add(type);
    }

    public int findSizeof(String type) {
        symboltable.ClassScope classScope = getClassHash(type);
        return classScope.getVarSize();
    }

    //should be called after parsing whole source file.
    public boolean checkUndeclared() {
        try {
            if (knownTypes.containsAll(undeclared)) {
                return true;
            }
        } catch (NullPointerException ex) {
            return true;
        }
        //should throw parse error....
        System.out.println("Parse Error, undeclared type(s)");
        return false;

    }

    public boolean checkOverride(String fn, String cn) {
        symboltable.ClassScope scope = getClassHash(cn);
        symboltable.ClassScope parentscope = getScopeInheritanceChain(scope);
        if (parentscope == null) {
            System.out.println("NO OVERRIDE");
            scope.addFuncSize(8);
            return true;
        }
        symboltable.FuncSignature parentfuncSignature = parentscope.getFuncBind(fn);
        if (parentfuncSignature == null) {
            System.out.println("NO OVERRIDE");
            scope.addFuncSize(8);
            return true;
        }
        //compare the two function signatures to examine whether is overloading or overriding.
        symboltable.FuncSignature funcSignature = scope.getFuncBind(fn);
        if (funcSignature.getReturnType() == parentfuncSignature.getReturnType()) {
            Vector<String> arg = funcSignature.getArgTypes();
            Vector<String> argp = parentfuncSignature.getArgTypes();
            if (argp.size() == arg.size()) {
                for (int i = 0; i < argp.size(); i++) {
                    if (arg[i] == argp[i])
                        continue;
                    else {
                        System.out.println("Funcion overloading detected (different arg types). Thats not possible in minijava");
                        return false;
                    }
                }
                return true;
            }
            else {
                System.out.println("Funcion overloading detected (different # of args). Thats not possible in minijava");
                return false;
            }
        }
        else {
            System.out.println("Funcion overloading detected (different return values). Thats not possible in minijava");
            return false;
        }
    }
}
class PairStrings {
    private String s1;
    private String s2;

    public PairStrings(String fn,String cn){
        s1 = fn;
        s2 = cn;
    }
}
    /*public fillMissingOffsets(HashSet<String> set) {
        for (symboltable.ClassScope c : classHash.values()) {
            for (String s : set) {
                if ( c.hasValue(s)) {
                    int sz = findSizeof(s);

                    //go to vector of offsets and fill it ...
                }
            }
        }
    }*/
