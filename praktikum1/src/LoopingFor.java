public class LoopingFor {
    public static void main(String[] args) {
        System.out.println("For Loop");
        for (int i = 0; i < 3; i++) System.out.print(i + " ");
        System.out.println();

        System.out.println("While Loop");
        int j = 0;
        while (j < 3){
            System.out.print(j + " ");
            j++;
        }

        System.out.println();
        System.out.println("do-while");
        int k = 0;
        do {
            System.out.print(k + " ");
            k++;
        } while (k < 3);
            System.out.println();
    }
}
