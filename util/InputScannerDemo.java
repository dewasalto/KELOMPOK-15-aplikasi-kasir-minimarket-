package util;

import java.util.Scanner;

public class InputScannerDemo {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Kode : ");
        String kode = sc.nextLine();

        System.out.print("Nama : ");
        String nama = sc.nextLine();

        System.out.print("Harga: ");
        int harga = sc.nextInt();

        System.out.print("Stok : ");
        int stok = sc.nextInt();

        System.out.println("\nDATA BARANG");
        System.out.println(kode + " - " + nama + " - " + harga + " - " + stok);
    }
}
