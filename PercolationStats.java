/* *****************************************************************************
 *  Name:              Andrei Chenchik
 *  Coursera User ID:  c27c0b79c8a674f5a98145672b4581b2
 *  Last modified:     March 19, 2021
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double CONFIDENCE = 1.96;
    private final double[] exp;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException("size or number fo trials is 0 or less");

        exp = new double[trials];

        for (int i = 0; i < trials; i++) {
            Percolation grid = new Percolation(n);
            while (!grid.percolates()) {
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                grid.open(row, col);
            }

            exp[i] = (double) grid.numberOfOpenSites() / (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(exp);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(exp);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - CONFIDENCE * stddev() / Math.sqrt(exp.length);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + CONFIDENCE * stddev() / Math.sqrt(exp.length);
    }

    // test client (see below)
    public static void main(String[] args) {
        int gridSize = Integer.parseInt(args[0]);
        int numberOfExperiments = Integer.parseInt(args[1]);

        PercolationStats experiment = new PercolationStats(gridSize, numberOfExperiments);

        StdOut.printf("%-23s = %.16f\n", "mean", experiment.mean());
        StdOut.printf("%-23s = %.16f\n", "stddev", experiment.stddev());
        StdOut.printf("%-23s = [%.16f, %.16f]", "95% confidence interval",
                      experiment.confidenceLo(), experiment.confidenceHi());
    }
}
