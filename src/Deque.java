/* *****************************************************************************
 *  Name:              Andrei Chenchik
 *  Coursera User ID:  c27c0b79c8a674f5a98145672b4581b2
 *  Last modified:     March 19, 2021
 *  Created based on my understanding and knowledge which I got from slides,
 *  videos, code samples, assignments specs and faqs of coursera course
 *  "Algorithms, Part I" https://www.coursera.org/learn/algorithms-part1/
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private int size = 0;
    private Node first, last;

    // unit testing (required)
    public static void main(String[] args) {
        int n = 6;
        Deque<Integer> deque = new Deque<Integer>();

        StdOut.println("Testing addFirst (by adding " + n + " items)");
        for (int i = 0; i < n; i++) {
            deque.addFirst(i);
        }

        StdOut.println("Testing two iterators");
        for (int i : deque) {
            for (int j : deque) {
                StdOut.print(i + "-" + j + " ");
            }
            StdOut.println();
        }

        StdOut.println("Size of deque: " + deque.size());

        StdOut.println("Testing removeLast (" + n + " times)");
        for (int i = 0; i < n; i++) {
            StdOut.print(deque.removeLast() + " ");
        }
        StdOut.println();

        StdOut.println("Size of deque: " + deque.size());

        StdOut.println("Testing addLast (by adding " + n + " items)");
        for (int i = 0; i < n; i++) {
            deque.addLast(i);
        }
        StdOut.println();

        StdOut.println("Size of deque: " + deque.size());

        StdOut.println("Testing removeFirst (" + n + " times)");
        for (int i = 0; i < n; i++) {
            StdOut.print(deque.removeFirst() + " ");
        }
        StdOut.println();

        StdOut.println("Size of deque: " + deque.size());
    }

    // add the item to the front
    public void addFirst(Item item) {
        validateItem(item);
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;


        if (isEmpty()) {
            last = first;
        }
        else {
            oldFirst.prev = first;
        }

        size++;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // remove and return the item from the back
    public Item removeLast() {
        validateRemoval();
        Item item = last.item;
        last = last.prev;
        --size;
        if (size != 0) {
            last.next = null;
        }
        else {
            first = null;
        }
        return item;
    }

    private void validateRemoval() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty.");
        }
    }

    // add the item to the back
    public void addLast(Item item) {
        validateItem(item);
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.prev = oldLast;

        if (isEmpty()) {
            first = last;
        }
        else {
            oldLast.next = last;
        }

        size++;
    }

    private void validateItem(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("New item can't be null.");
        }
    }

    // remove and return the item from the front
    public Item removeFirst() {
        validateRemoval();
        Item item = first.item;
        first = first.next;
        --size;
        if (size != 0) {
            first.prev = null;
        }
        else {
            last = null;
        }
        return item;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (current == null) {
                throw new NoSuchElementException("No more elements left.");
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException("Not implemented.");
        }

    }
}