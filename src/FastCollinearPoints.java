/* *****************************************************************************
 *  Name:              Andrei Chenchik
 *  Coursera User ID:  c27c0b79c8a674f5a98145672b4581b2
 *  Last modified:     March 19, 2021
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class FastCollinearPoints {
    private final LineSegment[] segments;
    private int numberOfSegments = 0;
    private Point[] segmentStarts;
    private Double[] segmentSlopes;

    public FastCollinearPoints(Point[] userPoints)    // finds all line segments containing 4 points
    {
        checkIfNull(userPoints);

        int numberOfPoints = userPoints.length;
        Point[] points = new Point[numberOfPoints];

        int quadraticSize = numberOfPoints * numberOfPoints;
        segments = new LineSegment[quadraticSize];
        segmentStarts = new Point[quadraticSize];
        segmentSlopes = new Double[quadraticSize];

        for (int i = 0; i < numberOfPoints; i++) {
            checkIfNull(userPoints[i]);
            points[i] = userPoints[i];
        }

        Arrays.sort(points);
        for (int i = 1; i < numberOfPoints; i++) {
            checkIfDuplicate(points[i], points[i - 1]);
        }

        for (int i = 0; i < numberOfPoints; i++) {
            Arrays.sort(points, i + 1, numberOfPoints, points[i].slopeOrder());

            int j = i + 1;
            while (j < numberOfPoints) {
                double slope = points[i].slopeTo(points[j]);
                int count = 1;
                j++;

                while (j < numberOfPoints && points[i].slopeTo(points[j]) == slope) {
                    count++;
                    j++;
                }

                if (count >= 3) {
                    Arrays.sort(points, j - count, j);
                    Point startSegment, endSegment;
                    if (points[i].compareTo(points[j - count]) < 0) {
                        startSegment = points[i];
                        endSegment = points[j - 1];
                    }
                    else if ((points[j - 1].compareTo(points[i]) < 0)) {
                        startSegment = points[j - count];
                        endSegment = points[i];
                    }
                    else {
                        startSegment = points[j - count];
                        endSegment = points[j - 1];
                    }
                    appendSegment(startSegment, endSegment);
                }
            }
        }
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

    private void appendSegment(Point startSegment, Point endSegment) {
        double slope = startSegment.slopeTo(endSegment);

        for (int i = 0; i < numberOfSegments; i++) {
            if (slope == segmentSlopes[i] && (segmentStarts[i].compareTo(startSegment) == 0
                    || segmentStarts[i].slopeTo(startSegment) == slope)) {
                return;
            }
        }

        segments[numberOfSegments] = new LineSegment(startSegment, endSegment);
        segmentStarts[numberOfSegments] = startSegment;
        segmentSlopes[numberOfSegments] = slope;
        numberOfSegments++;
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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
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
