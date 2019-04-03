package symboltable;

import java.util.Vector;
//maybe funcName is redundant information here, we have it above in Hashmap<string,funcsignature>.
public class FuncSignature {
    private String funcName;
    private Vector<String> argTypes;
    private String returnType;

    public FuncSignature(String fn, String rt){
        funcName = fn;
        returnType = rt;
        argTypes = new Vector<>();
    }
    public void addArg(String arg){
        argTypes.add(arg);
    }

    public Vector<String> getArgTypes() {
        return argTypes;
    }

    public String getFuncName() {
        return funcName;
    }

    public String getReturnType() {
        return returnType;
    }
}
