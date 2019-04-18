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

    public boolean removeScopeInheritanceChain(symboltable.Scope scope) {
        if (ScopeInheritanceChain.remove(scope) == null)
            return false;
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

    public boolean removeFuncHash(String fn,String cn) {
        PairStrings pairStrings = new PairStrings(fn,cn);
        if(funcHash.remove(pairStrings) == null)
            return false;
        return true;
    }

    public symboltable.Scope getFuncHash(String s1, String s2) {
        PairStrings p = new PairStrings(s1, s2);
        //System.out.println(p);
        symboltable.Scope scope = funcHash.get(p);
        if ( scope == null  ) {
            System.out.println("WHY");
        }
        return scope;
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
        if (classScope == null)
            System.out.println("PANIC .Class doesnt exist");
        return classScope.getVarSize();
    }

    //call this func to find the func size of a base class.
    public int findFuncSizeof(String type) {
        symboltable.ClassScope classScope = getClassHash(type);
        if (classScope == null)
            System.out.println("PANIC .Class doesnt exist");
        return classScope.getFuncSize();
    }

    public String getClass(symboltable.Scope scope) {
        symboltable.ClassScope classScope = getScopeInheritanceChain(scope);
        if ( classScope == null) {
            System.out.println("PANIC.mother chain error");
            return null;
        }
        return classScope.getClassName();

    }

    //should be called after parsing whole source file.
    //knownTypes can't be null, because our source should have at least one type (grammar specifies it).
    public boolean checkUndeclared() {
        System.out.println(undeclared);
        System.out.println(knownTypes);
        try {
            if (knownTypes.containsAll(undeclared)) {
                return true;
            }
        //if undeclared is null, still not unresolved types so we return true.
        } catch (NullPointerException ex) {
            return true;
        }
        //undeclared.removeAll(knownTypes);
        //System.out.println(undeclared);
        //System.out.println(knownTypes);
        //should throw parse error....
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
    public symboltable.FuncSignature getFuncSignature(symboltable.Scope scope) {
        symboltable.ClassScope classScope = getScopeInheritanceChain(scope);
        if ( classScope == null)
            System.out.println("PANIC. mother chain.");
        symboltable.FuncSignature funcSignature = classScope.getFuncBind(scope.getName());
        if ( funcSignature == null)
            System.out.println("PANIC. func sign hash.");
        return funcSignature;
    }

    //find the type of an Identifier given his function scope (i.e class Scope).
    //if type is not found at function scope, search method's class and all parent classes.
    public String findType(symboltable.Scope scope,String id) {
        String type;
        while ( scope != null) {
            type = scope.get(id);
            if (type != null)
                return type;
            scope = getScopeInheritanceChain(scope);
        }
        return null;
    }

    public boolean checkSubType(String left,String right) {
        //check if one of types is primitive (no subtyping).
        if ( !isUserDefinedType(left) || !isUserDefinedType(right))
            return false;
        symboltable.ClassScope base = getClassHash(left);
        symboltable.ClassScope derived = getClassHash(right);
        if ( base == null || derived == null)
            System.out.println(">PANIC:checkSubType error..one of types doesnt exist...");
        //we know that left!=right when we enter this func so dont check if base==derived (initial_derived)...
        derived = getScopeInheritanceChain(derived);
        //climb the motherchain until you reach baseclass . if you dont, we have error...
        while(derived != null) {
            if (derived == base)
                return true;
            derived = getScopeInheritanceChain(derived);
        }
        return false;
    }

    public boolean isUserDefinedType(String type) {
        if ( type == "int" || type == "boolean"|| type == "int[]")
            return false;
        return true;
    }

    public boolean checkOverride(String fn, String cn) {
        symboltable.ClassScope scope = getClassHash(cn);
        symboltable.ClassScope parentscope = getScopeInheritanceChain(scope);
        symboltable.FuncSignature parentfuncSignature = null;
        while (parentscope != null) {
            /*if (parentscope == null) {
                System.out.println("NO OVERRIDE");
                scope.addFuncOffsets(fn, scope.getFuncSize());
                scope.addFuncSize();
                return true;
            }*/
            parentfuncSignature = parentscope.getFuncBind(fn);
            if (parentfuncSignature != null) {
                //add func to offset list and add funcsizecounter.
                /*scope.addFuncOffsets(fn, scope.getFuncSize());
                scope.addFuncSize();
                return true;*/
                break;
            }
            parentscope = getScopeInheritanceChain(parentscope);
        }
        if ( parentscope == null) {
            scope.addFuncOffsets(fn, scope.getFuncSize());
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
                        System.out.println(">Error:Funcion overloading detected at class " + cn + " at function " + fn + " (arg " + i +  " type is different). Thats not possible in minijava");
                        return false;
                    }
                }
                //we dont need to addFuncSize neither to add to funcoffsets...
                //additionaly, we dont need to check if other classes have same func signature or
                //if there is overloading because the check has been made from the class we found now.
                System.out.println("this func overrides another from one of the parent class(es)");
                return true;

            } else {
                System.out.println(">Error:Funcion overloading detected at class " + cn + " at function " + fn + ". Different # of arguments: (base class function has " + argp.size() + " arguments and this function has " + arg.size() + " ). Thats not possible in minijava");
                return false;
            }
        } else {
            System.out.println(">Error:Funcion overloading detected at class " + cn + " at function " + fn + " (different return values). Thats not possible in minijava");
            return false;
        }
    }

    public void print_offsets() {
        for ( String s : classHash.keySet()) {
            symboltable.ClassScope classScope = classHash.get(s);
            String cn = classScope.getClassName() + ".";
            for (PairStringInteger p : classScope.getVaroffsets()) {
                System.out.println(cn + p);
            }
            for (PairStringInteger p : classScope.getFuncoffsets()) {
                System.out.println(cn + p);
            }
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
            //return funcHash.get(p);
            HashMap<String,String> funcbind = scope.getBindings();
            for (String s2 : funcbind.keySet()) {
                String s3 = funcbind.get(s2);
                System.out.println(s3 + s2);
            }

        }
    }
    public void display_allFuncs() {
        for (PairStrings p : funcHash.keySet()) {
            System.out.println(p);
            symboltable.Scope scope = getFuncHash(p.s1,p.s2);
            if ( scope == null)
                System.out.println("THE ABOVE IS NULL");
        }

    }
}
//restore these fields to private.
class PairStrings {
    public String s1;
    public String s2;

    public PairStrings(String fn,String cn){
        s1 = fn;
        s2 = cn;
    }

    @Override
    public String toString() {
        return s1 + " " + s2;
    }

    @Override
    public boolean equals(Object p) {
        if (p instanceof PairStrings) {
            PairStrings pairStrings = (PairStrings) p;
            return (this.s1 == pairStrings.s1 && this.s2 == pairStrings.s2);
        }
        else
            System.out.println("PANIC . Pairstrings");
            return false;
    }

    @Override
    public int hashCode() {
        int result = 0;
        for ( int i = 0; i< this.s1.length(); i++ ) {
            result += s1.charAt(i);
        }
        for ( int i = 0; i< this.s2.length(); i++ ) {
            result += s2.charAt(i);

        }
        return result;
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
