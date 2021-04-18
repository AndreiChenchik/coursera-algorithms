/* *****************************************************************************
 *  Name:              Andrei Chenchik
 *  Coursera User ID:  c27c0b79c8a674f5a98145672b4581b2
 *  Last modified:     March 19, 2021
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class ShuffleSort {
    public static void main(String[] args) {
        int length = 20;
        Double[] doubleArray = new Double[length];
        for (int i = 0; i < length; i++) {
            doubleArray[i] = StdRandom.uniform();
            StdOut.print(doubleArray[i] + " ");
        }
        StdOut.println();

        sort(doubleArray);

        for (int i = 0; i < length; i++) {
            StdOut.print(doubleArray[i] + " ");
        }
    }

    public static <Item extends Comparable<Item>> void sort(Item[] array) {
        int arraySize = array.length;
        for (int i = 1; i < arraySize; i++) {
            int newPlace = StdRandom.uniform(i + 1);
            exchange(array, i, newPlace);
        }
    }

    private static <Item extends Comparable<Item>> boolean less(Item first, Item second) {
        return first.compareTo(second) < 0;
    }

    private static <Item extends Comparable<Item>> void exchange(Item[] array, int first, int second) {
        Item swap = array[first];
        array[first] = array[second];
        array[second] = swap;
    }
}
