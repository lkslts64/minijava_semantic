package symboltable;

import java.util.Vector;
//maybe funcName is redundant information here, we have it above in Hashmap<string,funcsignature>.
public class FuncSignature {
    private Vector<String> argTypes;
    private String returnType;

    public FuncSignature(String rt){
        returnType = rt;
        argTypes = new Vector<>();
    }
    public void addArg(String arg){
        argTypes.add(arg);
    }

    public String getArgType(int i) {
        try {
            return argTypes.get(i);
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            return null;
        }
    }


    public Vector<String> getArgTypes() {
        return argTypes;
    }

    public String getReturnType() {
        return returnType;
    }
}
