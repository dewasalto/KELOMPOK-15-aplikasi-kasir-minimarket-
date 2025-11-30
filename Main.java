import java.util.Scanner;

public class MainKasir {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Barang[] daftarBarang = new Barang[]{new Barang("B001", "Sabun Mandi", 5000), new Barang("B002", "Shampoo", 12000), new Barang("B003", "Pasta Gigi", 8000), new Barang("B004", "Sikat Gigi", 6000), new Barang("B005", "Minyak Goreng 1L", 15000), new Barang("B006", "Gula 1Kg", 14000), new Barang("B007", "Kopi Instan", 3000), new Barang("B008", "Teh Celup", 4000), new Barang("B009", "Air Mineral", 3000), new Barang("B010", "Mie Instan", 2500)};
        System.out.print("Masukkan kode barang : ");
        String kodeInput = input.nextLine();
        Barang barangDipilih = null;

        for(Barang b : daftarBarang) {
            if (b.getKode().equalsIgnoreCase(kodeInput)) {
                barangDipilih = b;
                break;
            }
        }

        if (barangDipilih == null) {
            System.out.println("Kode barang tidak ditemukan!");
        } else {
            barangDipilih.tampilkanInfo();
            System.out.println("\nPilih Metode Pembayaran:");
            System.out.println("1. Tunai");
            System.out.println("2. Non-Tunai");
            System.out.print("Pilih (1/2): ");
            int pilih = input.nextInt();
            input.nextLine();
            Pembayaran p;
            if (pilih == 1) {
                p = new PembayaranTunai();
            } else {
                PembayaranNonTunai non = new PembayaranNonTunai();
                System.out.print("Masukkan metode non-tunai (E-Wallet / QRIS / Transfer Bank): ");
                non.metode = input.nextLine();
                p = non;
            }

            p.prosesPembayaran();
            input.close();
        }
    }
}
