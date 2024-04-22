package com.github.evgenylizogubov;

import java.util.Scanner;

public class HomeWorkApp {
    static char[][] map;
    static final int SIZE = 3;
    static final char DOT_PLAYER = 'X';
    static final char DOT_AI = '0';
    static final char DOT_EMPTY = '*';
    
    static final Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        prepareMap();
        showMap();
        while (true) {
            playerTurn();
            showMap();
            
            if (isWinner()) {
                System.out.println("Победил игрок");
                break;
            }
            
            if (isDraw()) {
                System.out.println("Ничья");
                break;
            }
            
            botTurn();
            showMap();
            if (isWinner()) {
                System.out.println("Победил БОТ");
                break;
            }
            
            if (isDraw()) {
                System.out.println("Ничья");
                break;
            }
        }
    }
    
    public static boolean isWinner() {
        boolean isWin = false;
        char checkedDot;
        
        for (int x = 0; x < SIZE; x++) {
            checkedDot = map[0][x];
            
            if (checkedDot == DOT_EMPTY) {
                break;
            }
            
            for (int y = 1; y < SIZE; y++) {
                if (checkedDot == map[y][x]) {
                    isWin = true;
                } else {
                    isWin = false;
                    break;
                }
            }
            
            if (isWin) {
                return true;
            }
        }
        
        for (int y = 0; y < SIZE; y++) {
            checkedDot = map[y][0];
            
            if (checkedDot == DOT_EMPTY) {
                break;
            }
            
            for (int x = 1; x < SIZE; x++) {
                if (checkedDot == map[y][x]) {
                    isWin = true;
                } else {
                    isWin = false;
                    break;
                }
            }
            
            if (isWin) {
                return true;
            }
        }
        
        checkedDot = map[0][0];
        
        if (checkedDot != DOT_EMPTY) {
            for (int i = 1; i < SIZE; i++) {
                if (checkedDot == map[i][i]) {
                    isWin = true;
                } else {
                    isWin = false;
                    break;
                }
            }
        }
        
        checkedDot = map[SIZE - 1][0];
        
        if (!isWin && checkedDot != DOT_EMPTY) {
            for (int i = 1; i < SIZE; i++) {
                if (checkedDot == map[SIZE - 1 - i][i]) {
                    isWin = true;
                } else {
                    isWin = false;
                    break;
                }
            }
        }
        
        return isWin;
    }
    
    public static boolean isDraw() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public static void botTurn() {
        int x, y;
        
        int[] dotEmptyInPlayerLine = getDotEmptyInLine(DOT_PLAYER);
        if (dotEmptyInPlayerLine[0] >= 0) {
            x = dotEmptyInPlayerLine[0];
            y = dotEmptyInPlayerLine[1];
        } else {
            int[] dotEmptyInBotLine = getDotEmptyInLine(DOT_AI);
            x = dotEmptyInBotLine[0];
            y = dotEmptyInBotLine[1];
        }
        
        if (x == -1) {
            for (int emptyDotX = 0; emptyDotX < SIZE; emptyDotX++) {
                for (int emptyDotY = 0; emptyDotY < SIZE; emptyDotY++) {
                    if (map[emptyDotY][emptyDotX] == DOT_EMPTY) {
                        x = emptyDotX;
                        y = emptyDotY;
                        break;
                    }
                }
                
                if (x != 0) {
                    break;
                }
            }
        }
        
        map[y][x] = DOT_AI;
        System.out.printf("Ход ИИ: [%d, %d]\n", (x + 1), (y + 1));
    }
    
    public static int[] getDotEmptyInLine(char dotPlayer) {
        for (int checkedX = 0; checkedX < SIZE; checkedX++) {
            for (int checkedY = 0; checkedY < SIZE; checkedY++) {
                if (map[checkedY][checkedX] == dotPlayer) {
                    int[] otherDotPlayerInLine = getOtherInLineDot(checkedX, checkedY, dotPlayer);
                    int[] dotEmptyInLine = getInLineBlockingDot(checkedX, checkedY, otherDotPlayerInLine[0],
                            otherDotPlayerInLine[1], DOT_EMPTY);
                    if (otherDotPlayerInLine[0] >= 0 && dotEmptyInLine[0] >= 0) {
                        return dotEmptyInLine;
                    }
                }
            }
        }
        
        return new int[]{-1, -1};
    }
    
    public static int[] getOtherInLineDot(int x, int y, char dotName) {
        int[] otherDotInLine = {-1, -1};
        
        for (int checkedX = x - 1; checkedX <= x + 1; checkedX++) {
            if (checkedX < 0 || checkedX >= SIZE) {
                continue;
            }
            
            for (int checkedY = y - 1; checkedY <= y + 1; checkedY++) {
                if (checkedY < 0 || checkedY >= SIZE || (checkedX == x && checkedY == y)) {
                    continue;
                }

//                if (map[checkedY][checkedX] == dotName) {
                if (getInLineBlockingDot(x, y, checkedX, checkedY, dotName)[0] >= 0) {
                    if (getInLineBlockingDot(x, y, checkedX, checkedY, DOT_EMPTY)[0] >= 0) {
                        otherDotInLine[0] = checkedX;
                        otherDotInLine[1] = checkedY;
                        return otherDotInLine;
                        
                    }
                }
//                }
            }
        }
        
        return otherDotInLine;
    }
    
    public static int[] getInLineBlockingDot(int x1, int y1, int x2, int y2, char dotName) {
        int[] blockingDot = {-1, -1};
        
        if (x1 == x2) {
            for (int y = 0; y < SIZE; y++) {
                if (y != y1 && map[y][x1] == dotName) {
                    blockingDot[0] = x1;
                    blockingDot[1] = y;
                    return blockingDot;
                }
            }
        } else if (y1 == y2) {
            for (int x = 0; x < SIZE; x++) {
                if (x != x1 && map[y1][x] == dotName) {
                    blockingDot[0] = x;
                    blockingDot[1] = y1;
                    return blockingDot;
                }
            }
        } else if (SIZE % 2 == 1) {
            if (x1 == y1 && x2 == y2) {
                for (int i = 0; i < SIZE; i++) {
                    if (map[i][i] == dotName) {
                        blockingDot[0] = i;
                        blockingDot[1] = i;
                        return blockingDot;
                    }
                }
            } else if (x1 + y1 == x2 + y2) {
                for (int x = SIZE - 1, y = 0; x >= 0 && y < SIZE; x--, y++) {
                    if (map[y][x] == dotName) {
                        blockingDot[0] = x;
                        blockingDot[1] = y;
                        return blockingDot;
                    }
                }
            }
        }
        
        return blockingDot;
    }
    
    public static void playerTurn() {
        int x, y;
        do {
            System.out.println("Введите координаты в формате X Y");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(x, y));
        map[y][x] = DOT_PLAYER;
    }
    
    public static boolean isCellValid(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) {
            return false;
        }
        return map[y][x] == DOT_EMPTY;
    }
    
    public static void showMap() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println(" ");
        
        for (int i = 0; i < map.length; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    public static void prepareMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }
}
