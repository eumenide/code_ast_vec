package xy;

import java.io.*;

/**
 * @author: eumes
 * @date: 2019/2/15
 **/
public class FileUtils {
    public static File copyTo(File sourceFile, File copyToFile){
        if(sourceFile == null || !sourceFile.exists()){
            return null;
        }

        if(null == copyToFile){
            return null;
        }

        File copyToParent = copyToFile.getParentFile();
        if(copyToParent == null){
            return null;
        }

        if(!copyToParent.exists()){
            copyToParent.mkdirs();
        }

        if(!copyToFile.exists()){
            try {
                copyToFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // copy files
        int byteread = 0; // number of  read bytes
        InputStream in = null;
        OutputStream out = null;

        try {
            in = new FileInputStream(sourceFile);
            out = new FileOutputStream(copyToFile);
            byte[] buffer = new byte[1024];

            while ((byteread = in.read(buffer)) != -1) {
                out.write(buffer, 0, byteread);
            }
            return copyToFile;
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            return null;
        } finally {
            try {
                if (out != null)
                    out.close();
                if (in != null)
                    in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
