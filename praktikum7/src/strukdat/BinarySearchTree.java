package strukdat;

public class BinarySearchTree<K extends Comparable<? super K>, V> extends BinaryTree<K,V> implements Tree<K,V> {

    private GNode<K,V> root;
    public BinarySearchTree(){
        root = null;
    }
    @Override
    public void insert(K key, V data) {
        root = insertNode(root, key, data);
    }
    @Override
    public void delete(K key) {
        root = deleteNode(root, key);
    }
    public V search(K key){
        V info = null;
        info = getData(find(root,key));
        return info;
    }
    public K max(){
        K kunci = null;
        kunci = getKey(findMax(root));
        return kunci;
    }
    public K min(){
        K kunci = null;
        kunci = getKey(findMin(root));
        return kunci;

    }

    private GNode<K,V> insertNode(GNode<K,V> node, K k, V data) {
        //Jika tree masih kosong (belum ada node sama sekali), atau
        //T sebelumnnya adalah child node. Buat node baru yang akan 
        //di-link-kan ke child node sebelumnya
        if(node == null) {
        //buat node baru
            GNode<K,V> newNode = new GNode<K,V>(k, data);
            return newNode;
        }

        //.nk
        else if(k.compareTo(node.key) < 0) {
            node.llink = insertNode(node.llink, k, data);
            return node;
        }
        //key dari node baru lebih besar dari atau sama dengan 
        //key child node sebelumnya. Goto the right node (subtree)
        //node baru akan di-link ke right link
        else {
            node.rlink = insertNode(node.rlink, k, data);
            return node;
        }

    }
    private GNode<K,V> deleteNode(GNode<K,V> node, K k) {
        if (node == null)
            return node;

        // Cari node yang akan dihapus
        if (k.compareTo(node.key) < 0)
            node.llink = deleteNode(node.llink, k);
        else if (k.compareTo(node.key) > 0)
            node.rlink = deleteNode(node.rlink, k);
        else {
            // Node dengan satu atau tidak ada child
            if (node.llink == null)
                return node.rlink;
            else if (node.rlink == null)
                return node.llink;

            // Node dengan dua child: Dapatkan penerus inorder (node terkecil di subtree kanan)
            node.key = findMin(node.rlink).key;

            // Hapus penerus inorder
            node.rlink = deleteNode(node.rlink, node.key);
        }
        return node;
    }
    private GNode<K,V> find(GNode<K,V> node, K k) {
        //node adalah subtree (root dari subtree)
        if(node == null || node.key == k) return node;
        else if(node.key.compareTo(k) < 0) return find(node.rlink, k);
        else return find(node.llink, k);

    }
    private GNode<K,V> findMin(GNode<K,V> node) {
        if (node == null)
            return null;
        else if (node.llink == null)
            return node;
        else
            return findMin(node.llink);
    }
    private GNode<K,V> findMax(GNode<K,V> node) {
        if (node == null)
            return null;
        else if (node.rlink == null)
            return node;
        else
            return findMax(node.rlink);
    }

    public void inOrder() {
        printInOrder(root);
    }
    public void preOrder() {
        printPreOrder(root);
    }
    public void postOrder() {
        printPostOrder(root);
    }
    public void levelOrder() {
        printLevelOrder(root);
    }
    public K getKey(GNode<K,V> node) {
        return node.key;
    }
    public V getData(GNode<K,V> node) {
        return node.data;
    }

}
