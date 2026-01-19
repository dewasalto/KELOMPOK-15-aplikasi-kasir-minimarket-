package util;

import java.io.*;

public class FileProdukDemo {

    public static void main(String[] args) throws IOException {

        File file = new File("produk.txt");

        FileWriter fw = new FileWriter(file);
        fw.write("P001;Pensil;2000;50\n");
        fw.write("P002;Buku;5000;30\n");
        fw.close();

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        br.close();
    }
}
