package ua.lviv.iot;

class HashNode<K, V> {
    private K key;
    private V value;
    private final int hashCode;
    private HashNode<K, V> nextElem;

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public int getHashCode() {
        return hashCode;
    }

    public HashNode<K, V> getNextElem() {
        return nextElem;
    }

    public void setNextElem(HashNode<K, V> nextElem) {
        this.nextElem = nextElem;
    }

    HashNode(K key, V value, int hashCode) {
        this.key = key;
        this.value = value;
        this.hashCode = hashCode;
        this.nextElem = null;
    }
}
