package pembayaran;

public class PembayaranNonTunai extends Pembayaran {

    public PembayaranNonTunai(int total, MetodeBayar metode) {
        super(total, metode);
    }

    @Override
    public String proses() {
        return super.infoDasar() +
                "\nPembayaran non-tunai berhasil";
    }
}
