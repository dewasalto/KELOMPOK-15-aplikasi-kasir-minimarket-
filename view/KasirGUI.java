package view;

import controller.KasirController;
import pembayaran.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class KasirGUI extends JFrame {

    private KasirController controller = new KasirController();

    private DefaultListModel<String> listModel = new DefaultListModel<>();
    private JLabel totalLabel = new JLabel("0");
    private int total = 0;

    private JTable tableProduk;
    private DefaultTableModel tableModel;

    private CardLayout cardLayout = new CardLayout();
    private JPanel contentPanel = new JPanel(cardLayout);

    /* DRAWER */
    private JPanel drawer;
    private boolean drawerOpen = false;
    private Timer drawerTimer;

    public KasirGUI() {
        setTitle("Kasir Otomatis");
        setSize(900, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        /* ================= TOP BAR ================= */
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(new Color(10, 45, 130));
        topBar.setPreferredSize(new Dimension(0, 45));

        JButton menuBtn = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.WHITE);
                g.fillRect(10, 12, 20, 3);
                g.fillRect(10, 20, 20, 3);
                g.fillRect(10, 28, 20, 3);
            }
        };
        menuBtn.setPreferredSize(new Dimension(45, 40));
        menuBtn.setFocusPainted(false);
        menuBtn.setContentAreaFilled(false);
        menuBtn.setBorderPainted(false);

        JLabel title = new JLabel("Kasir Otomatis", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));

        topBar.add(menuBtn, BorderLayout.WEST);
        topBar.add(title, BorderLayout.CENTER);

        /* ================= CONTENT ================= */
        contentPanel.add(panelKasir(), "KASIR");
        contentPanel.add(panelTambahProduk(), "TAMBAH");
        contentPanel.add(panelLihatProduk(), "LIHAT");
        contentPanel.add(panelHapusProduk(), "HAPUS");

        /* ================= DRAWER ================= */
        drawer = createDrawer();
        drawer.setPreferredSize(new Dimension(220, 0));
        drawer.setVisible(false);

        menuBtn.addActionListener(e -> toggleDrawer());

        add(topBar, BorderLayout.NORTH);
        add(drawer, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    /* ================= DRAWER ================= */
    private JPanel createDrawer() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(10, 45, 130));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(drawerLabel("MENU"));
        panel.add(drawerButton("Home", "KASIR"));
        panel.add(drawerButton("Tambah Produk", "TAMBAH"));
        panel.add(drawerButton("Lihat Produk", "LIHAT"));
        panel.add(drawerButton("Hapus Produk", "HAPUS"));

        return panel;
    }

    private JLabel drawerLabel(String text) {
        JLabel l = new JLabel(text);
        l.setForeground(Color.LIGHT_GRAY);
        l.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 10));
        return l;
    }

    private JButton drawerButton(String text, String panelName) {
        JButton btn = new JButton(text);
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(10, 45, 130));
        btn.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 10));
        btn.setFocusPainted(false);
        btn.setHorizontalAlignment(SwingConstants.LEFT);

        btn.addActionListener(e -> {
            if (panelName.equals("LIHAT")) refreshTableProduk();
            cardLayout.show(contentPanel, panelName);
            toggleDrawer();
        });

        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btn.setBackground(new Color(20, 70, 160));
            }
            public void mouseExited(java.awt.event.MouseEvent e) {
                btn.setBackground(new Color(10, 45, 130));
            }
        });

        return btn;
    }

    private void toggleDrawer() {
        drawerOpen = !drawerOpen;
        drawer.setVisible(drawerOpen);
        revalidate();
        repaint();
    }

    /* ================= PANEL KASIR ================= */
    private JPanel panelKasir() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 8, 8));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        JTextField kode = new JTextField();
        JTextField jumlah = new JTextField();
        JTextField bayar = new JTextField();

        JButton tambah = new JButton("Tambah");
        tambah.addActionListener(e -> {
            int sub = controller.beliBarang(kode.getText(), Integer.parseInt(jumlah.getText()));
            if (sub < 0) {
                JOptionPane.showMessageDialog(this, "Barang / stok tidak valid");
                return;
            }
            total += sub;
            listModel.addElement(kode.getText() + " x" + jumlah.getText() + " = Rp " + sub);
            totalLabel.setText(String.valueOf(total));
        });

        JRadioButton tunai = new JRadioButton("Tunai", true);
        JRadioButton nonTunai = new JRadioButton("Non Tunai");

        ButtonGroup bg = new ButtonGroup();
        bg.add(tunai);
        bg.add(nonTunai);

        JComboBox<String> metode = new JComboBox<>(new String[]{"QRIS", "TRANSFER", "EWALLET"});
        metode.setEnabled(false);

        tunai.addActionListener(e -> metode.setEnabled(false));
        nonTunai.addActionListener(e -> metode.setEnabled(true));

        JButton bayarBtn = new JButton("Bayar");
        bayarBtn.addActionListener(e -> {
            Pembayaran p;
            if (tunai.isSelected())
                p = new PembayaranTunai(total, Integer.parseInt(bayar.getText()));
            else
                p = new PembayaranNonTunai(total,
                        MetodeBayar.valueOf(metode.getSelectedItem().toString()));
            tampilkanStruk(p.proses());
        });

        panel.add(new JLabel("Kode Barang")); panel.add(kode);
        panel.add(new JLabel("Jumlah")); panel.add(jumlah);
        panel.add(tambah); panel.add(new JScrollPane(new JList<>(listModel)));
        panel.add(new JLabel("Total")); panel.add(totalLabel);
        panel.add(new JLabel("Uang Bayar")); panel.add(bayar);
        panel.add(tunai); panel.add(nonTunai);
        panel.add(new JLabel("Metode")); panel.add(metode);
        panel.add(bayarBtn);

        return panel;
    }

    private void tampilkanStruk(String info) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < listModel.size(); i++)
            sb.append(listModel.get(i)).append("\n");

        sb.append("TOTAL : Rp ").append(total).append("\n");
        sb.append(info);

        JOptionPane.showMessageDialog(this, sb.toString());
        listModel.clear();
        total = 0;
        totalLabel.setText("0");
    }

    private JPanel panelTambahProduk() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        JTextField kode = new JTextField();
        JTextField nama = new JTextField();
        JTextField harga = new JTextField();
        JTextField stok = new JTextField();

        JButton simpan = new JButton("Simpan");
        simpan.addActionListener(e -> {
            controller.tambahProduk(
                    kode.getText(),
                    nama.getText(),
                    Integer.parseInt(harga.getText()),
                    Integer.parseInt(stok.getText())
            );
            refreshTableProduk();
            JOptionPane.showMessageDialog(this, "Produk ditambahkan");
        });

        panel.add(new JLabel("Kode")); panel.add(kode);
        panel.add(new JLabel("Nama")); panel.add(nama);
        panel.add(new JLabel("Harga")); panel.add(harga);
        panel.add(new JLabel("Stok")); panel.add(stok);
        panel.add(simpan);
        return panel;
    }

    private JPanel panelLihatProduk() {
        JPanel panel = new JPanel(new BorderLayout());
        tableModel = new DefaultTableModel(new String[]{"Kode", "Nama", "Harga", "Stok"}, 0);
        tableProduk = new JTable(tableModel);
        refreshTableProduk();
        panel.add(new JScrollPane(tableProduk), BorderLayout.CENTER);
        return panel;
    }

    private void refreshTableProduk() {
        if (tableModel == null) return;
        tableModel.setRowCount(0);
        for (Object[] row : controller.getDataProduk())
            tableModel.addRow(row);
    }

    private JPanel panelHapusProduk() {
        JPanel panel = new JPanel();
        JTextField kode = new JTextField(10);
        JButton hapus = new JButton("Hapus");

        hapus.addActionListener(e -> {
            controller.hapusProduk(kode.getText());
            refreshTableProduk();
            JOptionPane.showMessageDialog(this, "Produk dihapus");
        });

        panel.add(new JLabel("Kode Produk"));
        panel.add(kode);
        panel.add(hapus);
        return panel;
    }
}
