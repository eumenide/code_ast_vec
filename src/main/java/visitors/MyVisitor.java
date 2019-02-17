package visitors;

import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.stmt.*;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

/**
 * @author: eumes
 * @date: 2019/1/23
 **/
public class MyVisitor extends VoidVisitorAdapter<StringBuffer> {

    public MyVisitor() {
        super();
    }

//    @Override
//    public void visit(AnnotationDeclaration n, StringBuffer arg) {
//        super.visit(n, arg);
//        arg.append(" ");
//        arg.append(n.getNameAsString());
//    }

//    @Override
//    public void visit(AnnotationMemberDeclaration n, StringBuffer arg) {
//        super.visit(n, arg);
//        arg.append(" ");
//        arg.append(n.getNameAsString());
//    }

//    @Override
//    public void visit(BreakStmt n, StringBuffer arg) {
//        super.visit(n, arg);
//        arg.append(" break");
//    }
//
//    @Override
//    public void visit(CatchClause n, StringBuffer arg) {
//        super.visit(n, arg);
//        arg.append(" catch");
//        arg.append(" ");
//        arg.append(n.getParameter().getNameAsString());
//    }

    @Override
    public void visit(ClassOrInterfaceDeclaration n, StringBuffer arg) {
        super.visit(n, arg);
        arg.append(" ");
        arg.append(n.getNameAsString());
    }

/*    @Override
    public void visit(ConstructorDeclaration n, StringBuffer arg) {
        super.visit(n, arg);
        arg.append(" ");
        arg.append(n.getNameAsString());
    }*/

//    @Override
//    public void visit(ContinueStmt n, StringBuffer arg) {
//        super.visit(n, arg);
//        arg.append(" continue");
//    }
//
//    @Override
//    public void visit(DoStmt n, StringBuffer arg) {
//        super.visit(n, arg);
//        arg.append(" do");
//    }

   /* @Override
    public void visit(EnumDeclaration n, StringBuffer arg) {
        super.visit(n, arg);
        arg.append(" ");
        arg.append(n.getNameAsString());
    }*/

//    @Override
//    public void visit(FieldDeclaration n, StringBuffer arg) {
//        super.visit(n, arg);
//        for (VariableDeclarator node : n.getVariables()){
//            arg.append(" ");
//            arg.append(node.getNameAsString());
//        }
//    }
//
//    @Override
//    public void visit(ForEachStmt n, StringBuffer arg) {
//        super.visit(n, arg);
//        arg.append(" foreach");
//    }
//
    @Override
    public void visit(ForStmt n, StringBuffer arg) {
        super.visit(n, arg);
        arg.append(" for");
    }

    @Override
    public void visit(IfStmt n, StringBuffer arg) {
        super.visit(n, arg);
        arg.append(" if");
    }

/*    @Override
    public void visit(InitializerDeclaration n, StringBuffer arg) {
        super.visit(n, arg);
        arg.append(" static");
    }*/

    @Override
    public void visit(MethodCallExpr n, StringBuffer arg) {
        super.visit(n, arg);
        arg.append(" ");
        arg.append(n.getNameAsString());
    }

    @Override
    public void visit(MethodDeclaration n, StringBuffer arg) {
        super.visit(n, arg);
        arg.append(" ");
        arg.append(n.getNameAsString());
    }

 /*   @Override
    public void visit(ObjectCreationExpr n, StringBuffer arg) {
        super.visit(n, arg);
        arg.append(" ");
        arg.append(n.getTypeAsString());
    }*/

  /*  @Override
    public void visit(Name n, StringBuffer arg) {
        super.visit(n, arg);
        arg.append(" ");
        arg.append(n.getIdentifier());
    }*/

  /*  @Override
    public void visit(SuperExpr n, StringBuffer arg) {
        super.visit(n, arg);
        arg.append(" super");
    }*/

    @Override
    public void visit(SwitchStmt n, StringBuffer arg) {
        super.visit(n, arg);
        arg.append(" switch");
    }
//
//    @Override
//    public void visit(SynchronizedStmt n, StringBuffer arg) {
//        super.visit(n, arg);
//        arg.append(" synchronized");
//    }

  /*  @Override
    public void visit(ThisExpr n, StringBuffer arg) {
        super.visit(n, arg);
        arg.append(" this");
    }*/

//    @Override
//    public void visit(ThrowStmt n, StringBuffer arg) {
//        super.visit(n, arg);
//        arg.append(" throw");
//    }

//    @Override
//    public void visit(TryStmt n, StringBuffer arg) {
//        super.visit(n, arg);
//        arg.append(" try");
//    }

  /*  @Override
    public void visit(VariableDeclarationExpr n, StringBuffer arg) {
        super.visit(n, arg);
        for (VariableDeclarator variableDeclarator :
                n.getVariables()) {
            arg.append(" ");
            arg.append(variableDeclarator.getNameAsString());
        }
    }*/

//    @Override
//    public void visit(VariableDeclarator n, StringBuffer arg) {
//        super.visit(n, arg);
//        arg.append(" ");
//        arg.append(n.getNameAsString());
//    }

    @Override
    public void visit(WhileStmt n, StringBuffer arg) {
        super.visit(n, arg);
        arg.append(" while");
    }

  /*  @Override
    public void visit(MethodReferenceExpr n, StringBuffer arg) {
        super.visit(n, arg);
        arg.append(" ");
        arg.append(n.getIdentifier());
    }*/

    public static void main(String[] args) {

    }

}
