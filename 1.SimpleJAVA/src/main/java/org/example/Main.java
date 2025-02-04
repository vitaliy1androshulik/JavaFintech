package org.example;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    public static void main(String[] args) {
        //int, short, float, double, char, boolean, String
        System.out.println("Вкажіть кількість");
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        int[] list = new int[n];
        for (int i = 0; i < n; i++)
            list[i] = getRandomNumber(18, 60);
        System.out.println("List random");
        for (var item : list) {
            System.out.print(item+"\t");
        }
        System.out.println();
    }
}