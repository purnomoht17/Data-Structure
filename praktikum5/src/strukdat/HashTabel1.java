package strukdat;

class HashTable1<K,V> extends Hashing<K,V> {
    HashTable1(int kapasitas){
        super(kapasitas);
    }

    @Override
    public void put(K key, V value) {
        HashNode<K,V> N = new HashNode<K,V>(key, value);
        int h = convertToNumber(key) % table.maxSize();
        int originalHash = h;
        int i = 1;

        while (isCollision(h)) {
            h = (originalHash + i * i) % table.maxSize();
            i++;
        }

        if (size() == table.maxSize()) {
            System.out.println("Table is full!");
            return;
        }

        table.set(h, N);
        incSize();
    }

    @Override
    public void displayHashTable() {
        System.out.println("Hash table contains " + size() + " items");
        for (int i = 0; i < table.maxSize(); i++) {
            if (table.get(i) != null) {
                System.out.println(i + ": " + table.get(i).toString() + " ");
            } else {
                System.out.println(i + ": ");
            }
        }
    }
}


class Main {
    public static void main(String[] args) {
        HashTable1<Integer, Integer> hashTable1 = new HashTable1<>(10);

        int[] keys = {4371, 1323, 6173, 4199, 4344, 9679, 1989};

        for (int key : keys) {
            int hashIndex = key % 10;

        if (!hashTable1.isCollision(hashIndex)) {
            hashTable1.put(hashIndex, key);
        } else {
            // Jika terjadi tabrakan, gunakan quadratic probing
            int i = 1;
            while (true) {
                int newIndex =Math.abs(hashIndex - i * i);
                if (!hashTable1.isCollision(newIndex)) {
                    hashTable1.put(newIndex, key);
                    break;
                }
                i++;
            }
        }
    }
    hashTable1.displayHashTable();
    }
}