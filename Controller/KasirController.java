package controller;

import dao.ProdukDAO;
import model.Barang;

public class KasirController {

    private ProdukDAO dao = new ProdukDAO();

    public void tambahProduk(String kode, String nama, int harga, int stok) {
        dao.insert(new Barang(kode, nama, harga, stok));
    }

    public Object[][] getDataProduk() {
        var list = dao.getAll();
        Object[][] data = new Object[list.size()][4];

        for (int i = 0; i < list.size(); i++) {
            Barang b = list.get(i);
            data[i][0] = b.getKode();
            data[i][1] = b.getNama();
            data[i][2] = b.getHarga();
            data[i][3] = b.getStok();
        }
        return data;
    }

    public void hapusProduk(String kode) {
        dao.delete(kode);
    }
    public int beliBarang(String kode, int jumlah) {
        Barang b = dao.getByKode(kode);

        if (b == null) return -1;        // barang tidak ada
        if (b.getStok() < jumlah) return -2; // stok kurang

        int sisa = b.getStok() - jumlah;
        dao.updateStok(kode, sisa);

        return b.getHarga() * jumlah;
    }
        public int beliBarang(String kode) {
            return beliBarang(kode, 1); // default beli 1
    }

}
