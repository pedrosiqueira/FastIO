/**
 * BufferedWriter
 */
package iotest.out2;

public class W1 {

    private static void PrintLines() throws java.io.IOException {
        try (java.io.BufferedWriter out = new java.io.BufferedWriter(new java.io.OutputStreamWriter(System.out))) {
            for (int i = 0; i < numLines; i++) {
                for (int j = 0; j < numChars - 2; j++) {
                    int ch = _random.nextInt(95) + 32;
                    out.write((char) ch);
                    total += ch;
                }
                out.newLine();
            }
            out.flush();
        }
    }

    public static void main(String args[]) throws java.io.IOException {
        long startTime = System.nanoTime();
        PrintLines();
        long stopTime = System.nanoTime();
        System.err.println((stopTime - startTime) + "\t" + total);
    }

    static final java.util.Random _random = new java.util.Random(211166910);
    static final int numLines = 1000000;
    static final int numChars = 100;
    static long total = 0;
}