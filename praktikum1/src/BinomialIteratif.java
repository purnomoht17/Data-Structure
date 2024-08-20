import java.util.Scanner;

public class BinomialIteratif {
    public static int BinomialIteratif(int n, int k) {
        if (k == 0 || k == n) {
            return 1;
        } else {
            int hasil = 1;
            for (int i = 1; i <= k; i++) {
                hasil = hasil * (n - i + 1) / i;
            }
            return hasil;
        }
    }

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        System.out.print("Masukkan nilai n: ");
        int n = reader.nextInt();
        System.out.print("Masukkan nilai k: ");
        int k = reader.nextInt();
        int hasilBinomial = BinomialIteratif(n, k);
        System.out.println("Hasil binomial untuk n = " + n + " dan k = " + k + " adalah: " + hasilBinomial);
    }
}
