/* *****************************************************************************
 *  Name:              Andrei Chenchik
 *  Coursera User ID:  c27c0b79c8a674f5a98145672b4581b2
 *  Last modified:     March 19, 2021
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF conn;
    private boolean[][] grid;
    private int len;
    private int count;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        len = n;
        conn = new WeightedQuickUnionUF(n * n + 2);
        grid = new boolean[n][n];
        count = 0;
    }

    private int getConnId(int row, int col) {
        return (row - 1) * len + col;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (!grid[row - 1][col - 1]) {
            grid[row - 1][col - 1] = true;
            count++;

            if (row == 1) {
                conn.union(0, col);
            }
            else if (row == len) {
                conn.union(len * len + 1, len * (len - 1) + col);
            }

            if (isOpen(row - 1, col)) {
                conn.union(getConnId(row, col), getConnId(row - 1, col));
            }
            if (isOpen(row + 1, col)) {
                conn.union(getConnId(row, col), getConnId(row + 1, col));
            }
            if (isOpen(row, col - 1)) {
                conn.union(getConnId(row, col), getConnId(row, col - 1));
            }
            if (isOpen(row, col + 1)) {
                conn.union(getConnId(row, col), getConnId(row, col + 1));
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 1 || col < 1) {
            return false;
        }
        else {
            return grid[row - 1][col - 1];
        }
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        return conn.find(0) == conn.find(getConnId(row, col));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return count;
    }

    // does the system percolate?
    public boolean percolates() {
        return conn.find(0) == conn.find(len * len + 1);
    }

    public static void main(String[] args) {

    }
}
