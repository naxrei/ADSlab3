import java.util.LinkedList;

public class HashTable<K, V> {
    private LinkedList<HashNode<K, V>>[] chain;
    private int M = 11;
    private int size;
    public HashTable() {
        chain = new LinkedList[M];
        size = 0;
    }
    public HashTable(int m) {
        this.M = m;
        chain = new LinkedList[M];
        size = 0;
    }
    private int hash(K key) {
        return key.hashCode() % M;
    }
    public void put(K key, V value) {
        int index = hash(key);
        if (chain[index] == null){
            chain[index] = new LinkedList<HashNode<K, V>>();
        }
        for (HashNode<K, V> node: chain[index]){
            if(node.getKey().equals(key)){
                node.setValue(value);
                return;
            }
        }
        chain[index].add(new HashNode<K, V>(key, value));
        size++;
    }
    public V get(K key) {
        int index = hash(key);
        if (chain[index] == null) return null;

        for (HashNode<K, V> node: chain[index]){
            if(node.getKey().equals(key)) return node.getValue();
        }
        return null;
    }
    public V remove(K key) {
        int index = hash(key);
        if (chain[index] == null) return null;

        for (HashNode<K, V> node: chain[index]){
            if(node.getKey().equals(key)) {
                chain[index].remove(node);
                size--;
                return node.getValue();
            }
        }
        size--;
        return null;
    }
    public boolean contains(V value) {
        for(LinkedList<HashNode<K,V>> list: chain) {
            for(HashNode<K,V> node: list){
                if (node.getValue().equals(value)) return true;
            }
        }
        return false;
    }
    public K getKey(V value) {
        for(LinkedList<HashNode<K,V>> list: chain) {
            for(HashNode<K,V> node: list){
                if (node.getValue().equals(value)) return node.getKey();
            }
        }
        return null;
    }

    public int getSize() {
        return size;
    }

    /**
     * replace
     * @param key provides key that we need to calculate as hash
     * @param oldValue value for replace
     * @param newValue new value that replaces oldValue
     * @return true if oldValue were replaced, else otherwise
     * **/
    public boolean replace(K key, V newValue, V oldValue) {
        int index = hash(key);
        LinkedList<HashNode<K, V>> bucket = chain[index];
        if (bucket != null) {
            for (HashNode<K, V> item: bucket) {
                if(item.getValue().equals(oldValue)){
                    item.setValue(newValue);
                    return true;
                }
            }
        }
        return false;


    }

    @Override
    public String toString() {
        String out = "";
        for(int i = 0; i<this.chain.length;i++) {
            out += "Bucket " + i + ": " + chain[i].size() + "\n";
        }
        return out;
    }
}