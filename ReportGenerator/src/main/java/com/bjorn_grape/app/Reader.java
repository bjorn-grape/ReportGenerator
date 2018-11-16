package com.bjorn_grape.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Reader {

    String content = "";
    int index = 0;

    public Reader(String path) {
        try {
            String pwdToresources = new java.io.File("src/main/resources").getAbsolutePath();
            Scanner scan = new Scanner(new File(pwdToresources + "/" + path));
            String txt = scan.useDelimiter("\\A").next();
            scan.close();
            content = txt;

        } catch (FileNotFoundException e) {
            System.err.println("Can't open file!");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {

        Reader reader = new Reader("test.in");
        String str = reader.getToken();
        while (str.length() > 0) {
            System.out.println(str);
            str = reader.getToken();
        }
    }

    private boolean isASpace(char c) {
        return c == ' ' || c == '\n' || c == '\t';
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

        if (isPunctuation(s)) {
            while (notNull(s) && !isASpace(s) && isPunctuation(s)) {
                sb.append(s);
                s = getChar();
            }
        } else {
            while (notNull(s) && !isASpace(s) && !isPunctuation(s)) {
                sb.append(s);
                s = getChar();
            }
        }
        return sb.toString();
    }


    public void resetIndex() {
        index = 0;
    }
}
