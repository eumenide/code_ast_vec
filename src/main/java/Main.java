import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import net.sf.json.JSONArray;
import org.apache.log4j.Logger;
import utils.CodeAST;
import visitors.MyVisitor;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: eumes
 * @date: 2019/1/23
 **/
public class Main {

    public static Logger logger = Logger.getLogger(Main.class);

    public static List<CodeAST> astList = new ArrayList<>();

    public static Map<String, Integer> total_project = new HashMap<>();

    public static void main(String[] args) {

        String rootDir = "datasets/SourceFile/sourceFile_";
        String outDir = "datasets/";
        String[] projects = {"aspectj", "eclipseUI", "jdt", "swt", "tomcat"};
//        String[] projects = {"swt", "tomcat"};
//        String[] projects = {"aspectj"};

        init();

        for (String project : projects) {
            String fileName = rootDir + project + ".txt";
            String outputFile = outDir + project + ".json";
            astList.clear();
            readFileByLines(fileName, project);
            saveJson(outputFile, project);
        }

    }

    public static void init() {
//        total_project.put("aspectj", 2394);
//        total_project.put("eclipseUI", 17809);
//        total_project.put("jdt", 16302);
//        total_project.put("swt", 8560);
//        total_project.put("tomcat", 2567);
        total_project.put("aspectj", 1406);
        total_project.put("eclipseUI", 15179);
        total_project.put("jdt", 12682);
        total_project.put("swt", 8119);
        total_project.put("tomcat", 2355);
    }

    public static void saveJson(String file, String project) {
        //
        logger.info(project + "  save to json");

        JSONArray astArray = JSONArray.fromObject(astList);

        try{
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(new File(file))));
            writer.write(astArray.toString());
            writer.close();
        } catch (IOException e) {
            logger.error(e.getStackTrace());
        }

        logger.info(project + "  save ended");
    }

    public static void parseFile(String fileName, int line, String project) {
        fileName = fileName.substring(3);

        logger.info(project + "  " + line + " / " + total_project.get(project));

        try {
            StringBuffer stringBuffer = new StringBuffer();

            FileInputStream inputStream = new FileInputStream(fileName);
            CompilationUnit compilationUnit = JavaParser.parse(inputStream);

            compilationUnit.accept(new MyVisitor(), stringBuffer);

            CodeAST codeAST = new CodeAST();
            codeAST.setFile_id(line - 1);
            codeAST.setFile_ast(stringBuffer.toString());

            astList.add(codeAST);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            logger.error(e.getStackTrace());
        }
    }

    public static void readFileByLines(String fileName, String project) {
        File file = new File(fileName);
        BufferedReader reader = null;

        try {
            System.out.println("读取文件： " + fileName);
            reader = new BufferedReader(new FileReader(file));
            String tempStr;
            int line = 1;

            while ((tempStr = reader.readLine()) != null) {
                parseFile(tempStr, line++, project);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            logger.error(e.getStackTrace());
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getStackTrace());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    logger.error(e1.getStackTrace());
                }
            }
        }
    }

}
