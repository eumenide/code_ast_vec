package xy;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author: eumes
 * @date: 2019/2/15
 **/
public class JdtAstUtil {

    /**
     * get compilation unit of source code
     * @param javaFilePath
     * @return CompilationUnit
     */
    public static CompilationUnit getCompilationUnit(String javaFilePath){
        byte[] input = null;
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(javaFilePath));
            input = new byte[bufferedInputStream.available()];
            bufferedInputStream.read(input);
            bufferedInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ASTParser astParser = ASTParser.newParser(AST.JLS8);
        astParser.setSource(new String(input).toCharArray());
        astParser.setKind(ASTParser.K_COMPILATION_UNIT);
        CompilationUnit result = (CompilationUnit) (astParser.createAST(null));

        return result;
    }
}
