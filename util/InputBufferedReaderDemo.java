package util;

import java.io.*;

public class InputBufferedReaderDemo {

    public static void main(String[] args) throws IOException {

        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Nama Kasir : ");
        String nama = br.readLine();

        System.out.print("Shift      : ");
        String shift = br.readLine();

        System.out.println("\nKasir: " + nama);
        System.out.println("Shift: " + shift);
    }
}
