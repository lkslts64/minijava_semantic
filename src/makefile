all: compile

compile:
	java -jar ../jtb132di.jar minijava.jj
	java -jar ../javacc5.jar minijava-jtb.jj
	javac -d . Scope.java ClassScope.java FuncSignature.java Scope.java SymbolTable.java
	javac -d . TypeCheckerVisitor.java SymbolTableVisitor.java
	javac Main.java

clean:
	rm -f *.class *~
