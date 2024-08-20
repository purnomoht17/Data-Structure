import strukdat.BinarySearchTree;
import strukdat.Tree;

public class BSTApp {

    public static void main(String[] args) {
        BinarySearchTree<Integer,String> bst = new BinarySearchTree<>();

        bst.insert(27,"A");
        bst.insert(13,"B");
        bst.insert(54,"C");
        bst.insert(12,"D");
        bst.insert(24,"E");
        bst.insert(40,"F");
        bst.insert(56,"G");
        bst.insert(10,"H");
        bst.insert(15,"I");
        bst.insert(33,"J");
        bst.insert(77,"K");
        bst.insert(3,"L");
        bst.insert(22,"M");
        bst.insert(73,"N");
        bst.insert(85,"O");

        System.out.println("Inorder Traversal");
        bst.inOrder();
        System.out.println();
        System.out.println("Search 12 => " + bst.search(12));       //cari node
        System.out.println("Min = " + bst.min());   //min node
        System.out.println("Max = " + bst.max());   //max node
        System.out.println();

        System.out.println("Postorder Traversal");
        System.out.print("Postorder before remove 33 : ");
        bst.postOrder();
        System.out.println();
        System.out.print("Postorder after remove 33 : ");
        bst.delete(33);     //delete node
        bst.postOrder();
        System.out.println();
        System.out.println();

        System.out.println("Preorder");
        bst.preOrder();
        System.out.println();
        System.out.println();

        System.out.println("Levelorder Traversal");
        bst.levelOrder();
    }
}
