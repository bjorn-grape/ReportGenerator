package com.bjorn_grape.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Reader {

    String content = "";
    int index = 0;

    public Reader(String path) {
        try {
            String pwdToresources = new java.io.File("src/main/java/resources").getAbsolutePath();
            Scanner scan = new Scanner(new File(pwdToresources + "/" + path));
            String txt = scan.useDelimiter("\\A").next();
            scan.close();
            content = txt;

        } catch (FileNotFoundException e) {
            System.err.println("Can't open file!");
            e.printStackTrace();
        }
    }

    private boolean isASpace(char c) {
        return c == ' ' || c == '\t';
    }

    private boolean isPunctuation(char s) {
        return s == '.' || s == ';' || s == ','
                || s == '?' || s == '\''
                || s == '"' || s == '!';
    }

    private char getChar() {
        if (index >= content.length())
            return '\0';
        return content.charAt(index++);
    }

    private boolean notNull(char c) {
        return c != '\0';
    }

    public String getToken() {
        StringBuilder sb = new StringBuilder();
        char s = getChar();
        while (notNull(s) && isASpace(s)) {
            s = getChar();
        }

        if (s == '\n') {
            sb.append('\n');
        } else if (isPunctuation(s)) {
            while (notNull(s) && !isASpace(s) && isPunctuation(s) && s != '\n') {
                sb.append(s);
                s = getChar();
            }
            if(s == '\n')
                index--;
        } else {
            while (notNull(s) && !isASpace(s) && !isPunctuation(s) && s != '\n') {
                sb.append(s);
                s = getChar();
            }
            if(s == '\n')
                index--;
        }
        return sb.toString();
    }


    public void resetIndex() {
        index = 0;
    }
}
