package org.example;

import java.util.ArrayList;

public class BinarySearch{
    public static int binarySearchHumsters(ArrayList<HamsterData>arr, int foodAmount){
        int mid = arr.size()/2;
        return binarySearch(arr, foodAmount,mid, arr.size()- mid, 0);
    }

    private static int  binarySearch(ArrayList<HamsterData>arr, int foodAmount,int current, int left, int previousShift){
        arr.forEach(hamsterData -> hamsterData.setTotalFood(current));
        GFG.kthSmallest(arr, current);
        int totalConsumption =0;
        for (int i =0; i<current;i++){
            totalConsumption+= arr.get(i).totalFood;
        }
        int movement=0;
        if(left != 0) {
            if (totalConsumption <= foodAmount) {
                movement = (int) Math.ceil(left/2.0);
                left = left - movement;
                return binarySearch(arr, foodAmount, current + movement, left, movement);
            }
            else {
                movement = (int) Math.ceil(left / 2.0);
                left = left - movement;
                return binarySearch(arr, foodAmount, current - movement, left, movement);
            }
        }
        return current - previousShift;

    }

}
