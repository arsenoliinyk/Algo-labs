package org.example;

import java.util.ArrayList;

public class GFG {

    // partition function similar to quick sort
    // Considers last element as pivot and adds
    // elements with less value to the left and
    // high value to the right and also changes
    // the pivot position to its respective position
    // in the final array.
    public static int partition(ArrayList<HamsterData> arr, int low,
                                int high) {
        int pivot = arr.get(high).greedFood, pivotloc = low;
        for (int i = low; i <= high; i++) {
            // inserting elements of less value
            // to the left of the pivot location
            if (arr.get(i).greedFood < pivot) {
                int temp = arr.get(i).greedFood;
                arr.set(i,arr.get(pivotloc));
                arr.get(pivotloc).greedFood = temp;
                pivotloc++;
            }
        }

        // swapping pivot to the final pivot location
        int temp = arr.get(high).greedFood;
        arr.set(high, arr.get(pivotloc));
        arr.get(pivotloc).greedFood = temp;

        return pivotloc;
    }

    // finds the kth position (of the sorted array)
    // in a given unsorted array i.e this function
    // can be used to find both kth largest and
    // kth smallest element in the array.
    // ASSUMPTION: all elements in arr[] are distinct
    public static int kthSmallest(ArrayList<HamsterData> arr, int k) {
        return kthSmallest(arr, 0, arr.size() - 1, k);
    }

    private static int kthSmallest(ArrayList<HamsterData> arr, int low,
                                   int high, int k) {
        // find the partition
        int partition = partition(arr, low, high);

        // if partition value is equal to the kth position,
        // return value at k.
        if (partition == k - 1)
            return arr.get(partition).greedFood;

            // if partition value is less than kth position,
            // search right side of the array.
        else if (partition < k - 1)
            return kthSmallest(arr, partition + 1, high, k);

            // if partition value is more than kth position,
            // search left side of the array.
        else
            return kthSmallest(arr, low, partition - 1, k);
    }
}
