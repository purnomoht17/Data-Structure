package src;/*
 * Learning LinkedList Singular
 * head -> |10|o->|20|o->|30| <-o tail
 *
 * @author: Lely Hiryanto
 */


import java.util.*;

class NodeSingle<T> {
    /*
     * class untuk mendefinisikan satu node di linkedlist secara generic
     * [data | next]
     * data = bagian node yang berisi informasi (teks, bilangan, object of a class)
     * next = bagian node yang berisi alamat (pointer) ke node lainnya
     */

    T data;
    NodeSingle<T> next;

    //constructor
    NodeSingle(T value) {
        data = value;
        next = null;
    }
}

public class SingleList<T> {
    NodeSingle<T> head;
    NodeSingle<T> tail;

    //constructor
    public SingleList() {
        head = null;
        tail = null;
    }

    /*
     * Method pushQ, yang akan meletakkan setiap node baru yang dibuat di akhir list
     * (queue)
     */
    public void pushQ(T value) {
        //buat node baru
		NodeSingle<T> newNode = new NodeSingle<>(value);
        //jika list kosong, head dan tail sama-sama menunjuk ke node pertama
		if (head == null) {
            head = newNode;
            tail = newNode;
        }
        //jika tidak kosong, tail diupdate untuk menunjuk ke node baru
		else {
			tail.next = newNode;
            tail = newNode;
		}
	}

    /*
     * Method pushS, yang akan meletakkan setiap node baru yang dibuat di awal list
     * (stack)
     */
    public void pushS(T value) {
        //buat node baru
		NodeSingle<T> newNode = new NodeSingle<>(value);
        //jika list kosong, head dan tail sama-sama menunjuk ke node pertama
		if (head == null) {
			head = newNode;
			tail = newNode;
		}
        //jika tidak kosong, node baru menunjuk ke node pertama di list
        //head diupdate untuk menunjuk ke node baru
		else {
            newNode.next = head;
			head = newNode;
		}
	}

    /*
     * Mencetak bagian informasi dari setiap node di linkedlist
     */
    public void cetakList() {
        //set pointer curr untuk menunjuk ke node pertama (node pertama yang ditunjuk oleh head)
		NodeSingle<T> curr = head;
        //jika list kosong, tampilkan pesan list kosong
		if(curr == null) System.out.println("List kosong!");
        /*
         * jika list tidak kosong, maka cetak bagian informasi
         * dari setiap node yang dikunjungi.
        */
		else {
            System.out.print("[ ");
			while(curr != null) {
				System.out.print(curr.data.toString() + " ");
                /*
                 * curr diupdate untuk menunjuk ke node selanjutnya
                 * sampai curr null, dimana curr diset ke bagian 'next'
                 * dari node terakhir yang bernilai null. Artinya,
                 * pointer curr sudah ada di akhir list.
                 */
				curr = curr.next;
			}
		}
        System.out.println("]");
	}


    //pop(), remove first element untuk queue dan stack
    NodeSingle<T> pop() {
        NodeSingle<T> n;
        if(head == null) n = null;
        else {
            n = head;
            head = head.next;
            if (head == null) {
                tail = null;
            }
        }
        return n;
    }

    //return bagian data dari node
    public T remove() {
        NodeSingle<T> n = pop();
        if(n == null) return null;
        else return n.data;
    }

    //@overloading method remove()
    boolean remove(T data) {
        NodeSingle<T> curr = head;
        NodeSingle<T> prev = head;
        boolean deleted = false;

        while(curr != null && !deleted) {
            // jika setiap elemen dari data sama
            // dengan data yang dicari
            if(curr.data.equals(data)) {
                deleted = true;
                //node sebelum dilink ke node berikut dari node yang diremove
                prev.next = curr.next;
                //jika node pertama yang diremove, update head ke node kedua
                if(curr == head) head = head.next;
                //jika list hanya memiliki satu nod, set head = null (list kosong)
                if(head == null) tail = null;
            }
            //jika belum ditemukan node yang akan diremove
            //simpan pointer ke node saat ini, dan update
            //pointer ke node berikutnya
            else {
                prev = curr;
                curr = curr.next;
            }
        }

        return deleted;
    }

    //mengecek apakah list kosong atau tidak
    public boolean isEmpty() {
        if(head == null) return true;
        else return false;
    }
}



