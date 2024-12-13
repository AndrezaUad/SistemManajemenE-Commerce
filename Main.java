import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Pengguna> penggunaMap = new HashMap<>();
        List<Produk> produkList = new ArrayList<>();
        Penyimpanan penyimpanan = new Penyimpanan();
        List<Pesanan> pesananList = new ArrayList<>();

        // Menambahkan admin dan pelanggan ke sistem
        Admin admin = new Admin("A001", "Admin", "admin@ecom.com", "admin123", "Super Admin");
        Pelanggan pelanggan = new Pelanggan("P001", "Rina", "rina@gmail.com", "pass123", "Jl. Anggrek No. 5");
        penggunaMap.put(admin.email, admin);
        penggunaMap.put(pelanggan.email, pelanggan);

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
                if (pengguna instanceof Admin) {
                    handleAdminMenu((Admin) pengguna, scanner, produkList, penyimpanan, pesananList);
                } else if (pengguna instanceof Pelanggan) {
                    handlePelangganMenu((Pelanggan) pengguna, scanner, produkList, penyimpanan, pesananList);
                }
            } else {
                System.out.println("Email atau password salah.");
            }
        }
        scanner.close();
    }

    private static void handleAdminMenu(Admin admin, Scanner scanner, List<Produk> produkList, Penyimpanan penyimpanan, List<Pesanan> pesananList) {
        while (true) {
            System.out.println("\n=== Menu Admin ===");
            System.out.println("1. Tambah Produk");
            System.out.println("2. Lihat Daftar Produk");
            System.out.println("3. Lihat Pesanan");
            System.out.println("4. Logout");
            System.out.print("Pilih menu: ");
            int menu = scanner.nextInt();
            scanner.nextLine(); // Membersihkan buffer

            if (menu == 4) {
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
                    penyimpanan.tambahStok(idProduk, stok);
                    System.out.println("Produk berhasil ditambahkan.");
                }
                case 2 -> produkList.forEach(Produk::tampilkanProduk);
                case 3 -> pesananList.forEach(pesanan -> System.out.println("Pesanan ID: " + pesanan.getIdPesanan() + ", Total: " + pesanan.hitungTotal()));
                default -> System.out.println("Menu tidak valid.");
            }
        }
    }

    private static void handlePelangganMenu(Pelanggan pelanggan, Scanner scanner, List<Produk> produkList, Penyimpanan penyimpanan, List<Pesanan> pesananList) {
        while (true) {
            System.out.println("\n=== Menu Pelanggan ===");
            System.out.println("1. Lihat Produk");
            System.out.println("2. Buat Pesanan");
            System.out.println("3. Lihat Riwayat Pembelian");
            System.out.println("4. Perbarui Profil");
            System.out.println("5. Logout");
            System.out.print("Pilih menu: ");
            int menu = scanner.nextInt();
            scanner.nextLine(); // Membersihkan buffer

            if (menu == 5) {
                pelanggan.logout();
                break;
            }

            switch (menu) {
                case 1 -> produkList.forEach(Produk::tampilkanProduk);
                case 2 -> {
                    System.out.print("Masukkan ID Pesanan: ");
                    String idPesanan = scanner.nextLine();
                    Pesanan pesanan = new Pesanan(idPesanan, new Date());
                    while (true) {
                        System.out.print("Masukkan ID Produk (atau ketik 'selesai'): ");
                        String idProduk = scanner.nextLine();
                        if (idProduk.equalsIgnoreCase("selesai")) break;

                        Produk produk = produkList.stream()
                                .filter(p -> p.getIdProduk().equals(idProduk))
                                .findFirst()
                                .orElse(null);

                        if (produk != null && penyimpanan.cekKetersediaan(idProduk)) {
                            pesanan.tambahProduk(produk);
                            penyimpanan.kurangiStok(idProduk, 1);
                            System.out.println("Produk ditambahkan ke pesanan.");
                        } else {
                            System.out.println("Produk tidak tersedia.");
                        }
                    }
                    pesananList.add(pesanan);
                    System.out.println("Pesanan berhasil dibuat dengan total: " + pesanan.hitungTotal());
                }
                case 3 -> pelanggan.lihatRiwayatPembelian();
                case 4 -> {
                    System.out.print("Masukkan Nama Baru: ");
                    String namaBaru = scanner.nextLine();
                    System.out.print("Masukkan Alamat Baru: ");
                    String alamatBaru = scanner.nextLine();
                    pelanggan.perbaruiProfil(namaBaru, alamatBaru);
                }
                default -> System.out.println("Menu tidak valid.");
            }
        }
    }
}
