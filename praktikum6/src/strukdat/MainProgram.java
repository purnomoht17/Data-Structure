package strukdat;

import strukdat.LinearProbing;
import strukdat.TheArrayList;


class Student {
    private int nim;
    private String nama;

    Student(int nim, String nama) {
        /* 
         * penggunaan 'this' digunakan untuk membedakan antara
         * variabel dari class dan parameter pada constructor
        */
        this.nim = nim;
        this.nama = nama;
    }

    @Override //toString dari class String
    /*
     * agar method CetakList() dari class TheArrayList 
     * dapat mencetak nim dan nama dari setiap object class ini.
    */
    public String toString() {
        return(Integer.toString(nim) + " - " + nama + " ");
    }
}

//Main Method
public class MainProgram {
    public static void main(String[] args) {
        TheArrayList<Integer> myList1 = new TheArrayList<Integer>(10);
        myList1.add(1);
        myList1.add(2);
        myList1.add(4);
        myList1.add(5);
        myList1.add(6);
        myList1.add(2,3);
        myList1.cetakList();
        myList1.remove(3);
        myList1.cetakList();

        // TheArrayList<String> myList2 = new TheArrayList<String>(10);
        // myList2.add("Lely");
        // myList2.add("Callysta");
        // myList2.add("Antony");
        // myList2.add("Bella");
        // myList2.add(2,"Edrick");
        // myList2.cetakList();
        // myList2.set(2, "Ghati");
        // myList2.cetakList();
        // String nama = myList2.get(3);
        // System.out.println(nama);

        // TheArrayList<Student> myList3 = new TheArrayList<Student>(10);
        // myList3.add(new Student(535230001, "Dodi Lim"));
        // myList3.add(new Student(535230001, "Didi Ciang"));
        // myList3.add(new Student(535230001, "Dedi Koh"));
        // myList3.add(new Student(535230001, "Dudi Liang"));
        // myList3.cetakList();

        // LinearProbing<Object,String> T = new LinearProbing<Object,String>(7); 
        // T.put(2, "B");
        // T.put(10, "J");
        // T.put(14, "N");
        // T.put(19, "S");
        // T.put(24, "X");
        // T.put(23, "W");
        // T.displayHashTable();

        // if (T.get(23) != null) System.out.println("Data found: " + T.get(23));
        // else System.out.println("data does not exist!");
    }
}
