package strukdat;
import strukdat.TheArrayList;
import strukdat.GNode;

public class Heap<K extends Comparable<? super K>,V> {
    TheArrayList<GNode<K, V>> arrList;
    boolean priority;

    //membuat array list dan mengeset apakah heap biasa (heap max)
    //atau heap dengan priority (heap min)
    public Heap(int capacity, boolean priority) {
        arrList = new TheArrayList<GNode<K, V>>(capacity);
        this.priority = priority;
    }

    //mengembalikan jumlah elemen di heap
    public int size() {
        return arrList.size();
    }

    //mengembalikan bagian value (data/informasi)
    //dari node berdasarkan index
    public V getData(int index) {
        return arrList.get(index).data;
    }

    //mengembalikan bagian value (data/informasi)
    //dari node berdasarkan value yang diberikan
    public V getData(GNode<K, V> node) {
        return node.data;
    }

    //mengembalikan bagian key dari node
    //berdasarkan index
    public K getKey(int index) {
        return arrList.get(index).key;
    }

    //mengembalikan bagian key dari node
    //berdasarkan value yang diberikan
    public K getKey(GNode<K, V> node) {
        return node.key;
    }

    //menambahkan node <key,value> ke heap tanpa heapify
    public void add(K key, V data) {
        arrList.add(new GNode<K, V>(key, data));
    }

    //menyisipan node <key,value> ke heap
    //dengan heapify (max atau min)
    public void insert(K key, V data) {
        arrList.add(new GNode<K, V>(key, data));
        int size = arrList.size();
        for (int i = size / 2 - 1; i >= 0; i = (i + 1) / 2 - 1) {
            if (priority) heapifyMin(size, i);
            else heapifyMax(size, i);
        }
    }

    //membuat heap
    public void buildHeap() {
        int size = arrList.size();
        // build heapSort (rearrange array)
        for (int i = size / 2 - 1; i >= 0; i--) {
            if (priority) heapifyMin(size, i);
            else heapifyMax(size, i);
        }
    }

    // to max heapify a subtree rooted at node i
    void heapifyMax(int size, int i) {
        int parent = i; // initialize parent node
        int left = 2 * i + 1; // initialize left child node
        int right = 2 * i + 2; // initialize right child node
        // if left child is larger than parent
        if (left < size && arrList.get(left).key.compareTo(arrList.get(parent).key) > 0)
            parent = left;
        // if right child is larger than parent
        if (right < size && arrList.get(right).key.compareTo(arrList.get(parent).key) > 0)
            parent = right;
        // if parent is not root
        if (parent != i) {
            // swap
            GNode<K, V> temp = arrList.get(i);
            arrList.set(i, arrList.get(parent));
            arrList.set(parent, temp);
            // recursively heapify the affected sub-tree
            heapifyMax(size, parent);
        }
    }

    //heapsort
    public void sort() {
        int size = arrList.size();
        // build heapSort (rearrange array)
        buildHeap();
        // one by one extract an element from heapSort
        for (int i = size - 1; i >= 0; i--){
            // swap current root node to rightmost leaf node
            GNode<K, V> temp = arrList.get(0);
            arrList.set(0, arrList.get(i));
            arrList.set(i, temp);

            // call max or min heapify on the reduced heap
            if (priority) heapifyMin(i, 0);
            else heapifyMax(i, 0);
        }
    }

    // to min heapify a subtree rooted at node i
    void heapifyMin(int size, int i){
        int smallest = i; // initialize smallest node
        int left = 2 * i + 1; // initialize left child node
        int right = 2 * i + 2; // initialize right child node

        // if left child is smaller than smallest
        if (left < size && arrList.get(left).key.compareTo(arrList.get(smallest).key) < 0)
            smallest = left;
        // if right child is smaller than smallest
        if (right < size && arrList.get(right).key.compareTo(arrList.get(smallest).key) < 0)
            smallest = right;
        // if smallest is not root
        if (smallest != i) {
            // swap
            GNode<K, V> temp = arrList.get(i);
            arrList.set(i, arrList.get(smallest));
            arrList.set(smallest, temp);
            // recursively heapify the affected sub-tree
            heapifyMin(size, smallest);
        }
    }
    //mengembalikan root node (tidak menghapus)
    public GNode<K,V> first() {
        if (arrList.isEmpty()) {
            return null;
        }
        return arrList.get(0);
    }
    //mengembalikan root node dan menghapusnya dari heap
    public GNode<K,V> removeFirst() {
        if (arrList.isEmpty()) {
            return null;
        }
        // Get the root node
        GNode<K, V> root = arrList.get(0);
        int lastIndex = arrList.size() - 1;
        // Replace the root with the last node
        arrList.set(0, arrList.get(lastIndex));
        // Remove the last node
        arrList.remove(lastIndex);

        // Heapify down from the root
        if (priority)
            heapifyMin(arrList.size(), 0);
        else
            heapifyMax(arrList.size(), 0);

        return root;
    }
    // A utility function to print array of size n
    public void display() {
        arrList.cetakList();
    }
}