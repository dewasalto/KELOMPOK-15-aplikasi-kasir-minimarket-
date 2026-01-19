import database.Database;
import view.KasirGUI;

public class Main {
    public static void main(String[] args) {

        // 🔥 WAJIB PANGGIL DATABASE DULU
        Database.getConnection();

        // BARU TAMPILKAN GUI
        new KasirGUI();
    }
}
