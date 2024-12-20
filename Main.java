import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Pengguna> penggunaMap = new HashMap<>();
        List<Produk> produkList = new ArrayList<>();
        Penyimpanan penyimpanan = new Penyimpanan();
        List<Pesanan> pesananList = new ArrayList<>();
        List<Diskon> daftarDiskon = new ArrayList<>();
        List<Laporan> laporanList = new ArrayList<>();

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

        System.out.println("=== Sistem E-Commerce ===");

        while (true) {
            System.out.println("\n1. Login");
            System.out.println("2. Keluar");
            System.out.print("Pilih menu: ");
            int menu = scanner.nextInt();
            scanner.nextLine(); // Membersihkan buffer

            if (menu == 2) {
                System.out.println("Keluar dari sistem.");
                break;
            }

            // Proses Login
            System.out.print("Masukkan email: ");
            String email = scanner.nextLine();
            System.out.print("Masukkan password: ");
            String password = scanner.nextLine();

            Pengguna pengguna = penggunaMap.get(email);
            if (pengguna != null && pengguna.login(email, password)) {
                System.out.println("Login berhasil.");
                pengguna.tampilkanInfo();
                if (pengguna instanceof Admin) {
                    handleAdminMenu((Admin) pengguna, scanner, produkList, penyimpanan, pesananList, daftarDiskon, laporanList);
                } else if (pengguna instanceof Pelanggan) {
                    handlePelangganMenu((Pelanggan) pengguna, scanner, produkList, penyimpanan, pesananList, daftarDiskon);
                }
            } else {
                System.out.println("Email atau password salah.");
            }
        }
        scanner.close();
    }

    private static void handleAdminMenu(Admin admin, Scanner scanner, List<Produk> produkList, Penyimpanan penyimpanan, List<Pesanan> pesananList, List<Diskon> daftarDiskon, List<Laporan> laporanList) {
        while (true) {
            System.out.println("\n=== Menu Admin ===");
            System.out.println("1. Tambah Produk");
            System.out.println("2. Lihat Daftar Produk");
            System.out.println("3. Kelola Penyimpanan");
            System.out.println("4. Lihat Pesanan");
            System.out.println("5. Kelola Diskon");
            System.out.println("6. Buat Laporan Penjualan");
            System.out.println("7. Lihat Laporan Penjualan");
            System.out.println("8. Hapus Produk");
            System.out.println("9. Perbarui Produk");
            System.out.println("10. Logout");
            System.out.print("Pilih menu: ");
            int menu = scanner.nextInt();
            scanner.nextLine(); // Membersihkan buffer

            if (menu == 10) {
                admin.logout();
                break;
            }

            switch (menu) {
                case 1 -> {
                    System.out.print("Masukkan ID Produk: ");
                    String idProduk = scanner.nextLine();
                    System.out.print("Masukkan Nama Produk: ");
                    String namaProduk = scanner.nextLine();
                    System.out.print("Masukkan Kategori Produk: ");
                    String kategori = scanner.nextLine();
                    System.out.print("Masukkan Harga Produk: ");
                    int harga = scanner.nextInt();
                    System.out.print("Masukkan Stok Produk: ");
                    int stok = scanner.nextInt();
                    scanner.nextLine(); // Membersihkan buffer

                    Produk produk = new Produk(idProduk, namaProduk, kategori, harga, stok);
                    produkList.add(produk);
                    penyimpanan.tambahProduk(produk); // Menambahkan produk ke penyimpanan
                    System.out.println("Produk berhasil ditambahkan.");
                }
                case 2 -> produkList.forEach(Produk::tampilkanProduk);
                case 3 -> {
                    // Kelola penyimpanan - tambah stok produk
                    System.out.print("Masukkan ID Produk untuk menambah stok: ");
                    String idProduk = scanner.nextLine();
                    System.out.print("Masukkan jumlah stok yang ingin ditambahkan: ");
                    int jumlah = scanner.nextInt();
                    scanner.nextLine(); // Membersihkan buffer

                    // Cari produk berdasarkan ID di produkList
                    Optional<Produk> produkOpt = produkList.stream().filter(p -> p.getIdProduk().equals(idProduk)).findFirst();
                    if (produkOpt.isPresent()) {
                        Produk produk = produkOpt.get();
                        // Tambahkan stok produk di Penyimpanan dan pada produk
                        penyimpanan.tambahStok(idProduk, jumlah);
                        produk.setStok(produk.getStok() + jumlah); // Memperbarui stok produk
                        System.out.println("Stok produk " + produk.getNama() + " berhasil ditambahkan. Stok baru: " + produk.getStok());
                    } else {
                        System.out.println("Produk dengan ID " + idProduk + " tidak ditemukan.");
                    }
                }
                case 4 -> pesananList.forEach(pesanan -> System.out.println("Pesanan ID: " + pesanan.getIdPesanan() + ", Total: " + pesanan.hitungTotal()));
                case 5 -> {
                    // Mengelola diskon
                    System.out.println("=== Kelola Diskon ===");
                    System.out.print("Masukkan ID Diskon: ");
                    String idDiskon = scanner.nextLine();
                    System.out.print("Masukkan Jenis Diskon: ");
                    String jenisDiskon = scanner.nextLine();
                    System.out.print("Masukkan Minimum Pembelian untuk Diskon: ");
                    int minPembelian = scanner.nextInt();
                    System.out.print("Masukkan Tanggal Mulai (in ms): ");
                    long tglMulaiMillis = scanner.nextLong();
                    System.out.print("Masukkan Tanggal Akhir (in ms): ");
                    long tglAkhirMillis = scanner.nextLong();
                    scanner.nextLine(); // Membersihkan buffer
                    Date tglMulai = new Date(tglMulaiMillis);
                    Date tglAkhir = new Date(tglAkhirMillis);

                    Diskon diskon = new Diskon(idDiskon, jenisDiskon, minPembelian, tglMulai, tglAkhir);
                    daftarDiskon.add(diskon);
                    System.out.println("Diskon berhasil ditambahkan.");
                }
                case 6 -> {
                    // Membuat laporan penjualan
                    System.out.println("=== Laporan Penjualan ===");
                    System.out.print("Masukkan ID Laporan: ");
                    String idLaporan = scanner.nextLine();
                    System.out.print("Masukkan Rentang Tanggal Laporan: ");
                    String rentangTanggal = scanner.nextLine();

                    // Menghitung total pendapatan bulanan dan tahunan
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
                }
                case 7 -> {
                    System.out.println("\n=== Daftar Laporan Penjualan ===");
                    if (laporanList.isEmpty()) {
                        System.out.println("Tidak ada laporan.");
                    } else {
                        for (Laporan laporan : laporanList) {
                            laporan.tampilkanLaporan();
                        }
                    }
                }
                case 8 -> {
                    // Menghapus produk
                    System.out.print("Masukkan ID Produk yang ingin dihapus: ");
                    String idProduk = scanner.nextLine();
                    penyimpanan.hapusProduk(idProduk);  // Menghapus produk dari penyimpanan
                }
                case 9 -> {
                    // Memperbarui produk
                    System.out.print("Masukkan ID Produk yang ingin diperbarui: ");
                    String idProduk = scanner.nextLine();
                    System.out.print("Masukkan Nama Baru: ");
                    String nama = scanner.nextLine();
                    System.out.print("Masukkan Kategori Baru: ");
                    String kategori = scanner.nextLine();
                    System.out.print("Masukkan Harga Baru: ");
                    int harga = scanner.nextInt();
                    System.out.print("Masukkan Stok Baru: ");
                    int stok = scanner.nextInt();
                    scanner.nextLine(); // Membersihkan buffer

                    penyimpanan.perbaruiProduk(idProduk, nama, kategori, harga, stok);
                }
                default -> System.out.println("Menu tidak valid.");
            }
        }
    }

    private static void handlePelangganMenu(Pelanggan pelanggan, Scanner scanner, List<Produk> produkList, Penyimpanan penyimpanan, List<Pesanan> pesananList, List<Diskon> daftarDiskon) {
        while (true) {
            System.out.println("\n=== Menu Pelanggan ===");
            System.out.println("1. Buat Pesanan");
            System.out.println("2. Lihat Riwayat Pembelian");
            System.out.println("3. Perbarui Profil");
            System.out.println("4. Logout");
            System.out.print("Pilih menu: ");
            int menu = scanner.nextInt();
            scanner.nextLine(); // Membersihkan buffer

            if (menu == 4) {
                pelanggan.logout();
                break;
            }

            switch (menu) {
                case 1 -> {
                    // Membuat pesanan
                    Pesanan pesanan = new Pesanan("PES" + System.currentTimeMillis(), new Date());
                    while (true) {
                        System.out.print("Masukkan ID Produk untuk ditambahkan (atau 'exit' untuk selesai): ");
                        String idProduk = scanner.nextLine();
                        if (idProduk.equalsIgnoreCase("exit")) break;
                        Optional<Produk> produkOpt = produkList.stream().filter(p -> p.getIdProduk().equals(idProduk)).findFirst();
                        if (produkOpt.isPresent()) {
                            Produk produk = produkOpt.get();
                            if (penyimpanan.cekKetersediaan(idProduk)) {
                                pesanan.tambahProduk(produk);
                                penyimpanan.kurangiStok(idProduk, 1); // Mengurangi stok setelah pesanan
                                System.out.println("Produk ditambahkan ke pesanan.");
                            } else {
                                System.out.println("Produk tidak tersedia.");
                            }
                        } else {
                            System.out.println("Produk tidak ditemukan.");
                        }
                    }
                    pesananList.add(pesanan);
                    simpanRiwayatPembelian(pelanggan, pesanan); // Simpan riwayat ke file
                }
                case 2 -> bacaRiwayatPembelian(pelanggan); // Membaca riwayat pembelian dari file
                default -> System.out.println("Menu tidak valid.");
            }
        }
    }

    private static void simpanRiwayatPembelian(Pelanggan pelanggan, Pesanan pesanan) {
        String fileName = pelanggan.getIdPengguna() + "_riwayat.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write("Pesanan ID: " + pesanan.getIdPesanan() + "\n");
            writer.write("Tanggal: " + pesanan.getTanggal() + "\n");
            writer.write("Total: " + pesanan.hitungTotal() + "\n");
            writer.write("--------------------------\n");
            System.out.println("Riwayat pembelian berhasil disimpan ke file.");
        } catch (IOException e) {
            System.out.println("Gagal menyimpan riwayat pembelian: " + e.getMessage());
        }
    }

    private static void bacaRiwayatPembelian(Pelanggan pelanggan) {
        String fileName = pelanggan.getIdPengguna() + "_riwayat.txt";
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("Belum ada riwayat pembelian.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            System.out.println("\n=== Riwayat Pembelian ===");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Gagal membaca riwayat pembelian: " + e.getMessage());
        }
    }
}
