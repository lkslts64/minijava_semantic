
//
// Generated by JTB 1.3.2 DIT@UoA patched
//

package visitor;
import symboltable.ClassScope;
import symboltable.FuncSignature;
import symboltable.Scope;
import symboltable.SymbolTable;
import syntaxtree.*;
import java.util.*;

/**
 * Provides default methods which visit each node in the tree in depth-first
 * order.  Your visitors may extend this class.
 */
public class TypeCheckerVisitor implements GJVisitor<String, Scope> {
    //
    // Auto class visitors--probably don't need to be overridden.
    //
    public String visit(NodeList n, Scope argu) {
        if (n.size() == 1)
            return n.elementAt(0).accept(this,argu);
        String _ret=null;
        int _count=0;
        for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); ) {
            e.nextElement().accept(this,argu);
            _count++;
        }
        return _ret;
    }

    public String visit(NodeListOptional n, Scope argu) {
        if ( n.present() ) {
            if (n.size() == 1)
                return n.elementAt(0).accept(this,argu);
            String _ret=null;
            int _count=0;
            for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); ) {
                e.nextElement().accept(this,argu);
                _count++;
            }
            return _ret;
        }
        else
            return null;
    }

    public String visit(NodeOptional n, Scope argu) {
        if ( n.present() )
            return n.node.accept(this,argu);
        else
            return null;
    }

    public String visit(NodeSequence n, Scope argu) {
        if (n.size() == 1)
            return n.elementAt(0).accept(this,argu);
        String _ret=null;
        int _count=0;
        for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); ) {
            e.nextElement().accept(this,argu);
            _count++;
        }
        return _ret;
    }

    public String visit(NodeToken n, Scope argu) { return n.tokenImage; }

    //
    // User-generated visitor methods below
    //

    private SymbolTable sym;
    private boolean error;
    private int argcount;                   //used so we know the number of arg we check each time in Expression Rest and Expression List.
    public TypeCheckerVisitor(SymbolTable sym) {
        this.sym = sym;
    }

    public void printErrMsg(String err) {
        System.out.println(err);
        if ( error == false)
            error = true;
    }

    /**
     * f0 -> MainClass()
     * f1 -> ( TypeDeclaration() )*
     * f2 -> <EOF>
     */
    public String visit(Goal n, Scope argu) {
        String _ret=null;
        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        n.f2.accept(this, argu);
        return _ret;
    }

    /**
     * f0 -> "class"
     * f1 -> Identifier()
     * f2 -> "{"
     * f3 -> "public"
     * f4 -> "static"
     * f5 -> "void"
     * f6 -> "main"
     * f7 -> "("
     * f8 -> "String"
     * f9 -> "["
     * f10 -> "]"
     * f11 -> Identifier()
     * f12 -> ")"
     * f13 -> "{"
     * f14 -> ( VarDeclaration() )*
     * f15 -> ( Statement() )*
     * f16 -> "}"
     * f17 -> "}"
     */
    public String visit(MainClass n, Scope argu) {
        String _ret=null;
        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        n.f2.accept(this, argu);
        n.f3.accept(this, argu);
        n.f4.accept(this, argu);
        n.f5.accept(this, argu);
        n.f6.accept(this, argu);
        n.f7.accept(this, argu);
        n.f8.accept(this, argu);
        n.f9.accept(this, argu);
        n.f10.accept(this, argu);
        n.f11.accept(this, argu);
        n.f12.accept(this, argu);
        n.f13.accept(this, argu);
        n.f14.accept(this, argu);
        n.f15.accept(this, argu);
        n.f16.accept(this, argu);
        n.f17.accept(this, argu);
        return _ret;
    }

    /**
     * f0 -> ClassDeclaration()
     *       | ClassExtendsDeclaration()
     */
    public String visit(TypeDeclaration n, Scope argu) {
        return n.f0.accept(this, argu);
    }

    /**
     * f0 -> "class"
     * f1 -> Identifier()
     * f2 -> "{"
     * f3 -> ( VarDeclaration() )*
     * f4 -> ( MethodDeclaration() )*
     * f5 -> "}"
     */
    public String visit(ClassDeclaration n, Scope argu) {
        String _ret=null;
        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        n.f2.accept(this, argu);
        n.f3.accept(this, argu);
        n.f4.accept(this, argu);
        n.f5.accept(this, argu);
        return _ret;
    }

    /**
     * f0 -> "class"
     * f1 -> Identifier()
     * f2 -> "extends"
     * f3 -> Identifier()
     * f4 -> "{"
     * f5 -> ( VarDeclaration() )*
     * f6 -> ( MethodDeclaration() )*
     * f7 -> "}"
     */
    public String visit(ClassExtendsDeclaration n, Scope argu) {
        String _ret=null;
        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        n.f2.accept(this, argu);
        n.f3.accept(this, argu);
        n.f4.accept(this, argu);
        n.f5.accept(this, argu);
        n.f6.accept(this, argu);
        n.f7.accept(this, argu);
        return _ret;
    }

    /**
     * f0 -> Type()
     * f1 -> Identifier()
     * f2 -> ";"
     */
    public String visit(VarDeclaration n, Scope argu) {
        String _ret=null;
        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        n.f2.accept(this, argu);
        return _ret;
    }

    /**
     * f0 -> "public"
     * f1 -> Type()
     * f2 -> Identifier()
     * f3 -> "("
     * f4 -> ( FormalParameterList() )?
     * f5 -> ")"
     * f6 -> "{"
     * f7 -> ( VarDeclaration() )*
     * f8 -> ( Statement() )*
     * f9 -> "return"
     * f10 -> Expression()
     * f11 -> ";"
     * f12 -> "}"
     */
    public String visit(MethodDeclaration n, Scope argu) {
        String _ret=null;
        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        n.f2.accept(this, argu);
        n.f3.accept(this, argu);
        n.f4.accept(this, argu);
        n.f5.accept(this, argu);
        n.f6.accept(this, argu);
        n.f7.accept(this, argu);
        n.f8.accept(this, argu);
        n.f9.accept(this, argu);
        n.f10.accept(this, argu);
        n.f11.accept(this, argu);
        n.f12.accept(this, argu);
        return _ret;
    }

    /**
     * f0 -> FormalParameter()
     * f1 -> FormalParameterTail()
     */
    public String visit(FormalParameterList n, Scope argu) {
        String _ret=null;
        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        return _ret;
    }

    /**
     * f0 -> Type()
     * f1 -> Identifier()
     */
    public String visit(FormalParameter n, Scope argu) {
        String _ret=null;
        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        return _ret;
    }

    /**
     * f0 -> ( FormalParameterTerm() )*
     */
    public String visit(FormalParameterTail n, Scope argu) {
        return n.f0.accept(this, argu);
    }

    /**
     * f0 -> ","
     * f1 -> FormalParameter()
     */
    public String visit(FormalParameterTerm n, Scope argu) {
        String _ret=null;
        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        return _ret;
    }

    /**
     * f0 -> ArrayType()
     *       | BooleanType()
     *       | IntegerType()
     *       | Identifier()
     */
    public String visit(Type n, Scope argu) {
        return n.f0.accept(this, argu);
    }

    /**
     * f0 -> "int"
     * f1 -> "["
     * f2 -> "]"
     */
    public String visit(ArrayType n, Scope argu) {
        String _ret=null;
        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        n.f2.accept(this, argu);
        return _ret;
    }

    /**
     * f0 -> "boolean"
     */
    public String visit(BooleanType n, Scope argu) {
        return n.f0.accept(this, argu);
    }

    /**
     * f0 -> "int"
     */
    public String visit(IntegerType n, Scope argu) {
        return n.f0.accept(this, argu);
    }

    /**
     * f0 -> Block()
     *       | AssignmentStatement()
     *       | ArrayAssignmentStatement()
     *       | IfStatement()
     *       | WhileStatement()
     *       | PrintStatement()
     */
    public String visit(Statement n, Scope argu) {
        return n.f0.accept(this, argu);
    }

    /**
     * f0 -> "{"
     * f1 -> ( Statement() )*
     * f2 -> "}"
     */
    public String visit(Block n, Scope argu) {
        String _ret=null;
        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        n.f2.accept(this, argu);
        return _ret;
    }

    /**
     * f0 -> Identifier()
     * f1 -> "="
     * f2 -> Expression()
     * f3 -> ";"
     */
    public String visit(AssignmentStatement n, Scope argu) {
        String _ret=null;
        String type = argu.get(n.f0.accept(this, argu));
        if ( type  == null) {
            printErrMsg("unkown identifier");
        }
        if ( type != "int[]")
            printErrMsg("expected int[] type");
        if ( n.f2.accept(this, argu) != "int") {
            printErrMsg("expected int");
        }
        return _ret;
    }

    /**
     * f0 -> Identifier()
     * f1 -> "["
     * f2 -> Expression()
     * f3 -> "]"
     * f4 -> "="
     * f5 -> Expression()
     * f6 -> ";"
     */
    public String visit(ArrayAssignmentStatement n, Scope argu) {
        String _ret=null;
        String type = argu.get(n.f0.accept(this, argu));
        if ( type  == null) {
            printErrMsg("unkown identifier");
        }
        if ( type != "int[]")
            printErrMsg("expected int[] type");
        if ( n.f2.accept(this, argu) != "int") {
            printErrMsg("expected int");
        }
        if ( n.f5.accept(this, argu) != "int") {
            printErrMsg("expected int");
        }
        return _ret;
    }

    /**
     * f0 -> "if"
     * f1 -> "("
     * f2 -> Expression()
     * f3 -> ")"
     * f4 -> Statement()
     * f5 -> "else"
     * f6 -> Statement()
     */
    public String visit(IfStatement n, Scope argu) {
        String _ret=null;
        if (n.f2.accept(this, argu) != "boolean" ) {
            printErrMsg(">Error:Condition in if statement should be of type boolean");
        }
        n.f4.accept(this, argu);
        n.f6.accept(this, argu);
        return _ret;
    }

    /**
     * f0 -> "while"
     * f1 -> "("
     * f2 -> Expression()
     * f3 -> ")"
     * f4 -> Statement()
     */
    public String visit(WhileStatement n, Scope argu) {
        String _ret=null;
        if ( n.f2.accept(this, argu) != "boolean") {
            printErrMsg(">Error:Condition in while statement should be of type boolean");
        }
        n.f4.accept(this, argu);
        return _ret;
    }

    /**
     * f0 -> "System.out.println"
     * f1 -> "("
     * f2 -> Expression()
     * f3 -> ")"
     * f4 -> ";"
     */
    public String visit(PrintStatement n, Scope argu) {
        String _ret=null;
        n.f2.accept(this, argu);
        return _ret;
    }

    /**
     * f0 -> AndExpression()
     *       | CompareExpression()
     *       | PlusExpression()
     *       | MinusExpression()
     *       | TimesExpression()
     *       | ArrayLookup()
     *       | ArrayLength()
     *       | MessageSend()
     *       | Clause()
     */
    public String visit(Expression n, Scope argu) {
        return n.f0.accept(this, argu);
    }

    /**
     * f0 -> Clause()
     * f1 -> "&&"
     * f2 -> Clause()
     */
    public String visit(AndExpression n, Scope argu) {
        if ( n.f0.accept(this, argu) != "boolean") {
            printErrMsg(">Error:Trying to compare to operands that are not of type boolean");
        }
        if ( n.f2.accept(this, argu) != "boolean") {
            printErrMsg(">Error:Trying to compare to operands that are not of type boolean");
        }
        return "boolean";
    }

    /**
     * f0 -> PrimaryExpression()
     * f1 -> "<"
     * f2 -> PrimaryExpression()
     */
    public String visit(CompareExpression n, Scope argu) {
        if ( n.f0.accept(this, argu) != "int") {
            printErrMsg(">Error:Trying to compare to operands that are not of type int");
        }
        if ( n.f2.accept(this, argu) != "int") {
            printErrMsg(">Error:Trying to compare to operands that are not of type int");
        }
        return "int";
    }

    /**
     * f0 -> PrimaryExpression()
     * f1 -> "+"
     * f2 -> PrimaryExpression()
     */
    public String visit(PlusExpression n, Scope argu) {
        if ( n.f0.accept(this, argu) != "int") {
            printErrMsg(">Error:Trying to add to operands that are not of type int");
        }
        if ( n.f2.accept(this, argu) != "int") {
            printErrMsg(">Error:Trying to add to operands that are not of type int");
        }
        return "int";
    }

    /**
     * f0 -> PrimaryExpression()
     * f1 -> "-"
     * f2 -> PrimaryExpression()
     */
    public String visit(MinusExpression n, Scope argu) {
        if ( n.f0.accept(this, argu) != "int") {
            printErrMsg(">Error:Trying to compare to operands that are not of type int");
        }
        if ( n.f2.accept(this, argu) != "int") {
            printErrMsg(">Error:Trying to compare to operands that are not of type int");
        }
        return "int";
    }

    /**
     * f0 -> PrimaryExpression()
     * f1 -> "*"
     * f2 -> PrimaryExpression()
     */
    public String visit(TimesExpression n, Scope argu) {
        if ( n.f0.accept(this, argu) != "int") {
            printErrMsg(">Error:Trying to multiply to operands that are not of type int");
        }
        if ( n.f2.accept(this, argu) != "int") {
            printErrMsg(">Error:Trying to multiply to operands that are not of type int");
        }
        return "int";
    }

    /**
     * f0 -> PrimaryExpression()
     * f1 -> "["
     * f2 -> PrimaryExpression()
     * f3 -> "]"
     */
    public String visit(ArrayLookup n, Scope argu) {
        String _ret=null;
        if ( n.f0.accept(this, argu) != "int[]")
            printErrMsg(">Error:You attempted to lookup an array but symbol is not of type int[].");
        if (n.f2.accept(this, argu) != "int") {
            printErrMsg(">Error:Offset of array lookup requires int type inisde brackets...");
        }
        return "int";
    }

    /**
     * f0 -> PrimaryExpression()
     * f1 -> "."
     * f2 -> "length"
     */
    public String visit(ArrayLength n, Scope argu) {
        if ( n.f0.accept(this, argu) != "int[]")
            printErrMsg(">Error:You attempted to find the length of an array but symbol is not of type int[].");
        return "int";
    }

    /**
     * f0 -> PrimaryExpression()
     * f1 -> "."
     * f2 -> Identifier()
     * f3 -> "("
     * f4 -> ( ExpressionList() )?
     * f5 -> ")"
     */
    //if receiver and function name exist, we return the returnType of function even if arg_list is not correct.
    public String visit(MessageSend n, Scope argu) {
        String _ret=null;
        if ( n.f0.accept(this, argu) == null)       //if type of ID doesn't exists
           return _ret;

        ClassScope classScope = sym.getClassHash(n.f0.accept(this,argu));
        if ( classScope == null) {
            printErrMsg(">Error:Receiver of method is not a class.");
            return _ret;
        }
        //search all parentclasses to find if the method exists.
        while ( classScope != null) {
            Scope scope = sym.getFuncHash(n.f2.accept(this,argu),classScope.getName());
            if ( scope != null) {
                n.f4.accept(this, scope);
                return sym.getReturnType(scope.getName(),classScope.getName());
            }
            classScope = sym.getScopeInheritanceChain(classScope);
        }
        printErrMsg(">Error:Type " + n.f0.accept(this,argu) + "has no method with name " + n.f2.accept(this,argu));
        return _ret;
    }

    /**
     * f0 -> Expression()
     * f1 -> ExpressionTail()
     */
    public String visit(ExpressionList n, Scope argu) {
        String _ret=null;
        argcount = 0;
        FuncSignature funcSignature = sym.getFuncSignature(argu);
        String type = funcSignature.getArgType(argcount);
        if ( type == null) {
            printErrMsg(">Error: Different # of arguments in function " + argu.getName());
            return _ret;
        }
        if ( type != n.f0.accept(this, argu)) {
            printErrMsg(">Error:Argument type #" + argcount + " in function " + argu.getName() + " doesn't match");
        }
        argcount++;

        n.f1.accept(this, argu);
        return _ret;
    }

    /**
     * f0 -> ( ExpressionTerm() )*
     */
    public String visit(ExpressionTail n, Scope argu) {
        return n.f0.accept(this, argu);
    }

    /**
     * f0 -> ","
     * f1 -> Expression()
     */
    public String visit(ExpressionTerm n, Scope argu) {
        String _ret=null;
        FuncSignature funcSignature = sym.getFuncSignature(argu);
        String type = funcSignature.getArgType(argcount);
        if ( type == null) {
            printErrMsg(">Error: Different # of arguments in function " + argu.getName());
            return _ret;
        }
        if ( type != n.f0.accept(this, argu)) {
            printErrMsg(">Error:Argument type #" + argcount + " in function " + argu.getName() + " doesn't match");
        }
        argcount++;
        return _ret;
    }

    /**
     * f0 -> NotExpression()
     *       | PrimaryExpression()
     */
    public String visit(Clause n, Scope argu) {
        return n.f0.accept(this, argu);
    }

    /**
     * f0 -> IntegerLiteral()
     *       | TrueLiteral()
     *       | FalseLiteral()
     *       | Identifier()
     *       | ThisExpression()
     *       | ArrayAllocationExpression()
     *       | AllocationExpression()
     *       | BracketExpression()
     */
    //we want to return a Type at any case here. So, if it is an IDENTIFIER
    //we find its Type by quering the Map.
    public String visit(PrimaryExpression n, Scope argu) {
        String type_or_ID = n.f0.accept(this,argu);
        //IDENTIFIER case...
       if  (!(sym.isDeclared(type_or_ID))) {
           String type = argu.get(type_or_ID);
           if (type == null) {
               printErrMsg(">Error:Unknown symbol " + type_or_ID);
               return null;
           }
           else
               return type;
       }
       //Type case....
       else
           return type_or_ID;
    }

    /**
     * f0 -> <INTEGER_LITERAL>
     */
    public String visit(IntegerLiteral n, Scope argu) {
        //return n.f0.accept(this, argu);
        return "int";
    }

    /**
     * f0 -> "true"
     */
    public String visit(TrueLiteral n, Scope argu) {
        //return n.f0.accept(this, argu);
        return "boolean";
    }

    /**
     * f0 -> "false"
     */
    public String visit(FalseLiteral n, Scope argu) {
        //return n.f0.accept(this, argu);
        return "boolean";
    }

    /**
     * f0 -> <IDENTIFIER>
     */
    public String visit(Identifier n, Scope argu) {
        return n.f0.accept(this, argu);
    }

    /**
     * f0 -> "this"
     */
    public String visit(ThisExpression n, Scope argu) {
        //return n.f0.accept(this, argu);
        return sym.getClass(argu);
    }

    /**
     * f0 -> "new"
     * f1 -> "int"
     * f2 -> "["
     * f3 -> Expression()
     * f4 -> "]"
     */
    public String visit(ArrayAllocationExpression n, Scope argu) {
        String _ret=null;
        if ( n.f3.accept(this, argu) != "int") {
            printErrMsg(">Error:ArrayAllocationExpression requires int type inisde brackets...");
        }
        return "int[]";
    }

    /**
     * f0 -> "new"
     * f1 -> Identifier()
     * f2 -> "("
     * f3 -> ")"
     */
    public String visit(AllocationExpression n, Scope argu) {
        String _ret=null;
        n.f0.accept(this, argu);
        String type = argu.get( n.f1.accept(this, argu));
        if ( type == null)
            printErrMsg("Type " + n.f1.accept(this,argu) + " hasn't been declared");
        return type;
    }

    /**
     * f0 -> "!"
     * f1 -> Clause()
     */
    public String visit(NotExpression n, Scope argu) {
        String _ret=null;
        if ( n.f1.accept(this, argu) != "boolean" )
            printErrMsg(">Error:Unary operator ! followed by non boolean expression");
        return "boolean";
    }

    /**
     * f0 -> "("
     * f1 -> Expression()
     * f2 -> ")"
     */
    public String visit(BracketExpression n, Scope argu) {
        return n.f1.accept(this, argu);
    }

}

