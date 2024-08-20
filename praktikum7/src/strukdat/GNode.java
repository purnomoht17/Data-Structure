package strukdat;


//public class Generic node untuk data berupa pasangan (key, value)
public class GNode<K,V>{
    K key; //key ada bilangan bulat
    V data; //object data dari sebuah class
    GNode<K,V> llink; //left link
    GNode<K,V> rlink; //left link

    //constructor
    public GNode(K k, V data) {
        this.key = k;
        this.data = data;
        llink = null;
        rlink = null;
    }

    @Override //toString() dari class String
    public String toString() {
        return(key.toString() + ":" + data.toString() + " ");
    }
}