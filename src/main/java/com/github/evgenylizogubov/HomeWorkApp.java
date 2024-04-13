package com.github.evgenylizogubov;

import java.util.Arrays;

public class HomeWorkApp {
    public static void main(String[] args) {
        setValuesOfOneToArray();
        fillArray();
        doubleValuesOfArrayIfLessSix();
        fillTwoDimensionalArray();
        findMaxAndMinValuesInArray();
    }
    
    private static void setValuesOfOneToArray() {
        int[] array = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        System.out.println(Arrays.toString(array));
        
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 0) {
                array[i] = 1;
            } else if (array[i] == 1) {
                array[i] = 0;
            }
        }
        
        System.out.println(Arrays.toString(array));
        System.out.println("---");
    }
    
    private static void fillArray() {
        int[] array = new int[8];
        
        for (int i = 0, n = 0; i < array.length; i++, n += 3) {
            array[i] = n;
        }
        
        System.out.println(Arrays.toString(array));
        System.out.println("---");
    }
    
    private static void doubleValuesOfArrayIfLessSix() {
        int[] array = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        System.out.println(Arrays.toString(array));
        
        for (int i = 0; i < array.length; i++) {
            if (array[i] < 6) {
                array[i] *= 2;
            }
        }
        
        System.out.println(Arrays.toString(array));
        System.out.println("---");
    }
    
    private static void fillTwoDimensionalArray() {
        int[][] twoDimensionalArray = new int[5][5];
        int arraySize = twoDimensionalArray.length;
        
        for (int i = 0; i < arraySize; i++) {
            for (int j = 0; j < arraySize; j++) {
                if (i == j || i == arraySize - j - 1) {
                    twoDimensionalArray[i][j] = 1;
                }
                System.out.print(twoDimensionalArray[i][j] + " ");
            }
            System.out.println();
        }
        
        System.out.println("---");
    }
    
    private static void findMaxAndMinValuesInArray() {
        int[] array = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        System.out.println(Arrays.toString(array));
        
        int min = array[0];
        int max = array[0];
        
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            } else if (array[i] < min) {
                min = array[i];
            }
        }
        
        System.out.println("Min: " + min);
        System.out.println("Max: " + max);
        System.out.println("---");
    }
}
