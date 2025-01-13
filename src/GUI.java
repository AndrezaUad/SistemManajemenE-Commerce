import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class GUI {
    private JFrame frame;
    private JPanel panel;
    private Map<String, Pengguna> penggunaMap = new HashMap<>();
    private List<Produk> produkList = new ArrayList<>();
    private Penyimpanan penyimpanan = new Penyimpananimpl();
    private List<Pesanan> pesananList = new ArrayList<>();
    private List<Diskon> daftarDiskon = new ArrayList<>();
    private List<Laporan> laporanList = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public GUI() {
        frame = new JFrame("Sistem E-Commerce");
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Menutup aplikasi saat jendela ditutup
        frame.setSize(400, 300); // Menentukan ukuran frame
        frame.add(panel); // Menambahkan panel utama ke frame
        frame.setVisible(true); // Menampilkan GUI

        // Setup pengguna
        Admin admin = new Admin("A001", "Admin", "admin@ecom.com", "admin123", "Super Admin");
        Pelanggan pelanggan = new Pelanggan("P001", "Rina", "rina@gmail.com", "pass123", "Jl. Anggrek No. 5");
        penggunaMap.put(admin.email, admin);
        penggunaMap.put(pelanggan.email, pelanggan);

        // Setup produk
        Produk produk1 = new Produk("PR001", "Laptop", "Elektronik", 10000000, 10);
        Produk produk2 = new Produk("PR002", "Smartphone", "Elektronik", 5000000, 15);
        produkList.add(produk1);
        produkList.add(produk2);
        penyimpanan.tambahProduk(produk1);
        penyimpanan.tambahProduk(produk2);

        // Setup diskon
        Diskon diskon1 = new Diskon("D001", "Diskon 10%", 5000000, new Date(System.currentTimeMillis() - 10000000), new Date(System.currentTimeMillis() + 10000000));
        daftarDiskon.add(diskon1);

        setupLoginPage();
    }

    private void setupLoginPage() {
        JPanel loginPanel = new JPanel();
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(20);
        JButton loginButton = new JButton("Login");

        loginPanel.add(emailLabel);
        loginPanel.add(emailField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

                Pengguna pengguna = penggunaMap.get(email);
                if (pengguna != null && pengguna.login(email, password)) {
                    JOptionPane.showMessageDialog(frame, "Login berhasil!");
                    showMainMenu(pengguna);
                } else {
                    JOptionPane.showMessageDialog(frame, "Email atau password salah.");
                }
            }
        });

        frame.getContentPane().removeAll();
        frame.add(loginPanel);
        frame.revalidate();
        frame.repaint();
    }

    private void showMainMenu(Pengguna pengguna) {
        JPanel mainMenuPanel = new JPanel();
        mainMenuPanel.setLayout(new BoxLayout(mainMenuPanel, BoxLayout.Y_AXIS));

        if (pengguna instanceof Admin) {
            showAdminMenu(mainMenuPanel);
        } else if (pengguna instanceof Pelanggan) {
            showPelangganMenu(mainMenuPanel);
        }

        frame.getContentPane().removeAll();
        frame.add(mainMenuPanel);
        frame.revalidate();
        frame.repaint();
    }

    private void showAdminMenu(JPanel panel) {
        JButton tambahProdukButton = new JButton("Tambah Produk");
        JButton lihatProdukButton = new JButton("Lihat Daftar Produk");
        JButton kelolaPenyimpananButton = new JButton("Kelola Penyimpanan");
        JButton lihatPesananButton = new JButton("Lihat Pesanan");
        JButton kelolaDiskonButton = new JButton("Kelola Diskon");
        JButton buatLaporanButton = new JButton("Buat Laporan Penjualan");
        JButton lihatLaporanButton = new JButton("Lihat Laporan Penjualan");
        JButton hapusProdukButton = new JButton("Hapus Produk");
        JButton perbaruiProdukButton = new JButton("Perbarui Produk");
        JButton logoutButton = new JButton("Logout");

        panel.add(tambahProdukButton);
        panel.add(lihatProdukButton);
        panel.add(kelolaPenyimpananButton);
        panel.add(lihatPesananButton);
        panel.add(kelolaDiskonButton);
        panel.add(buatLaporanButton);
        panel.add(lihatLaporanButton);
        panel.add(hapusProdukButton);
        panel.add(perbaruiProdukButton);
        panel.add(logoutButton);

        tambahProdukButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showTambahProdukForm();
            }
        });

        lihatProdukButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showLihatProduk();
            }
        });

        kelolaPenyimpananButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showKelolaPenyimpanan();
            }
        });

        kelolaDiskonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showKelolaDiskon();
            }
        });

        buatLaporanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showBuatLaporanPenjualan();
            }
        });

        lihatLaporanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showLihatLaporanPenjualan();
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setupLoginPage();
            }
        });
    }

    private void showPelangganMenu(JPanel panel) {
        JButton buatPesananButton = new JButton("Buat Pesanan");
        JButton lihatRiwayatButton = new JButton("Lihat Riwayat Pembelian");
        JButton perbaruiProfilButton = new JButton("Perbarui Profil");
        JButton logoutButton = new JButton("Logout");

        panel.add(buatPesananButton);
        panel.add(lihatRiwayatButton);
        panel.add(perbaruiProfilButton);
        panel.add(logoutButton);

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setupLoginPage();
            }
        });

        buatPesananButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showBuatPesananForm();
            }
        });

        lihatRiwayatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showRiwayatPembelian();
            }
        });

        perbaruiProfilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPerbaruiProfilForm();
            }
        });
    }

    private void showTambahProdukForm() {
        JPanel tambahProdukPanel = new JPanel();
        JLabel idLabel = new JLabel("ID Produk:");
        JTextField idField = new JTextField(20);
        JLabel namaLabel = new JLabel("Nama Produk:");
        JTextField namaField = new JTextField(20);
        JLabel kategoriLabel = new JLabel("Kategori Produk:");
        JTextField kategoriField = new JTextField(20);
        JLabel hargaLabel = new JLabel("Harga Produk:");
        JTextField hargaField = new JTextField(20);
        JLabel stokLabel = new JLabel("Stok Produk:");
        JTextField stokField = new JTextField(20);
        JButton submitButton = new JButton("Tambah Produk");

        tambahProdukPanel.add(idLabel);
        tambahProdukPanel.add(idField);
        tambahProdukPanel.add(namaLabel);
        tambahProdukPanel.add(namaField);
        tambahProdukPanel.add(kategoriLabel);
        tambahProdukPanel.add(kategoriField);
        tambahProdukPanel.add(hargaLabel);
        tambahProdukPanel.add(hargaField);
        tambahProdukPanel.add(stokLabel);
        tambahProdukPanel.add(stokField);
        tambahProdukPanel.add(submitButton);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String nama = namaField.getText();
                String kategori = kategoriField.getText();
                int harga = Integer.parseInt(hargaField.getText());
                int stok = Integer.parseInt(stokField.getText());

                Produk produk = new Produk(id, nama, kategori, harga, stok);
                produkList.add(produk);
                penyimpanan.tambahProduk(produk);
                JOptionPane.showMessageDialog(frame, "Produk berhasil ditambahkan.");
                showAdminMenu(panel);
            }
        });

        frame.getContentPane().removeAll();
        frame.add(tambahProdukPanel);
        frame.revalidate();
        frame.repaint();
    }

    private void showLihatProduk() {
        StringBuilder produkListStr = new StringBuilder();
        for (Produk produk : produkList) {
            produkListStr.append(produk.toString()).append("\n");
        }

        JOptionPane.showMessageDialog(frame, produkListStr.toString());
    }

    private void showKelolaPenyimpanan() {
        StringBuilder penyimpananListStr = new StringBuilder();
        for (Produk produk : produkList) {
            penyimpananListStr.append(produk.toString()).append("\n");
        }

        JOptionPane.showMessageDialog(frame, penyimpananListStr.toString());
    }

    private void showKelolaDiskon() {
        StringBuilder diskonListStr = new StringBuilder();
        for (Diskon diskon : daftarDiskon) {
            diskonListStr.append(diskon.toString()).append("\n");
        }

        JOptionPane.showMessageDialog(frame, diskonListStr.toString());
    }

      private void showBuatLaporanPenjualan() {
        // Logic untuk membuat laporan penjualan (simulasi)
        LaporanPenjualan laporan = new LaporanPenjualan("Laporan Penjualan", new Date(), 100000000);
        laporanList.add(laporan);
        JOptionPane.showMessageDialog(frame, "Laporan Penjualan telah dibuat.");
    }

    private void showLihatLaporanPenjualan() {
        StringBuilder laporanListStr = new StringBuilder();
        for (Laporan laporan : laporanList) {
            laporanListStr.append(laporan.toString()).append("\n");
        }

        JOptionPane.showMessageDialog(frame, laporanListStr.toString());
    }

    private void showBuatPesananForm() {
        JPanel buatPesananPanel = new JPanel();
        JLabel produkLabel = new JLabel("Masukkan ID Produk:");
        JTextField produkField = new JTextField(20);
        JButton tambahButton = new JButton("Tambah Produk ke Pesanan");
        JButton submitButton = new JButton("Buat Pesanan");

        buatPesananPanel.add(produkLabel);
        buatPesananPanel.add(produkField);
        buatPesananPanel.add(tambahButton);
        buatPesananPanel.add(submitButton);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pesanan pesanan;
                pesanan = new Pesanan("PES" + System.currentTimeMillis(), new Date());
                pesananList.add(pesanan);
                JOptionPane.showMessageDialog(frame, "Pesanan berhasil dibuat.");
                showPelangganMenu(panel);
            }
        });

        frame.getContentPane().removeAll();
        frame.add(buatPesananPanel);
        frame.revalidate();
        frame.repaint();
    }

    private void showRiwayatPembelian() {
        StringBuilder riwayatPembelian = new StringBuilder();
        for (Pesanan pesanan : pesananList) {
            riwayatPembelian.append(pesanan.toString()).append("\n");
        }

        JOptionPane.showMessageDialog(frame, riwayatPembelian.toString());
    }

    private void showPerbaruiProfilForm() {
        JPanel perbaruiProfilPanel = new JPanel();
        JLabel alamatLabel = new JLabel("Alamat:");
        JTextField alamatField = new JTextField(20);
        JButton submitButton = new JButton("Perbarui Profil");

        perbaruiProfilPanel.add(alamatLabel);
        perbaruiProfilPanel.add(alamatField);
        perbaruiProfilPanel.add(submitButton);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String alamat = alamatField.getText();
                // Update alamat pelanggan
                Pelanggan pelanggan = (Pelanggan) penggunaMap.get("rina@gmail.com");
                JOptionPane.showMessageDialog(frame, "Profil berhasil diperbarui.");
                showPelangganMenu(panel);
            }
        });

        frame.getContentPane().removeAll();
        frame.add(perbaruiProfilPanel);
        frame.revalidate();
        frame.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI();
            }
        });
    }
}
