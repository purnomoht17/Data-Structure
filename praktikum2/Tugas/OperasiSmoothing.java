package Tugas;

import java.util.Scanner;

public class OperasiSmoothing {
    public static void main(String[] args) {
        Scanner input =  new Scanner(System.in);

        System.out.print("Masukkan Jumlah data hasil pengukuran : ");
        int N = input.nextInt();

        double[] B = new double[N];

        double[] V = new double[N];

        System.out.println("Masukkan data hasil pengukuran : ");
        for(int i = 0; i< N; i++){
            System.out.print("Data ke- " + (i + 1) + " : ");
            B[i] = input.nextDouble();
        }

        for(int i = 0;  i < N; i++){
            if(i == 0 || i == (N-1)){
                V[i] = B[i];
            } else{
                V[i] = (B[i-1] + B[i] + B[i+1]) / 3;
            }
        }

        System.out.println("Data");
        for (int i = 0; i < N; i++){
            System.out.println("Data ke- " + (i+1) + " : " + B[i]);
        }

        System.out.println("Data Smoothing");
        for(int i = 0; i < N; i++){
            System.out.println("Data Setelah Smoothing ke- : " + (i+1) + " : " + V[i]);
        }

        input.close();
    }
}
