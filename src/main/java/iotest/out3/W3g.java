/**
 * BufferedOutputStream.write(sb.toString().getBytes()) + flush_sb(1048576)
 */
package iotest.out3;

public class W3g {

    static final java.util.Random _random = new java.util.Random(211166910);
    static int numLines = 10000;
    static int numColumns = 999;

    private static void PrintLines() throws java.io.IOException {
        for (int i = 0; i < numLines; i++) {
            int j = 0;//quantas colunas ja foram geradas para a linha atual
            while (j + 10 < numColumns) {//gera 10 colunas (uma string e um int de 5 digitos cada)
                String s = randomString(5);
                int n = (_random.nextInt(10000) + 10000);
                sb.append(s).append(n);
                j += 10;
            }
            sb.append(randomString(numColumns - j)).append('\n');//gera o restante de colunas que faltou e coloca um \n
            FlushSb();
        }
        flush_close();
    }

    public static void main(String args[]) throws java.io.IOException {
        for (int i = 0; i < args.length; i++) {
            if ("-l".equals(args[i])) numLines = Integer.parseInt(args[i + 1]);
            else if ("-c".equals(args[i])) numColumns = Integer.parseInt(args[i + 1]);
        }
        long startTime = System.nanoTime();
        PrintLines();
        long stopTime = System.nanoTime();
        System.err.println((stopTime - startTime));
    }

    private static String randomString(int length) {
        StringBuilder strbld = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            strbld.append((char) (_random.nextInt(95) + 33));
        }
        return strbld.toString();
    }

    static void flush_close() throws java.io.IOException {
        out.write(sb.toString().getBytes());
        out.flush();
        out.close();
    }

    private static void FlushSb() throws java.io.IOException {
        if (sb.length() >= 4 * 1048576 / 5) {
            out.write(sb.toString().getBytes());
            sb = new StringBuilder(1048576);
        }
    }

    static StringBuilder sb = new StringBuilder(1048576);
    static java.io.BufferedOutputStream out = new java.io.BufferedOutputStream(System.out);
}
