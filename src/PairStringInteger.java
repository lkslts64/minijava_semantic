package symboltable;

public class PairStringInteger {
    private String funcname;
    private Integer offset;
    public PairStringInteger(String s,Integer i ){
        funcname = s;
        offset = i;
    }

    @Override
    public String toString() {
        return funcname + " : " + offset;
    }

    public String getFuncname() {
        return funcname;
    }

    public Integer getOffset() {
        return offset;
    }
}

