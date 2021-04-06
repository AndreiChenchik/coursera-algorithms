/* *****************************************************************************
 *  Name:              Andrei Chenchik
 *  Coursera User ID:  c27c0b79c8a674f5a98145672b4581b2
 *  Last modified:     March 19, 2021
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class DijkstraEvaluate {
    public static void main(String[] args) {
        LinkedStack<String> ops = new LinkedStack<String>();
        LinkedStack<Double> vals = new LinkedStack<Double>();

        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            switch (s) {
                case "(":
                    break;
                case "+":
                case "*":
                    ops.push(s);
                    break;
                case ")":
                    String op = ops.pop();
                    switch (op) {
                        case "+":
                            vals.push(vals.pop() + vals.pop());
                            break;
                        case "*":
                            vals.push(vals.pop() * vals.pop());
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    vals.push(Double.parseDouble(s));
                    break;
            }
        }
        StdOut.println(vals.pop());
    }
}
