package strukdat;
public interface Tree<K,V> {
    public void insert(K key, V data);
    public void delete(K key);
}
