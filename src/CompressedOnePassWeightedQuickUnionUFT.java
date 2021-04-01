/* *****************************************************************************
 *  Name:              Andrei Chenchik
 *  Coursera User ID:  c27c0b79c8a674f5a98145672b4581b2
 *  Last modified:     March 19, 2021
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class CompressedOnePassWeightedQuickUnionUFT {
    private int[] comp;
    private int[] size;
    private int count;

    public CompressedOnePassWeightedQuickUnionUFT(int n) {
        comp = new int[n];
        size = new int[n];
        count = n;

        for (int i = 0; i < n; i++) {
            comp[i] = i;
            size[i] = 1;
        }
    }

    public static void main(String[] args) {
        Stopwatch sw = new Stopwatch();
        int size = StdIn.readInt();
        CompressedOnePassWeightedQuickUnionUFT
                union = new CompressedOnePassWeightedQuickUnionUFT(size);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (union.connected(p, q)) {
                continue;
            }
            union.union(p, q);
        }
        StdOut.println(size + " elements, " + union.count() + " components");
        StdOut.println("Time elapsed: " + sw.elapsedTime());
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public void union(int p, int q) {
        int pComp = find(p);
        int qComp = find(q);

        if (pComp == qComp) {
            return;
        }

        if (size[pComp] < size[qComp]) {
            comp[pComp] = qComp;
            size[qComp] += size[pComp];
        }
        else {
            comp[qComp] = pComp;
            size[pComp] += size[qComp];
        }

        count--;
    }

    public int count() {
        return count;
    }

    private int find(int p) {
        while (p != comp[p]) {
            comp[p] = comp[comp[p]];
            p = comp[p];
        }

        return p;
    }
}
