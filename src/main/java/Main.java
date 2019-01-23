import java.io.*;

/**
 * @author: eumes
 * @date: 2019/1/23
 **/
public class Main {

    public static void main(String[] args) {

        String rootDir = "datasets/SourceFile/sourceFile_";
        String[] files = {"aspectj", "eclipseUI", "jdt", "swt", "tomcat"};

        for (String file :
                files) {
            String fileName = rootDir + file + ".txt";
            readFileByLines(fileName);
        }

    }


    public static void saveJson(String file) {
        // TODO
    }

    public static void parseFile(String fileName, int line) {
        fileName = fileName.substring(3);
        System.out.println("line " + line + " : " + fileName);

//        TODO :

    }

    public static void readFileByLines(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;

        try{
            System.out.println("读取文件： " + fileName);
            reader = new BufferedReader(new FileReader(file));
            String tempStr;
            int line = 1;

            while ((tempStr = reader.readLine()) != null) {
               parseFile(tempStr, line++);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (reader != null) {
                try {
                    reader.close();
                }catch(IOException e1){

                }
            }
        }
    }

}
