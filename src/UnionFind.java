/* *****************************************************************************
 *  Name:              Andrei Chenchik
 *  Coursera User ID:  c27c0b79c8a674f5a98145672b4581b2
 *  Last modified:     March 19, 2021
 **************************************************************************** */

public class UnionFind {
    private int[] comp;
    private int count;

    public UnionFind(int n) {
        comp = new int[n];
        count = n;

        for (int i = 0; i < n; i++) {
            comp[i] = i;
        }
    }

    public void union(int p, int q) {
        // basic quick-find implementation

        int prev = comp[p];
        int next = comp[q];

        if (prev != next) return;

        count--;

        for (int i = 0; i < comp.length; i++) {
            if (comp[i] == prev) {
                comp[i] = next;
            }
        }
    }

    private int find(int p) {
        // basic quick-find implementation

        return comp[p];
    }

    public boolean connected(int p, int q) {
        return comp[p] == comp[q];
    }

    public int count() {
        return count;
    }

    public static void main(String[] args) {
        // Nothing to see here
    }
}