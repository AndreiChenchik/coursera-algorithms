/* *****************************************************************************
 *  Name:              Andrei Chenchik
 *  Coursera User ID:  c27c0b79c8a674f5a98145672b4581b2
 *  Last modified:     March 19, 2021
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class LinkedListQueueOfStrings {
    private Node first;
    private Node last;

    public static void main(String[] args) {
        LinkedListQueueOfStrings stack = new LinkedListQueueOfStrings();

        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("-")) {
                StdOut.print(stack.dequeue());
            }
            else {
                stack.enqueue(s);
            }
        }
    }

    public String dequeue() {
        String item = first.item;
        first = first.next;
        if (isEmpty()) {
            last = null;
        }
        return item;
    }

    public void enqueue(String item) {
        Node oldLast = last;
        last = new Node();
        last.item = item;

        if (isEmpty()) {
            first = last;
        }
        else {
            oldLast.next = last;
        }
    }

    public boolean isEmpty() {
        return first == null;
    }

    private class Node {
        private String item;
        private Node next;
    }
}
