import java.util.Scanner;

public class Faktorial {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan bilangan : ");
        int n = scanner.nextInt();
        System.out.println("Hasil Iteratif = " + faktorialIteratif(n));
        System.out.println("Hasil Rekursif = " + faktorialRekursif(n));
    }
    private static int faktorialIteratif(int n) {
        int result = 1;
        for (int i = n; i > 0; i--) {
            result = result * i;
        }
        return result;
    }

    private static int faktorialRekursif(int n){
        if (n <= 1) return 1;
        else return (n * faktorialRekursif(n - 1));
    }
}
