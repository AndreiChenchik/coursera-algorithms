/* *****************************************************************************
 *  Name:              Andrei Chenchik
 *  Coursera User ID:  c27c0b79c8a674f5a98145672b4581b2
 *  Last modified:     March 19, 2021
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class UnionStats {
    private static final int ATTEMPTS = 10;
    private static final double CONFIDENCE = 1.96;

    public static void main(String[] args) {
        StdOut.print(
                "Provide starting number of elements/pairs and x2 steps to get stats (mean from "
                        + ATTEMPTS
                        + " attempts): \n> ");
        while (!StdIn.isEmpty()) {
            int elements = StdIn.readInt();
            int steps = StdIn.readInt();
            double[][][] stats = new double[5][4][steps];

            for (int j = 0; j < steps; j++) {
                double[] experiments = new double[ATTEMPTS];
                double[] results;

                for (int i = 0; i < ATTEMPTS; i++) {
                    Stopwatch sw = new Stopwatch();
                    QuickFindUFT union = new QuickFindUFT(elements);
                    generatePairs(union);
                    experiments[i] = sw.elapsedTime();
                }
                results = calcStats(experiments);
                stats[0][0][j] = results[0];
                stats[0][1][j] = results[1];
                stats[0][2][j] = results[2];
                stats[0][3][j] = results[3];

                for (int i = 0; i < ATTEMPTS; i++) {
                    Stopwatch sw = new Stopwatch();
                    QuickUnionUFT union = new QuickUnionUFT(elements);
                    generatePairs(union);
                    experiments[i] = sw.elapsedTime();
                }
                results = calcStats(experiments);
                stats[1][0][j] = results[0];
                stats[1][1][j] = results[1];
                stats[1][2][j] = results[2];
                stats[1][3][j] = results[3];

                for (int i = 0; i < ATTEMPTS; i++) {
                    Stopwatch sw = new Stopwatch();
                    WeightedQuickUnionUFT union = new WeightedQuickUnionUFT(elements);
                    generatePairs(union);
                    experiments[i] = sw.elapsedTime();
                }
                results = calcStats(experiments);
                stats[2][0][j] = results[0];
                stats[2][1][j] = results[1];
                stats[2][2][j] = results[2];
                stats[2][3][j] = results[3];


                for (int i = 0; i < ATTEMPTS; i++) {
                    Stopwatch sw = new Stopwatch();
                    CompressedTwoPassWeightedQuickUnionUFT union
                            = new CompressedTwoPassWeightedQuickUnionUFT(elements);
                    generatePairs(union);
                    experiments[i] = sw.elapsedTime();
                }
                results = calcStats(experiments);
                stats[3][0][j] = results[0];
                stats[3][1][j] = results[1];
                stats[3][2][j] = results[2];
                stats[3][3][j] = results[3];


                for (int i = 0; i < ATTEMPTS; i++) {
                    Stopwatch sw = new Stopwatch();
                    CompressedOnePassWeightedQuickUnionUFT union
                            = new CompressedOnePassWeightedQuickUnionUFT(elements);
                    generatePairs(union);
                    experiments[i] = sw.elapsedTime();
                }
                results = calcStats(experiments);
                stats[4][0][j] = results[0];
                stats[4][1][j] = results[1];
                stats[4][2][j] = results[2];
                stats[4][3][j] = results[3];


                elements *= 2;
            }

            getStats(stats);
        }
    }

    private static void generatePairs(QuickFindUFT union) {
        int size = union.count();

        for (int i = 0; i < size; i++) {
            int p = StdRandom.uniform(size);
            int q = StdRandom.uniform(size);
            union.union(p, q);
        }
    }

    private static double[] calcStats(double[] experiments) {
        double mean = StdStats.mean(experiments);
        double stddev = StdStats.stddev(experiments);
        double confidenceLo = mean - CONFIDENCE * stddev / Math.sqrt(experiments.length);
        double confidenceHi = mean - CONFIDENCE * stddev / Math.sqrt(experiments.length);

        return new double[] { mean, stddev, confidenceLo, confidenceHi };
    }

    private static void generatePairs(QuickUnionUFT union) {
        int size = union.count();

        for (int i = 0; i < size; i++) {
            int p = StdRandom.uniform(size);
            int q = StdRandom.uniform(size);
            union.union(p, q);
        }
    }

    private static void generatePairs(WeightedQuickUnionUFT union) {
        int size = union.count();

        for (int i = 0; i < size; i++) {
            int p = StdRandom.uniform(size);
            int q = StdRandom.uniform(size);
            union.union(p, q);
        }
    }

    private static void generatePairs(CompressedTwoPassWeightedQuickUnionUFT union) {
        int size = union.count();

        for (int i = 0; i < size; i++) {
            int p = StdRandom.uniform(size);
            int q = StdRandom.uniform(size);
            union.union(p, q);
        }
    }

    private static void generatePairs(CompressedOnePassWeightedQuickUnionUFT union) {
        int size = union.count();

        for (int i = 0; i < size; i++) {
            int p = StdRandom.uniform(size);
            int q = StdRandom.uniform(size);
            union.union(p, q);
        }
    }

    private static void getStats(double[][][] stats) {
        for (int i = 0; i < stats.length; i++) {
            String className;
            switch (i) {
                case 0:
                    className = "QuickFindUFT";
                    break;
                case 1:
                    className = "QuickUnionUFT";
                    break;
                case 2:
                    className = "WeightedQuickUnionUFT";
                    break;
                case 3:
                    className = "CompressedTwoPassWeightedQuickUnionUFT";
                    break;
                case 4:
                    className = "CompressedOnePassWeightedQuickUnionUFT";
                    break;
                default:
                    className = "Unknown";
                    break;

            }

            StdOut.println(className);
            double previousStat = 1;

            for (int j = 0; j < stats[i][0].length; j++) {
                StdOut.printf("%8.4f", stats[i][0][j]);
                if (j > 0) StdOut.printf("(x%3.1f)", stats[i][0][j] / previousStat);
                previousStat = stats[i][0][j];
            }
            StdOut.println("");
        }

    }
}
