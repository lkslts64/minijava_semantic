package symboltable;

import symboltable.PairStringInteger;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

public class SymbolTable {

    private HashMap<symboltable.Scope, symboltable.ClassScope> ScopeInheritanceChain; //Scope -> ClassScope
    private HashMap<String, symboltable.ClassScope> classHash;   // Classname -> ClassScope .easy access to class
    private HashMap<PairStrings, symboltable.Scope> funcHash; // funcname-classname -> ClassScope
    private HashSet<String> undeclared;                     //types that we see and are not declared yet.
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


    //throw error if we try to put the same key in Map, that is if this func returns false..
    public boolean putScopeInheritanceChain(symboltable.Scope scope, symboltable.ClassScope classScope) {
        if (ScopeInheritanceChain.containsKey(scope)) {
            return false;
        }
        ScopeInheritanceChain.put(scope, classScope);
        return true;
    }

    //this is not used...
    public boolean removeScopeInheritanceChain(symboltable.Scope scope) {
        if (ScopeInheritanceChain.remove(scope) == null)
            return false;
        return true;
    }
    public symboltable.ClassScope getScopeInheritanceChain(symboltable.Scope scope) {
        return ScopeInheritanceChain.get(scope);
    }

    //throw error if we try to put the same key in Map, that is if this func returns false..
    public boolean putClassHash(String type, symboltable.ClassScope cs) {
        if (classHash.containsKey(type)) {
            return false;
        }
        classHash.put(type, cs);
        return true;
    }

    public symboltable.ClassScope getClassHash(String key) {
        return classHash.get(key);
    }

    public Set<String> getClassHash() {
        return classHash.keySet();
    }


    //throw error if we try to put the same key in Map, that is if this func returns false..
    public boolean putFuncHash(String fn, String cn, symboltable.Scope sc) {
        PairStrings p = new PairStrings(fn, cn);
        if (funcHash.containsKey(p)) {
            return false;
        }
        funcHash.put(p, sc);
        return true;
    }

    //this is not used..
    public boolean removeFuncHash(String fn,String cn) {
        PairStrings pairStrings = new PairStrings(fn,cn);
        if(funcHash.remove(pairStrings) == null)
            return false;
        return true;
    }

