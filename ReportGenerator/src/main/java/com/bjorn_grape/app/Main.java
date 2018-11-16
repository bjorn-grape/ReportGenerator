package com.bjorn_grape.app;

public class Main {
    public static void main(String[] args) {
        MarkovFactory mkv = new MarkovFactory("test.in");
        String ll = mkv.getNextWord("cake");
        for (int i = 0; i < 1000; i++) {
            System.out.print(ll + " ");
            ll = mkv.getNextWord(ll);
        }

    }
}
