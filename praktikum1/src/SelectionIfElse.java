public class SelectionIfElse {
    public static void main(String[] args) {
        boolean status = true;
        int bil1 = 5, bil2 = 10;

        //if
        if (status){
            System.out.println("status oke...");
            if(bil1 != bil2) System.out.println(bil1 + "\u2260" + bil2);
        }

        //if-else
        if (bil1 + bil2 > 0) System.out.println(bil1 + " + " + bil2 + " = " + (bil1+bil2));
        else System.out.println("Tidak valid");

        //if-else-if
        if (bil1 < bil2) System.out.println(bil1 + " < " + bil2);
        else if (bil1 > bil2 ) System.out.println(bil1 + " > " + bil2);
        else System.out.println(bil1 + " = " + bil2);
    }
}
