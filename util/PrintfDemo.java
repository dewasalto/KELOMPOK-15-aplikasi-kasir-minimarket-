package util;

public class PrintfDemo {

    public static void main(String[] args) {
        String nama = "Pulpen";
        int harga = 3000;

        System.out.printf("Nama Produk : %s\n", nama);
        System.out.printf("Harga       : Rp %,d\n", harga);
        System.out.println("Baris baru\nTab\tContoh");
    }
}
