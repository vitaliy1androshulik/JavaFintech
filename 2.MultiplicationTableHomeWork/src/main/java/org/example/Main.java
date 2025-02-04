package org.example;

import java.nio.channels.Selector;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static int RndNumberOfQuestions()
    {
        Random rnd = new Random();
        return rnd.nextInt(10,15);
    }
    public static int RndNumber()
    {
        Random rnd = new Random();
        return rnd.nextInt(0,20);
    }
    public static void main(String[] args) {
        int a, b, resFromUser, res;
        double score=0;
        Scanner scan = new Scanner(System.in);
        ArrayList<String> wrongList = new ArrayList<>();
        HashSet<String> uniqueQuestions = new HashSet<>();
        int countOfQuestion =RndNumberOfQuestions();
        for(int i =1; i<=countOfQuestion; i++)
        {
            do {
                a = RndNumber();
                b = RndNumber();
            } while (!uniqueQuestions.add(a + "," + b));

            System.out.print(i+". "+a+" * "+b+" = ");
            res = a*b;
            resFromUser = scan.nextInt();
            if(res == resFromUser)
            {
                System.out.println("Good!");
                score+=12/(double)countOfQuestion;
            }
            else
            {
                System.out.println("Bad! Try again!");
                wrongList.add(a+" * "+b+" != "+resFromUser);
            }
        }
        DecimalFormat df = new DecimalFormat("#.##");
        System.out.printf("Your score :: "+df.format(score));
        System.out.println();
        System.out.print("Do you want to see your wrong answers? (Y/N) :: ");
        String ress = scan.next();
        if(ress.equals("Y"))
        {
            System.out.println(wrongList);
        }
        else if (ress.equals("y")) {
            System.out.println(wrongList);
        } else
        {
            System.out.println("OK. See you soon <3");
        }
    }
}