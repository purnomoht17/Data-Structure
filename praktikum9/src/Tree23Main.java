class DataItem<V> {
   public V value;

   public DataItem(V value) {
      this.value = value;
   }

   public void displayItem() {
      System.out.print("/" + value);
   }
}

class Node<V> {
   private static final int ORDER = 4;
   private int numItems;
   private Node<V> parent;
   private Node<V>[] childArray = new Node[ORDER];
   private DataItem<V>[] itemArray = new DataItem[ORDER - 1];

   public void connectChild(int childNum, Node<V> child) {
      childArray[childNum] = child;
      if (child != null)
         child.parent = this;
   }

   public Node<V> disconnectChild(int childNum) {
      Node<V> tempNode = childArray[childNum];
      childArray[childNum] = null;
      return tempNode;
   }

   public Node<V> getChild(int childNum) {
      return childArray[childNum];
   }

   public Node<V> getParent() {
      return parent;
   }

   public boolean isLeaf() {
      return childArray[0] == null;
   }

   public int getNumItems() {
      return numItems;
   }

   public DataItem<V> getItem(int index) {
      return itemArray[index];
   }

   public boolean isFull() {
      return numItems == ORDER - 1;
   }

   public int findItem(V value) {
      for (int j = 0; j < ORDER - 1; j++) {
         if (itemArray[j] == null)
            break;
         else if (itemArray[j].value.equals(value))
            return j;
      }
      return -1;
   }

   public int insertItem(DataItem<V> newItem) {
      numItems++;
      V newValue = newItem.value;

      for (int j = ORDER - 2; j >= 0; j--) {
         if (itemArray[j] == null)
            continue;
         else {
            V itsValue = itemArray[j].value;
            if (newValue.toString().compareTo(itsValue.toString()) < 0)
               itemArray[j + 1] = itemArray[j];
            else {
               itemArray[j + 1] = newItem;
               return j + 1;
            }
         }
      }
      itemArray[0] = newItem;
      return 0;
   }

   public DataItem<V> removeItem() {
      DataItem<V> temp = itemArray[numItems - 1];
      itemArray[numItems - 1] = null;
      numItems--;
      return temp;
   }

   public void removeItemByKey(V value) {
      for (int i = 0; i < numItems; i++) {
         if (itemArray[i].value.equals(value)) {
            for (int j = i; j < numItems - 1; j++) {
               itemArray[j] = itemArray[j + 1];
            }
            itemArray[numItems - 1] = null;
            numItems--;
            break;
         }
      }
   }

   public void displayNode() {
      for (int j = 0; j < numItems; j++)
         itemArray[j].displayItem();
      System.out.println("/");
   }
}

class Tree23<V> {
   private Node<V> root = new Node<>();

   public int find(V value) {
      Node<V> curNode = root;
      int childNumber;
      while (true) {
         if ((childNumber = curNode.findItem(value)) != -1)
            return childNumber;
         else if (curNode.isLeaf())
            return -1;
         else
            curNode = getNextChild(curNode, value);
      }
   }

   public void insert(V value) {
      Node<V> curNode = root;
      DataItem<V> tempItem = new DataItem<>(value);

      while (true) {
         if (curNode.isLeaf())
            break;
         else
            curNode = getNextChild(curNode, value);
      }
      curNode.insertItem(tempItem);
      if (curNode.isFull()) {
         split(curNode);
         curNode = curNode.getParent();
         while (curNode.isFull()) {
            split(curNode);
            curNode = curNode.getParent();
         }
      }
   }

   public void delete(V value) {
      recDelete(root, value);
      if (root.getNumItems() == 0) {
         if (!root.isLeaf())
            root = root.getChild(0);
      }
   }

   private void recDelete(Node<V> thisNode, V value) {
      if (thisNode == null) {
         return;
      }
      int childIndex = thisNode.findItem(value);
      if (childIndex != -1) {
         if (thisNode.isLeaf()) {
            thisNode.removeItemByKey(value);
            return;
         } else {
            Node<V> predOrSucc = thisNode.getChild(childIndex);
            while (!predOrSucc.isLeaf()) {
               predOrSucc = predOrSucc.getChild(predOrSucc.getNumItems());
            }
            DataItem<V> replacement = predOrSucc.getItem(predOrSucc.getNumItems() - 1);
            thisNode.removeItemByKey(value);
            thisNode.insertItem(replacement);
            recDelete(predOrSucc, replacement.value);
         }
      } else {
         if (thisNode.isLeaf()) {
            return;
         } else {
            Node<V> nextNode = getNextChild(thisNode, value);
            recDelete(nextNode, value);
         }
      }
   }

   private Node<V> getNextChild(Node<V> theNode, V value) {
      int j;

      int numItems = theNode.getNumItems();
      for (j = 0; j < numItems; j++) {
         if (value.toString().compareTo(theNode.getItem(j).value.toString()) < 0)
            return theNode.getChild(j);
      }
      return theNode.getChild(j);
   }

   public void split(Node<V> thisNode) {
      DataItem<V> itemB, itemC;
      Node<V> parent, child2, child3;
      int itemIndex;

      itemC = thisNode.removeItem();
      itemB = thisNode.removeItem();
      child2 = thisNode.disconnectChild(2);
      child3 = thisNode.disconnectChild(3);

      Node<V> newRight = new Node<>();

      if (thisNode == root) {
         root = new Node<>();
         parent = root;
         root.connectChild(0, thisNode);
      } else
         parent = thisNode.getParent();

      itemIndex = parent.insertItem(itemB);
      int n = parent.getNumItems();

      for (int j = n - 1; j > itemIndex; j--) {
         Node<V> temp = parent.disconnectChild(j);
         parent.connectChild(j + 1, temp);
      }
      parent.connectChild(itemIndex + 1, newRight);
      newRight.insertItem(itemC);
      newRight.connectChild(0, child2);
      newRight.connectChild(1, child3);
   }

   public void displayTree() {
      recDisplayTree(root, 0, 0);
   }

   private void recDisplayTree(Node<V> thisNode, int level, int childNumber) {
      System.out.print("level=" + level + " child=" + childNumber + " ");
      thisNode.displayNode();

      int numItems = thisNode.getNumItems();
      for (int j = 0; j < numItems + 1; j++) {
         Node<V> nextNode = thisNode.getChild(j);
         if (nextNode != null)
            recDisplayTree(nextNode, level + 1, j);
         else
            return;
      }
   }
}

public class Tree23Main {
   public static void main(String[] args) {
      Tree23<Comparable<?>> theTree = new Tree23<>();

      theTree.insert("A");
      theTree.insert("B");
      theTree.insert("C");
      theTree.insert("D");
      theTree.insert("E");
      theTree.insert("F");
      theTree.insert("G");
      theTree.insert("H");
      theTree.insert("I");
      theTree.insert("J");
      theTree.insert(77);
      theTree.insert(2);
      theTree.insert(80);
      theTree.insert(3);
      theTree.insert(10);
      theTree.insert(17);
      theTree.insert(5);
      theTree.insert(62);
      theTree.insert(9);
      theTree.insert(55);
      theTree.insert(88);
      theTree.displayTree();

      Comparable<?> valueToFind = 22;
      int found = theTree.find(valueToFind);
      if (found != -1)
         System.out.println(valueToFind + " ditemukan di tree \n");
      else
         System.out.println(valueToFind + " tidak ditemukan di tree \n");

      Comparable<?> valueToDelete = "E";
      theTree.delete(valueToDelete);
      System.out.println("Setelah penghapusan " + valueToDelete);
      theTree.displayTree();
   }
}
