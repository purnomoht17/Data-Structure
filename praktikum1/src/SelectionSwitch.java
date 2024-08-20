public class SelectionSwitch {
    public static void main(String[] args) {
        byte pil = 1;
        switch (pil){
            case 1:
                System.out.println("Pilihan ke 1");
                break;
            case 2:
                System.out.println("Pilihan ke 2");
                break;
            case 3:
                System.out.println("Pilihan ke 3");
                break;
            default:
                System.out.println("Keluar");
                break;
        }
    }
}
