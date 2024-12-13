import java.util.ArrayList;
import java.util.List;

public class Pelanggan extends Pengguna {
    private String alamat;
    private List<Pesanan> riwayatPembelian;

    public Pelanggan(String idPengguna, String nama, String email, String password, String alamat) {
        super(idPengguna, nama, email, password);
        this.alamat = alamat;
        this.riwayatPembelian = new ArrayList<>();
    }

    public void lihatRiwayatPembelian() {
        System.out.println("Riwayat Pembelian: ");
        for (Pesanan pesanan : riwayatPembelian) {
            System.out.println(pesanan);
        }
    }

    public void perbaruiProfil(String nama, String alamat) {
        this.nama = nama;
        this.alamat = alamat;
        System.out.println("Profil diperbarui.");
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("Pelanggan: " + nama + " - Alamat: " + alamat);
    }
}
