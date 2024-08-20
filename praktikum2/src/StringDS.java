/*
 * Learning string data structure
 * 
 * @author: Lely Hiryanto
 */

import java.util.Scanner;

public class StringDS {
    
    public static void main(String[] args) {

        //input strings
        Scanner scan = new Scanner(System.in);

        System.out.print("NIM: "); String nim = scan.nextLine();
        System.out.print("Nama: "); String nama = scan.nextLine();
        System.out.print("Umur = "); int age = scan.nextInt();

        String prodi = nim.substring(0,3);
        if(prodi.equals("535")) prodi = "Teknik Informatika";
        else if(prodi.equals("825")) prodi = "Sistem Informasi";
        else prodi = "Untar";
        System.out.println(nama + " usia " + age + " tahun adalah mahasiswa " + prodi);
    }
}
