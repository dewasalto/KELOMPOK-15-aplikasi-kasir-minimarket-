package model;

public class Barang {

    private String kode;
    private String nama;
    private int harga;
    private int stok;

    public Barang(String kode, String nama, int harga, int stok) {
        this.kode = kode;
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
    }

    public String getKode() { return kode; }
    public String getNama() { return nama; }
    public int getHarga() { return harga; }
    public int getStok() { return stok; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Barang)) return false;
        Barang b = (Barang) obj;
        return kode.equals(b.kode);
    }

    @Override
    public int hashCode() {
        return kode.hashCode();
    }

    @Override
    public String toString() {
        return kode + " - " + nama + " (Rp " + harga + ", Stok: " + stok + ")";
    }
}
