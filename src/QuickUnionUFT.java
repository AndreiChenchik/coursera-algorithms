/* *****************************************************************************
 *  Name:              Andrei Chenchik
 *  Coursera User ID:  c27c0b79c8a674f5a98145672b4581b2
 *  Last modified:     March 19, 2021
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class QuickUnionUFT {
    private int[] comp;
    private int count;

    public QuickUnionUFT(int n) {
        comp = new int[n];
        count = n;

        for (int i = 0; i < n; i++) {
            comp[i] = i;
        }
    }

    private int find(int p) {
        while (p != comp[p]) {
            p = comp[p];
        }
        return p;
    }

    public void union(int p, int q) {
        int pComp = find(p);
        int qComp = find(q);

        if (pComp == qComp) { return; }

        comp[pComp] = qComp;
        count--;
    }

    public boolean connected(int p, int q) {
        return comp[p] == comp[q];
    }

    public int count() {
        return count;
    }

    public static void main(String[] args) {
        Stopwatch sw = new Stopwatch();
        int size = StdIn.readInt();
        QuickUnionUFT union = new QuickUnionUFT(size);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (union.connected(p, q)) { continue; }
            union.union(p, q);
            // StdOut.println(p + "-" + q);
        }
        StdOut.println(size + " elements, " + union.count() + " components");
        StdOut.println("Time elapsed: " + sw.elapsedTime());
    }
}
