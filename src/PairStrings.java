package symboltable;

public class PairStrings {
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
}
