/* *****************************************************************************
 *  Name:              Andrei Chenchik
 *  Coursera User ID:  c27c0b79c8a674f5a98145672b4581b2
 *  Last modified:     March 19, 2021
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class MergeSort<Item extends Comparable<Item>> {
    public static void main(String[] args) {
        int length = 200;
        Double[] doubleArray = new Double[length];
        for (int i = 0; i < length; i++) {
            doubleArray[i] = StdRandom.uniform();
            StdOut.print(doubleArray[i] + " ");
        }
        StdOut.println();

        sort(doubleArray);
        StdOut.println("Sorted: " + isSorted(doubleArray));

        for (int i = 0; i < length; i++) {
            StdOut.print(doubleArray[i] + " ");
        }
    }

    public static <Item extends Comparable<Item>> void sort(Item[] array) {
        int arraySize = array.length;
        Item[] auxArray = (Item[]) new Comparable[arraySize];

        sort(array, auxArray, 0, arraySize - 1);

    }


    public static <Item extends Comparable<Item>> void sort(Item[] array, Item[] aux, int lo,
                                                            int hi) {
        if (hi <= lo) {
            return;
        }

        int mid = lo + (hi - lo) / 2;
        sort(array, aux, lo, mid);
        sort(array, aux, mid + 1, hi);
        merge(array, aux, lo, mid, hi);
    }


    private static <Item extends Comparable<Item>> boolean isSorted(Item[] array) {
        int arraySize = array.length;
        for (int i = 1; i < arraySize; i++) {
            if (less(array[i], array[i - 1])) {
                return false;
            }
        }
        return true;
    }

    private static <Item extends Comparable<Item>> boolean less(Item first, Item second) {
        return first.compareTo(second) < 0;
    }

    private static <Item extends Comparable<Item>> void merge(Item[] array, Item[] aux, int lo,
                                                              int mid, int hi) {
        assert isSorted(array, lo, mid);
        assert isSorted(array, mid + 1, hi);

        for (int k = lo; k <= hi; k++) {
            aux[k] = array[k];
        }

        int i = lo;
        int j = mid + 1;

        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                array[k] = aux[j++];
            }
            else if (j > hi) {
                array[k] = aux[i++];
            }
            else if (less(aux[j], aux[i])) {
                array[k] = aux[j++];
            }
            else {
                array[k] = aux[i++];
            }
        }
    }

    private static <Item extends Comparable<Item>> boolean isSorted(Item[] array, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            if (less(array[i], array[i - 1])) {
                return false;
            }
        }
        return true;
    }
}
