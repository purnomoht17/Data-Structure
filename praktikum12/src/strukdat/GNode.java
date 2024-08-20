package strukdat;

//public class generic node untuk data berupa pasangan (Key, Value)
public class GNode<K,V> {
    K key;    //key ada bilangan bulat
    V data;   // object data dari sebuah class
    GNode<K,V> llink; //left link
    GNode<K,V> rlink; //right link

    //constructor
    public GNode(K k, V data) {
        this.key = k; 
        this.data = data;   
        this.llink = null;  
        this.rlink = null;  
    }

    @Override //toString() dari class String
    public String toString() {
        return(key.toString() + ":" + data.toString() + " ");
    }
}
