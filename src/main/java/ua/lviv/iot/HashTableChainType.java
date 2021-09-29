package ua.lviv.iot;

import java.util.ArrayList;

public class HashTableChainType<K, V> {
    private ArrayList< HashNode<K, V> > bucketArr;
    private int numOfBuckets;
    private int size;

    public HashTableChainType(int numOfBucketsP) {
        this.numOfBuckets = numOfBucketsP;
        size = 0;
        bucketArr = new ArrayList<>();
        initArr(numOfBuckets);
    }

    public void print() {
        for (HashNode<K, V> headNode : bucketArr) {
            if(headNode == null) {

            } else {
                int index = getIndex(headNode.getKey());
                System.out.print("[ Index: " + index + " Addr: " + headNode.toString() + " ] -->");
                printChain(headNode);
                System.out.println();
            }
        }
    }

    private void printChain(HashNode<K, V> head) {
        System.out.print(" = [  Key=" + head.getKey() + " Value=" + head.getValue() + " ] --> " + head.getNextElem() );
        if(head.getNextElem() != null){
            printChain(head.getNextElem());
        }
    }

    public ArrayList<HashNode<K, V>> getBucketArr() {
        return bucketArr;
    }

    public HashTableChainType(){
        this(11);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    private void initArr(int numOfBucketsP) {
        for (int i = 0; i < numOfBucketsP; i++) {
            bucketArr.add(null);
        }
    }

    private int hashCode (K key) {
        return key.hashCode();
    }

    private int getIndex(K key) {
        int index = hashCode(key) % numOfBuckets;
        return index < 0 ? -index : index;
    }

    private HashNode<K, V> iterate(K key) {
        int index = getIndex(key);
        int hashCode = hashCode(key);

        HashNode<K, V> head = bucketArr.get(index);
        HashNode<K, V> prevElem = null;

        while (head != null) {
            if (head.getKey().equals(key) && hashCode == head.getHashCode()){
                break;
            }
            prevElem = head ;
            head = head.getNextElem();
        }
        return prevElem;
    }

    private void resize() {
        numOfBuckets *= 2;
        ArrayList<HashNode<K, V>> temp = bucketArr;
        bucketArr = new ArrayList<>();
        initArr(numOfBuckets);

        for (HashNode<K, V> headNode : temp) {
            while (headNode != null) {
                put(headNode.getKey(), headNode.getValue());
                headNode = headNode.getNextElem();
            }
        }
    }

    public synchronized V remove(K key) {
        HashNode<K, V> prevElem = iterate(key);
        int index = getIndex(key);
        HashNode<K, V> head = bucketArr.get(index);

        if(prevElem == null && head != null) {
            bucketArr.set(index, head.getNextElem());
            return head.getValue();
        }if (prevElem != null && prevElem.getNextElem() != null) {
            V temp = prevElem.getNextElem().getValue();
            prevElem.setNextElem(prevElem.getNextElem().getNextElem());
            return temp;
        }
        return null;
    }

    public V get(K key) {
        HashNode<K, V> prevElem = iterate(key);
        int index = getIndex(key);
        HashNode<K, V> head = bucketArr.get(index);

        if (prevElem == null && head != null) {
            return head.getValue();
        }if (prevElem != null && prevElem.getNextElem() != null) {
            return prevElem.getNextElem().getValue();
        }
        return null;
    }

    public synchronized void put(K key, V value) {
        int index = getIndex(key);
        int hashCode = hashCode(key);
        HashNode<K, V> head = bucketArr.get(index);
        HashNode<K, V> prevElem = iterate(key);

        if (prevElem == null && head != null) {
            head.setValue(value);
            return;
        } else if (prevElem != null && prevElem.getNextElem() != null) {
            prevElem.getNextElem().setValue(value);
            return;
        }

        size++;
        HashNode<K, V> newNode = new HashNode<K, V> (key, value, hashCode);
        newNode.setNextElem(head);
        bucketArr.set(index, newNode);
        if ((float) (size / numOfBuckets) >= 0.7) {
            resize();
        }
    }



}

