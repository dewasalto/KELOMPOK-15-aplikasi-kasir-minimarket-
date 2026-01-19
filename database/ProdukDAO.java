package dao;

import database.Database;
import model.Barang;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdukDAO {

    private Connection conn = Database.getConnection();

    public void insert(Barang b) {
        String sql = "INSERT INTO produk(kode,nama,harga,stok) VALUES(?,?,?,?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, b.getKode());
            ps.setString(2, b.getNama());
            ps.setInt(3, b.getHarga());
            ps.setInt(4, b.getStok());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Barang> getAll() {
        List<Barang> list = new ArrayList<>();
        String sql = "SELECT * FROM produk";

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Barang(
                        rs.getString("kode"),
                        rs.getString("nama"),
                        rs.getInt("harga"),
                        rs.getInt("stok")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void delete(String kode) {
        String sql = "DELETE FROM produk WHERE kode=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, kode);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Barang getByKode(String kode) {
        String sql = "SELECT * FROM produk WHERE kode=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, kode);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Barang(
                        rs.getString("kode"),
                        rs.getString("nama"),
                        rs.getInt("harga"),
                        rs.getInt("stok")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateStok(String kode, int stok) {
        String sql = "UPDATE produk SET stok=? WHERE kode=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, stok);
            ps.setString(2, kode);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
