package com.github.evgenylizogubov;

import java.util.Arrays;

public class HomeWorkApp {
    private static final int SIZE = 10000000;
    private static final int H = SIZE / 2;
    
    public static void main(String[] args) throws InterruptedException {
        calculateBySingleThread();
        calculateByMultiThread();
    }
    
    private static void calculateBySingleThread() {
        float[] arr = new float[SIZE];
        Arrays.fill(arr, 1);
        
        long start = System.currentTimeMillis();
        
        for (int i = 0; i < SIZE; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        
        System.out.println(System.currentTimeMillis() - start);
    }
    
    private static void calculateByMultiThread() throws InterruptedException {
        float[] arr = new float[SIZE];
        Arrays.fill(arr, 1);
        
        float[] a1 = new float[H];
        float[] a2 = new float[H];
        
        long start = System.currentTimeMillis();
        
        System.arraycopy(arr, 0, a1, 0, H);
        System.arraycopy(arr, H, a2, 0, H);
        
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < H; i++) {
                a1[i] = (float)(a1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        });
        
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < H; i++) {
                a2[i] = (float)(a1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        });
        
        t1.start();
        t2.start();
        
        t1.join();
        t2.join();
        
        System.arraycopy(a1, 0, arr, 0, H);
        System.arraycopy(a2, 0, arr, H, H);
        
        System.out.println(System.currentTimeMillis() - start);
    }
}
