package symboltable;

public class FuncScope extends symboltable.Scope {
    private String returnValue;

    public FuncScope(String val){
        super();
        returnValue = val;
    }
    public String getReturnValue() {
        return returnValue;
    }
}
