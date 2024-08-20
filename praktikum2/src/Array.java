package src;/*
 * Learning array data structure
 * 
 * @author: Lely Hiryanto
 */

import java.util.Scanner;
import java.lang.Math;   

public class Array {
    //constants
    static final int MIN = 1;
    static final int MAX = 100;

    public static void main(String[] args) {
        //input the number of elements in an array
        Scanner scan = new Scanner(System.in);
        int n;
        do {
            System.out.print("Masukkan n (minimum n = 2) = ");
            n = scan.nextInt();
        } while(n < 2);
        scan.close();

        //declare an array named as data
        int data[] = new int[n];

        //generate random numbers for the array
        for(int i= 0; i < n; i++) {
            data[i] = (int)(Math.random()*(MAX - MIN + 1));
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }
}
