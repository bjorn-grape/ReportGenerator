package com.bjorn_grape.app;

public class Main {

    private static int WordNumber = 1000;

    public static void main(String[] args) {
        MarkovFactory mkv = new MarkovFactory("test.in");
        String str = mkv.getNextWord("ACT");
        for (int i = 0; i < WordNumber; i++) {
            System.out.print(str + " ");
            str = mkv.getNextWord(str);
        }
        System.out.println();
    }
}
