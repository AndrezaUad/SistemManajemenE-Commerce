import java.util.*;

public class Pesanan {
    private String idPesanan;
    private Date tanggal;
    private List<Produk> produkList = new ArrayList<>();

    public Pesanan(String idPesanan, Date tanggal) {
        this.idPesanan = idPesanan;
        this.tanggal = tanggal;
    }

    public String getIdPesanan() {
        return idPesanan;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void tambahProduk(Produk produk) {
        produkList.add(produk);
    }

    public int hitungTotal() {
        return produkList.stream().mapToInt(Produk::getHarga).sum();
    }
}
