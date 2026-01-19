package pembayaran;

public abstract class Pembayaran implements MetodePembayaran {

    protected int total;
    protected MetodeBayar metode;

    public Pembayaran(int total, MetodeBayar metode) {
        this.total = total;
        this.metode = metode;
    }

    public String infoDasar() {
        return "Metode: " + metode + "\nTotal: Rp " + total;
    }

    @Override
    public abstract String proses();
}
