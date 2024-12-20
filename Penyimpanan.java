import java.util.*;

public class Penyimpanan {
    private Map<String, Produk> produkMap = new HashMap<>();

    public void tambahProduk(Produk produk) {
        produkMap.put(produk.getIdProduk(), produk);
    }

    public boolean cekKetersediaan(String idProduk) {
        Produk produk = produkMap.get(idProduk);
        return produk != null && produk.getStok() > 0;
    }

    public void kurangiStok(String idProduk, int jumlah) {
        Produk produk = produkMap.get(idProduk);
        if (produk != null) {
            produk.setStok(produk.getStok() - jumlah);
        }
    }

    public void tambahStok(String idProduk, int jumlah) {
        Produk produk = produkMap.get(idProduk);
        if (produk != null) {
            produk.setStok(produk.getStok() + jumlah);
        }
    }

    public void hapusProduk(String idProduk) {
        produkMap.remove(idProduk);
    }

    public void perbaruiProduk(String idProduk, String nama, String kategori, int harga, int stok) {
        Produk produk = produkMap.get(idProduk);
        if (produk != null) {
            produk.setStok(stok);
        }
    }
}
