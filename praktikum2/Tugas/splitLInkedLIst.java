package Tugas;

class Node {
    String data;
    Node next;

    Node(String data) {
        this.data = data;
        this.next = null;
    }
}

class LinkedList {
    Node head;

    void insert(String data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            return;
        }
        Node lastNode = head;
        while (lastNode.next != null) {
            lastNode = lastNode.next;
        }
        lastNode.next = newNode;
    }

    void splitList(LinkedList A, LinkedList FIRST, LinkedList SECOND) {
        int length = length(A);
        int mid = length / 2;

        Node current = A.head;
        Node prev = null;

        for (int i = 0; i < mid; i++) {
            prev = current;
            current = current.next;
        }

        FIRST.head = A.head;
        SECOND.head = current;

        if (prev != null) {
            prev.next = null;
        }
    }

    int length(LinkedList list) {
        int count = 0;
        Node current = list.head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    void display() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }
}

public class splitLInkedLIst {
    public static void main(String[] args) {
        LinkedList A = new LinkedList();
        A.insert("Senin");
        A.insert("Selasa");
        A.insert("Rabu");
        A.insert("Kamis");
        A.insert("Jumat");
        A.insert("Sabtu");
        A.insert("Minggu");

        LinkedList FIRST = new LinkedList();
        LinkedList SECOND = new LinkedList();

        System.out.println("Linked list A:");
        A.display();

        A.splitList(A, FIRST, SECOND);

        System.out.println("Linked list FIRST:");
        FIRST.display();
        System.out.println("Linked list SECOND:");
        SECOND.display();
    }
}

