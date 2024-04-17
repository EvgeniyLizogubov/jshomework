package com.github.evgenylizogubov;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class HomeWorkApp {
    private static final Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        guessTheNumber();
        guessTheWord();
    }
    
    private static void guessTheNumber() {
        int hiddenNumber = new Random().nextInt(10);
        boolean win = false;
        
        System.out.println("Отгадайте число от 0 до 9, у вас есть 3 попытки.");
        
        for (int i = 0; i < 3; i++) {
            System.out.println("Попытка №" + (i + 1));
            int value = takeAndCheckInput("Введите число", 0, 9);
            
            if (value == hiddenNumber) {
                win = true;
                break;
            } else if (value < hiddenNumber) {
                System.out.println("\n" + "Загаданное число больше " + value + "\n");
            } else {
                System.out.println("\n" + "Загаданное число меньше " + value + "\n");
            }
        }
        
        System.out.println(win ? "Поздравляем! Вы отгадали загаданное число." : "Было загадано число - " + hiddenNumber);
        
        if (takeAndCheckInput("Повторить игру ещё раз? 1 - да / 0 - нет", 0, 1) == 1) {
            guessTheNumber();
        }
    }
    
    private static int takeAndCheckInput(String output, int minValue, int maxValue) {
        int input;
        
        do {
            System.out.println(output);
            input = sc.nextInt();
        } while (input < minValue || input > maxValue);
        
        return input;
    }
    
    private static void guessTheWord() {
        String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry",
                "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut", "pear",
                "pepper", "pineapple", "pumpkin", "potato"};
        String hiddenWord = words[new Random().nextInt(words.length)];
        char[] guessedLetters = "#".repeat(15).toCharArray();
        boolean win = false;
        String playerAnswer;
        
        System.out.println("Отгадайте загаданное слово");
        
        do {
            System.out.println("Введите слово:");
            playerAnswer = sc.nextLine().toLowerCase();
            
            if (playerAnswer.equals(hiddenWord)) {
                win = true;
            } else {
                for (int i = 0; i < playerAnswer.length(); i++) {
                    char letter = playerAnswer.charAt(i);
                    
                    if (hiddenWord.length() > i && letter == hiddenWord.charAt(i)) {
                        guessedLetters[i] = letter;
                    }
                }
                
                System.out.println("Отгаданные буквы - " + Arrays.toString(guessedLetters));
            }
        } while (!win);
        
        System.out.println("Поздравляем! Вы отгадали!");
    }
}
