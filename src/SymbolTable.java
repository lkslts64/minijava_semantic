package symboltable;

import symboltable.PairStringInteger;

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
        //add all primitive types
        knownTypes.add("int");
        knownTypes.add("int[]");
        knownTypes.add("boolean");
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

    public boolean putClassHash(String type, symboltable.ClassScope cs) {
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

    public boolean putFuncHash(String fn, String cn, symboltable.Scope sc) {
        PairStrings p = new PairStrings(fn, cn);
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

    public void addKnownTypes(String type) {
        knownTypes.add(type);
    }

    public void addUndeclared(String type) {
        undeclared.add(type);
    }

    //call this func to find the var size of a base class.
    public int findVarSizeof(String type) {
        symboltable.ClassScope classScope = getClassHash(type);
        return classScope.getVarSize();
    }

    //call this func to find the func size of a base class.
    public int findFuncSizeof(String type) {
        symboltable.ClassScope classScope = getClassHash(type);
        return classScope.getFuncSize();
    }

    //should be called after parsing whole source file.
    //knownTypes can't be null, because our source should have at least one type (grammar specifies it).
    public boolean checkUndeclared() {
        try {
            if (knownTypes.containsAll(undeclared)) {
                return true;
            }
        //if undeclared is null, still not unresolved types so we return true.
        } catch (NullPointerException ex) {
            return true;
        }
        //should throw parse error....
        System.out.println("Parse Error, undeclared type(s)");
        return false;
    }

    //this func should be called after symbol table creation.
    //find return type of a func given its primary key.
    public String getReturnType(String fn,String cn) {
        symboltable.ClassScope scope = getClassHash(cn);
        if (scope == null)
            return null;
        symboltable.FuncSignature funcSignature = scope.getFuncBind(fn);
        if (funcSignature == null)
            return null;
        return funcSignature.getReturnType();
    }

    public boolean checkOverride(String fn, String cn) {
        symboltable.ClassScope scope = getClassHash(cn);
        symboltable.ClassScope parentscope = getScopeInheritanceChain(scope);
        if (parentscope == null) {
            System.out.println("NO OVERRIDE");
            scope.addFuncOffsets(fn,scope.getFuncSize());
            scope.addFuncSize();
            return true;
        }
        symboltable.FuncSignature parentfuncSignature = parentscope.getFuncBind(fn);
        if (parentfuncSignature == null) {
            System.out.println("NO OVERRIDE");
            //add func to offset list and add funcsizecounter.
            scope.addFuncOffsets(fn,scope.getFuncSize());
            scope.addFuncSize();
            return true;
        }
        //compare the two function signatures to examine whether is overloading or overriding.
        symboltable.FuncSignature funcSignature = scope.getFuncBind(fn);
        if (funcSignature.getReturnType() == parentfuncSignature.getReturnType()) {
            Vector<String> arg = funcSignature.getArgTypes();
            Vector<String> argp = parentfuncSignature.getArgTypes();
            if (argp.size() == arg.size()) {
                for (int i = 0; i < argp.size(); i++) {
                    if (arg.get(i) == argp.get(i))
                        continue;
                    else {
                        System.out.println("Funcion overloading detected (different arg types). Thats not possible in minijava");
                        return false;
                    }
                }
                //we dont need to addFuncSize neither to add to funcoffsets...
                System.out.println("THIS FUNC OVERRIDES ANOTHER FROM PARENT CLASS");
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
    public void display_contents() {
        //classes...
        for ( String s : classHash.keySet()) {
            System.out.println("---------------------");
            System.out.println(s);
            System.out.println("---------------------");
            symboltable.ClassScope classScope = classHash.get(s);
            HashMap<String, symboltable.FuncSignature> funcSignatureHashMap = classScope.getFuncbindings();
            String meta = classScope.getClassName() + " " + classScope.getFuncSize() + " " + classScope.getVarSize();
            System.out.println(meta);
            for ( String s2 : funcSignatureHashMap.keySet()) {
                symboltable.FuncSignature funcSignature = classScope.getFuncBind(s2);
                Vector<String> argvec = funcSignature.getArgTypes();
                System.out.println("args:");
                for (String s3 : argvec) {
                    System.out.println(s3);
                }
                System.out.print("return type:");
                System.out.println(funcSignature.getReturnType());
            }
            System.out.println("----------------------");
            System.out.println("offsets");
            System.out.println("----------------------");
            for (PairStringInteger p : classScope.getVaroffsets()) {
                System.out.println(p);
            }
            System.out.println("FuncOFFSETS");
            for (PairStringInteger p : classScope.getFuncoffsets()) {
                System.out.println(p);
            }
        }
        System.out.println("FUNCTIONSSSSS");
        //functions...
        for (PairStrings p : funcHash.keySet()) {
            System.out.println("---------------------");
            System.out.println(p);
            System.out.println("---------------------");
            symboltable.Scope scope = funcHash.get(p);
            HashMap<String,String> funcbind = scope.getBindings();
            for (String s2 : funcbind.keySet()) {
                String s3 = funcbind.get(s2);
                System.out.println(s3 + s2);
            }

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

    @Override
    public String toString() {
        return s1 + " " + s2;
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