    public symboltable.Scope getFuncHash(String s1, String s2) {
        PairStrings p = new PairStrings(s1, s2);
        //System.out.println(p);
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

    //Find how many bytes a base class needs for its fields.
    public int findVarSizeof(String type) {
        symboltable.ClassScope classScope = getClassHash(type);
        if (classScope == null)
            System.out.println("PANIC .Class doesnt exist");
        return classScope.getVarSize();
    }

    //Find how many bytes a base class needs for its functions.
    public int findFuncSizeof(String type) {
        symboltable.ClassScope classScope = getClassHash(type);
        if (classScope == null)
            System.out.println("PANIC .Class doesnt exist");
        return classScope.getFuncSize();
    }

    //Find class name given a function scope.
    public String getClass(symboltable.Scope scope) {
        symboltable.ClassScope classScope = getScopeInheritanceChain(scope);
        if ( classScope == null) {
            System.out.println("PANIC.mother chain error");
            return null;
        }
        return classScope.getClassName();

    }

    //should be called after parsing whole source file.
    //knownTypes can't be null (see constructor of this class).
    public boolean checkUndeclared() {
        try {
            if (knownTypes.containsAll(undeclared))
                return true;
            else
                return  false;
        //if undeclared is null, still not unresolved types so we return true.
        } catch (NullPointerException ex) {
            return true;
        }
    }

    //find return type of a func.
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

    //find the type of an Identifier given his function scope.
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

    public PairStringInteger findTypeVerbose(symboltable.Scope scope, String id) {
        String type;
        symboltable.Scope temp = scope;
        while ( scope != null) {
            type = scope.get(id);
            if (type != null) {
                if (temp != scope) {
                    symboltable.ClassScope classScope = (symboltable.ClassScope)scope;
                    for (PairStringInteger p : classScope.getVaroffsets()) {
                        if ( p.funcname.equals(id)) {
                            return new PairStringInteger(type,p.offset);
                        }
                    }
                    return null; //error if not found
                }
                return new PairStringInteger(type,-1);
            }
            scope = getScopeInheritanceChain(scope);
        }
        return null;
    }
    //check if type 'right' is subtype of supertype 'left'.
    public boolean checkSubType(String left,String right) {
        //if one of types is primitive then no subtyping.
        if ( !isUserDefinedType(left) || !isUserDefinedType(right))
            return false;
        symboltable.ClassScope base = getClassHash(left);
        symboltable.ClassScope derived = getClassHash(right);
        if ( base == null || derived == null)
            System.out.println(">PANIC:checkSubType error..one of types doesnt exist...");
        //we know that left!=right when we enter this func so dont check if base==derived (initial_derived)...
        derived = getScopeInheritanceChain(derived);
        //climb the motherchain until you reach baseclass.
        while(derived != null) {
            if (derived == base)
                return true;
            derived = getScopeInheritanceChain(derived);
        }
        return false;
    }

    public boolean isUserDefinedType(String type) {
        if ( type.equals("int")|| type.equals("boolean") || type.equals("int[]"))
            return false;
        return true;
    }

    //check if given method overrides another one from its base class (if it has one).
    //Also, fill the vector for method offsets if method doesn't override one from its base class.
    public boolean checkOverride(String fn, String cn) {
        symboltable.ClassScope scope = getClassHash(cn);
        symboltable.ClassScope parentscope = getScopeInheritanceChain(scope);
        symboltable.FuncSignature parentfuncSignature = null;
        //search if one of parent classes has any method with the same method
        while (parentscope != null) {
            parentfuncSignature = parentscope.getFuncBind(fn);
            if (parentfuncSignature != null) {
                break;
            }
            parentscope = getScopeInheritanceChain(parentscope);
        }
        //no overriding case
        if ( parentscope == null) {
            scope.addFuncOffsets(fn, scope.getFuncSize());
            scope.addFuncSize();
            return true;
        }
        //overriding case
        //compare the two function signatures to examine whether is overloading or overriding.
        symboltable.FuncSignature funcSignature = scope.getFuncBind(fn);
        if (funcSignature.getReturnType().equals(parentfuncSignature.getReturnType())) {
            Vector<String> arg = funcSignature.getArgTypes();
            Vector<String> argp = parentfuncSignature.getArgTypes();
            if (argp.size() == arg.size()) {
                for (int i = 0; i < argp.size(); i++) {
                    if (arg.get(i).equals(argp.get(i)))
                        continue;
                    else {
                        System.out.println(">Error:Funcion overloading detected at class " + cn + " at function " + fn + " (arg " + i +  " type is different). Thats not possible in minijava");
                        return false;
                    }
                }
                //overriding found... we dont need to addFuncSize neither to add to funcoffsets...
                //additionaly, we dont need to check if other superclasses have same func signature or if there is overloading because the check has been made by them.
                //search funcOffsets of parent class that we found the the same function name (its the only class that contains this name
                // in funcoffsets.
                int method_off = parentscope.searchFuncOffset(fn);
                if (method_off < 0)
                    System.out.println("PANIC!no offset found in checkOverride");
                scope.addOverriden(fn,method_off);     //applied only at HW3.
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
            for(PairStringInteger over: classScope.getOverriden()) {
                System.out.println(cn + over);
            }
            for (PairStringInteger p : classScope.getFuncoffsets()) {
                System.out.println(cn + p);
            }
            System.out.println("VTABLEEEEEEEEEEEEEEEE");
            PairStrings[] res = getVtable(s);
            if (res == null) {
                System.out.println(s + "null");
                continue;
            }
            for (PairStrings pairStrings : res) {
                System.out.println(pairStrings);
            }
        }
    }

    public PairStrings[][] getVtables() {
        int class_num = classHash.size();
        PairStrings[][] vtables = new PairStrings[class_num][];
        int i = 0;
        for ( String c : classHash.keySet()) {
            vtables[i] = getVtable(c);
        }
        return vtables;
    }

    //creates a v-table like structure for one class. Specificaly, it creates an array of (funcName,className) pairs whose index is identical to the index of
    // the v-table of this class.
    public PairStrings[] getVtable(String cn) {
        symboltable.ClassScope classScope = getClassHash(cn);
        int func_sz = classScope.getFuncSize();
        if (func_sz == 0)
            return null;
        int arr_sz = func_sz / 8; //size of array that we will return..
        PairStrings[] vtable = new PairStrings[arr_sz];
        //HashSet<String> overriden = new HashSet<>();

        //for this class andd all parent classes, insert all methods and inherited methods specifying the class at each.
        while (classScope != null) {
            for (PairStringInteger p : classScope.getFuncoffsets()) {
                if (vtable[p.getOffset() / 8] == null)
                    vtable[p.getOffset() / 8] = new PairStrings(p.getFuncname(), classScope.getName());
            }
            for (PairStringInteger p: classScope.getOverriden()) {
                if (vtable[p.getOffset() / 8] == null)
                    vtable[p.getOffset() / 8] = new PairStrings(p.getFuncname(), classScope.getName());
            }
            classScope = getScopeInheritanceChain(classScope);
        }
        return vtable;

    }


    //just for debuging purposes
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
/*class PairStrings {
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
            return (this.s1.equals(pairStrings.s1) && this.s2.equals(pairStrings.s2));
        }
        else
            System.out.println("PANIC . Pairstrings");
            return false;
    }

    //compute a classic hashcode for strings...
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
}*/
