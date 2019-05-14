all: compile

compile:
	java -jar ../jtb132di.jar minijava.jj
	java -jar ../javacc5.jar minijava-jtb.jj
	javac -d . ClassScope.java FuncSignature.java Scope.java SymbolTable.java PairStringInteger.java PairStrings.java
	javac -d . TypeCheckerVisitor.java SymbolTableVisitor.java LLVMGenerator.java
	javac Main.java

clean:
	rm -f *.class *~
