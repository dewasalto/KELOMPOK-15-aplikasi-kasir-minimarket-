package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    private static final String URL = "jdbc:sqlite:kasir.db";
    private static Connection conn;

    public static Connection getConnection() {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(URL);
                System.out.println("Koneksi SQLite berhasil");
                createTableProduk();
            } catch (SQLException e) {
                System.out.println("Koneksi SQLite gagal");
                e.printStackTrace();
            }
        }
        return conn;
    }

    /* ================= CREATE TABLE ================= */
    private static void createTableProduk() {
        String sql = """
                CREATE TABLE IF NOT EXISTS produk (
                    kode TEXT PRIMARY KEY,
                    nama TEXT NOT NULL,
                    harga INTEGER NOT NULL,
                    stok INTEGER NOT NULL
                );
                """;

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabel produk siap");
        } catch (SQLException e) {
            System.out.println("Gagal membuat tabel produk");
            e.printStackTrace();
        }
    }
}
