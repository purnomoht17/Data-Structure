package strukdat;

public class BinaryTree<K,V> {
    //rekursif in order traversal
    public void printInOrder(GNode<K,V> node) {
        if(node == null) return;
        else {
            //ke left node secara rekursif
            printInOrder(node.llink);
            //cetak node
            System.out.print(node + " ");
            //ke right node secara rekursif
            printInOrder(node.rlink);
        }
    }

    //post order traversal
    public void printPostOrder(GNode<K,V> node) {
        if(node == null) return;
        else {
            //ke left node secara rekursif
            printPostOrder(node.llink);
            //ke right node secara rekursif
            printPostOrder(node.rlink);
            //cetak node
            System.out.print(node + " ");
        }
    }

    //pre order traversal
    void printPreOrder(GNode<K,V> node) {
        if(node == null) return;
        else {
            //cetak node
            System.out.print(node + " ");
            //ke left node secara rekursif
            printPreOrder(node.llink);
            //ke right node secara rekursif
            printPreOrder(node.rlink);
        }
    }

    //cetak node di setiap level dari root, left child,
    //dan right child secara rekursif
    public void printLevelOrderRec(SingleList<GNode<K,V>> q) {
        if(q.isEmpty()) return;
        GNode<K,V> node = q.remove();
        //cetak key dari node
        System.out.print(node + " ");
        if(node.llink != null) q.pushQ(node.llink);
        if(node.rlink != null) q.pushQ(node.rlink);
        printLevelOrderRec(q);
    }

    //level order traversal
    public void printLevelOrder(GNode<K,V> node) {
        //buat queue untuk menampung node disetiap level
        SingleList<GNode<K,V>> q = new SingleList<GNode<K,V>>();
        q.pushQ(node);
        //memanggil fungsi rekursif untuk mencetak key
        //dari node di setiap level
        printLevelOrderRec(q);
    }
}
