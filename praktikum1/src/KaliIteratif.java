import java.util.Scanner;

public class KaliIteratif {
    public static int KaliIteratif(int a, int b) {
        int hasil = 0;
        for (int i = 0; i < b; i++) {
            hasil += a;
        }
        return hasil;
    }

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        System.out.print("Masukkan nilai a: ");
        int a = reader.nextInt();
        System.out.print("Masukkan nilai b: ");
        int b = reader.nextInt();
        int hasilPerkalian = KaliIteratif(a, b);
        System.out.println("Hasil perkalian a * b adalah: " + hasilPerkalian);
    }
}
