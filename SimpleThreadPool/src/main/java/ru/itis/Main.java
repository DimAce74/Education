package ru.itis;

import java.util.StringTokenizer;

import static java.lang.Character.isDigit;
import static java.lang.Character.isUpperCase;


public class Main {
    public static void main(String[] args) {
        String text = "Ма1ма мы2лА Мы3лом Ра4муУ";

        String[] words = text.split(" ");

        Runnable showStartUpperCase = new Runnable() {
            @Override
            public void run() {
                    for (String word:words) {
                        char[] charsOfWords = word.toCharArray();
                        if (isUpperCase(charsOfWords[0])) {
                            System.out.println("startUp " + word + " " + Thread.currentThread().getName());
                        }
                    }

            }
        };
        Runnable showEndUpperCase = new Runnable() {
            @Override
            public void run() {
                for (String word:words) {
                    char[] charsOfWords = word.toCharArray();
                    if (isUpperCase(charsOfWords[charsOfWords.length - 1])) {
                        System.out.println("endUp " + word + " " + Thread.currentThread().getName());
                    }
                }

            }
        };

        Runnable showAllNumber = new Runnable() {
            @Override
            public void run() {
                char[] chars = text.toCharArray();
                for (char ch : chars) {
                    if(isDigit(ch)) {
                        System.out.println("number " + ch +" "+ Thread.currentThread().getName());
                    }
                }
            }
        };

        WorkQueue workQueue = new WorkQueue(3);

        workQueue.execute(showStartUpperCase);
        workQueue.execute(showEndUpperCase);
        workQueue.execute(showAllNumber);
    }
}
