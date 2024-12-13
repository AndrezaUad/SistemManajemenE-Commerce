public class Admin extends Pengguna {
    private String peranAdmin;

    public Admin(String idPengguna, String nama, String email, String password, String peranAdmin) {
        super(idPengguna, nama, email, password);
        this.peranAdmin = peranAdmin;
    }

    public void kelolaProduk() {
        System.out.println("Admin mengelola produk.");
    }

    public void kelolaPesanan() {
        System.out.println("Admin mengelola pesanan.");
    }

    public void kelolaLaporan() {
        System.out.println("Admin mengelola laporan.");
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("Admin: " + nama + " - Peran: " + peranAdmin);
    }
}
