import strukdat.HuffmanNode;
import strukdat.Heap;
import strukdat.TheArrayList;

public class HuffmanCoding {
    public static void main(String[] args) {
        char[] charArray = {'A', 'I', 'M', 'O', 'K', 'N', 'F', 'R', 'T'};
        int[] charFreq = {45, 35, 29, 19, 15, 10, 8, 5, 4};

        // Create a priority queue with min heap
        Heap<Integer, HuffmanNode> pq = new Heap<Integer, HuffmanNode>(charArray.length, true);

        // Input each character and its frequency to pq
        for (int i = 0; i < charArray.length; i++) {
            HuffmanNode node = new HuffmanNode(charFreq[i], charArray[i], null, null);
            pq.insert(charFreq[i], node);
        }

        // Build minimum heap from pq
        pq.buildHeap();

        HuffmanNode root = null;
        HuffmanNode x, y;
        int sum;

        while (pq.size() > 1) {
            sum = pq.getKey(pq.first());
            x = pq.getData(pq.first());
            pq.removeFirst();

            sum += pq.getKey(pq.first());
            y = pq.getData(pq.first());
            pq.removeFirst();

            root = new HuffmanNode(sum, ' ', x, y);
            pq.insert(sum, root);
        }

        TheArrayList<String> huffmanCodes = new TheArrayList<String>(charArray.length);
        if (root != null) {
            huffmanCodes = root.getHuffmanCodes(root, charArray.length);
        } else {
            System.out.println("Pohon Huffman tidak berhasil dibangun.");
        }

        System.out.print("[ ");
        for (int i = 0; i < charArray.length; i++) {
            System.out.print(huffmanCodes.get(i) + " ");
        }
        System.out.println("]");

        System.out.println("---------------------");
        System.out.println(" Huruf | Huffman code");
        System.out.println("---------------------");
        for (int i = 0; i < huffmanCodes.size(); i++) {
            // Split each string in ArrayList to get the character and its Huffman code
            String[] parts = huffmanCodes.get(i).split(": ");

            // Display the character and its Huffman code
            System.out.println("  " + parts[0] + "   |   " + parts[1]);
        }

        System.out.println("---------------------");
    }
}