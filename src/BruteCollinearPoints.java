/* *****************************************************************************
 *  Name:              Andrei Chenchik
 *  Coursera User ID:  c27c0b79c8a674f5a98145672b4581b2
 *  Last modified:     March 19, 2021
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class BruteCollinearPoints {
    private final LineSegment[] segments;
    private final int numberOfSegments;

    public BruteCollinearPoints(
            Point[] userPoints)    // finds all line segments containing 4 points
    {
        checkIfNull(userPoints);
        int numberOfPoints = userPoints.length;
        Point[] points = new Point[numberOfPoints];
        int segmentsFound = 0;
        segments = new LineSegment[numberOfPoints * numberOfPoints];

        for (int i = 0; i < numberOfPoints; i++) {
            checkIfNull(userPoints[i]);
            points[i] = userPoints[i];
        }

        Arrays.sort(points);

        for (int i = 1; i < numberOfPoints; i++) {
            checkIfDuplicate(points[i], points[i - 1]);
        }

        for (int i = 0; i < numberOfPoints - 3; i++) {
            for (int j = i + 1; j < numberOfPoints - 2; j++) {
                for (int k = j + 1; k < numberOfPoints - 1; k++) {
                    if (points[i].slopeTo(points[j]) != points[j].slopeTo(points[k])) {
                        continue;
                    }
                    for (int m = k + 1; m < numberOfPoints; m++) {
                        if (points[j].slopeTo(points[k]) == points[k].slopeTo(points[m])) {
                            segments[segmentsFound++] = new LineSegment(points[i], points[m]);
                        }
                    }
                }
            }
        }
        numberOfSegments = segmentsFound;
    }

    private void checkIfNull(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("Points can't be null");
        }
    }

    private void checkIfNull(Point point) {
        if (point == null) {
            throw new IllegalArgumentException("Point can't be null");
        }
    }

    private void checkIfDuplicate(Point point1, Point point2) {
        if (point1.compareTo(point2) == 0) {
            throw new IllegalArgumentException("Points can't be duplicate");
        }
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }


        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }

    public LineSegment[] segments()                // the line segments
    {
        LineSegment[] allSegments = new LineSegment[numberOfSegments];
        for (int i = 0; i < numberOfSegments; i++) {
            allSegments[i] = segments[i];
        }

        return allSegments;
    }

    public int numberOfSegments()        // the number of line segments
    {
        return numberOfSegments;
    }
}
