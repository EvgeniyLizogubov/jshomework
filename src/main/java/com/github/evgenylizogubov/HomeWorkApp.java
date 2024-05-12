package com.github.evgenylizogubov;

public class HomeWorkApp {
    
    private static final int ARRAY_SIZE = 4;
    
    public static void main(String[] args) {
        String[][] array = {
                {"1", "1", "1", "1"},
                {"2", "2", "2", "2"},
                {"3", "3", "3", "3"},
                {"4", "4", "4", "4"}
        };
        
        try {
            System.out.println(sumElementsTwoDimensionalArray(array));
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private static int sumElementsTwoDimensionalArray(String[][] array) throws MyArraySizeException, MyArrayDataException {
        if (array.length != ARRAY_SIZE) {
            throw new MyArraySizeException("Размер массива не " + ARRAY_SIZE + "x" + ARRAY_SIZE);
        }
        
        int sum = 0;
        
        for (int i = 0; i < ARRAY_SIZE; i++) {
            if (array[i].length != ARRAY_SIZE) {
                throw new MyArraySizeException("Размер массива не " + ARRAY_SIZE + "x" + ARRAY_SIZE);
            }
            
            for (int j = 0; j < array[i].length; j++) {
                int number;
                
                try {
                    number = Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException("Некорректные данные на позиции - [" + i + "][" + j + "]");
                }
                
                sum += number;
            }
        }
        
        return sum;
    }
}
