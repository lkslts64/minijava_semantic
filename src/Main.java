import myvisitors.SymbolTableVisitor;
import myvisitors.TypeCheckerVisitor;
import syntaxtree.*;
import visitor.*;
import java.io.*;
import symboltable.*;


class Main {
    public static void main (String [] args) {
        if (args.length < 1) {
            System.err.println("Usage: java Driver <inputFile>");
            System.exit(1);
        }
        for (int i = 0; i < args.length; i++) {
            System.out.println("---------------------------------------------");
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(args[i]);
                MiniJavaParser parser = new MiniJavaParser(fis);
                SymbolTableVisitor sym = new SymbolTableVisitor();
                Goal root = parser.Goal();
                //System.out.println(root.accept(sym));
                if (root.accept(sym, null) == "ERROR")
                    System.exit(0);
                //sym.symbolTable.display_contents();
                //sym.symbolTable.print_offsets();
                TypeCheckerVisitor typeCheckerVisitor = new TypeCheckerVisitor(sym.symbolTable);
                root.accept(typeCheckerVisitor, null);
            } catch (ParseException ex) {
                System.out.println(ex.getMessage());
            } catch (FileNotFoundException ex) {
                System.err.println(ex.getMessage());
            } finally {

                try {
                    if (fis != null) fis.close();
                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }
    }
}
