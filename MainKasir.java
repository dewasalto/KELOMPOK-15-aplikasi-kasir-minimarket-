import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MainKasir {
    public static void main(String[] args) {

        Map<String, Barang> daftarBarang = new HashMap<>();

        // 10 produk
        daftarBarang.put("A01", new Barang("A01", "Beras 5kg", 65000));
        daftarBarang.put("A02", new Barang("A02", "Gula 1kg", 14000));
        daftarBarang.put("A03", new Barang("A03", "Minyak 1L", 16000));
        daftarBarang.put("A04", new Barang("A04", "Mie Instan", 3500));
        daftarBarang.put("A05", new Barang("A05", "Telur 1kg", 28000));
        daftarBarang.put("A06", new Barang("A06", "Susu Bubuk", 45000));
        daftarBarang.put("A07", new Barang("A07", "Teh Celup", 12000));
        daftarBarang.put("A08", new Barang("A08", "Kopi Sachet", 2000));
        daftarBarang.put("A09", new Barang("A09", "Air Mineral 1.5L", 6000));
        daftarBarang.put("A10", new Barang("A10", "Roti Tawar", 18000));

        Scanner sc = new Scanner(System.in);
        int total = 0;

        while (true) {
            System.out.print("Masukkan kode barang (atau 0 untuk selesai): ");
            String kode = sc.nextLine();

            if (kode.equals("0")) break;

            if (daftarBarang.containsKey(kode)) {
                Barang b = daftarBarang.get(kode);
                System.out.println("Nama Barang : " + b.getNama());
                System.out.println("Harga       : Rp " + b.getHarga());
                total += b.getHarga();
            } else {
                System.out.println("Kode barang tidak ditemukan!");
            }
        }

        System.out.println("\nTotal pembayaran: Rp " + total);

        System.out.println("\nPilih metode pembayaran:");
        System.out.println("1. Tunai");
        System.out.println("2. Non Tunai");
        System.out.print("Pilih (1/2): ");
        int metode = sc.nextInt();

        Pembayaran pembayaran;

        if (metode == 1) {
            pembayaran = new PembayaranTunai(total);
        } else {
            pembayaran = new PembayaranNonTunai(total);
        }

        pembayaran.prosesPembayaran();
    }
}
