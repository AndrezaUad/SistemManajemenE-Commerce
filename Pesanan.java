import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pesanan {
    private String idPesanan;
    private List<Produk> daftarProduk;
    private int totalHarga;
    private Date tanggal;

    public Pesanan(String idPesanan, Date tanggal) {
        this.idPesanan = idPesanan;
        this.tanggal = tanggal;
        this.daftarProduk = new ArrayList<>();
        this.totalHarga = 0;
    }

    public void tambahProduk(Produk produk) {
        daftarProduk.add(produk);
        totalHarga += produk.getHarga();
    }

    public void hapusProduk(Produk produk) {
        if (daftarProduk.remove(produk)) {
            totalHarga -= produk.getHarga();
        }
    }

    public int hitungTotal() {
        return totalHarga;
    }

    public void batalkanPesanan() {
        daftarProduk.clear();
        totalHarga = 0;
        System.out.println("Pesanan dibatalkan.");
    }

    @Override
    public String toString() {
        return "Pesanan ID: " + idPesanan + ", Total Harga: " + totalHarga;
    }

    public String getIdPesanan() {
    }
}
