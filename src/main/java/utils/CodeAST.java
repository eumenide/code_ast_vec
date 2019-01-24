package utils;

import java.io.Serializable;

/**
 * @author: eumes
 * @date: 2019/1/24
 **/
public class CodeAST implements Serializable {

    private int file_id;

    private String file_ast;

    public CodeAST() {

    }

    public CodeAST(int file_id, String file_ast) {
        this.file_id = file_id;
        this.file_ast = file_ast;
    }

    public int getFile_id() {
        return file_id;
    }

    public void setFile_id(int file_id) {
        this.file_id = file_id;
    }

    public String getFile_ast() {
        return file_ast;
    }

    public void setFile_ast(String file_ast) {
        this.file_ast = file_ast;
    }

    @Override
    public String toString() {
        return "CodeAST{" +
                "file_id=" + file_id +
                ", file_ast='" + file_ast + '\'' +
                '}';
    }
}
