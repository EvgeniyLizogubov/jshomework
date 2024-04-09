package com.github.evgenylizogubov;

public class HomeWorkApp {
    public static void main(String[] args) {
        printTreeWords();
        checkSumSing();
        printColor();
        compareNumbers();
    }
    
    private static void printTreeWords() {
        System.out.println("Orange" + '\n' + "Banana" + '\n' + "Apple");
    }
    
    private static void checkSumSing() {
        int a = 111;
        int b = 222;
        
        if (a + b >= 0) {
            System.out.println("Сумма положительная");
        } else {
            System.out.println("Сумма отрицательная");
        }
    }
    
    private static void printColor() {
        int value = 50;
        
        if (value <= 0) {
            System.out.println("Красный");
        } else if (value > 0 && value <= 100) {  // Проверка условия > 0 избыточна, т.к. проверяется ранее
            System.out.println("Желтый");
        } else if (value > 100) {                // Проверка условия избыточна, т.к. подходят все значения, оставшиеся после предыдущих проверок
            System.out.println("Зеленый");
        }
    }
    
    private static void compareNumbers() {
        int a = 1;
        int b = 2;
        
        if (a >= b) {
            System.out.println("a >= b");
        } else {
            System.out.println("a < b");
        }
    }
}
