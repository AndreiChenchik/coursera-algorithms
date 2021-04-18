/* *****************************************************************************
 *  Name:              Andrei Chenchik
 *  Coursera User ID:  c27c0b79c8a674f5a98145672b4581b2
 *  Last modified:     March 19, 2021
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class ShellSort {
    public static void main(String[] args) {
        int length = 200;
        Double[] doubleArray = new Double[length];
        for (int i = 0; i < length; i++) {
            doubleArray[i] = StdRandom.uniform();
            StdOut.print(doubleArray[i] + " ");
        }

        sort(doubleArray);
        StdOut.println("\nSorted: " + isSorted(doubleArray));

        for (int i = 0; i < length; i++) {
            StdOut.print(doubleArray[i] + " ");
        }
    }

    public static <Item extends Comparable<Item>> void sort(Item[] array) {
        int arraySize = array.length;

        int h = 1;
        while (h < arraySize / 3) {
            h = 3 * h + 1;
        }

        while (h >= 1) {
            for (int i = h; i < arraySize; i++) {
                for (int j = i; j >= h; j -= h) {
                    if (less(array[j], array[j - h])) {
                        exchange(array, j, j - h);
                    }
                    else {
                        break;
                    }
                }
            }
            h = h / 3;
        }
    }

    private static <Item extends Comparable<Item>> boolean isSorted(Item[] array) {
        for (int i = 1; i < array.length; i++) {
            if (less(array[i], array[i - 1])) {
                return false;
            }
        }
        return true;
    }

    private static <Item extends Comparable<Item>> boolean less(Item first, Item second) {
        return first.compareTo(second) < 0;
    }

    private static <Item extends Comparable<Item>> void exchange(Item[] array, int first,
                                                                 int second) {
        Item swap = array[first];
        array[first] = array[second];
        array[second] = swap;
    }
}
