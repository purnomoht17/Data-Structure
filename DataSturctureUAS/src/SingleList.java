class Node<T> {
    /*
     * class untuk mendefinisikan satu node di linkedlist secara generic
     * [data | next]   
     * data = bagian node yang berisi informasi (teks, bilangan, object of a class)
     * next = bagian node yang berisi alamat (pointer) ke node lainnya
     */

    T data;
    Node<T> next;

    //constructor
    Node(T value) {
        data = value;   //menyimpan nilai atau data yang ingin disimpan dalam node
        next = null;    //awalnya node tidak memiliki node berikutnya
    }
}

public class SingleList<T> {
    Node<T> head;
    Node<T> tail;
    // Constructor
    public SingleList() {
        head = null;    // Menginisialisasi bahwa list kosong, baik head maupun tail adalah null
        tail = null;
    }
    // Method untuk menyisipkan node baru di akhir list
    public void pushQ(T value) {
        // Buat node baru dengan nilai (value) yang diberikan
        Node<T> newNode = new Node<T>(value);
        // Jika list masih kosong (head == null), maka node baru menjadi head dan tail
        if (head == null) {
            head = newNode;
            tail = newNode;
        }
        // Jika list tidak kosong
        else {
            // Sisipkan node baru setelah tail dan kemudian update tail menjadi node baru
            tail.next = newNode;
            tail = newNode;
        }
    }
}




