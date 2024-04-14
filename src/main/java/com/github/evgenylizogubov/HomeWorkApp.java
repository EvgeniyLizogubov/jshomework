package com.github.evgenylizogubov;

import java.util.Arrays;

public class HomeWorkApp {
//    private static int[] arrayy = {1, 2, 3, 4, 5, 6, 7, 8};
    
    public static void main(String[] args) {
        setValuesOfOneToArray();
        fillArray();
        doubleValuesOfArrayIfLessSix();
        fillTwoDimensionalArray();
        findMaxAndMinValuesInArray();
        
        int[] array1 = {2, 2, 2, 1, 2, 2, 10, 1};
        System.out.println(checkEqualitySumsLeftAndRightPartsOfArray(array1));
        System.out.println("---");
        
        int[] array2 = {1, 2, 3, 4, 5, 6, 7, 8};
        System.out.println(Arrays.toString(array2));
        shiftArrayElements(array2, 3);
        System.out.println(Arrays.toString(array2));
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
    
    private static boolean checkEqualitySumsLeftAndRightPartsOfArray(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int leftPartSumArray = Arrays.stream(Arrays.copyOfRange(array, 0, i)).sum();
            int rightPartSumArray = Arrays.stream(Arrays.copyOfRange(array, i, array.length)).sum();
            
            if (leftPartSumArray == rightPartSumArray) {
                return true;
            }
        }
        
        return false;
    }
    
    private static void shiftArrayElements(int[] array, int shift) {
        int n = array.length;
        
        if (shift > n) {
            shift = shift % n;
        }
        
        if (shift < 0) {
            shift = n + shift;
        }
        
        int i, j, k, temp;
        for (i = 0; i < gcd(shift, n); i++) {
            temp = array[i];
            j = i;
            while (true) {
                k = j + shift;
                if (k >= n) {
                    k = k - n;
                }
                if (k == i) {
                    break;
                }
                array[j] = array[k];
                j = k;
            }
            array[j] = temp;
        }
    }
    
    // Function to get gcd of a and b
    private static int gcd(int a, int b) {
        if (b == 0)
            return a;
        else
            return gcd(b, a % b);
    }
}
