import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.Name;
import com.github.javaparser.ast.expr.SuperExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.FileInputStream;
import java.util.List;

/**
 * @author: eumes
 * @date: 2019/1/22
 **/
public class Example {

    public static void main(String[] args) throws Exception{
//        String fileName = "datasets/85a827a BrowserProperties.java";

        String fileName = "datasets/tmp1.java";
        FileInputStream inputStream = new FileInputStream(fileName);

        CompilationUnit compilationUnit = JavaParser.parse(inputStream);

//        printNode(compilationUnit.getChildNodes());


//        System.out.println(compilationUnit.toString());

        compilationUnit.accept(new MethodVisitor(), null);
    }

    public static void printNode(List<Node> list) {
        list.forEach(node ->
            {
                System.out.println(" |__ " + node.toString());
                System.out.print("  ");
                printNode(node.getChildNodes());
            });
    }

    private static class MethodVisitor extends VoidVisitorAdapter<Void> {
        @Override
        public void visit(MethodDeclaration n, Void arg) {
            System.out.println("method: " + n.getName());
            super.visit(n, arg);

        }

        @Override
        public void visit(ClassOrInterfaceDeclaration n, Void arg) {
            System.out.println("class: " + n.getName());
            System.out.println("extends: " + n.getExtendedTypes());
            System.out.println("implements: " + n.getImplementedTypes());
            super.visit(n, arg);

        }

        @Override
        public void visit(PackageDeclaration n, Void arg) {
            System.out.println("package: " + n.getName());
            super.visit(n, arg);
        }

        @Override
        public void visit(MethodCallExpr n, Void arg) {
            System.out.println("call: " + n.getName());
            super.visit(n, arg);
        }


    }


}
