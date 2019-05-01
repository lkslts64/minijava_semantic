class Example{
    public static void main(String[] a){
    	A a;
      B b;
    }
}

class A{
    int i;
    boolean flag;
    int j;
    public int foo(int j,boolean k) {
      return 0;
    }
    public boolean fa() {
      return true;
    }
}

class B extends A{
    A type;
    int k;
    public int foo(int j,boolean k) {
      return 0;
    }
    public int foo(int j,int[] k) {
      return 0;
    }
    public boolean bla() {
      return true;
    }
    public int foo(int j,int k) {
      return 0;
    }
}

class C extends B{
  public int foo(int j,boolean k) {
    return 0;
  }
  public boolean fa() {
    return true;
  }
}
