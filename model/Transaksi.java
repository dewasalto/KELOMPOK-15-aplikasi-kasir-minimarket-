package model;

import java.util.List;

public class Transaksi {

    private List<Barang> daftarBarang;

    public Transaksi(List<Barang> daftarBarang) {
        this.daftarBarang = daftarBarang;
    }

    public List<Barang> getDaftarBarang() {
        return daftarBarang;
    }
}
