/* *****************************************************************************
 *  Name:              Andrei Chenchik
 *  Coursera User ID:  c27c0b79c8a674f5a98145672b4581b2
 *  Last modified:     March 19, 2021
 *  Created based on my understanding and knowledge which I got from slides,
 *  videos, code samples, assignments specs and faqs of coursera course
 *  "Algorithms, Part I" https://www.coursera.org/learn/algorithms-part1/
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] queue;
    private int size = 0;
    private int nextSlot = 0;

    // construct an empty randomized queue
    public RandomizedQueue() {
        queue = (Item[]) new Object[1];
    }

    // unit testing (required)
    public static void main(String[] args) {
        int n = 5;
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        StdOut.println("Size of deque: " + queue.size() + ". Is it empty? " + queue.isEmpty());

        StdOut.println("Testing enqueue (by adding " + n + " items)");
        for (int i = 0; i < n; i++) {
            queue.enqueue(i);
        }
        StdOut.println("Size of deque: " + queue.size() + ". Is it empty? " + queue.isEmpty());

        StdOut.println("Testing two iterators");
        for (int a : queue) {
            for (int b : queue)
                StdOut.print(a + "-" + b + " ");
            StdOut.println();
        }
        StdOut.println("Size of deque: " + queue.size() + ". Is it empty? " + queue.isEmpty());

        StdOut.println("Testing dequeue (by removing " + n + " items)");
        for (int i = 0; i < n; i++) {
            StdOut.print(queue.dequeue() + " ");
        }
        StdOut.println();
        StdOut.println("Size of deque: " + queue.size() + ". Is it empty? " + queue.isEmpty());

        StdOut.println("Testing enqueue (by adding " + n + " items)");
        for (int i = 0; i < n; i++) {
            queue.enqueue(i);
        }
        StdOut.println("Size of deque: " + queue.size() + ". Is it empty? " + queue.isEmpty());

        StdOut.println("Testing sampling (by getting " + n * 2 + " items)");
        for (int i = 0; i < n * 2; i++) {
            StdOut.print(queue.sample() + " ");
        }
        StdOut.println();
        StdOut.println("Size of deque: " + queue.size() + ". Is it empty? " + queue.isEmpty());


        StdOut.println("Testing dequeue (by removing " + n + " items)");
        for (int i = 0; i < n; i++) {
            StdOut.print(queue.dequeue() + " ");
        }
        StdOut.println();
        StdOut.println("Size of deque: " + queue.size() + ". Is it empty? " + queue.isEmpty());
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("New item can't be null.");
        }
        if (nextSlot == queue.length) {
            resize(2 * queue.length);
        }
        queue[nextSlot++] = item;
        size++;
    }

    // remove and return a random item
    public Item dequeue() {
        int elem = getRandomElement();
        Item item = queue[elem];
        queue[elem] = null;
        size--;
        if (size > 0 && size == queue.length / 4) {
            resize(queue.length / 2);
        }
        return item;
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        int nextCopySlot = 0;
        for (int i = 0; i < queue.length; i++) {
            if (queue[i] != null) {
                copy[nextCopySlot++] = queue[i];
            }
        }
        queue = copy;
        nextSlot = nextCopySlot;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        int elem = getRandomElement();
        return queue[elem];
    }

    // return an independent iterator over items in random order

    private int getRandomElement() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty.");
        }
        int item;
        do {
            item = StdRandom.uniform(queue.length);
        } while (queue[item] == null);

        return item;
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private final Item[] iterationQueue;
        private int cursor = 0;

        RandomizedQueueIterator() {
            iterationQueue = (Item[]) new Object[size];
            int randomItem;

            for (int i = 0; i < queue.length; i++) {
                if (queue[i] != null) {
                    do {
                        randomItem = StdRandom.uniform(size);
                    } while (iterationQueue[randomItem] != null);
                    iterationQueue[randomItem] = queue[i];
                }
            }
        }

        public boolean hasNext() {
            return cursor < size;
        }

        public Item next() {
            if (cursor == iterationQueue.length) {
                throw new NoSuchElementException("No more elements left.");
            }
            return iterationQueue[cursor++];
        }

        public void remove() {
            throw new UnsupportedOperationException("Not implemented.");
        }
    }
}
