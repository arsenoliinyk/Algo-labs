package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

class HamsterData {
    int baseFood;
    int greedFood;
    int totalFood;

    public HamsterData(int bFood, int gFood) {
        this.greedFood = gFood;
        this.baseFood = bFood;
    }

    public void setTotalFood(int n) {
        this.totalFood = baseFood + (greedFood * (n - 1));
    }

    public int getTotalFood() {
        return this.totalFood;
    }
}

public class Hamster {
    public static int calculateHamsters(String filepath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filepath));
        ArrayList<String> arr = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            arr.add(line);
        }
        ArrayList<HamsterData> hamsterArray = new ArrayList<>();
        int totalFood = Integer.parseInt(arr.get(0));
        int totalOfHamsters = Integer.parseInt(arr.get(1));
        for (int i = 0; i < totalOfHamsters; i++) {
            hamsterArray.add(new HamsterData(Integer.parseInt(arr.get(i + 2).split(" ")[0]),
                    Integer.parseInt(arr.get(i + 2).split(" ")[1])));
        }
        return (BinarySearch.binarySearchHumsters(hamsterArray, totalFood));
    }

    public static void main(String[] args) throws IOException {
        System.out.println(calculateHamsters("./src/main/java/org/example/Hamster.in"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("hamster.out"));
        writer.write("Numb of hums u can afford: "+Integer.toString(calculateHamsters("./src/main/java/org/example/Hamster.in")));
        writer.close();
    }
}
