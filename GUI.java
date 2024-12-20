import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class GUI {

    private JFrame frame;
    private JPanel panelLogin, panelAdmin, panelPelanggan;
    private JTextField tfEmail, tfPassword;
    private JButton btnLogin, btnLogoutAdmin, btnLogoutPelanggan;
    private Map<String, Pengguna> penggunaMap;
    private List<Produk> produkList;
    private Penyimpanan penyimpanan;
    private List<Pesanan> pesananList;
    private List<Diskon> daftarDiskon;
    private List<Laporan> laporanList;
    private JTextArea textArea;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GUI().initialize());
    }

    public void initialize() {
        penggunaMap = new HashMap<>();
        produkList = new ArrayList<>();
        penyimpanan = new Penyimpanan();
        pesananList = new ArrayList<>();
        daftarDiskon = new ArrayList<>();
        laporanList = new ArrayList<>();

        // Menambahkan admin dan pelanggan ke sistem
        Admin admin = new Admin("A001", "Admin", "admin@ecom.com", "admin123", "Super Admin");
        Pelanggan pelanggan = new Pelanggan("P001", "Rina", "rina@gmail.com", "pass123", "Jl. Anggrek No. 5");
        penggunaMap.put(admin.email, admin);
        penggunaMap.put(pelanggan.email, pelanggan);

        // Menambahkan produk ke daftar dan penyimpanan
        Produk produk1 = new Produk("PR001", "Laptop", "Elektronik", 10000000, 10);
        Produk produk2 = new Produk("PR002", "Smartphone", "Elektronik", 5000000, 15);
        produkList.add(produk1);
        produkList.add(produk2);
        penyimpanan.tambahProduk(produk1);
        penyimpanan.tambahProduk(produk2);

        // Menambahkan diskon ke daftar
        Diskon diskon1 = new Diskon("D001", "Diskon 10%", 5000000, new Date(System.currentTimeMillis() - 10000000), new Date(System.currentTimeMillis() + 10000000));
        daftarDiskon.add(diskon1);

        // Setup frame
        frame = new JFrame("Sistem E-Commerce");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Panel login
        panelLogin = new JPanel();
        panelLogin.setLayout(new GridLayout(3, 2));
        JLabel lblEmail = new JLabel("Email:");
        tfEmail = new JTextField();
        JLabel lblPassword = new JLabel("Password:");
        tfPassword = new JPasswordField();
        btnLogin = new JButton("Login");

        panelLogin.add(lblEmail);
        panelLogin.add(tfEmail);
        panelLogin.add(lblPassword);
        panelLogin.add(tfPassword);
        panelLogin.add(new JLabel());
        panelLogin.add(btnLogin);

        frame.getContentPane().add(panelLogin, BorderLayout.CENTER);

        // Text area untuk menampilkan output
        textArea = new JTextArea(10, 40);
        textArea.setEditable(false);
        frame.getContentPane().add(new JScrollPane(textArea), BorderLayout.SOUTH);

        // Action listener untuk login
        btnLogin.addActionListener(e -> login());

        frame.setVisible(true);
    }

    private void login() {
        String email = tfEmail.getText();
        String password = tfPassword.getText();
        Pengguna pengguna = penggunaMap.get(email);

        if (pengguna != null && pengguna.login(email, password)) {
            textArea.setText("Login berhasil.\n");
            pengguna.tampilkanInfo();

            if (pengguna instanceof Admin) {
                handleAdminMenu((Admin) pengguna);
            } else if (pengguna instanceof Pelanggan) {
                handlePelangganMenu((Pelanggan) pengguna);
            }

        } else {
            textArea.setText("Email atau password salah.\n");
        }
    }

    private void handleAdminMenu(Admin admin) {
        panelLogin.setVisible(false);

        panelAdmin = new JPanel();
        panelAdmin.setLayout(new GridLayout(10, 1));

        JButton btnAddProduct = new JButton("Tambah Produk");
        JButton btnViewProducts = new JButton("Lihat Daftar Produk");
        JButton btnManageStock = new JButton("Kelola Penyimpanan");
        JButton btnViewOrders = new JButton("Lihat Pesanan");
        JButton btnManageDiscounts = new JButton("Kelola Diskon");
        JButton btnCreateReport = new JButton("Buat Laporan Penjualan");
        JButton btnViewReports = new JButton("Lihat Laporan Penjualan");
        JButton btnRemoveProduct = new JButton("Hapus Produk");
        JButton btnUpdateProduct = new JButton("Perbarui Produk");
        btnLogoutAdmin = new JButton("Logout");

        panelAdmin.add(btnAddProduct);
        panelAdmin.add(btnViewProducts);
        panelAdmin.add(btnManageStock);
        panelAdmin.add(btnViewOrders);
        panelAdmin.add(btnManageDiscounts);
        panelAdmin.add(btnCreateReport);
        panelAdmin.add(btnViewReports);
        panelAdmin.add(btnRemoveProduct);
        panelAdmin.add(btnUpdateProduct);
        panelAdmin.add(btnLogoutAdmin);

        frame.getContentPane().removeAll();
        frame.getContentPane().add(panelAdmin, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();

        // Action listener untuk logout admin
        btnLogoutAdmin.addActionListener(e -> logoutAdmin());

        // Implementasi action listener untuk admin
        btnAddProduct.addActionListener(e -> addProduct());
        btnViewProducts.addActionListener(e -> viewProducts());
        btnRemoveProduct.addActionListener(e -> removeProduct());
        btnUpdateProduct.addActionListener(e -> updateProduct());
        btnManageStock.addActionListener(e -> manageStock());
        btnManageDiscounts.addActionListener(e -> manageDiscounts());
        btnCreateReport.addActionListener(e -> createReport());
        btnViewReports.addActionListener(e -> viewReports());

    }

    private void logoutAdmin() {
        panelAdmin.setVisible(false);
        panelLogin.setVisible(true);
        frame.revalidate();
        frame.repaint();
    }

    private void handlePelangganMenu(Pelanggan pelanggan) {
        panelLogin.setVisible(false);

        panelPelanggan = new JPanel();
        panelPelanggan.setLayout(new GridLayout(4, 1));

        JButton btnCreateOrder = new JButton("Buat Pesanan");
        JButton btnViewHistory = new JButton("Lihat Riwayat Pembelian");
        JButton btnUpdateProfile = new JButton("Perbarui Profil");
        btnLogoutPelanggan = new JButton("Logout");

        panelPelanggan.add(btnCreateOrder);
        panelPelanggan.add(btnViewHistory);
        panelPelanggan.add(btnUpdateProfile);
        panelPelanggan.add(btnLogoutPelanggan);

        frame.getContentPane().removeAll();
        frame.getContentPane().add(panelPelanggan, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();

        // Action listener untuk logout pelanggan
        btnLogoutPelanggan.addActionListener(e -> logoutPelanggan());

        // Implementasi action listener untuk menu pelanggan
        btnCreateOrder.addActionListener(e -> createOrder(pelanggan));
    }

    private void logoutPelanggan() {
        panelPelanggan.setVisible(false);
        panelLogin.setVisible(true);
        frame.revalidate();
        frame.repaint();
    }

    private void addProduct() {
        String idProduk = JOptionPane.showInputDialog("Masukkan ID Produk:");
        String namaProduk = JOptionPane.showInputDialog("Masukkan Nama Produk:");
        String kategori = JOptionPane.showInputDialog("Masukkan Kategori Produk:");
        int harga = Integer.parseInt(JOptionPane.showInputDialog("Masukkan Harga Produk:"));
        int stok = Integer.parseInt(JOptionPane.showInputDialog("Masukkan Stok Produk:"));

        Produk produk = new Produk(idProduk, namaProduk, kategori, harga, stok);
        produkList.add(produk);
        penyimpanan.tambahProduk(produk);
        textArea.append("Produk berhasil ditambahkan.\n");
    }

    private void viewProducts() {
        textArea.setText("");
        for (Produk produk : produkList) {
            textArea.append(produk.toString() + "\n");
        }
    }

    private void removeProduct() {
        String idProduk = JOptionPane.showInputDialog("Masukkan ID Produk untuk dihapus:");
        Produk produkToRemove = null;
        for (Produk produk : produkList) {
            if (produk.getIdProduk().equals(idProduk)) {
                produkToRemove = produk;
                break;
            }
        }
        if (produkToRemove != null) {
            produkList.remove(produkToRemove);
            penyimpanan.hapusProduk(idProduk);
            textArea.append("Produk berhasil dihapus.\n");
        } else {
            textArea.append("Produk tidak ditemukan.\n");
        }
    }

    private void updateProduct() {
        String idProduk = JOptionPane.showInputDialog("Masukkan ID Produk untuk diperbarui:");
        Produk produkToUpdate = null;
        for (Produk produk : produkList) {
            if (produk.getIdProduk().equals(idProduk)) {
                produkToUpdate = produk;
                break;
            }
        }
        if (produkToUpdate != null) {
            String nama = JOptionPane.showInputDialog("Masukkan Nama Baru:", produkToUpdate.getNama());
            String kategori = JOptionPane.showInputDialog("Masukkan Kategori Baru:", produkToUpdate.getKategori());
            int harga = Integer.parseInt(JOptionPane.showInputDialog("Masukkan Harga Baru:", produkToUpdate.getHarga()));
            int stok = Integer.parseInt(JOptionPane.showInputDialog("Masukkan Stok Baru:", produkToUpdate.getStok()));

            produkToUpdate.setNama(nama);
            produkToUpdate.setKategori(kategori);
            produkToUpdate.setHarga(harga);
            produkToUpdate.setStok(stok);

            textArea.append("Produk berhasil diperbarui.\n");
        } else {
            textArea.append("Produk tidak ditemukan.\n");
        }
    }

    private void manageStock() {
        String idProduk = JOptionPane.showInputDialog("Masukkan ID Produk untuk menambah stok:");
        int jumlah = Integer.parseInt(JOptionPane.showInputDialog("Masukkan jumlah stok yang ingin ditambahkan:"));
        for (Produk produk : produkList) {
            if (produk.getIdProduk().equals(idProduk)) {
                penyimpanan.tambahStok(idProduk, jumlah);
                produk.setStok(produk.getStok() + jumlah);
                textArea.append("Stok produk berhasil ditambahkan. Stok baru: " + produk.getStok() + "\n");
                return;
            }
        }
        textArea.append("Produk tidak ditemukan.\n");
    }

    private void manageDiscounts() {
        String idDiskon = JOptionPane.showInputDialog("Masukkan ID Diskon:");
        String jenisDiskon = JOptionPane.showInputDialog("Masukkan Jenis Diskon:");
        int minPembelian = Integer.parseInt(JOptionPane.showInputDialog("Masukkan Minimum Pembelian untuk Diskon:"));
        long tglMulaiMillis = Long.parseLong(JOptionPane.showInputDialog("Masukkan Tanggal Mulai (in ms):"));
        long tglAkhirMillis = Long.parseLong(JOptionPane.showInputDialog("Masukkan Tanggal Akhir (in ms):"));
        Date tglMulai = new Date(tglMulaiMillis);
        Date tglAkhir = new Date(tglAkhirMillis);

        Diskon diskon = new Diskon(idDiskon, jenisDiskon, minPembelian, tglMulai, tglAkhir);
        daftarDiskon.add(diskon);
        textArea.append("Diskon berhasil ditambahkan.\n");
    }

    private void createReport() {
        String idLaporan = JOptionPane.showInputDialog("Masukkan ID Laporan:");
        String rentangTanggal = JOptionPane.showInputDialog("Masukkan Rentang Tanggal Laporan:");

        int totalBulanan = 0, totalTahunan = 0;
        for (Pesanan pesanan : pesananList) {
            totalTahunan += pesanan.hitungTotal();
            if (/* kondisi untuk pendapatan bulanan */ true) {
                totalBulanan += pesanan.hitungTotal();
            }
        }

        LaporanPenjualan laporanPenjualan = new LaporanPenjualan(idLaporan, rentangTanggal, totalBulanan, totalTahunan);
        laporanList.add(laporanPenjualan);
        laporanPenjualan.buatLaporan();
        textArea.append("Laporan penjualan berhasil dibuat.\n");
    }

    private void viewReports() {
        textArea.setText("");
        for (Laporan laporan : laporanList) {
            laporan.tampilkanLaporan();
        }
    }

    private void createOrder(Pelanggan pelanggan) {
        Pesanan pesanan = new Pesanan("PES001", new Date());
        while (true) {
            String idProduk = JOptionPane.showInputDialog("Masukkan ID Produk untuk ditambahkan (atau 'exit' untuk selesai):");
            if (idProduk.equalsIgnoreCase("exit")) break;

            Optional<Produk> produkOpt = produkList.stream().filter(p -> p.getIdProduk().equals(idProduk)).findFirst();
            if (produkOpt.isPresent()) {
                Produk produk = produkOpt.get();
                if (penyimpanan.cekKetersediaan(idProduk)) {
                    pesanan.tambahProduk(produk);
                    penyimpanan.kurangiStok(idProduk, 1);
                    textArea.append("Produk ditambahkan ke pesanan.\n");
                } else {
                    textArea.append("Produk tidak tersedia.\n");
                }
            } else {
                textArea.append("Produk tidak ditemukan.\n");
            }
        }
        pesananList.add(pesanan);
    }
}
