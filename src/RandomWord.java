import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/* *****************************************************************************
 *  Name:              Andrei Chenchik
 *  Coursera User ID:  c27c0b79c8a674f5a98145672b4581b2
 *  Last modified:     March 19, 2021
 **************************************************************************** */

public class RandomWord {
    public static void main(String[] args) {
        String randomWord = "";
        String newWord;
        int counter = 0;

        while (!StdIn.isEmpty()) {
            counter++;
            newWord = StdIn.readString();

            if (StdRandom.bernoulli(1.0 / counter)) {
                randomWord = newWord;
            }
        }

        StdOut.println(randomWord);
    }
}
