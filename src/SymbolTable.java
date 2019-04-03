package symboltable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;

public class SymbolTable {

    private HashMap<symboltable.Scope, symboltable.ClassScope> InheritanceChain;
    private HashMap<String,symboltable.ClassScope> classHash;   // Classname,classScope .easy access to class
    private HashMap<symboltable.PrimaryKeyFunc, symboltable.Scope> funcHash; // key_funcname,classScope
    private Vector<String,String> waitingList;              // Type,Class_that_type_found .Types that have not been declared yet
    private HashSet<String> knownTypes;                     //all known types .


    public SymbolTable() {
        InheritanceChain = new HashMap<>();
        classHash = new HashMap<>();
        funcHash = new HashMap<>();
        waitingList = new Vector<>();
        knownTypes = new HashSet<>();
    }

    public checkOverride(symboltable.PrimaryKeyFunc) {
        //$1 = classHash_get(primarekey.className);
        //$2 = InhertianceChain_get($1);
        //$3 = $2_get(primarekey.funcName);
        //if ($3 == null)
        // {    NO OVERRIDE!
        //
        //
        // }
    }
}


