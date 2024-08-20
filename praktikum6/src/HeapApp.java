import strukdat.Heap;

import java.util.Random;

public class HeapApp {
    // Fungsi untuk menghasilkan data integer acak
    private static int[] generateRandomData(int size) {
        int[] data = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            data[i] = random.nextInt();
        }
        return data;
    }
    public static void main(String[] args) {
        System.out.println("Spesifikasi Komputer");
        System.out.println("Name : Macbook Air");
        System.out.println("Chip : Apple M1");
        System.out.println("Memory : 8 GB");

        int sizes[] = {10000, 20000, 40000, 80000};
        for (int size : sizes) {
            int[] data = generateRandomData(size);
            System.out.println("\nMengurutkan " + size + " data menggunakan Heapsort...");

            long startTime = System.nanoTime();
            Heap<Integer, Integer> heap = new Heap<>(size, true);
            for (int value : data) {
                heap.add(value, value);
            }
            heap.sort();
            long endTime = System.nanoTime();

            long duration = (endTime - startTime) / 1000000; // Konversi ke milidetik
            System.out.println("Waktu yang dibutuhkan: " + duration + " milidetik");
        }
    }
}
