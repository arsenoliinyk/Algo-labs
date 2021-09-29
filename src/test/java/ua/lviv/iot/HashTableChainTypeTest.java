package ua.lviv.iot;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Objects;


public class HashTableChainTypeTest {
    public final int numBuckets = 5;
    public HashTableChainType<String, Integer> hashTable;

    @Before
    public void init() {
        hashTable = new HashTableChainType<>(numBuckets);
    }

    @Test
    public void testPut() {
        hashTable.put("1", 1);
        int index = Objects.hashCode("1") % numBuckets;
        Assert.assertEquals(Integer.valueOf(1), hashTable.getBucketArr().get(index).getValue());
    }

    @Test
    public void testGet() {
        hashTable.put("1", 1);
        Assert.assertEquals(Integer.valueOf(1), hashTable.get("1"));
    }

    @Test
    public void testRemove() {
        hashTable.put("1", 1);
        hashTable.remove("1");
        Assert.assertNull(hashTable.get("1"));
    }

    @Test
    public void testIsEmpty() {
        Assert.assertTrue(hashTable.isEmpty());
    }

    @Test
    public void testSinglyLinkedList() {
        hashTable.put("1", 1);
        hashTable.put("6", 6);
        int indexL = Objects.hashCode("6") % numBuckets;
        Assert.assertEquals(Integer.valueOf(1), hashTable.getBucketArr().get(indexL).getNextElem().getValue());
    }
}