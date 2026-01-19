package pembayaran;

public class PembayaranTunai extends Pembayaran {

    private int uangBayar;

    public PembayaranTunai(int total, int uangBayar) {
        super(total, MetodeBayar.TUNAI);
        this.uangBayar = uangBayar;
    }

    @Override
    public String proses() {
        if (uangBayar < total) {
            return "Uang tidak cukup!";
        }
        return super.infoDasar() +
                "\nKembalian: Rp " + (uangBayar - total);
    }
}
